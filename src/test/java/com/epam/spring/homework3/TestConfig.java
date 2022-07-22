package com.epam.spring.homework3;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@TestConfiguration
public class TestConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        User basicUser = new User("basic_user@gmail.com", "1234", List.of(
                new SimpleGrantedAuthority("ROLE_USER")
        ));

        User admin = new User("admin_user@gmail.com", "1234", List.of(
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN")
        ));

        return new InMemoryUserDetailsManager(List.of(basicUser, admin));
    }

}