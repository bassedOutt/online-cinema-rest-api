package com.epam.spring.homework3;

import com.epam.spring.homework3.dto.MovieDto;
import com.epam.spring.homework3.dto.SessionDto;
import com.epam.spring.homework3.dto.UserDto;
import com.epam.spring.homework3.dto.mapper.EntityMapper;
import com.epam.spring.homework3.model.Movie;
import com.epam.spring.homework3.model.Role;
import com.epam.spring.homework3.model.Session;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

public class TestUtil {

    private static final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

    public static Movie createMovie(){
        Movie movie = new Movie();
        movie.setDuration(156);
        movie.setEnDescription("The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.");
        movie.setEnTitle("Pulp Fiction");
        movie.setImageUrl("https://upload.wikimedia.org/wikipedia/uk/0/06/Pulp_Fiction_%28Soundtrack%29.png");
        movie.setPrice(135);
        movie.setUaDescription("Життя двох вбивць, боксера, гангстера та його дружини, а також пари бандитів із закусочної переплітаються в чотирьох казках про насильство та спокуту.");
        movie.setUaTitle("Кримінальне чтиво");
        return movie;
    }

    public static MovieDto createMovieDto(){
        return mapper.movieToMovieDto(createMovie());
    }

    public static Session createSession(){
        Session session = new Session();
        session.setStartTime(LocalDateTime.now());
        return session;
    }


    public static SessionDto createSessionDto(){
        return mapper.sessionToSessionDto(createSession());
    }

    public static UserDto createUserDto(){
        return UserDto.builder()
                .name("George")
                .surname("Washington")
                .email("washington@gov.us")
                .password("123456")
                .roles(List.of(roleUser()))
                .build();
    }

    public static UserDto createAdminDto(){
        return UserDto.builder()
                .name("Donald")
                .surname("Trump")
                .email("trump@gov.us")
                .password("123456")
                .roles(List.of(roleUser(),roleAdmin()))
                .build();
    }

    public static Role roleUser(){
        return new Role(1L,"USER");
    }

    public static Role roleAdmin(){
        return new Role(2L,"ADMIN");
    }


}
