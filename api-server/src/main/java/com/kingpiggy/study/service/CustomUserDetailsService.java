package com.kingpiggy.study.service;

import com.kingpiggy.study.domain.user.CustomUserDetails;
import com.kingpiggy.study.domain.user.UserEntity;
import com.kingpiggy.study.domain.user.UserRepository;
import com.kingpiggy.study.enumclass.ErrorCode;
import com.kingpiggy.study.web.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userIdStr) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findById(Long.parseLong(userIdStr))
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));

        CustomUserDetails userDetails = CustomUserDetails.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .authorities(userEntity.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()))
                .build();

        return userDetails;
    }
}
