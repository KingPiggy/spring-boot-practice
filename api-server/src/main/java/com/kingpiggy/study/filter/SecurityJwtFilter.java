package com.kingpiggy.study.filter;

import com.kingpiggy.study.domain.user.CustomUserDetails;
import com.kingpiggy.study.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kingpiggy.study.ApiConstants.FILTER_EXCLUDED_URI_LIST;
import static com.kingpiggy.study.ApiConstants.PAYLOAD_USER_ID;

@RequiredArgsConstructor
@Component
public class SecurityJwtFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = jwtTokenProvider.resolveCookie(request);

        if(token != null) {
            try {
                if(jwtTokenProvider.validationToken(token)) {
                    if(SecurityContextHolder.getContext().getAuthentication() == null) {
                        Authentication authentication = jwtTokenProvider.getAuthentication(token);

                        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                        System.out.println("My user details");
                        System.out.println(userDetails);

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }

                    // else : validationToken에서 Exception 발생하여 catch로 잡힘
                }
            } catch (Exception e) {
                System.out.println(e);
                Long userId = Long.parseLong(jwtTokenProvider.getClaims(token).get(PAYLOAD_USER_ID).toString());
                System.out.println(userId);
                // get refresh token
                // refresh 만료 여부 체크
                // 만료 X : refresh으로 accessToken 갱신 가능하면 갱신
                // 만료 : 둘다 재발급
            }
        }


        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return FILTER_EXCLUDED_URI_LIST.contains(request.getRequestURI());
    }

}
