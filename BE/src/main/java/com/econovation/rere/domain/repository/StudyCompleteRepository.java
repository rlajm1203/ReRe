package com.econovation.rere.domain.repository;

import com.econovation.rere.domain.entity.CardBook;
import com.econovation.rere.domain.entity.StudyComplete;
import com.econovation.rere.domain.entity.Theme;
import com.econovation.rere.domain.entity.UserCardBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyCompleteRepository extends JpaRepository<StudyComplete, Integer> {

//  사용자가 이미 학습을 했는지 하지 않았는지 체크하기 위한 용도
    public boolean existsByUserCardBookAndTheme(UserCardBook userCardBook, Theme theme);

//  사용자가 이미 학습을 했을 경우 학습 내역을 가져오는 용도
    public Optional<StudyComplete> findByUserCardBookAndTheme(UserCardBook userCardBook, Theme theme);
}
