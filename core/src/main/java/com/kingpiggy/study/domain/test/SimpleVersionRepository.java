package com.kingpiggy.study.domain.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

/**
 * SimpleVersionRepository class.
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
public interface SimpleVersionRepository extends JpaRepository<SimpleVersionEntity, String> {

    Optional<SimpleVersionEntity> findByTitle(String title);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select s from SimpleVersionEntity s where s.title = :title")
    Optional<SimpleVersionEntity> findByTitleWithOptimistic(@Param("title") String title);

}
