package com.kingpiggy.study.web.controller;

import com.kingpiggy.study.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController class
 * controller for auth API
 *
 * @author SEUNGHOON
 * @version 0.0.1, 2022-06-05
 *
 */
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public void signIn() {
        
    }

    @PostMapping
    public void signUp() {

    }

}
