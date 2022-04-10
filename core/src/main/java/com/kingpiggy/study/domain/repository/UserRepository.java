package com.kingpiggy.study.domain.repository;

import com.kingpiggy.study.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
