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

    @GetMapping("/login-id/check")
    public ApiResult<Boolean> checkUserLoginId(@RequestBody @Valid UserLoginIdRequestDTO userLoginIdRequestDTO) {
        userService.checkLoginId(userLoginIdRequestDTO);
        return ApiUtils.success(true, "사용 가능한 아이디입니다.");
    }

    @GetMapping("/nickname/check")
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
    public ApiResult<UserLoginResponseDTO> login(@RequestBody @Valid UserLoginRequestDTO loginRequest, HttpServletRequest request) {
        User user = userService.login(loginRequest);
        HttpSession session = request.getSession();
        session.setAttribute("USER", user);
        session.setMaxInactiveInterval(1800);

        UserLoginResponseDTO userLoginResponseDTO = UserLoginResponseDTO.builder()
                .nickname(user.getNickname())
                .build();

        return ApiUtils.success(userLoginResponseDTO,"로그인 성공");
    }

    @PostMapping("/logout")
    public ApiResult<Boolean> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new InvalidLogoutException("로그인 되어 있지 않습니다.");
        }
        session.invalidate();
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
