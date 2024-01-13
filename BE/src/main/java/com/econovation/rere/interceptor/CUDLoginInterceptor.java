package com.econovation.rere.interceptor;

import com.econovation.rere.exception.NotAthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class CUDLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false); // false는 현재 세션이 존재하지 않을 때 세션을 생성하지 않도록 지시함
        String httpMethod = request.getMethod();

//          카드북,목차,카드 생성/수정/삭제 요청에 대해서만 로그인 체크
        if (httpMethod.equals(HttpMethod.DELETE.toString()) || httpMethod.equals(HttpMethod.POST.toString()) || httpMethod.equals(HttpMethod.PUT.toString())) {
            // 로그인 하지 않았을 때
            if (session == null || session.getAttribute("USER") == null) {
                throw new NotAthenticationException("로그인이 필요합니다.");
            }
            // 로그인한 사용자에 대해서는 요청 처리 계속
            return true;
        }
        return true;
    }
}