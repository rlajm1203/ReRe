package com.econovation.rere.controller;

import com.econovation.rere.apiresponse.ApiResult;
import com.econovation.rere.apiresponse.ApiUtils;
import com.econovation.rere.config.CurrentUser;
import com.econovation.rere.domain.dto.request.*;
import com.econovation.rere.domain.dto.response.UserLoginResponseDTO;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.exception.InvalidLogoutException;
import com.econovation.rere.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/login-id/check")
    public ApiResult<Boolean> checkUserLoginId(@RequestBody @Valid UserLoginIdRequestDTO userLoginIdRequestDTO) {
        userService.checkLoginId(userLoginIdRequestDTO);
        return ApiUtils.success(true, "사용 가능한 아이디입니다.");
    }

    @PostMapping("/nickname/check")
    public ApiResult<Boolean> checkUserNickname(@RequestBody @Valid UserNicknameRequestDTO userNicknameRequestDTO) {
        userService.checkNickname(userNicknameRequestDTO);
        return ApiUtils.success(true, "사용 가능한 닉네임입니다.");
    }

    @PostMapping("/signup")
    public ApiResult<Boolean> createUser(@RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO) {
        userService.join(userCreateRequestDTO);
        return ApiUtils.success(true, "회원가입 성공");
    }

    @PostMapping("/login")
    public ApiResult<UserLoginResponseDTO> login(@RequestBody @Valid UserLoginRequestDTO loginRequest, HttpSession session) {
        UserLoginResponseDTO userLoginResponseDTO = userService.login(loginRequest, session);
        return ApiUtils.success(userLoginResponseDTO, "로그인 성공");
    }

    @PostMapping("/logout")
    public ApiResult<Boolean> logout(HttpSession session) {
        userService.logout(session);
        return ApiUtils.success(true, "로그아웃 성공");
    }

    @PutMapping("update/pw")
    public ApiResult<Boolean> updatePw(@CurrentUser User user, @RequestBody @Valid UserPwUpdateRequestDTO userPwUpdateRequestDTO, HttpServletRequest request){
        userService.updatePw(user.getUserId(), userPwUpdateRequestDTO);
        return ApiUtils.success(true, "비밀번호 변경 성공");
    }

    @PutMapping("update/nickname")
    public ApiResult<Boolean> updateNickname(@CurrentUser User user, @RequestBody @Valid UserNicknameUpdateRequestDTO userNicknameUpdateRequestDTO, HttpServletRequest request){
        userService.updateNickname(user.getUserId(), userNicknameUpdateRequestDTO);
        return ApiUtils.success(true, "닉네임 변경 성공");
    }
}
