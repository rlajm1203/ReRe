package com.econovation.rere.domain.repository;

import com.econovation.rere.domain.entity.Card;
import com.econovation.rere.domain.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {

//    Theme에 딸린 카드들 가져오기
    public List<Card> findAllByTheme(Theme theme);

    public Integer deleteByCardId(Integer CardId);

//    리턴 타입이 Integer인 이유는 삭제된 개수를 반환하기 때문이다.
//    따라서 0보다 큰지만 확인하면 될 듯
    public Integer deleteAllByTheme(Theme theme);

    public Integer countAllByTheme(Theme theme);

//    카드 ID로 카드 가져오기
    public Optional<Card> findByCardId(Integer cardId);
}
