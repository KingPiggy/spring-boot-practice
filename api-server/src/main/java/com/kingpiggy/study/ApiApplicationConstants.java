package com.kingpiggy.study;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface ApiApplicationConstants {

    /* JWT Constants */
    Long ACCESS_TOKEN_EXPIRE_MILLI_SECOND = 60 * 60 * 1000L; // 1 hour
    Long REFRESH_TOKEN_EXPIRE_MILLI_SECOND = 7 * 24 * 60 * 60 * 1000L; // 7 day
    String BEARER_STR = "Bearer";
    String PAYLOAD_ROLES = "roles";
    String PAYLOAD_USER_ID = "userId";
    String ACCESS_TOKEN_COOKIE_NAME = "accessToken";

    /* Sample Movie */
    int SAMPLE_MOVIE_SIZE = 10;

    List<String> FILTER_EXCLUDED_URI_LIST = Collections.singletonList("/api/auth/sign-up");

}
