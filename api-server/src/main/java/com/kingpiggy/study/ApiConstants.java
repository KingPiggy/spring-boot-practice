package com.kingpiggy.study;

import java.util.Collections;
import java.util.List;

public class ApiConstants {

    /* JWT Constants */
    public static final Long ACCESS_TOKEN_EXPIRE_MILLI_SECOND = 60 * 60 * 1000L; // 1 hour
    public static final Long REFRESH_TOKEN_EXPIRE_MILLI_SECOND = 7 * 24 * 60 * 60 * 1000L; // 7 day
    public static final String BEARER_STR = "Bearer";
    public static final String PAYLOAD_ROLES = "roles";
    public static final String PAYLOAD_USER_ID = "userId";
    public static final String ACCESS_TOKEN_COOKIE_NAME = "accessToken";

    /* Sample Movie */
    public static final int SAMPLE_MOVIE_SIZE = 35;

    public static final List<String> FILTER_EXCLUDED_URI_LIST = Collections.singletonList("/api/auth/sign-up");

}
