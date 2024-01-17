package com.econovation.rere.service;

import com.econovation.rere.domain.dto.request.*;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void checkLoginId(UserLoginIdRequestDTO userLoginIdRequestDTO) throws DuplicateLoginIdException{
        Optional<User> user = userRepository.findByLoginId(userLoginIdRequestDTO.getLoginId());
        if (user.isPresent()) {
            throw new DuplicateLoginIdException("중복된 아이디입니다.");
        }
    }

    public void checkNickname(UserNicknameRequestDTO userNicknameRequestDTO) throws DuplicateNicknameException{
        Optional<User> user = userRepository.findByNickname(userNicknameRequestDTO.getNickname());
        if (user.isPresent()) {
            throw new DuplicateNicknameException("중복된 닉네임입니다.");
        }
    }

    public String join(UserCreateRequestDTO userCreateRequestDTO) throws DuplicateLoginIdException{
        User user = userCreateRequestDTO.toEntity();
        if(userRepository.findByLoginId(userCreateRequestDTO.getLoginId()).isPresent()){
            throw new DuplicateLoginIdException();
        }
        if(userRepository.findByNickname(userCreateRequestDTO.getNickname()).isPresent()){
            throw new DuplicateNicknameException();
        }
        user.updatePw(passwordEncoder.encode(user.getPw()));
        userRepository.save(user);
        return userCreateRequestDTO.getLoginId();
    }

    public void logout(HttpSession session) {
        if (session != null) session.invalidate();
    }

    public User login(UserLoginRequestDTO loginRequest) throws InvalidLoginException, UserNotFoundException{
        User user = userRepository.findByLoginId(loginRequest.getLoginId())
                .orElseThrow(() -> new UserNotFoundException("해당 사용자가 존재하지 않습니다."));
        if (!passwordEncoder.matches(loginRequest.getPw(), user.getPw())) {
            throw new InvalidLoginException("아이디와 비밀번호가 일치하지 않습니다.");
        }
        return user;
    }

    public void updatePw(Integer userId, UserPwUpdateRequestDTO userPwUpdateRequestDTO) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자가 존재하지 않습니다."));
        String pw = userPwUpdateRequestDTO.getPw();
        user.updatePw(passwordEncoder.encode(pw));
    }

    public void updateNickname(Integer userId, UserNicknameUpdateRequestDTO userNicknameUpdateRequestDTO) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자가 존재하지 않습니다."));
        String nickname = userNicknameUpdateRequestDTO.getNickname();
        log.info(nickname);
        user.updateNickname(nickname);
    }
}
