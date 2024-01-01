package com.econovation.rere.domain.repository;

import com.econovation.rere.domain.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
