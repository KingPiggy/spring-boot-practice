package com.kingpiggy.study.domain.orders;

import com.kingpiggy.study.BaseLocalTest;
import com.kingpiggy.study.enumclass.ErrorCode;
import com.kingpiggy.study.enumclass.OrderType;
import com.kingpiggy.study.enumclass.StoreType;
import com.kingpiggy.study.web.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest extends BaseLocalTest {

    @Autowired
    private OrderRepository orderRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    @DisplayName("[성공] 주문 엔티티 저장")
    void test_Save() throws Exception {
        // given
        Long sampleId = 1L;

        OrderEntity orderEntity = makeOrderEntity(
                sampleId,
                StoreType.SMART_STORE,
                "꿀꿀상점",
                LocalDate.now(),
                55000L,
                5000L,
                3000L,
                53000L,
                OrderType.BUY
        );

        // when
        orderRepository.save(orderEntity);
        em.flush();
        em.clear();

        // then
        OrderEntity result = orderRepository.findById(sampleId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));

        assertAll(
                () -> assertEquals(sampleId, result.getOrderId())
        );

        System.out.println(result);

    }

    public OrderEntity makeOrderEntity(Long orderId, StoreType storeType, String storeName, LocalDate orderDate, Long productAmount, Long discountAmount, Long deliveryAmount, Long orderAmount, OrderType orderType) {
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