package com.econovation.rere.interceptor;

import com.econovation.rere.exception.NotAthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false); // false는 현재 세션이 존재하지 않을 때 세션을 생성하지 않도록 지시함

        // 로그인 하지 않았을 떄
        if (session == null || session.getAttribute("USER") == null) {
            throw new NotAthenticationException("접근 권한이 없습니다.");
        }
        // 로그인한 사용자에 대해서는 요청 처리 계속
        return true;
    }
}
