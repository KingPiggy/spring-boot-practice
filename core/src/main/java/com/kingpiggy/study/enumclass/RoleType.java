package com.kingpiggy.study.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {

    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER");

    private final String key;

}
