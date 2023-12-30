package com.econovation.rere.repository;

import com.econovation.rere.entity.CardBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardBookRepository extends JpaRepository<CardBook, Integer> {
//    CardBookRepository는 레포지터리로 만들기 위해 JpaRepository 인터페이스를 상속하고,
//    JpaRepository를 상속할 때는 제네릭 타입으로 CardBook(엔티티 타입), Integer(PK 타입)를 지정한다.
//
}
