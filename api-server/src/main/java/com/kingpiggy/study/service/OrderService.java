package com.kingpiggy.study.service;

import com.kingpiggy.study.aop.timer.Timer;
import com.kingpiggy.study.component.DummyGenerator;
import com.kingpiggy.study.domain.orders.OrderEntity;
import com.kingpiggy.study.enumclass.OrderType;
import com.kingpiggy.study.enumclass.StoreType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * OrderService class.
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
public class OrderService {

    private final OrderBackupService orderBackupService;
    private final DummyGenerator dummyGenerator;

    @Transactional(rollbackFor = Exception.class)
    public void syncOrderOfStoreType(StoreType storeType) {
        // 1. 기존 데이터 백업
        orderBackupService.backupForStoreType(storeType);

        // 2. 동기화 시킬 데이터 받아오기
        dummyGenerator.makeOrderSyncDummy(storeType); // 데이터를 받아오는 것을 대체하여 더미 데이터 생성

        // 3. 동기화
        this.updateFromSyncData(storeType);
    }

    @Timer
    private void updateFromSyncData(StoreType storeType) {

    }

    private OrderEntity makeOrderEntity(Long orderId, StoreType storeType, String storeName, LocalDate orderDate, Long productAmount, Long discountAmount, Long deliveryAmount, Long orderAmount, OrderType orderType) {
        return OrderEntity.builder()
                .orderId(orderId)
                .storeType(storeType)
                .storeName(storeName)
                .orderDate(orderDate)
                .productAmount(productAmount)
                .discountAmount(discountAmount)
                .deliveryAmount(deliveryAmount)
                .orderAmount(orderAmount)
                .orderType(orderType)
                .build();
    }

}
