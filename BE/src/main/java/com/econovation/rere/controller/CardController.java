package com.econovation.rere.controller;

import com.econovation.rere.apiresponse.ApiResult;
import com.econovation.rere.apiresponse.ApiUtils;
import com.econovation.rere.domain.dto.response.CardResponseDTO;
import com.econovation.rere.domain.dto.response.StudyCompleteResponseDTO;
import com.econovation.rere.domain.entity.Card;
import com.econovation.rere.service.CardService;
import com.econovation.rere.service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class CardController {

    private final CardService cardService;
    private final StudyService studyService;

//  목차에 속한 카드들을 조회하기
    @GetMapping("/cardbook/{cardbookId}/theme/{themeId}/cards")
    public ApiResult<List<CardResponseDTO>> cardpageCards(@PathVariable("cardbookId") Integer cardbookId, @PathVariable("themeId") Integer themeId){
        List<Card> cards = cardService.getAll(themeId);
        return ApiUtils.success(CardResponseDTO.toCardResponseDTOS(cards),themeId+"목차의 카드 목록입니다.");
    }

//    학습을 완료하기
    @PostMapping("/cardbook/{cardbookId}/theme/{themeId}/cards")
    public ApiResult<StudyCompleteResponseDTO> studyComplete(@PathVariable("cardbookId") Integer cardbookId, @PathVariable("themeId") Integer themeId){
        StudyCompleteResponseDTO studyCompleteResponseDTO = studyService.studyComplete(cardbookId,themeId,1);
        String message = "학습이 완료되었습니다." + " 다음은 Step "+studyCompleteResponseDTO.getStep() % 5 + " 을 학습할 단계입니다.";
        return ApiUtils.success(studyCompleteResponseDTO, message);
    }


}
