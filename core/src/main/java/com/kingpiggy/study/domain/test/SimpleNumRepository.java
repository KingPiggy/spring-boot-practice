package com.kingpiggy.study.domain.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

/**
 * SimpleNumRepository class.
 * <PRE>
 * Describe here.
 * </PRE>
 *
 * <PRE>
 * <B>History:</B>
 * SeungHoon Lee, 0.1.0, Created at 2023.04.22
 * </PRE>
 *
 * @author : SEUNGHOON
 * @version : 0.1.0
 */
@Repository
public interface SimpleNumRepository extends JpaRepository<SimpleNumEntity, String> {

    Optional<SimpleNumEntity> findByTitle(String title);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from SimpleNumEntity s where s.title = :title")
    Optional<SimpleNumEntity> findByTitleWithPessimistic(@Param("title") String title);

}
