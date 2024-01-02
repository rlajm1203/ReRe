package com.econovation.rere.controller;

import com.econovation.rere.domain.dto.request.UserCreateRequestDTO;
import com.econovation.rere.domain.dto.request.UserLoginIdRequestDTO;
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
    public ResponseEntity<String> checkUserLoginId(@RequestBody @Valid UserLoginIdRequestDTO userLoginIdRequestDTO) {
        if (userService.checkLoginId(userLoginIdRequestDTO)) {
            return new ResponseEntity<>("이미 존재하는 아이디 입니다.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("사용가능한 아이디 입니다.", HttpStatus.OK);
        }

    }

    // 회원 가입 요청 처리
    @PostMapping("signup")
    public ResponseEntity<String> joinMember(@RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO) {
        userService.join(userCreateRequestDTO);
        log.info("info log={}", userCreateRequestDTO);
        return new ResponseEntity<>("회원 가입이 완료되었습니다.", HttpStatus.CREATED);
    }


}
