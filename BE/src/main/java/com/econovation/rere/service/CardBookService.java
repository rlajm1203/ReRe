package com.econovation.rere.service;


import com.econovation.rere.domain.dto.request.CardBookCreateRequestDTO;
import com.econovation.rere.domain.dto.request.CardBookUpdateRequestDTO;
import com.econovation.rere.domain.dto.response.CardBookResponseDTO;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = false)
public class CardBookService {


    private final CardBookRepository cardBookRepository;
    private final UserCardBookRepository userCardBookRepository;
    private final UserRepository userRepository;

//    생성
    public CardBookResponseDTO create(CardBookCreateRequestDTO cardBookCreateRequestDTO, Integer userId){

        User user = this.userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("userId에 해당하는 user가 존재하지 않습니다."));
        LocalDateTime timenow = LocalDateTime.now();
        // 필요한 정보만 따로 추출하는 메소드를 작성해야 할 듯

        CardBook cardBook = cardBookCreateRequestDTO.toEntity(user.getNickname(),timenow);

        UserCardBook userCardBook = UserCardBook.builder()
                .user(user)
                .cardbook(cardBook)
                .build();
        
        cardBook = this.cardBookRepository.save(cardBook);
        userCardBook = this.userCardBookRepository.save(userCardBook);

        return CardBookResponseDTO.builder()
                .name(cardBook.getName())
                .writer(user.getNickname())
                .updateDate(timenow)
                .cardbookId(cardBook.getCardbookId())
                .build();
    }

//    수정
    public CardBookResponseDTO update(CardBookUpdateRequestDTO cardBookUpdateRequestDTO){
        CardBook cardBook = this.cardBookRepository.findById(cardBookUpdateRequestDTO.getCardbookId())
                .orElseThrow(()->new IllegalArgumentException("해당 카드북이 존재하지 않습니다."));

        cardBook.setName(cardBookUpdateRequestDTO.getName());
        cardBook.setUpdateDate(LocalDateTime.now());
        cardBook = this.cardBookRepository.save(cardBook);

        return CardBookResponseDTO.builder()
                .name(cardBook.getName())
                .writer(cardBook.getWriter())
                .updateDate(cardBook.getUpdateDate())
                .cardbookId(cardBook.getCardbookId())
                .build();
    }

//    삭제
    public boolean remove(Integer cardbookId){
        if(this.cardBookRepository.deleteByCardbookId(cardbookId)==1) return true;
        else return false;
    }

//    검색
    public List<CardBookResponseDTO> search(String name){
        List<CardBook> cardBooks = this.cardBookRepository.findByNameOrderByScrapCnt(name);

        int length = cardBooks.size();

        List<CardBookResponseDTO> cardBookResponseDTOS = new ArrayList<>(length);

        int i = 0;
        for(CardBookResponseDTO dto : cardBookResponseDTOS){
            CardBook cardBook = cardBooks.get(i);
            dto = CardBookResponseDTO.builder()
                    .name(cardBook.getName())
                    .writer(cardBook.getWriter())
                    .updateDate(cardBook.getUpdateDate())
                    .cardbookId(cardBook.getCardbookId())
                    .build();
        }

        return cardBookResponseDTOS;
    }

//    메인 페이지 (스크랩 수 내림차순을 기준으로 정렬)
    public List<CardBookResponseDTO> getAll(){
        List<CardBook> cardBooks = this.cardBookRepository.findAll();
        cardBooks.sort((cb1, cb2)->(cb1.getScrapCnt() - cb2.getScrapCnt()));

        int length = cardBooks.size();
        List<CardBookResponseDTO> cardBookResponseDTOS = new ArrayList<>(length);

        int i = 0;

        for(CardBookResponseDTO dto : cardBookResponseDTOS){
            CardBook cardBook = cardBooks.get(i);
            dto = CardBookResponseDTO.builder()
                    .name(cardBook.getName())
                    .writer(cardBook.getWriter())
                    .updateDate(cardBook.getUpdateDate())
                    .cardbookId(cardBook.getCardbookId())
                    .build();
        }

        return cardBookResponseDTOS;
    }

}
