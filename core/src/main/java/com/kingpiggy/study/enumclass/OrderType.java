package com.kingpiggy.study.enumclass;

import lombok.AllArgsConstructor;

/**
 * OrderType class.
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
@AllArgsConstructor
public enum OrderType {

    BUY("구매"),
    CANCEL("취소"),
    REFUND("환불"),
    ;

    private String name;

}
