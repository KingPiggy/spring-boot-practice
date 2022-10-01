package com.kingpiggy.study.service;

import com.kingpiggy.study.domain.movie.Movie;
import com.kingpiggy.study.domain.movie.MovieMapper;
import com.kingpiggy.study.domain.movie.MovieRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kingpiggy.study.web.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kingpiggy.study.ApiApplicationConstants.SAMPLE_MOVIE_SIZE;

@Slf4j
@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;

    @Transactional(readOnly = true)
    public ApiResponse countMovie() {
        return ApiResponse.OK(movieMapper.countMovie());
    }

    @Transactional
    public void createSampleMovies() {
        Optional<Movie> movie = movieRepository.findTopByOrderByIdDesc();
        if (movie.isPresent()) {
            return;
        }

        List<Movie> sampleMovies = new ArrayList<>();

        for (int i = 0; i < SAMPLE_MOVIE_SIZE; i++) {
            sampleMovies.add(createMovieEntity(i + 1));
        }

        movieRepository.saveAll(sampleMovies);
    }

    public Movie createMovieEntity(int i) {
        return Movie.builder()
                .title("Sample Movie_" + i)
                .adult(false)
                .overview("Overview_" + i)
                .releaseDate(LocalDate.now())
                .build();
    }

    public Page<Movie> getMoviesPaging(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

}
