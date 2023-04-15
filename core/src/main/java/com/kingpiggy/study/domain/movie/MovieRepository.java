package com.kingpiggy.study.domain.movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository <MovieEntity, Long> {

    @Query(
        "SELECT  m " +
        "FROM    MovieEntity AS m " +
        "WHERE   m.releaseDate BETWEEN :startedAt AND :endedAt " +
        "ORDER BY m.releaseDate, m.title "
    )
    List<MovieEntity> findAllByPeriod(@Param("startedAt") LocalDate startedAt, @Param("endedAt") LocalDate endedAt);

    Optional<MovieEntity> findTopByOrderByIdDesc();

    List<MovieEntity> findAllByAdult(Boolean adult);

}
