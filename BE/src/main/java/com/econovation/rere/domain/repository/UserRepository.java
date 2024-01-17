package com.econovation.rere.domain.repository;

import com.econovation.rere.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findByNickname(String nickname);
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByUserId(Integer userId);

}