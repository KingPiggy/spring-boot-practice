package com.kingpiggy.study.web.controller.test;

import com.kingpiggy.study.service.test.BulkCalculateService;
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
    public void bulkInsertForSimpleId() {
        bulkCalculateService.bulkInsertForSimpleId();
    }

    @PostMapping("/insert/simple-id/v2")
    public void bulkInsertForSimpleId2() {
        bulkCalculateService.bulkInsertForSimpleId2();
    }

    @PostMapping("/insert/auto-increment")
    public void bulkInsertForAutoIncrement() {
        bulkCalculateService.bulkInsertForAutoIncrement();
    }

}
