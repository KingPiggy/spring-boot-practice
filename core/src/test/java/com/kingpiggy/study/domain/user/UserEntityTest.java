package com.kingpiggy.study.domain.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserEntityTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("[성공] QueryDSL 적용 테스트")
    void test_QUserClass() throws Exception {
        // given
        UserEntity userEntity = UserEntity.builder()
                .email("kingpiggy@naver.com")
                .password("1234")
                .password("1234")
                .name("kingpiggy")
                .build();
        UserEntity userEntity2 = UserEntity.builder()
                .email("damian@naver.com")
                .password("1234")
                .password("1234")
                .name("damian")
                .build();
        em.persist(userEntity);
        em.persist(userEntity2);

        // when
        QUser qUser = QUser.user;
        QUser qUser2 = new QUser("u"); // 별칭 지정

        // then
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        UserEntity result = jpaQueryFactory.selectFrom(qUser)
                .where(qUser.name.eq("kingpiggy"))
                .fetchOne();

        UserEntity result2 = jpaQueryFactory.selectFrom(qUser2)
                .where(qUser2.name.eq("damian"))
                .fetchOne();

        assertAll(
                () -> assertEquals("kingpiggy", result.getName()),
                () -> assertEquals("damian", result2.getName())
        );
        System.out.println(result);
        System.out.println(result2);
    }

}
