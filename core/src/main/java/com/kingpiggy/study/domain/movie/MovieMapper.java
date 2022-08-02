package com.kingpiggy.study.domain.movie;

import org.apache.ibatis.annotations.Mapper;

/**
 * MovieMapper class.
 * <PRE>
 * Describe here.
 * </PRE>
 *
 * <PRE>
 * <B>History:</B>
 * damian.lee, 0.0.1, Created at 2022.08.01
 * </PRE>
 *
 * @author : SEUNGHOON
 * @version : 0.0.1
 */

@Mapper
public interface MovieMapper {

    int countMovie();

}
