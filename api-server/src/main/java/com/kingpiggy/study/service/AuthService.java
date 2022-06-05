package com.kingpiggy.study.service;

import com.kingpiggy.study.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AuthService class
 * service for auth service
 *
 * @author SEUNGHOON
 * @version 0.0.1, 2022-06-05
 *
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public void signIn() {

    }

    @Transactional(rollbackFor = Exception.class)
    public void signUp() {

    }

}
