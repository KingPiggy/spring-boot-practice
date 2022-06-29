package com.kingpiggy.study.web.controller;

import com.kingpiggy.study.service.AuthService;
import com.kingpiggy.study.util.JwtTokenProvider;
import com.kingpiggy.study.web.ApiResponse;
import com.kingpiggy.study.web.dto.TokenResponse;
import com.kingpiggy.study.web.dto.UserLoginRequest;
import com.kingpiggy.study.web.dto.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;

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
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/auth/login")
    public ApiResponse login(@RequestBody @Valid UserLoginRequest request, HttpServletResponse httpServletResponse) {

        ApiResponse result = authService.login(request);

        TokenResponse tokenResponse = (TokenResponse) result.getData();
        jwtTokenProvider.createCookie(httpServletResponse, tokenResponse.getAccessToken());

        return result;
    }

    @PostMapping("/api/auth/sign-up")
    public ApiResponse signUp(@RequestBody @Valid UserSignUpRequest request) {
        return authService.signUp(request);
    }

    @PostMapping("/api/auth/check-email")
    public ApiResponse checkEmail(@RequestBody HashMap<String, Object> requestMap) {
        String email = requestMap.get("email").toString();
        return authService.checkDuplicatedEmail(email);
    }

    @PostMapping("/api/auth/grant/admin-role")
    public ApiResponse giveAdminRole() {
        return authService.grantAdminRole(1L);
    }

}
