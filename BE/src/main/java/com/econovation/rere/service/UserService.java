package com.econovation.rere.service;

import com.econovation.rere.domain.dto.request.UserCreateRequestDTO;
import com.econovation.rere.domain.dto.request.UserLoginIdRequestDTO;
import com.econovation.rere.domain.dto.request.UserLoginRequestDTO;
import com.econovation.rere.domain.dto.request.UserNicknameRequestDTO;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.domain.repository.UserRepository;
import com.econovation.rere.exception.DuplicateLoginIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void checkLoginId(UserLoginIdRequestDTO userLoginIdRequestDTO) {
        Optional<User> user = userRepository.findByLoginId(userLoginIdRequestDTO.getLoginId());
        if (user.isPresent()) {
            throw new DuplicateLoginIdException("중복된 아이디입니다.");
        }
    }

    public boolean checkNickname(UserNicknameRequestDTO userNicknameRequestDTO) {
        Optional<User> user = userRepository.findByNickname(userNicknameRequestDTO.getNickname());
        return user.isPresent();
    }

    public String join(UserCreateRequestDTO userCreateRequestDTO) {

        User user = userCreateRequestDTO.toEntity();
        user.updatePw(passwordEncoder.encode(user.getPw()));
        userRepository.save(user);
        return userCreateRequestDTO.getLoginId();
    }

    public Optional<User> login(UserLoginRequestDTO loginRequest) {
        return userRepository.findByLoginId(loginRequest.getLoginId())
                .filter(user -> passwordEncoder.matches(loginRequest.getPw(), user.getPw()));
    }

    // 로그인 상태 확인
    public boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 현재 세션을 가져옴, 없으면 null 반환
        return (session != null && session.getAttribute("USER") != null);
    }

}
