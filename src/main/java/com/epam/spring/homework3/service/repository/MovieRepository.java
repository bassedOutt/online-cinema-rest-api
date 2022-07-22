package com.epam.spring.homework3.service.repository;

import com.epam.spring.homework3.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    Optional<Movie> findMovieByEnTitle(String title);

    Optional<Movie> findMovieByUaTitle(String title);

    Optional<Movie> deleteMovieById(Long id);

}
