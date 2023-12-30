package com.econovation.rere.repository;

import com.econovation.rere.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

//    스프링 DI 방식에는 필드 주입, 생성자 주입 등이 있는데
//    순환 참조와 같은 이유로 생성자 주입이 권장된다.
//    하지만 테스트 코드에서는 생성자 주입을 사용할 수 없으므로
//    @Autowired를 통한 필드 주입 방식을 사용한다.
    @Autowired
    private UserRepository userRepository;

    @Test
    void testJpa(){
//        user1 생성
        User user1 = new User();

//        user1에 대한 정보 입력
//        각 Entity에 대한 정보 입력은 추후 생성자로 수정할 예정
        user1.setNickname("KimJongMin");
        user1.setLoginId("rere");
        user1.setPw("1234");
        user1.setUserId(1);
        this.userRepository.save(user1);

    }

}
