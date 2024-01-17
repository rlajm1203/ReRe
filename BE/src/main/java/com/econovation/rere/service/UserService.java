package com.econovation.rere.service;

import com.econovation.rere.domain.dto.request.*;
import com.econovation.rere.domain.dto.response.UserLoginResponseDTO;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.domain.repository.UserRepository;
import com.econovation.rere.exception.DuplicateLoginIdException;
import com.econovation.rere.exception.DuplicateNicknameException;
import com.econovation.rere.exception.InvalidLoginException;
import com.econovation.rere.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 아이디 중복 체크
    @Transactional(readOnly = true)
    public void checkLoginId(UserLoginIdRequestDTO userLoginIdRequestDTO) {
        userRepository.findByLoginId(userLoginIdRequestDTO.getLoginId()).ifPresent(
                user -> { throw new DuplicateLoginIdException("중복된 아이디 입니다."); }
        );
    }

    // 닉네임 중복 체크
    @Transactional(readOnly = true)
    public void checkNickname(UserNicknameRequestDTO userNicknameRequestDTO) {
        userRepository.findByNickname(userNicknameRequestDTO.getNickname()).ifPresent(
                user -> { throw new DuplicateNicknameException("중복된 닉네임 입니다."); }
        );
    }

    // 회원가입
    @Transactional
    public String join(UserCreateRequestDTO userCreateRequestDTO) {
        userRepository.findByLoginId(userCreateRequestDTO.getLoginId()).ifPresent(
                user -> { throw new DuplicateLoginIdException("중복된 아이디 입니다.");});
        userRepository.findByNickname(userCreateRequestDTO.getNickname()).ifPresent(
                user -> { throw new DuplicateNicknameException("중복된 닉네임 입니다.");});

        User user = userCreateRequestDTO.toEntity();
        user.updatePw(passwordEncoder.encode(user.getPw()));
        userRepository.save(user);
        return userCreateRequestDTO.getLoginId();
    }


    // 로그인
    public UserLoginResponseDTO login(UserLoginRequestDTO loginRequest, HttpSession session) {
        User user = userRepository.findByLoginId(loginRequest.getLoginId())
                .orElseThrow(() -> new UserNotFoundException("해당 사용자가 존재하지 않습니다."));

        if (!passwordEncoder.matches(loginRequest.getPw(), user.getPw())) {
            throw new InvalidLoginException("아이디와 비밀번호가 일치하지 않습니다.");
        }

        session.setAttribute("USER", user);
        session.setMaxInactiveInterval(1800);

        return UserLoginResponseDTO.builder()
                .nickname(user.getNickname())
                .build();
    }

    // 로그아웃
    public void logout(HttpSession session) {
        if (session != null) session.invalidate();
    }

    // 비밀번호 변경
    @Transactional
    public void updatePw(Integer userId, UserPwUpdateRequestDTO userPwUpdateRequestDTO) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자가 존재하지 않습니다."));

        String pw = userPwUpdateRequestDTO.getPw();
        user.updatePw(passwordEncoder.encode(pw));
    }

    // 닉네임 변경
    @Transactional
    public void updateNickname(Integer userId, UserNicknameUpdateRequestDTO userNicknameUpdateRequestDTO) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자가 존재하지 않습니다."));

        String nickname = userNicknameUpdateRequestDTO.getNickname();

        userRepository.findByNickname(nickname).ifPresent(
                existingUser -> { throw new DuplicateNicknameException("중복된 닉네임 입니다."); }
        );

        user.updateNickname(nickname);
    }
}
