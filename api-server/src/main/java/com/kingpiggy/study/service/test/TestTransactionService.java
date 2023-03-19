package com.kingpiggy.study.service.test;

import com.kingpiggy.study.domain.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TestTransactionService class.
 * <PRE>
 * Describe here.
 * </PRE>
 *
 * <PRE>
 * <B>History:</B>
 * SeungHoon Lee, 0.1.0, Created at 2023.03.19
 * </PRE>
 *
 * @author : SEUNGHOON
 * @version : 0.1.0
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class TestTransactionService {

    @Transactional(readOnly = true)
    public void cascadeRollback() {
        log.info("[TestTransactionService] cascadeRollback");
    }

}
