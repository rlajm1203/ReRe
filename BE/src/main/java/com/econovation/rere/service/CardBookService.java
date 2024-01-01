package com.econovation.rere.service;


import com.econovation.rere.domain.dto.request.CardBookCreateRequestDTO;
import com.econovation.rere.domain.dto.response.CardBookResponseDTO;
import com.econovation.rere.domain.entity.CardBook;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.domain.entity.UserCardBook;
import com.econovation.rere.domain.repository.CardBookRepository;
import com.econovation.rere.domain.repository.UserCardBookRepository;
import com.econovation.rere.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = false)
public class CardBookService {


    private final CardBookRepository cardBookRepository;
    private final UserCardBookRepository userCardBookRepository;
    private final UserRepository userRepository;

    public CardBookResponseDTO create(CardBookCreateRequestDTO cardBookCreateRequestDTO, Integer userId){

        User user = this.userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("userId에 해당하는 user가 존재하지 않습니다."));
        LocalDateTime timenow = LocalDateTime.now();
        // 필요한 정보만 따로 추출하는 메소드를 작성해야 할 듯

//        빌더 패턴 적용
        CardBook cardBook = CardBook.builder()
                .name(cardBookCreateRequestDTO.getName())
                .writer(user.getNickname())
                .createDate(timenow)
                .updateDate(timenow)
                .build();

        UserCardBook userCardBook = UserCardBook.builder()
                .user(user)
                .cardbook(cardBook)
                .build();
        
        cardBook = this.cardBookRepository.save(cardBook);
        userCardBook = this.userCardBookRepository.save(userCardBook);

        return CardBookResponseDTO.builder()
                .name(cardBook.getName())
                .writer(user.getNickname())
                .timenow(timenow)
                .cardbookId(cardBook.getCardbookId())
                .build();
    }
}
