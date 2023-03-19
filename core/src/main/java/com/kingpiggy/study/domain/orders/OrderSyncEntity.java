package com.kingpiggy.study.domain.orders;

import com.kingpiggy.study.enumclass.OrderType;
import com.kingpiggy.study.enumclass.StoreType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * OrderSyncEntity class.
 * <PRE>
 * 동기화 시킬 데이터
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
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderSyncEntity {

    @Id
    private Long orderId;

    @Column
    @Enumerated(EnumType.STRING)
    private StoreType storeType;

    @Column
    private String storeName;

    @Column
    private LocalDate orderDate;

    @Column
    private Long productAmount;

    @Column
    private Long discountAmount;

    @Column
    private Long deliveryAmount;

    @Column
    private Long orderAmount;

    @Column
    private OrderType orderType;

    @Column
    private LocalDateTime createdDate;

}
