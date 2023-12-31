package com.econovation.rere.service;


import com.econovation.rere.entity.CardBook;
import com.econovation.rere.repository.CardBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CardBookService {

    private final CardBookRepository cardBookRepository;

    public CardBook create(String name, String writer, LocalDateTime createDate){
        CardBook cardBook = new CardBook(name, writer, createDate);
        cardBookRepository.save(cardBook);

        return cardBook;
    }
}
