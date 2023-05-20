package com.kingpiggy.study.domain.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimpleIdRepository extends JpaRepository<SimpleIdEntity, Long> {

    List<SimpleIdEntity> findAllByOrderById();

}
