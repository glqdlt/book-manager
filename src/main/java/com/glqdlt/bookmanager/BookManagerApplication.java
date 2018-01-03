package com.glqdlt.bookmanager;

import com.glqdlt.bookmanager.persistence.entity.BookEntity;
import com.glqdlt.bookmanager.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ComponentScan(basePackages = "com.glqdlt.bookmanager.*")
@SpringBootApplication
public class BookManagerApplication implements CommandLineRunner {

    @Autowired
    BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookManagerApplication.class, args);
    }

    @Override
    public void run(String... strings) {

//         아래는 테스트 코드
        List<BookEntity> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(
                    new BookEntity(
                            "subject" + i, "author" + i, 2, "note" + i, "path" + i, "server" + i,
                            "admin" + i, new Date(), new Date(), new Date(), 1, "thumb", "review"));
        }
        bookRepository.save(list);
    }
}
