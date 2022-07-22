package com.epam.spring.homework3.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.epam.spring.homework3.dto.UserDto;
import com.epam.spring.homework3.dto.mapper.EntityMapper;
import com.epam.spring.homework3.exception.RoleNotFoundException;
import com.epam.spring.homework3.exception.UserNotFoundException;
import com.epam.spring.homework3.model.Role;
import com.epam.spring.homework3.model.User;
import com.epam.spring.homework3.service.UserService;
import com.epam.spring.homework3.service.repository.RoleRepository;
import com.epam.spring.homework3.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.epam.spring.homework3.constants.Constants.ROLE_CLAIM;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final EntityMapper mapper = EntityMapper.INSTANCE;
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final Algorithm algorithm;

    @PreAuthorize(value = "hasRole('ADMIN')")
    public List<UserDto> findAll() {
        log.info("fetching all users");
        return repository.findAll().stream().map(mapper::toUserDto).collect(Collectors.toList());
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @Override
    public UserDto findById(Long id) {
        log.info("fetching user with id {} ", id);
        return mapper.toUserDto(repository.getById(id));
    }

    @Override
    @PreAuthorize(value = "hasRole('ADMIN')")
    public UserDto addRoleToUser(String email, String roleName) {
        log.info("adding role {} to user {}", roleName, email);
        User user = repository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        Role role = roleRepository.findByName(roleName).orElseThrow(RoleNotFoundException::new);
        user.addRole(role);
        repository.save(user);
        return mapper.toUserDto(user);
    }

    @PreAuthorize(value = "hasRole('USER')")
    @Override
    public UserDto insert(UserDto entity) {
        log.info("saving user {} to database", entity.getEmail());
        User user = mapper.fromUserDto(entity);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return addRoleToUser(user.getEmail(),"ROLE_USER");
    }

    @PreAuthorize(value = "hasRole('USER')")
    @Override
    public UserDto update(UserDto entity) {
        log.info("updating user: {}", entity.getEmail());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if(!currentPrincipalName.equals(entity.getEmail())){
            throw new AccessDeniedException("Access denied");
        }
        User user = mapper.fromUserDto(entity);
        repository.save(user);
        return entity;
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @Override
    public void delete(Long id) {
        log.info("deleting user with an id: {}", id);
        repository.deleteById(id);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @Override
    public UserDto findByEmail(String email) {
        log.info("Searching for user with email: {}", email);
        UserDto userDto = mapper.toUserDto(repository.findByEmail(email).orElseThrow(UserNotFoundException::new));
        log.info("User found: {}", userDto);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repository.findByEmail(s).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with email %s not found", s)));

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public Map<String, String> refreshAccessToken(String refreshToken, String requestUrl) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String username = decodedJWT.getSubject();
        UserDto user = this.findByEmail(username);

        String accessToken = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(requestUrl)
                .withClaim(ROLE_CLAIM, user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toList()))
                .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        return tokens;
    }

}
