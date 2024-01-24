package com.econovation.rere.controller;

import com.econovation.rere.apiresponse.ApiResult;
import com.econovation.rere.apiresponse.ApiUtils;
import com.econovation.rere.config.CurrentUser;
import com.econovation.rere.domain.dto.request.*;
import com.econovation.rere.domain.dto.response.CardBookResponseDTO;
import com.econovation.rere.domain.dto.response.ImageResponseDTO;
import com.econovation.rere.domain.dto.response.MainPageResponseDTO;
import com.econovation.rere.domain.dto.response.UserCardBookResponseDTO;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.exception.NotAthenticationException;
import com.econovation.rere.service.CardBookService;
import com.econovation.rere.service.ThemeService;
import com.econovation.rere.service.UserCardBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class CardBookController {

    private final CardBookService cardBookService;
    private final UserCardBookService userCardBookService;

//    생성
    @PostMapping("/cardbook")
    public ApiResult<CardBookResponseDTO> createCardBook(
            @CurrentUser User user,
            @RequestParam("name") String name,
            @RequestParam("image") MultipartFile image) throws IOException {
        log.info("카드북 생성 요청 (Nickname) : " + user.getNickname());
        CardBookCreateRequestDTO cardBookCreateRequestDTO = CardBookCreateRequestDTO.builder()
                .name(name)
                .image(image)
                .build();
        CardBookResponseDTO cardBookResponseDTO = cardBookService.register(cardBookCreateRequestDTO, user.getUserId());
        return ApiUtils.success(cardBookResponseDTO, "카드북이 생성되었습니다.");
    }

//    수정
    @PutMapping("/cardbook")
    public ApiResult<CardBookResponseDTO> updateCardBook(
            @CurrentUser User user,
            @RequestParam("name") String name,
            @RequestParam("cardbookId") Integer cardbookId,
            @RequestParam("image") MultipartFile image) throws IOException {
        log.info("카드북 수정 요청 (Nickname) : " + user.getNickname());
        CardBookUpdateRequestDTO cardBookUpdateRequestDTO = CardBookUpdateRequestDTO.builder()
                .name(name)
                .cardbookId(cardbookId)
                .image(image)
                .build();
        if(!cardBookService.getCardbook(cardBookUpdateRequestDTO.getCardbookId()).getWriterId().equals(user.getUserId())) throw new NotAthenticationException("카드북 작성자가 아닙니다.");
        CardBookResponseDTO cardBookResponseDTO = cardBookService.update(cardBookUpdateRequestDTO);
        return ApiUtils.success(cardBookResponseDTO,"카드북이 수정되었습니다.");
    }

//    삭제
    @DeleteMapping("/cardbook/{cardbookId}")
    public ApiResult<Boolean> removeCardBook(@CurrentUser User user, @PathVariable("cardbookId") Integer cardbookId){
        log.info("카드북 삭제 요청 (Nickname) : " + user.getNickname());
        if(!cardBookService.getCardbook(cardbookId).getWriter().equals(user.getNickname())) throw new NotAthenticationException("카드북 작성자가 아닙니다.");
        Boolean result = cardBookService.remove(cardbookId);

        return ApiUtils.success(result, "카드북 삭제가 완료되었습니다.");
    }

//    검색
    @GetMapping("/cardbook/search")
    public ApiResult<List<CardBookResponseDTO>> searchCardBook(@RequestParam String keyword) {
        log.info("카드북 검색 요청 (keyword) : " + keyword);
        List<CardBookResponseDTO> cardBookResponseDTOS = cardBookService.search(keyword);
        return ApiUtils.success(cardBookResponseDTOS, "검색에 성공하였습니다.");
    }

//    메인 페이지 카드북 조회
    @GetMapping("/cardbooks")
    public ApiResult<MainPageResponseDTO> mainpageCardBook(@CurrentUser User user){
        log.info("메인 페이지 조회 요청");
        if(user!=null)
            log.info("USER : " + user.getNickname());
        else
            log.info("USER : "+null);

        List<CardBookResponseDTO> defaultCardbook = cardBookService.getDefaultCardbook();
        List<CardBookResponseDTO> myCardbook = new ArrayList<>();

        if(user!=null) myCardbook = cardBookService.getMyCardbook(user.getUserId());
        MainPageResponseDTO mainPageResponseDTO = MainPageResponseDTO.builder()
                .originCardbooks(defaultCardbook)
                .myCardbooks(myCardbook)
                .build();

        return ApiUtils.success(mainPageResponseDTO, "메인 페이지 카드북 목록입니다.");
    }

    // 카드북 이미지 조회
//    @GetMapping("/cardbook/{cardbookId}/image")
//    public ApiResult<ImageResponseDTO> getCardBookImage(@PathVariable Integer cardbookId) {
//        log.info("카드북 이미지 조회 요청 (CardbookID) : "+cardbookId);
//        byte[] imageData = cardBookService.getCardBookImage(cardbookId);
//        String contentType = cardBookService.determineMimeType(imageData);
//        ImageResponseDTO imageResponseDTO = new ImageResponseDTO(imageData, contentType);
//        return ApiUtils.success(imageResponseDTO, "Image loaded successfully");
//    }

    // 이미지 바로 보여짐
    @GetMapping("/cardbook/{cardBookId}/image")
    public ResponseEntity<byte[]> getCardBookImage(@PathVariable Integer cardBookId) {
        byte[] imageData = cardBookService.getCardBookImage(cardBookId);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }

//    사용자가 카드북 담기
    @PostMapping("/usercardbook/{cardbookId}")
    public ApiResult<UserCardBookResponseDTO> chooseCardBook(@CurrentUser User user, @PathVariable("cardbookId") Integer cardbookId){
        log.info("사용자 : "+user.getNickname()+", 카드북 담기 (cardbookId) : "+cardbookId);
        UserCardBookResponseDTO userCardBookResponseDTO = userCardBookService.choose(user.getUserId(), cardbookId);
        return ApiUtils.success(userCardBookResponseDTO, "카드북을 담았습니다.");
    }

//    사용자가 카드북을 담기 취소
    @DeleteMapping("/usercardbook/{cardbookId}")
    public ApiResult<UserCardBookResponseDTO> unchooseCardBook(@CurrentUser User user, @PathVariable("cardbookId") Integer cardbookId){
        log.info("사용자 : "+user.getNickname()+", 카드북 담기 취소 (cardbookId) : "+cardbookId);
        return ApiUtils.success(userCardBookService.unchoose(user.getUserId(), cardbookId), "카드북을 제외하였습니다.");
    }
}
