package com.kingpiggy.study.domain.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("[성공] QueryDSL 적용 테스트")
    void test_QUserClass() throws Exception {
        // given
        User user = User.builder()
                .email("kingpiggy@naver.com")
                .password("1234")
                .password("1234")
                .name("kingpiggy")
                .build();
        User user2 = User.builder()
                .email("damian@naver.com")
                .password("1234")
                .password("1234")
                .name("damian")
                .build();
        em.persist(user);
        em.persist(user2);

        // when
        QUser qUser = QUser.user;
        QUser qUser2 = new QUser("u"); // 별칭 지정

        // then
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        User result = jpaQueryFactory.selectFrom(qUser)
                .where(qUser.name.eq("kingpiggy"))
                .fetchOne();

        User result2 = jpaQueryFactory.selectFrom(qUser2)
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
