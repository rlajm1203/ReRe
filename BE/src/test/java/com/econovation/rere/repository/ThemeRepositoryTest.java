package com.econovation.rere.repository;

import com.econovation.rere.domain.entity.Theme;
import com.econovation.rere.domain.repository.ThemeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ThemeRepositoryTest {

    @Autowired
    private ThemeRepository repository;

    @Test
    void testThemeInsert(){
        Theme theme = new Theme();
        LocalDateTime time = LocalDateTime.now();
        theme.setName("SW 응용 기초");

//        theme과 cardbook은 의존 관계가 존재하는데,
//        어떻게 설정하지?
//        CardBookRepository의 findAll()을 이용해서
//        CardBook Name과 일치하는 곳에 cardbook_cardbook_id 를 삽입하면 될 듯?
//        theme.setCardbook();

        theme.setCreateDate(time);
        theme.setUpdateDate(time);

        this.repository.save(theme);

    }
}
