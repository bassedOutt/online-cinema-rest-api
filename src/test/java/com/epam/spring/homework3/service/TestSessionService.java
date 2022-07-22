package com.epam.spring.homework3.service;

import com.epam.spring.homework3.TestUtil;
import com.epam.spring.homework3.dto.MovieDto;
import com.epam.spring.homework3.dto.SessionDto;
import com.epam.spring.homework3.exception.SessionTimeConflictException;
import com.epam.spring.homework3.model.Session;
import com.epam.spring.homework3.service.impl.SessionServiceImpl;
import com.epam.spring.homework3.service.repository.SessionRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;
import java.util.List;

import static com.epam.spring.homework3.service.SessionService.SESSION_SORTERS.BY_NAME;
import static com.epam.spring.homework3.service.SessionService.SESSION_SORTERS.BY_TIME;
import static java.time.Month.MAY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TestSessionService {

    @MockBean
    private SessionRepository repository;

    @MockBean
    private SessionServiceImpl service;


    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldThrowSessionTimeConflictException() {

        MovieDto movieDto = TestUtil.createMovieDto();

        List<SessionDto> sessions = List.of(
                SessionDto.builder()
                        .startTime(LocalDateTime.of(2022, MAY, 1, 10, 0))
                        .movie(movieDto)
                        .build(),
                SessionDto.builder()
                        .startTime(LocalDateTime.of(2022, MAY, 1, 12, 0))
                        .movie(movieDto)
                        .build()
        );

        SessionDto conflictingSession = SessionDto.builder()
                .startTime(LocalDateTime.of(2022, MAY, 1, 9, 0))
                .movie(movieDto)
                .build();

        when(service.findAll()).thenReturn(sessions);
        when(service.insert(any(SessionDto.class))).thenCallRealMethod();

        SessionTimeConflictException exception = assertThrows(SessionTimeConflictException.class,
                ()->service.insert(conflictingSession));

        String expectedMessage = "Chosen time conflicts with other sessions";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

}
