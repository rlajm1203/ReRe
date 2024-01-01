package com.econovation.rere.service;

import com.econovation.rere.domain.dto.request.UserCreateRequestDTO;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String join(UserCreateRequestDTO userCreateRequestDTO) {

        User user = userCreateRequestDTO.toEntity();
        userRepository.save(user);
        return userCreateRequestDTO.getLoginId();
    }


}
