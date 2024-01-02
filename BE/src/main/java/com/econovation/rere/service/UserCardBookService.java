package com.econovation.rere.service;

import com.econovation.rere.domain.entity.Card;
import com.econovation.rere.domain.entity.CardBook;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.domain.entity.UserCardBook;
import com.econovation.rere.domain.repository.CardBookRepository;
import com.econovation.rere.domain.repository.UserCardBookRepository;
import com.econovation.rere.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = false)
public class UserCardBookService {

    private final UserCardBookRepository userCardBookRepository;
    private final UserRepository userRepository;
    private final CardBookRepository cardBookRepository;

//    사용자가 특정 카드북을 담았을 때
    public UserCardBook choose(Integer userId, Integer cardbookId){
        UserCardBook userCardBook = UserCardBook.builder()
                .user(userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자 입니다.")))
                .cardbook(cardBookRepository.findById(cardbookId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 카드북입니다.")))
                .build();
        return userCardBookRepository.save(userCardBook);
    }

//    담은 카드북을 담기 취소하는 것,
    public void unchoose(Integer userId, Integer cardbookId){
        CardBook cardbook = cardBookRepository.findById(cardbookId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 카드북입니다."));
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));

//        userCardBookRepository.deleteUserCardBookByCardbookAndUser(cardbook, user);
    }
}
