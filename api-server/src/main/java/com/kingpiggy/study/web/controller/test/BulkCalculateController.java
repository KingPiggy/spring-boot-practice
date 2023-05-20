package com.kingpiggy.study.web.controller.test;

import com.kingpiggy.study.service.test.BulkCalculateService;
import com.kingpiggy.study.web.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BulkCalculateController class.
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
@RestController
@RequestMapping("/test/bulk")
@RequiredArgsConstructor
public class BulkCalculateController {

    private final BulkCalculateService bulkCalculateService;

    @PostMapping("/insert/simple-id")
    public ApiResponse bulkInsertForSimpleId() {
        bulkCalculateService.bulkInsertForSimpleId();
        return ApiResponse.OK("Done");
    }

    @PostMapping("/insert/simple-id/v2")
    public ApiResponse bulkInsertForSimpleId2() {
        bulkCalculateService.bulkInsertForSimpleId2();
        return ApiResponse.OK("Done");
    }

    @PostMapping("/insert/auto-increment")
    public ApiResponse bulkInsertForAutoIncrement() {
        bulkCalculateService.bulkInsertForAutoIncrement();
        return ApiResponse.OK("Done");
    }

    @PostMapping("/update/simple-id")
    public ApiResponse bulkUpdateForSimpleId() {
        bulkCalculateService.bulkUpdateForSimpleId();
        return ApiResponse.OK("Done");
    }

}
