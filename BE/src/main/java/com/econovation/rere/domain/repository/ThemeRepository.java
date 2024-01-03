package com.econovation.rere.domain.repository;

import com.econovation.rere.domain.entity.CardBook;
import com.econovation.rere.domain.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {

//    Theme 삭제를 위한 메소드
    public Integer deleteByThemeId(Integer themeId);

    public Optional<Theme> findByCardbookAndThemeId(Integer cardbookId, Integer themeId);

    public List<Theme> findAllByCardbook(CardBook cardBook);

}
