package com.kingpiggy.study.web.controller;

import com.kingpiggy.study.enumclass.StoreType;
import com.kingpiggy.study.service.OrderService;
import com.kingpiggy.study.web.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderController class.
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
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/sync")
    public ApiResponse syncOrderOfStoreType(@RequestParam StoreType storeType) {
        orderService.syncOrderOfStoreType(storeType);

        return ApiResponse.OK("URL Mapping Success");
    }

}
