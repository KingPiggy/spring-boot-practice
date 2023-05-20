package com.kingpiggy.study.service.test;

import com.kingpiggy.study.aop.timer.Timer;
import com.kingpiggy.study.common.Constants;
import com.kingpiggy.study.web.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * BulkCalculateService class.
 * <PRE>
 * Describe here.
 * </PRE>
 *
 * <PRE>
 * <B>History:</B>
 * SeungHoon Lee, 0.1.0, Created at 2023.04.05
 * </PRE>
 *
 * @author : SEUNGHOON
 * @version : 0.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BulkCalculateService {

    private final SimpleDataService simpleDataService;

    @Timer
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse bulkInsertForSimpleId() {
        simpleDataService.bulkInsertForIdEntity(Constants.TEST_CASE_COUNT_2000);

        return ApiResponse.OK("Done");
    }

    @Timer
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse bulkInsertForSimpleId2() {
        simpleDataService.bulkInsertForIdEntity2(Constants.TEST_CASE_COUNT_2000, 100);

        return ApiResponse.OK("Done");
    }

    @Timer
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse bulkInsertForAutoIncrement() {
        simpleDataService.bulkInsertForAutoIncIdEntity(Constants.TEST_CASE_COUNT_2000);

        return ApiResponse.OK("Done");
    }

    @Timer
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse bulkUpdateForSimpleId() {
        simpleDataService.bulkUpdateForSimpleId(Constants.TEST_CASE_COUNT_2000);

        return ApiResponse.OK("Done");
    }

}
