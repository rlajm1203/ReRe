package com.econovation.rere.controller;

import com.econovation.rere.apiresponse.ApiError;
import com.econovation.rere.apiresponse.ApiResult;
import com.econovation.rere.apiresponse.ApiUtils;
import com.econovation.rere.domain.dto.request.UserCreateRequestDTO;
import com.econovation.rere.domain.dto.request.UserLoginIdRequestDTO;
import com.econovation.rere.domain.dto.request.UserNicknameRequestDTO;
import com.econovation.rere.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("check-login")
    public ApiResult<?> checkUserLoginId(@RequestBody @Valid UserLoginIdRequestDTO userLoginIdRequestDTO) {
        if (userService.checkLoginId(userLoginIdRequestDTO)) {
            return ApiUtils.error("중복된 아이디입니다", HttpStatus.BAD_REQUEST);
        } else {
            return ApiUtils.success("사용 가능한 아이디입니다");
        }
    }

    @PostMapping("/check-nickname")
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


}
