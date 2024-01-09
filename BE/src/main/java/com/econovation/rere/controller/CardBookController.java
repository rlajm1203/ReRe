package com.econovation.rere.controller;

import com.econovation.rere.apiresponse.ApiResult;
import com.econovation.rere.apiresponse.ApiUtils;
import com.econovation.rere.domain.dto.request.*;
import com.econovation.rere.domain.dto.response.CardBookResponseDTO;
import com.econovation.rere.domain.dto.response.MainPageResponseDTO;
import com.econovation.rere.domain.dto.response.UserCardBookResponseDTO;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.exception.NotAthenticationException;
import com.econovation.rere.service.CardBookService;
import com.econovation.rere.service.ThemeService;
import com.econovation.rere.service.UserCardBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class CardBookController {

    private final CardBookService cardBookService;
    private final ThemeService themeService;
    private final UserCardBookService userCardBookService;

//    생성
    @PostMapping("/cardbook")
    public ApiResult<CardBookResponseDTO> createCardBook(@RequestBody @Valid CardBookCreateRequestDTO  cardBookCreateRequestDTO, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("USER");
        CardBookResponseDTO cardBookResponseDTO = cardBookService.register(cardBookCreateRequestDTO, user.getUserId());
        return ApiUtils.success(
                cardBookResponseDTO
                ,"카드북이 생성되었습니다."
        );
    }

//    수정
    @PutMapping("/cardbook")
    public ApiResult<CardBookResponseDTO> updateCardBook(@RequestBody @Valid CardBookUpdateRequestDTO cardBookUpdateRequestDTO, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("USER");

        if(user.getNickname() != cardBookService.getCardbook(cardBookUpdateRequestDTO.getCardbookId()).getName()) throw new NotAthenticationException("카드북 작성자가 아닙니다.");
        CardBookResponseDTO cardBookResponseDTO = cardBookService.update(cardBookUpdateRequestDTO);

        return ApiUtils.success(
                cardBookResponseDTO
                ,"카드북이 수정되었습니다."
        );
    }

//    삭제
    @DeleteMapping("/cardbook")
    public ApiResult<Boolean> removeCardBook(@RequestBody @Valid CardBookRemoveRequestDTO cardBookRemoveRequestDTO, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("USER");

        if(user.getNickname() != cardBookService.getCardbook(cardBookRemoveRequestDTO.getCardbookId()).getWriter()) throw new NotAthenticationException("카드북 작성자가 아닙니다.");

        Boolean result = cardBookService.remove(cardBookRemoveRequestDTO);
        return ApiUtils.success(result, "카드북 삭제가 완료되었습니다.");
    }

//    검색
    @GetMapping("/cardbook/search")
    public ApiResult<List<CardBookResponseDTO>> searchCardBook(@RequestParam String keyword) {
        List<CardBookResponseDTO> cardBookResponseDTOS = cardBookService.search(keyword);
        return ApiUtils.success(cardBookResponseDTOS, "검색에 성공하였습니다.");
    }

//    메인 페이지 카드북 조회
    @GetMapping("/cardbooks")
    public ApiResult<MainPageResponseDTO> mainpageCardBook(HttpServletRequest request){
        List<CardBookResponseDTO> defaultCardbook = cardBookService.getDefaultCardbook();
        List<CardBookResponseDTO> myCardbook = cardBookService.getMyCardbook(
                ((User)request.getSession().getAttribute("USER")).getUserId()
        );

        MainPageResponseDTO mainPageResponseDTO = MainPageResponseDTO.builder()
                .originCardbooks(defaultCardbook)
                .myCardbooks(myCardbook)
                .build();

        return ApiUtils.success(mainPageResponseDTO, "메인 페이지 카드북 목록입니다.");
    }

//    사용자가 카드북 담기
    @PostMapping("/cardbook/{cardbookId}")
    public ApiResult<UserCardBookResponseDTO> chooseCardBook(@PathVariable("cardbookId") Integer cardbookId, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("USER");
        UserCardBookResponseDTO userCardBookResponseDTO = userCardBookService.choose(user.getUserId(), cardbookId);
        return ApiUtils.success(userCardBookResponseDTO, "카드북을 담았습니다.");
    }

//    사용자가 카드북을 담기 취소
    @DeleteMapping("/cardbook/{cardbookId}")
    public ApiResult<UserCardBookResponseDTO> unchooseCardBook(@PathVariable("cardbookId") Integer cardbookId, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("USER");
        return ApiUtils.success(userCardBookService.unchoose(user.getUserId(), cardbookId), "카드북을 제외하였습니다.");
    }
}
