package com.econovation.rere.controller;

import com.econovation.rere.apiresponse.ApiResult;
import com.econovation.rere.apiresponse.ApiUtils;
import com.econovation.rere.config.CurrentUser;
import com.econovation.rere.domain.dto.response.CardPageResponseDTO;
import com.econovation.rere.domain.dto.response.CardResponseDTO;
import com.econovation.rere.domain.dto.response.StudyCompleteResponseDTO;
import com.econovation.rere.domain.entity.Card;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.exception.NotAthenticationException;
import com.econovation.rere.service.CardBookService;
import com.econovation.rere.service.CardService;
import com.econovation.rere.service.StudyService;
import com.econovation.rere.service.ThemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class CardController {

    private final CardService cardService;
    private final StudyService studyService;
    private final ThemeService themeService;
    private final CardBookService cardBookService;

//  목차에 속한 카드들을 조회하기
    @GetMapping("/cardbook/{cardbookId}/theme/{themeId}/cards")
    public ApiResult<List<CardResponseDTO>> cardpageCards(@PathVariable("cardbookId") Integer cardbookId, @PathVariable("themeId") Integer themeId){
        log.info("카드 조회 요청 (CardbookID) : "+cardbookId + ", (ThemeID) : "+themeId);
        List<Card> cards = cardService.getAll(themeId);
        String name = themeService.getThemeById(themeId).getName();
        return ApiUtils.success(CardResponseDTO.toCardResponseDTOS(cards),name+" 목차의 카드 목록입니다.");
    }

//    학습을 완료하기
    @PostMapping("/cardbook/{cardbookId}/theme/{themeId}/cards")
    public ApiResult<StudyCompleteResponseDTO> studyComplete(@CurrentUser User user, @PathVariable("cardbookId") Integer cardbookId, @PathVariable("themeId") Integer themeId){
        log.info("학습 완료 요청 (CardbookID) : "+cardbookId+", (ThemeID) : "+themeId+", (UserID) : " + user.getUserId());
        StudyCompleteResponseDTO studyCompleteResponseDTO = studyService.studyComplete(cardbookId,themeId, user.getUserId());
        String message = "학습이 완료되었습니다." + " 다음은 Step "+studyCompleteResponseDTO.getStep() % 5 + " 을 학습할 단계입니다.";
        return ApiUtils.success(studyCompleteResponseDTO, message);
    }

//    카드 삭제하기
    @DeleteMapping("/cardbook/{cardbookId}/theme/{themeId}/card/{cardId}")
    public ApiResult<Boolean> deleteCard(@CurrentUser User user, @PathVariable("cardbookId") Integer cardbookId, @PathVariable("themeId") Integer themeId, @PathVariable("cardId") Integer cardId){
        log.info("카드 삭제 요청 (CardbookID) : "+cardbookId+", (ThemeID) : "+themeId +", (CardID) : "+cardId+", (UserID) : " + user.getUserId());
        if(!cardBookService.getCardbook(cardbookId).getWriter().equals(user.getNickname())) throw new NotAthenticationException("카드북 작성자가 아닙니다.");
        return ApiUtils.success(cardService.remove(cardId), "카드 삭제가 완료되었습니다.");
    }

//


}
