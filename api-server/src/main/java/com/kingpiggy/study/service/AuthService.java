package com.kingpiggy.study.service;

import com.kingpiggy.study.domain.user.UserEntity;
import com.kingpiggy.study.domain.user.UserRepository;
import com.kingpiggy.study.enumclass.ErrorCode;
import com.kingpiggy.study.util.JwtTokenProvider;
import com.kingpiggy.study.web.ApiResponse;
import com.kingpiggy.study.web.BusinessException;
import com.kingpiggy.study.web.dto.response.TokenResponse;
import com.kingpiggy.study.web.dto.request.UserLoginRequest;
import com.kingpiggy.study.web.dto.request.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

/**
 * AuthService class
 * service for auth service
 *
 * @author SEUNGHOON
 * @version 0.0.1, 2022-06-05
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(rollbackFor = Exception.class)
    public ApiResponse login(UserLoginRequest request) {
        UserEntity userEntity = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException(ErrorCode.EMAIL_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            throw new BusinessException(ErrorCode.PASSWORD_NOT_EQUAL);
        }

        TokenResponse tokenResponse = jwtTokenProvider.createTokenDto(userEntity.getId(), userEntity.getRoles());

        userEntity.setRefreshToken(tokenResponse.getRefreshToken());

        return ApiResponse.OK(tokenResponse);
    }

    @Transactional(rollbackFor = Exception.class)
    public ApiResponse signUp(UserSignUpRequest request) {
        Optional<UserEntity> user = userRepository.findByEmail(request.getEmail());

        if (user.isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATED_EMAIL);
        }

        UserEntity savedUserEntity = userRepository.save(UserEntity.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Collections.singletonList("ROLE_USER"))
                .build());

        return ApiResponse.OK("Saved user ID : " + savedUserEntity.getId());
    }

    @Transactional(readOnly = true)
    public ApiResponse checkDuplicatedEmail(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATED_EMAIL);
        }

        return ApiResponse.OK("Not duplicated");
    }

    @Transactional
    public ApiResponse grantAdminRole(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EMAIL_NOT_FOUND));

        userEntity.getRoles().add("ROLE_ADMIN");

        return ApiResponse.OK(userEntity.getRoles());
    }

}
