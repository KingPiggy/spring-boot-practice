package com.kingpiggy.study.enumclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    /**
     * Common
     */
    ENTITY_NOT_FOUND("C001", " Entity Not Found"),

    INVALID_INPUT_VALUE("C002", " Invalid Input Value"),
    METHOD_NOT_ALLOWED("C003", " Invalid Input Value"),

    INTERNAL_SERVER_ERROR("C004", "Internal Server Error"),
    INVALID_TYPE_VALUE("C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED("C006", "Access is Denied"),
    AUTHORIZATION_LEVEL_ISSUE("C007", "Without Permission"),
    EXPIRED_TOKEN_ERROR("C008", "Token Expired"),
    NEW_TOKEN_REQUIRED("C009", "Need to login again"),
    ALREADY_VERIFIED_TOKEN("C010", "Already used token"),
    ALREADY_VERIFIED_CODE("C011", "Already used code"),
    EXPIRED_CODE_ERROR("C012", "Expired code"),
    CODE_ALREADY_EXIST("C013", "Code already exists"),
    ALREADY_CODE_REGISTERED("C014", "User can register code only one time"),
    ALREADY_CODE_CREATE("C015", "General User can create code only one time"),
    DISABLED_USE_OWN_CODE("C016", "Disabled to use own code"),
    PRE_CONDITION_FAILED("C017", "Pre condition fail"),
    PERMISSION_DENIED("C018", "Permission denied"),

    /* Auth */
    EMAIL_NOT_FOUND("A001", "Email not found"),
    PASSWORD_NOT_EQUAL("A002", "Password is not correct");

    /* Define other errors here */

    private final String code;
    private final String message;

    ErrorCode(final String code, final String message) {
        this.message = message;
        this.code = code;
    }

}
