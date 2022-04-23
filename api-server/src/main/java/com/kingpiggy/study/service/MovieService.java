package com.kingpiggy.study.service;

import com.kingpiggy.study.domain.entity.Movie;
import com.kingpiggy.study.domain.repository.MovieRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public void createSampleMovies() {
        log.info("[MovieService:createSampleMovies] create sample movie data");

        List<Movie> sampleMovies = new ArrayList<>();
        sampleMovies.add(Movie.builder()
            .title("귀멸의 칼날 극장판 무한열차편")
            .adult(false)
            .overview("귀멸의 칼날 극장판 무한열차편. 한국어 더빙!")
            .releaseDate(LocalDate.parse("2020-10-16"))
            .build());

        sampleMovies.add(Movie.builder()
            .title("스파이더맨: 노 웨이 홈")
            .adult(false)
            .overview("스파이더맨 재미있다!")
            .releaseDate(LocalDate.parse("2021-12-15"))
            .build());

        sampleMovies.add(Movie.builder()
            .title("수퍼 소닉 2")
            .adult(false)
            .overview("파란 고슴도치! 빨강이와 맞서서 노랑이와 함께")
            .releaseDate(LocalDate.parse("2022-03-30"))
            .build());

        sampleMovies.add(Movie.builder()
            .title("수퍼 소닉")
            .adult(false)
            .overview("파란 고슴도치 매우 빠르다!")
            .releaseDate(LocalDate.parse("2020-02-12"))
            .build());

        sampleMovies.add(Movie.builder()
            .title("신비한 동물들과 덤블도어의 비밀")
            .adult(false)
            .overview("덤블도어의 비밀은 뭘까")
            .releaseDate(LocalDate.parse("2022-04-07"))
            .build());

        sampleMovies.add(Movie.builder()
            .title("이터널스")
            .adult(false)
            .overview("지구를 지키는 외계인들")
            .releaseDate(LocalDate.parse("2021-11-03"))
            .build());

        sampleMovies.add(Movie.builder()
            .title("킹스맨: 퍼스트 에이전트")
            .adult(false)
            .overview("최초의 킹스맨들! 재미있다!")
            .releaseDate(LocalDate.parse("2021-12-22"))
            .build());

        movieRepository.saveAll(sampleMovies);
        log.info("[MovieService:createSampleMovies] created done sample movie data [{}]", sampleMovies.size());
    }

}
