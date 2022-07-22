package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.TestConfig;
import com.epam.spring.homework3.dto.UserDto;
import com.epam.spring.homework3.service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.spring.homework3.TestUtil.createAdminDto;
import static com.epam.spring.homework3.TestUtil.createUserDto;
import static com.epam.spring.homework3.constants.Constants.API_URL;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = TestConfig.class)
@AutoConfigureMockMvc(addFilters = false)
class TestUserController {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createUserTest() throws Exception {
        UserDto userDto = createUserDto();
        when(userService.insert(Mockito.any(UserDto.class))).thenReturn(userDto);

        Gson gson = new Gson();
        String json = gson.toJson(userDto);

        mockMvc.perform(post(API_URL + "/user")
                        .contentType(APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(userDto.getEmail()));
    }

    @Test
    void whenUpdatingUserThenStatusOk() throws Exception {

        UserDto userDto = createUserDto();
        Gson gson = new Gson();
        String json = gson.toJson(userDto);

        mockMvc.perform(put(API_URL + "/user")
                        .with(user(userDto.getEmail()).password("1234").roles("USER"))
                        .contentType(APPLICATION_JSON).content(json)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findAllUsersTest() throws Exception {
        when(userService.findAll()).thenReturn(List.of(createUserDto(), createAdminDto()));

        mockMvc.perform(get(API_URL + "/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    void whenAuthorizationHeaderIsNotPresentThenStatusBadRequest() throws Exception {

        mockMvc.perform(get(API_URL + "/user/token/refresh"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    void whenAuthorizationHeaderIsPresentThenStatusOk() throws Exception {

        Map<String, String> tokens = new HashMap<>();

        tokens.put("access_token", "dfgdfgdf");
        tokens.put("refresh_token", "dgfhfgjghh");

        Gson gson = new Gson();

        when(userService.refreshAccessToken(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(tokens);

        mockMvc.perform(get(API_URL + "/user/token/refresh").header(AUTHORIZATION, "Bearer fghfghgfh"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(gson.toJson(tokens)));

    }
}
