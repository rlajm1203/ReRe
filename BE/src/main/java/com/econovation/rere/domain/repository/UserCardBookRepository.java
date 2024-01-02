package com.econovation.rere.domain.repository;

import com.econovation.rere.domain.entity.CardBook;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.domain.entity.UserCardBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserCardBookRepository extends JpaRepository<UserCardBook, Integer> {

//    특정 사용자가 담은 카드북의 정보를 가져온다.
    List<UserCardBook> findAllByUser(User user);

//    삭제된 개수 리턴
    Integer e(CardBook cardBook, User user);

//    사용자가 카드북을 이미 담았는지 체크
    boolean existsByCardbookAndUser(CardBook cardBook, User user);

}
