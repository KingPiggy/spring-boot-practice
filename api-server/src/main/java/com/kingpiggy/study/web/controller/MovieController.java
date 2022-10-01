package com.kingpiggy.study.web.controller;

import com.kingpiggy.study.service.MovieService;
import com.kingpiggy.study.web.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * MovieController class.
 * <PRE>
 * Describe here.
 * </PRE>
 *
 * <PRE>
 * <B>History:</B>
 * damian.lee, 0.0.1, Created at 2022.08.02
 * </PRE>
 *
 * @author : SEUNGHOON
 * @version : 0.0.1
 */

@RequiredArgsConstructor
@RestController
public class MovieController {

    private final MovieService movieService;

    @GetMapping("api/movies/count")
    public ApiResponse countMovie() {
        return movieService.countMovie();
    }

    @GetMapping("api/movies")
    public ApiResponse getMoviesPaging(@RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return ApiResponse.OK(movieService.getMoviesPaging(pageable));
    }

}
