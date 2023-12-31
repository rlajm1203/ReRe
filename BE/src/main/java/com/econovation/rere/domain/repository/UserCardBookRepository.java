package com.econovation.rere.domain.repository;

import com.econovation.rere.domain.entity.User;
import com.econovation.rere.domain.entity.UserCardBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCardBookRepository extends JpaRepository<UserCardBook, Integer> {

//    특정 사용자가 담은 카드북의 목록을 가져온다.
    List<UserCardBook> findAllByUser(User user);

}
