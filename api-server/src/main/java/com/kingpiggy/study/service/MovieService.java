package com.kingpiggy.study.service;

import com.kingpiggy.study.aop.timer.Timer;
import com.kingpiggy.study.domain.movie.MovieEntity;
import com.kingpiggy.study.domain.movie.MovieMapper;
import com.kingpiggy.study.domain.movie.MovieRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.kingpiggy.study.web.dto.response.MovieResponse;
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
    public int countMovie() {
        return movieMapper.countMovie();
    }

    @Timer
    @Transactional
    public void createSampleMovies() {
        Optional<MovieEntity> movie = movieRepository.findTopByOrderByIdDesc();
        if (movie.isPresent()) {
            return;
        }

        List<MovieEntity> sampleMovieEntities = new ArrayList<>();

        for (int i = 0; i < SAMPLE_MOVIE_SIZE; i++) {
            sampleMovieEntities.add(createMovieEntity(i + 1));
        }

        movieRepository.saveAll(sampleMovieEntities);
    }

    private MovieEntity createMovieEntity(int i) {
        return MovieEntity.builder()
                .title("Sample Movie_" + i)
                .adult(i % 2 == 0)
                .overview("Overview_" + i)
                .releaseDate(LocalDate.now())
                .build();
    }

    @Transactional(readOnly = true)
    public Page<MovieEntity> getMoviesPaging(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<MovieResponse> findAllMovieEntityByAdult(boolean isAdultMovie) {
        return movieRepository.findAllByAdult(isAdultMovie)
                .stream()
                .map(m -> MovieResponse.builder()
                        .title(m.getTitle())
                        .overview(m.getOverview())
                        .adult(m.getAdult())
                        .releaseDate(m.getReleaseDate())
                        .build())
                .collect(Collectors.toList());
    }

}
