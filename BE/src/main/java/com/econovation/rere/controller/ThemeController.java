package com.econovation.rere.controller;

import com.econovation.rere.apiresponse.ApiResult;
import com.econovation.rere.apiresponse.ApiUtils;
import com.econovation.rere.config.CurrentUser;
import com.econovation.rere.domain.dto.request.CardbookThemesResponseDTO;
import com.econovation.rere.domain.dto.request.ThemeCreateRequestDTO;
import com.econovation.rere.domain.dto.request.ThemeUpdateRequestDTO;
import com.econovation.rere.domain.dto.response.ThemePageResponseDTO;
import com.econovation.rere.domain.dto.response.ThemeResponseDTO;
import com.econovation.rere.domain.entity.Theme;
import com.econovation.rere.domain.entity.User;
import com.econovation.rere.exception.NotAthenticationException;
import com.econovation.rere.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class ThemeController {

    private final CardBookService cardBookService;
    private final ThemeService themeService;
    private final UserCardBookService userCardBookService;
    private final StudyService studyService;
    private final CardService cardService;

    //    새로운 목차 생성
    @PostMapping("/cardbook/{cardbookId}/theme")
    public ApiResult<Boolean> createTheme(@CurrentUser User user, @RequestBody @Valid ThemeCreateRequestDTO themeCreateRequestDTO, @PathVariable("cardbookId") Integer cardbookId){

        log.info("사용자 : "+user.getNickname()+", 목차 생성 : "+themeCreateRequestDTO.getName());
        if(!cardBookService.getCardbook(cardbookId).getWriter().equals(user.getNickname())) throw new NotAthenticationException("카드북 작성자가 아닙니다.");
        return ApiUtils.success(themeService.register(themeCreateRequestDTO, cardbookId),"목차 생성에 성공하였습니다.");
    }

    //    해당 카드북의 모든 목차 가져오기
    @GetMapping("/cardbook/{cardbookId}/themes")
    public ApiResult<CardbookThemesResponseDTO> themepageThemes(@CurrentUser User user, @PathVariable Integer cardbookId){
//        log.info("사용자 : "+user.getNickname()+", 목차 조회 (CardbookID) : "+cardbookId);
//        이 서비스를 실행하는 순간, 학습 시간을 체크하는 스레드가 실행
//        studyService.studyTimeCheck(cardbookId, user.getUserId());

        CardbookThemesResponseDTO responseDTO = new CardbookThemesResponseDTO();
        String cardbookName = cardBookService.getCardbook(cardbookId).getName();
        log.info("목차 조회 (cardbookName) : " + cardbookName);
        responseDTO.setCardbookName(cardbookName);

        // themes 설정
        List<ThemeResponseDTO> themes;
        if (user == null) { themes = themeService.getAllNotLogin(cardbookId); }
        else {
            if (userCardBookService.checkExistsInUserCardBook(user.getUserId(), cardbookId))
                themes = themeService.getAll(cardbookId, user.getUserId());
            else
                throw new NotAthenticationException("카드북을 담지 않았습니다.");
        }
        responseDTO.setThemes(themes);
        return ApiUtils.success(responseDTO, "목차 조회에 성공하였습니다.");
    }

    //    목차,카드 수정하기
    @PutMapping("/cardbook/{cardbookId}/theme/{themeId}")
    public ApiResult<Boolean> updateThemeAndCards(@CurrentUser User user, @RequestBody @Valid ThemeUpdateRequestDTO themeUpdateRequestDTO, @PathVariable("cardbookId") Integer cardbookId, @PathVariable("themeId") Integer themeId){
        log.info("사용자 : "+user.getNickname()+", 목차 수정 (themeID): "+themeId);
        if(!cardBookService.getCardbook(cardbookId).getWriter().equals(user.getNickname())) throw new NotAthenticationException("카드북 작성자가 아닙니다.");
        return ApiUtils.success(themeService.update(themeUpdateRequestDTO, themeId),"목차가 수정되었습니다.");
    }

//    목차 삭제하기
    @DeleteMapping("/cardbook/{cardbookId}/theme/{themeId}")
    public ApiResult<Boolean> deleteThemeAndCards(@CurrentUser User user, @PathVariable("cardbookId") Integer cardbookId, @PathVariable("themeId") Integer themeId){
        log.info("사용자 : "+user.getNickname()+", 목차 삭제 (themeID : "+themeId);
        if(!cardBookService.getCardbook(cardbookId).getWriter().equals(user.getNickname())) throw new NotAthenticationException("카드북 작성자가 아닙니다.");
        return ApiUtils.success(themeService.remove(themeId), "목차가 삭제되었습니다.");
    }
}