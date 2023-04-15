package com.kingpiggy.study.service.test;

import com.kingpiggy.study.component.HikariLogPrinter;
import com.kingpiggy.study.service.MovieService;
import com.kingpiggy.study.web.dto.response.MovieResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class TransactionService {

    private final HikariLogPrinter hikariLogPrinter;
    private final SimpleDataService simpleDataService;
    private final MovieService movieService;

    @Transactional
    public void testLog() {
        hikariLogPrinter.printHikariLog();
    }

    @Transactional(readOnly = true)
    public List<MovieResponse> readOnlyTransaction(boolean isAdult) {
        return movieService.findAllMovieEntityByAdult(isAdult);
    }

    @Transactional(rollbackFor = Exception.class)
    public void cascadeRollback() {
        log.info("[TransactionService] cascadeRollback");
        this.save("저장", "정상 작동");
        this.saveWithError("오류 유발하며 저장", "RuntimeException");
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(String title, String description) {
        simpleDataService.saveSimpleAutoInc(title, description);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveWithError(String title, String description) {
        simpleDataService.saveSimpleAutoIncWithError(title, description);
    }
}
