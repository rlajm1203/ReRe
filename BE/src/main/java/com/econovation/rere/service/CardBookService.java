package com.econovation.rere.service;


import com.econovation.rere.domain.dto.request.CardBookCreateRequestDTO;
import com.econovation.rere.domain.dto.request.CardBookRemoveRequestDTO;
import com.econovation.rere.domain.dto.request.CardBookUpdateRequestDTO;
import com.econovation.rere.domain.dto.response.CardBookResponseDTO;
import com.econovation.rere.domain.dto.response.UserCardBookResponseDTO;
import com.econovation.rere.domain.entity.Card;
import com.econovation.rere.domain.entity.CardBook;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.domain.entity.UserCardBook;
import com.econovation.rere.domain.repository.CardBookRepository;
import com.econovation.rere.domain.repository.UserCardBookRepository;
import com.econovation.rere.domain.repository.UserRepository;
import com.econovation.rere.exception.CardBookNotFoundException;
import com.econovation.rere.exception.SearchNotFoundException;
import com.econovation.rere.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CardBookService {


    private final CardBookRepository cardBookRepository;
    private final UserCardBookRepository userCardBookRepository;
    private final UserRepository userRepository;

//    생성
    @Transactional(readOnly = false)
    public CardBookResponseDTO register(CardBookCreateRequestDTO cardBookCreateRequestDTO, Integer userId) throws UserNotFoundException {

        User user = this.userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException());
        LocalDateTime timenow = LocalDateTime.now();
        // 필요한 정보만 따로 추출하는 메소드를 작성해야 할 듯

        CardBook cardBook = cardBookCreateRequestDTO.toEntity(user.getNickname(),timenow);

        UserCardBook userCardBook = UserCardBook.builder()
                .user(user)
                .cardbook(cardBook)
                .chooseDate(timenow)
                .build();
        
        cardBook = this.cardBookRepository.save(cardBook);
        userCardBook = this.userCardBookRepository.save(userCardBook);

        return CardBookResponseDTO.toCardBookResponseDTO(cardBook);
    }

//    수정
    @Transactional(readOnly = false)
    public CardBookResponseDTO update(CardBookUpdateRequestDTO cardBookUpdateRequestDTO) throws CardBookNotFoundException{
        CardBook cardBook = this.cardBookRepository.findById(cardBookUpdateRequestDTO.getCardbookId())
                .orElseThrow(()->new CardBookNotFoundException());

        cardBook.setName(cardBookUpdateRequestDTO.getName());
        cardBook.setUpdateDate(LocalDateTime.now());

        return CardBookResponseDTO.toCardBookResponseDTO(cardBook);
    }

//    삭제
    @Transactional(readOnly = false)
    public boolean remove(CardBookRemoveRequestDTO cardBookRemoveRequestDTO){
        if(cardBookRepository.deleteByCardbookId(cardBookRemoveRequestDTO.getCardbookId())==1) return true;
        else return false;
    }

//    검색
    public List<CardBookResponseDTO> search(String name) throws SearchNotFoundException{
        List<CardBook> cardBooks = cardBookRepository.findByNameContainingOrderByScrapCnt(name);

//        검색 결과가 비었는지 확인
        if(cardBooks.isEmpty()) throw new SearchNotFoundException();

        return CardBookResponseDTO.toCardBookResponseDTOS(cardBooks);
    }

//    카드북 id를 기준으로 카드북 가져오기
    public CardBook getCardbook(Integer cardbookId){
        return cardBookRepository.findById(cardbookId).orElseThrow(()->new CardBookNotFoundException());
    }

//    메인 페이지에 띄울 기본 카드북 가져오기 (스크랩 수 내림차순을 기준으로 정렬)
    public List<CardBookResponseDTO> getDefaultCardbook(){
        List<CardBook> cardBooks = cardBookRepository.findByWriter("admin");
        cardBooks.sort((cb1, cb2)->( cb2.getScrapCnt()-cb1.getScrapCnt()));

        return CardBookResponseDTO.toCardBookResponseDTOS(cardBooks.subList(0,3));

    }

    public List<CardBookResponseDTO> getMyCardbook(Integer userId) throws UserNotFoundException{
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException());
        List<UserCardBook> usercardBooks = userCardBookRepository.findAllByUser(user);

        return UserCardBookResponseDTO.toCardBookResponseDTOS(usercardBooks);
    }
}
