package com.kingpiggy.study.service;

import com.kingpiggy.study.aop.timer.Timer;
import com.kingpiggy.study.domain.orders.OrderBackupRepository;
import com.kingpiggy.study.domain.orders.OrderRepository;
import com.kingpiggy.study.enumclass.StoreType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * OrderBackupService class.
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
public class OrderBackupService {

    private final OrderRepository orderRepository;
    private final OrderBackupRepository orderBackupRepository;

    @Timer
    @Transactional(rollbackFor = Exception.class)
    public void backupForStoreType(StoreType storeType) {
        
    }

}
