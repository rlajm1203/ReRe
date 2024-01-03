package com.econovation.rere.service;

import com.econovation.rere.domain.dto.request.UserCreateRequestDTO;
import com.econovation.rere.domain.dto.request.UserLoginIdRequestDTO;
import com.econovation.rere.domain.dto.request.UserNicknameRequestDTO;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean checkLoginId(UserLoginIdRequestDTO userLoginIdRequestDTO) {
        Optional<User> user = userRepository.findByLoginId(userLoginIdRequestDTO.getLoginId());
        return user.isPresent();
    }

    public boolean checkNickname(UserNicknameRequestDTO userNicknameRequestDTO) {
        Optional<User> user = userRepository.findByNickname(userNicknameRequestDTO.getNickname());
        return user.isPresent();
    }

    public String join(UserCreateRequestDTO userCreateRequestDTO) {

        User user = userCreateRequestDTO.toEntity();
        userRepository.save(user);
        return userCreateRequestDTO.getLoginId();
    }


}
