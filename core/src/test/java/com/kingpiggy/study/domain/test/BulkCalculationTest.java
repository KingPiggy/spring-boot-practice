package com.kingpiggy.study.domain.test;

import com.kingpiggy.study.BaseLocalTest;
import com.kingpiggy.study.common.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * BulkCalculationTest class.
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

public class BulkCalculationTest extends BaseLocalTest {

    private int testCaseCount = 100;

    @Autowired
    private SimpleIdRepository simpleIdRepository;

    @Autowired
    private AutoIncIdRepository autoIncIdRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    @DisplayName("[성공] ID만 있는 엔티티에 벌크 인서트")
    void test_BulkInsertForSimpleId() throws Exception {
        // given
        List<SimpleIdEntity> testData = new ArrayList<>();
        for (int i = 0; i < Constants.TEST_CASE_COUNT_2000; i++) {
            testData.add(makeSimpleIdEntityForTest(i + 1));
        }

        // when
        simpleIdRepository.saveAll(testData);

        em.flush();
        em.clear();

        // then
        long count = simpleIdRepository.count();
        System.out.println(count);
    }

    @Test
    @DisplayName("[성공] Auto Increment 적용된 ID가 있는 엔티티에 벌크 인서트")
    void test_BulkInsertForSimpleIdWithAuotoIncrement() throws Exception {
        // given
        List<AutoIncIdEntity> testData = new ArrayList<>();
        for (int i = 0; i < Constants.TEST_CASE_COUNT_2000; i++) {
            testData.add(makeSimpleAutoIncIdEntity(i + 1));
        }

        // when
        autoIncIdRepository.saveAll(testData);

        em.flush();
        em.clear();

        // then
        long count = autoIncIdRepository.count();
        System.out.println(count);

    }

    private SimpleIdEntity makeSimpleIdEntityForTest(int i) {
        return SimpleIdEntity.builder()
                .id(Long.valueOf(i))
                .title("simple title - " + i)
                .description("simple desc - " + i)
                .build();
    }

    private AutoIncIdEntity makeSimpleAutoIncIdEntity(int i) {
        return AutoIncIdEntity.builder()
                .title("simple title - " + i)
                .description("simple desc - " + i)
                .build();
    }

}
