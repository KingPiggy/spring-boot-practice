package com.kingpiggy.study.web.controller.test;

import com.kingpiggy.study.service.test.TransactionService;
import com.kingpiggy.study.web.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TransactionController class.
 * <PRE>
 * Describe here.
 * </PRE>
 *
 * <PRE>
 * <B>History:</B>
 * SeungHoon Lee, 0.1.0, Created at 2023.03.15
 * </PRE>
 *
 * @author : SEUNGHOON
 * @version : 0.1.0
 */
@RequiredArgsConstructor
@RequestMapping("/test/transaction")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/readOnly")
    public ApiResponse readOnly(@RequestParam boolean isAdult) {
        return ApiResponse.OK(transactionService.readOnlyTransaction(isAdult));
    }

    @GetMapping("/log")
    public void printLog() {
        transactionService.testLog();
    }

    @GetMapping("/cascadeRollback")
    public void cascadeRollback() {
        transactionService.cascadeRollback();
    }

}
