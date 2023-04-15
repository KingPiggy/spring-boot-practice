package com.kingpiggy.study.domain.movie;

import java.time.LocalDate;
import javax.persistence.*;

import com.kingpiggy.study.domain.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie")
@Entity
public class MovieEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Boolean adult;

    private String overview;

    private LocalDate releaseDate;

}
