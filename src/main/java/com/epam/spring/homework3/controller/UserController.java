package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.dto.UserDto;
import com.epam.spring.homework3.exception.TokenNotValidException;
import com.epam.spring.homework3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(OK)
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public UserDto createUser(@RequestBody @Valid UserDto user) {
        return userService.insert(user);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@RequestBody @Valid UserDto user) {
        return userService.update(user);
    }

    @PostMapping("/add_role_to_user")
    public UserDto addRoleToUser(@RequestBody String email, String role) {
        return userService.addRoleToUser(email, role);
    }

    @GetMapping("/token/refresh")
    @ResponseStatus(OK)
    public Map<String, String> refreshToken(HttpServletRequest request) {

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String refreshToken = authorizationHeader.substring("Bearer ".length());
            String requestUrl = request.getRequestURL().toString();
            return userService.refreshAccessToken(refreshToken, requestUrl);
        } else {
            throw new TokenNotValidException("Refresh token is not present or not valid");
        }
    }
}
