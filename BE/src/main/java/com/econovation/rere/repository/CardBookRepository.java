package com.econovation.rere.repository;

import com.econovation.rere.entity.CardBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardBookRepository extends JpaRepository<CardBook, Integer> {
//    CardBookRepository는 레포지터리로 만들기 위해 JpaRepository 인터페이스를 상속하고,
//    JpaRepository를 상속할 때는 제네릭 타입으로 CardBook(엔티티 타입), Integer(PK 타입)를 지정한다.


    /*
    CardBookRepository가 인터페이스이고 findByName은 구현도 하지 않았는데 어떻게 실행되지 라는 의문이 생기는데

    이는 JpaRepository를 상속한 CardBookRepository 객체가 생성될 때 벌어진다.
    (DI에 의해 스프링이 자동으로 CardBookRepository 객체를 성생하고, 이때 프록시 패턴이 사용됨)
    Repository 객체의 메소드가 실행될 때 JPA가 해당 메소드명을 분석하여 쿼리를 만들고 실행한다.

    즉, JPA는 메소드 이름을 분석하여 쿼리를 생성하므로 "findBy + 엔티티 속성명"과 같은 메소드를 작성하면 해당 속성 값으로 데이터를 조회할 수 있는 것이다.
    */


//    CardBook 엔티티의 Name 값으로 데이터를 조회하기 위한 메소드
    Optional<CardBook> findByName(String Name);


}
