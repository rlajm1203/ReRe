package com.econovation.rere.domain.repository;

import com.econovation.rere.domain.entity.Card;
import com.econovation.rere.domain.entity.CardBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
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
    List<CardBook> findByName(String Name);

//    메인 페이지용 메소드
//    List<CardBook> findAllOrderByScrapCnt();

//    검색 결과용 메소드 (카드북 이름이 완전히 일치해야 함)
    List<CardBook> findByNameOrderByScrapCnt(String name);

//    검색 결과용 메소드 (카드북 이름에 키워드 일부분이 포함되어도 가능)
    List<CardBook> findByNameContainingOrderByScrapCnt(String name);
 
//    CardBook의 제목과 작성자, 두 개의 조건으로 조회하기 위한 메소드
    Optional<CardBook> findByNameAndWriter(String Name, String Writer);

//    관리자 카드북, 내가 작성한 카드북을 찾을 때 사용한다.
    List<CardBook> findByWriter(String writer);

//    정상적으로 삭제되면 true, 정상적이지 않으면 false
    Integer deleteByCardbookId(Integer cardbookId);


}
