package com.kingpiggy.study;

import com.kingpiggy.study.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * MovieCommandLineRunner class.
 * 애플리케이션 구동 후 실행되는 CommandLineRunner
 *
 */
@RequiredArgsConstructor
@Component
public class MovieCommandLineRunner implements CommandLineRunner {

    private final MovieService movieService;

    @Override
    public void run(String... args) throws Exception {
        movieService.createSampleMovies();
    }

}
