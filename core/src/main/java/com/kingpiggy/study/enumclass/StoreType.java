package com.kingpiggy.study.enumclass;

import lombok.AllArgsConstructor;

/**
 * StoreType class.
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
public enum StoreType {

    SMART_STORE("네이버 스마트스토어"),
    COUPANG("쿠팡"),
    ALI_EXPRESS("알리 익스프레스")
    ;

    private String name;

}
