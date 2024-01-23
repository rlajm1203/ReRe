package com.econovation.rere.interceptor;

import com.econovation.rere.domain.entity.User;
import com.econovation.rere.exception.NotAthenticationException;
import com.econovation.rere.service.CardBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class ReadLoginInterceptor implements HandlerInterceptor {

    @Autowired
    CardBookService cardBookService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("실행되나?");
        String httpMethod = request.getMethod();
        String uri = request.getRequestURI();

//        정규 표현식으로 /cardbook/{cardbookId}/themes 에서 {cardbookId} 값을 추출
//        1번 인덱스 : cardbook
//        2번 인덱스 : 1
//        3번 인덱스 : theme
//        4번 인덱스 : 1
//        5번 인덱스 : cards
        Integer cardbookId = Integer.parseInt(uri.split("\\/")[2]);


//        기본 제공 카드북이 아닐 경우, 로그인한 사용자인지 확인
        if(!cardBookService.getCardbook(cardbookId).getWriter().equals("관리자")){
            HttpSession session = request.getSession(false);
            if(session==null || session.getAttribute("USER")==null) throw new NotAthenticationException("로그인이 필요합니다.");
//            로그인한 사용자에 대해서는 계속 수행
            return true;
        }

//        기본 제공 카드북일 경우, 다음 과정 수행
        return true;

    }
}