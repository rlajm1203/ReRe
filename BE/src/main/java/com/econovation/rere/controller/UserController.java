package com.econovation.rere.controller;

import com.econovation.rere.apiresponse.ApiError;
import com.econovation.rere.apiresponse.ApiResult;
import com.econovation.rere.apiresponse.ApiUtils;
import com.econovation.rere.domain.dto.request.UserCreateRequestDTO;
import com.econovation.rere.domain.dto.request.UserLoginIdRequestDTO;
import com.econovation.rere.domain.dto.request.UserLoginRequestDTO;
import com.econovation.rere.domain.dto.request.UserNicknameRequestDTO;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/login-id/check")
    public ApiResult<?> checkUserLoginId(@RequestBody @Valid UserLoginIdRequestDTO userLoginIdRequestDTO) {
        if (userService.checkLoginId(userLoginIdRequestDTO)) {
            return ApiUtils.error("중복된 아이디입니다", HttpStatus.BAD_REQUEST);
        } else {
            return ApiUtils.success("사용 가능한 아이디입니다");
        }
    }

    @GetMapping("/nickname/check")
    public ApiResult<?> checkUserNickname(@RequestBody @Valid UserNicknameRequestDTO userNicknameRequestDTO) {
        if (userService.checkNickname(userNicknameRequestDTO)) {
            return ApiUtils.error("중복된 닉네임입니다", HttpStatus.BAD_REQUEST);
        } else {
            return ApiUtils.success("사용 가능한 닉네임입니다");
        }
    }

    @PostMapping("/signup")
    public ApiResult<?> joinMember(@RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO) {
        userService.join(userCreateRequestDTO);
        return ApiUtils.success("회원가입 성공");
    }

    @PostMapping("/login")
    public ApiResult<?> login(@RequestBody @Valid UserLoginRequestDTO loginRequest, HttpServletRequest request) {
        Optional<User> user = userService.login(loginRequest);
        if (user.isPresent()) {
            // 세션 생성 및 사용자 정보 저장
            HttpSession session = request.getSession();
            session.setAttribute("USER", user.get());
            session.setMaxInactiveInterval(1800);
            return ApiUtils.success("로그인 성공");
        } else {
            return ApiUtils.error("로그인 실패", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ApiResult<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            return ApiUtils.success("로그아웃 성공");
        }
        return ApiUtils.error("로그아웃 실패", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/check-login-status")
    public ApiResult<?> chechkLogin(HttpServletRequest request) {
        if(userService.isUserLoggedIn(request)) {
            return ApiUtils.success("사용자가 로그인 했습니다.");
        } else {
            return ApiUtils.error("사용자가 로그인하지 않았습니다.", HttpStatus.UNAUTHORIZED);
        }
    }
}
