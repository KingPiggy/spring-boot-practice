package com.kingpiggy.study.util;

import com.kingpiggy.study.enumclass.ErrorCode;
import com.kingpiggy.study.service.CustomUserDetailsService;
import com.kingpiggy.study.web.BusinessException;
import com.kingpiggy.study.web.dto.response.TokenResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;

import static com.kingpiggy.study.ApiConstants.*;


@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secretKey}")
    private String secretKey;

    @Value("${jwt.token.issuer}")
    private String issuer;

    private final CustomUserDetailsService userDetailsService;

    private Key key;

    @PostConstruct
    protected void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /**
     * Create token response for login
     *
     * @param userId // User ID
     * @param roles  // User Roles
     * @return
     */
    public TokenResponse createTokenDto(Long userId, List<String> roles) {
        String accessToken = issueAccessToken(userId, roles);
        String refreshToken = issueRefreshToken(userId, roles);

        return TokenResponse.builder()
                .grantType(BEARER_STR)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpireDate(ACCESS_TOKEN_EXPIRE_MILLI_SECOND)
                .build();
    }

    public String issueAccessToken(Long userId, List<String> roles) {
        //Header
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //Payloads
        Map<String, Object> payloads = new HashMap<>();
        payloads.put(PAYLOAD_USER_ID, userId);
        payloads.put(PAYLOAD_ROLES, roles);

        // Token create date
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParams(headers)
                .setIssuedAt(now)
                .setIssuer(issuer)
                .setClaims(payloads)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_MILLI_SECOND))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String issueRefreshToken(Long userId, List<String> roles) {
        //Header
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //Payloads
        Map<String, Object> payloads = new HashMap<>();
        payloads.put(PAYLOAD_USER_ID, userId);
        payloads.put(PAYLOAD_ROLES, roles);

        // Token create date
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParams(headers)
                .setIssuedAt(now)
                .setIssuer(issuer)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_MILLI_SECOND))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Get authentication by token
     *
     * @param token // JWT Token
     * @return Authentication
     */
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        if (claims.get(PAYLOAD_ROLES) == null) {
            throw new BusinessException(ErrorCode.NO_ROLES);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.get(PAYLOAD_USER_ID).toString());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Get claims of token
     *
     * @param token // JWT Token
     * @return Claims
     */
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * Save token to cookie
     *
     * @param response
     * @param token
     */
    public void createCookie(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie.from(ACCESS_TOKEN_COOKIE_NAME, token)
                .path("/")
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }

    /**
     * Expire access token
     *
     * @param response
     */
    public void deleteCookie(HttpServletResponse response) {
        ResponseCookie cookie;

        cookie = ResponseCookie.from(ACCESS_TOKEN_COOKIE_NAME, "")
                .maxAge(0) // Make it expired
                .path("/")
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }

    /**
     * Get access token from cookie
     *
     * @param request
     * @return
     */
    public String resolveCookie(HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(ACCESS_TOKEN_COOKIE_NAME)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public boolean validationToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

}
