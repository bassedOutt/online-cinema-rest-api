package com.epam.spring.homework3.service;

import com.epam.spring.homework3.dto.UserDto;

import java.util.Map;

public interface UserService extends CrudService<UserDto> {
    UserDto findByEmail(String email);

    UserDto findById(Long id);

    UserDto addRoleToUser(String email, String rolename);

    Map<String, String> refreshAccessToken(String refreshToken, String requestUrl);

}
