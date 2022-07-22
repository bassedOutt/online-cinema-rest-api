package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.dto.MovieDto;
import com.epam.spring.homework3.dto.mapper.EntityMapper;
import com.epam.spring.homework3.exception.EntityNotFoundException;
import com.epam.spring.homework3.model.Movie;
import com.epam.spring.homework3.service.repository.MovieRepository;
import com.epam.spring.homework3.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private MovieRepository repository;
    private static final EntityMapper mapper = EntityMapper.INSTANCE;

    @Autowired
    public void setRepository(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MovieDto> findAll() {
        log.info("getting all movies");
        List<Movie> movies = repository.findAll();
        log.debug(movies.toString());
        return movies.stream()
                .map(mapper::movieToMovieDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto findById(Long id) {
        log.info("Searching for movie with id: {}", id);
        Movie movieDto = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return map(movieDto);
    }

    @Override
    @PreAuthorize(value = "hasRole('ADMIN')")
    public MovieDto insert(MovieDto entity) {
        log.info("Inserting movie :{}", entity);
        Movie movie = repository.save(map(entity));
        return map(movie);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @Override
    public MovieDto update(MovieDto entity) {
        log.info("updating movie: {}", entity);

        if(repository.findMovieByEnTitle(entity.getEnTitle()).isEmpty())
            throw new EntityNotFoundException("Movie is not present in the database");

        Movie movie = repository.save(map(entity));
        return map(movie);
    }

    @Override
    @PreAuthorize(value = "hasRole('ADMIN')")
    public MovieDto deleteById(Long id) {
        log.info("deleting movie with an id: {}", id);
        Movie movie = repository.deleteMovieById(id).orElseThrow(EntityNotFoundException::new);
        return map(movie);
    }

    @Override
    public MovieDto findByTitle(String title) {
        log.info("Searching for movie with title:{}", title);

        Optional<Movie> movieUa = repository.findMovieByUaTitle(title);
        Optional<Movie> movieEn = repository.findMovieByEnTitle(title);

        if (movieEn.isPresent()) {
            return map(movieEn.get());
        } else if (movieUa.isPresent()) {
            return map((movieUa.get()));
        }
        throw new EntityNotFoundException("Movie not found");
    }

    private Movie map(MovieDto movieDto) {
        log.info("Mapping [MovieDto] to [Movie]");
        return mapper.fromMovieDto(movieDto);
    }

    private MovieDto map(Movie movie) {
        log.info("Mapping [Movie] to [MovieDto]");
        return mapper.movieToMovieDto(movie);
    }

}
