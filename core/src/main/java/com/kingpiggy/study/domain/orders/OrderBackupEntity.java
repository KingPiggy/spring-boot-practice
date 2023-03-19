package com.kingpiggy.study.domain.orders;

import com.kingpiggy.study.enumclass.OrderType;
import com.kingpiggy.study.enumclass.StoreType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * OrderBackupEntity class.
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
@Entity
public class OrderBackupEntity {

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
    private LocalDateTime originCreatedDate;

    @Column
    private LocalDateTime originModifiedDate;

    @Column
    private LocalDateTime originDeletedTime;

    @Column
    private LocalDateTime createdDate;

}
