package com.econovation.rere.domain.repository;

import com.econovation.rere.domain.entity.Card;
import com.econovation.rere.domain.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {

//    Theme에 딸린 카드들 가져오기
    public List<Card> findAllByTheme(Theme theme);

}
