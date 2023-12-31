package com.econovation.rere.repository;

import com.econovation.rere.domain.entity.User;
import com.econovation.rere.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {

//    스프링 DI 방식에는 필드 주입, 생성자 주입 등이 있는데
//    순환 참조와 같은 이유로 생성자 주입이 권장된다.
//    하지만 테스트 코드에서는 생성자 주입을 사용할 수 없으므로
//    @Autowired를 통한 필드 주입 방식을 사용한다.
    @Autowired
    private UserRepository userRepository;

    @Test
    void testUserInsert(){
//        user1 생성

//        DB에 이미 있는 값을 넣으면 에러 발생
        User user1 = new User("rere1", "1q2w3e4r!", "econovation");

//        user1에 대한 정보 입력
//        각 Entity에 대한 정보 입력은 추후 생성자로 수정할 예정
//        user1.setUserId(3);
//        User 엔티티의 Primary Key는 userId 인데, 작성하지 않아도 자동으로 값이 1씩 증가한다.
        this.userRepository.save(user1);
    }

    @Test
    void testUserDelete(){
        Optional<User> ou = this.userRepository.findById(2);

        if(ou.isPresent()){
            User user = ou.get();
            this.userRepository.delete(user);
        }
    }
}
