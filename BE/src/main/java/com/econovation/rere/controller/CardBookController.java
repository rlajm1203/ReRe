package com.econovation.rere.controller;

import com.econovation.rere.apiresponse.ApiResult;
import com.econovation.rere.apiresponse.ApiUtils;
import com.econovation.rere.domain.dto.request.*;
import com.econovation.rere.domain.dto.response.CardBookResponseDTO;
import com.econovation.rere.service.CardBookService;
import com.econovation.rere.service.ThemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class CardBookController {

    private final CardBookService cardBookService;
    private final ThemeService themeService;

//    생성
    @PostMapping("/cardbook")
    public ApiResult<CardBookResponseDTO> createCardBook(@RequestBody CardBookCreateRequestDTO  cardBookCreateRequestDTO, Integer userId){
        CardBookResponseDTO cardBookResponseDTO = cardBookService.register(cardBookCreateRequestDTO, 2);
        log.info(cardBookResponseDTO+"");
        return ApiUtils.success(
                cardBookResponseDTO
                ,"카드북이 생성되었습니다."
        );
    }

//    수정
    @PutMapping("/cardbook")
    public ApiResult<CardBookResponseDTO> modifyCardBook(@RequestBody CardBookUpdateRequestDTO cardBookUpdateRequestDTO, Integer userId){
        CardBookResponseDTO cardBookResponseDTO = cardBookService.modify(cardBookUpdateRequestDTO);
        return ApiUtils.success(
                cardBookResponseDTO
                ,"카드북이 수정되었습니다."
        );
    }

//    삭제
    @DeleteMapping("/cardbook")
    public ApiResult<Boolean> removeCardBook(@RequestBody CardBookRemoveRequestDTO cardBookRemoveRequestDTO){
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
    public ApiResult<List<CardBookResponseDTO>> mainpageCardBook(){
        List<CardBookResponseDTO> cardBookResponseDTOS = cardBookService.getDefaultCardbook();
        return ApiUtils.success(cardBookResponseDTOS, "메인 페이지 카드북 목록입니다.");
    }

//    새로운 목차 생성
    @PostMapping("/cardbook/{cardbookId}/theme/new")
    public void createTheme(@RequestBody ThemeCreateRequestDTO themeCreateRequestDTO, @PathVariable("cardbookId") Integer cardbookId){
        //log.info("cardbookId" + cardbookId);
        log.info(""+themeService.register(themeCreateRequestDTO, cardbookId));
    }

//    해당 카드북의 모든 목차 가져오기
    @GetMapping("/cardbook/{cardbookId}/themes")
    public void themepageThemes(@PathVariable Integer cardbookId){
        themeService.getAll(cardbookId).forEach(theme -> log.info(""+theme));
    }

//    목차,카드 수정하기
    @PutMapping("/cardbook/{cardbookId}/theme/{themeId}")
    public void modifyThemeAndCards(@RequestBody ThemeUpdateRequestDTO themeUpdateRequestDTO, @PathVariable Integer themeId){
        log.info(themeService.modify(themeUpdateRequestDTO, themeId)+"");
    }
}
