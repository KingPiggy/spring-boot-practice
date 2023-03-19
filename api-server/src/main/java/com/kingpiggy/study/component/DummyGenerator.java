package com.kingpiggy.study.component;

import com.kingpiggy.study.aop.timer.Timer;
import com.kingpiggy.study.domain.orders.OrderSyncRepository;
import com.kingpiggy.study.enumclass.StoreType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * DummyGenerator class.
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
@Component
@RequiredArgsConstructor
public class DummyGenerator {

    private final OrderSyncRepository orderSyncRepository;

    @Timer
    public void makeOrderSyncDummy(StoreType storeType) {
        // Delete -> Insert
    }

}
