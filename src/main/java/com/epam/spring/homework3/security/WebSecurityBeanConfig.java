package com.epam.spring.homework3.security;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSecurityBeanConfig {

    @Bean
    public Algorithm algorithm(){
        return  Algorithm.HMAC256("secret".getBytes());
    }
}
