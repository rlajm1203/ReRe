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
import com.econovation.rere.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class CardBookService {


    private final CardBookRepository cardBookRepository;
    private final UserCardBookRepository userCardBookRepository;
    private final UserRepository userRepository;

//    생성
    @Transactional(readOnly = false)
    public CardBookResponseDTO register(CardBookCreateRequestDTO cardBookCreateRequestDTO, Integer userId) throws UserNotFoundException, IOException {

        User user = this.userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException());
        LocalDateTime timenow = LocalDateTime.now();

        byte[] imageData = processImageData(cardBookCreateRequestDTO.getImage());

        CardBook cardBook = cardBookCreateRequestDTO.toEntity(user.getNickname(),timenow,imageData);
        cardBookRepository.save(cardBook);

        UserCardBook userCardBook = UserCardBook.builder()
                .user(user)
                .cardbook(cardBook)
                .chooseDate(timenow)
                .build();
        userCardBookRepository.save(userCardBook);

        return CardBookResponseDTO.toCardBookResponseDTO(cardBook);
    }

    private byte[] processImageData(MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            return imageFile.getBytes();
        } else {
            return loadDefaultImageData();
        }
    }

    private byte[] loadDefaultImageData() {
        try {
            ClassPathResource resource = new ClassPathResource("static/images/default-image.png");
            try (InputStream inputStream = resource.getInputStream()) {
                return inputStream.readAllBytes();
            }
        } catch (IOException e) {
            throw new ImageNotLoadException();
        }
    }

//    수정
    @Transactional(readOnly = false)
    public CardBookResponseDTO update(CardBookUpdateRequestDTO cardBookUpdateRequestDTO) throws CardBookNotFoundException, IOException {
        CardBook cardBook = this.cardBookRepository.findById(cardBookUpdateRequestDTO.getCardbookId())
                .orElseThrow(()->new CardBookNotFoundException());

        cardBook.setName(cardBookUpdateRequestDTO.getName());
        cardBook.setUpdateDate(LocalDateTime.now());
        byte[] imageData = processImageData(cardBookUpdateRequestDTO.getImage());
        cardBook.setImage(imageData);

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

        Integer length = cardBooks.size();

        if(length>=3) return CardBookResponseDTO.toCardBookResponseDTOS(cardBooks.subList(0,3));
        else if(length==2) return CardBookResponseDTO.toCardBookResponseDTOS(cardBooks.subList(0,2));
        else if(length==1) return CardBookResponseDTO.toCardBookResponseDTOS(cardBooks.subList(0,1));
        else return CardBookResponseDTO.toCardBookResponseDTOS(new ArrayList<CardBook>());
    }

    public List<CardBookResponseDTO> getMyCardbook(Integer userId) throws UserNotFoundException{
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException());
        List<UserCardBook> usercardBooks = userCardBookRepository.findAllByUser(user);

        return UserCardBookResponseDTO.toCardBookResponseDTOS(usercardBooks);
    }

    // 카드북 이미지 조회
    public byte[] getCardBookImage(Integer cardbookId) {
        CardBook cardBook = cardBookRepository.findById(cardbookId)
                .orElseThrow(() -> new EntityNotFoundException("CardBook not found"));


        if (cardBook.getImage() == null) {
            throw new EntityNotFoundException("이미지가 존재하지 않습니다.");
        }
        return cardBook.getImage();
    }

    // 이미지 타입
    public String determineMimeType(byte[] imageData) {
        String mimeType = "application/octet-stream"; // 기본 MIME 타입

        if (imageData.length < 4) {
            return mimeType; // 데이터가 너무 짧아 식별 불가
        }

        // 파일 시그니처를 비교하여 MIME 타입 결정
        String signature = bytesToHex(imageData, 4);

        // JPEG: 첫 3바이트가 FFD8FF일 때
        if (signature.startsWith("FFD8FF")) {
            mimeType = "image/jpeg";
        }
        // PNG: 첫 4바이트가 89504E47일 때
        else if (signature.startsWith("89504E47")) {
            mimeType = "image/png";
        }
        // 추가적인 파일 형식...

        return mimeType;
    }

    // 바이트 배열을 16진수 문자열로 변환하는 메소드
    private String bytesToHex(byte[] bytes, int length) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString().toUpperCase();
    }
}
