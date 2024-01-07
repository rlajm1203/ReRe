package com.econovation.rere.service;

import com.econovation.rere.domain.dto.response.UserCardBookResponseDTO;
import com.econovation.rere.domain.entity.UserCardBook;
import com.econovation.rere.domain.repository.CardBookRepository;
import com.econovation.rere.domain.repository.UserCardBookRepository;
import com.econovation.rere.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserCardBookService {

    private final UserCardBookRepository userCardBookRepository;
    private final UserRepository userRepository;
    private final CardBookRepository cardBookRepository;

//    사용자가 특정 카드북을 담았을 때
    @Transactional(readOnly = false)
    public UserCardBookResponseDTO choose(Integer userId, Integer cardbookId){
        // userId -> Session에서 꺼내서 사용
        // cardbookId -> 요청에서 찾아서 사용
        UserCardBook userCardBook = UserCardBook.builder()
                .user(userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자 입니다.")))
                .cardbook(cardBookRepository.findById(cardbookId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 카드북입니다.")))
                .chooseDate(LocalDateTime.now())
                .build();
        userCardBookRepository.save(userCardBook);

        return UserCardBookResponseDTO.toUserCardBookResponse(userCardBook);
    }

//    담은 카드북을 담기 취소하는 것,
    @Transactional(readOnly = false)
    public void unchoose(Integer userId, Integer cardbookId){
        userCardBookRepository.findByCardbookAndUser(
                cardBookRepository.findById(cardbookId).orElseThrow(),
                userRepository.findById(userId).orElseThrow()
        ).orElseThrow().setDeleted(true);
    }
}
