package com.econovation.rere.controller;

import com.econovation.rere.apiresponse.ApiResult;
import com.econovation.rere.apiresponse.ApiUtils;
import com.econovation.rere.config.CurrentUser;
import com.econovation.rere.domain.dto.request.*;
import com.econovation.rere.domain.dto.response.UserLoginResponseDTO;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
        log.info("Login ID 중복 체크 요청 : "+userLoginIdRequestDTO.getLoginId());
        userService.checkLoginId(userLoginIdRequestDTO);
        return ApiUtils.success(true, "사용 가능한 아이디입니다.");
    }

    @PostMapping("/nickname/check")
    public ApiResult<Boolean> checkUserNickname(@RequestBody @Valid UserNicknameRequestDTO userNicknameRequestDTO) {
        log.info("닉네임 중복 체크 요청 : "+userNicknameRequestDTO.getNickname());
        userService.checkNickname(userNicknameRequestDTO);
        return ApiUtils.success(true, "사용 가능한 닉네임입니다.");
    }

    @PostMapping("/signup")
    public ApiResult<Boolean> createUser(@RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO) {
        log.info("회원가입 요청 (ID): "+userCreateRequestDTO.getLoginId());
        log.info("회원가입 요청 (Nickname) : " + userCreateRequestDTO.getNickname());
        userService.join(userCreateRequestDTO);
        return ApiUtils.success(true, "회원가입 성공");
    }

    @PostMapping("/login")
    public ApiResult<UserLoginResponseDTO> login(@RequestBody @Valid UserLoginRequestDTO loginRequest, HttpSession session) {
        log.info("로그인 요청 (ID) : "+loginRequest.getLoginId());
        UserLoginResponseDTO userLoginResponseDTO = userService.login(loginRequest, session);
        return ApiUtils.success(userLoginResponseDTO, "로그인 성공");
    }

    @PostMapping("/logout")
    public ApiResult<Boolean> logout(HttpSession session) {
        log.info("로그아웃 요청 (ID): "+((User)session.getAttribute("USER")).getLoginId());
        userService.logout(session);

        return ApiUtils.success(true, "로그아웃 성공");
    }

    @PutMapping("update/pw")
    public ApiResult<Boolean> updatePw(@CurrentUser User user, @RequestBody @Valid UserPwUpdateRequestDTO userPwUpdateRequestDTO){
        log.info("비밀번호 변경 요청 (ID) : "+user.getUserId());
        userService.updatePw(user.getUserId(), userPwUpdateRequestDTO);
        return ApiUtils.success(true, "비밀번호 변경 성공");
    }

    @PutMapping("update/nickname")
    public ApiResult<Boolean> updateNickname(@CurrentUser User user, @RequestBody @Valid UserNicknameUpdateRequestDTO userNicknameUpdateRequestDTO){
        log.info("닉네임 변경 요청 (ID) : " + user.getNickname());
        userService.updateNickname(user.getUserId(), userNicknameUpdateRequestDTO);
        return ApiUtils.success(true, "닉네임 변경 성공");
    }
}
