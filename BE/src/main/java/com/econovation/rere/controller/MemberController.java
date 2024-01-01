package com.econovation.rere.controller;

import com.econovation.rere.domain.dto.request.UserCreateRequestDTO;
import com.econovation.rere.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 가입
    @PostMapping("signup")
    public ResponseEntity<String> joinMember(@RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO) {
        memberService.join(userCreateRequestDTO);
        log.info("info log={}", userCreateRequestDTO);
        return new ResponseEntity<>("회원 가입이 완료되었습니다.", HttpStatus.CREATED);
    }


}
