package com.kingpiggy.study.domain.repository;

import com.kingpiggy.study.domain.entity.Movie;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository <Movie, Long> {

    @Query(
        "SELECT  m " +
        "FROM    Movie AS m " +
        "WHERE   m.releaseDate BETWEEN :startedAt AND :endedAt " +
        "ORDER BY m.releaseDate, m.title "
    )
    List<Movie> findAllByPeriod(@Param("startedAt") LocalDate startedAt, @Param("endedAt") LocalDate endedAt);

}
