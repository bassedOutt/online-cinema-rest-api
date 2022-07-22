package com.epam.spring.homework3.service;

import com.epam.spring.homework3.dto.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> findAll();

    MovieDto findById(Long id);

    MovieDto insert(MovieDto entity);

    MovieDto update(MovieDto entity);

    MovieDto deleteById(Long id);

    MovieDto findByTitle(String title);

}
