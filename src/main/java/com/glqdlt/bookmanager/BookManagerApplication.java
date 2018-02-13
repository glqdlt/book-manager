package com.glqdlt.bookmanager;

import com.glqdlt.bookmanager.persistence.entity.BookEntity;
import com.glqdlt.bookmanager.persistence.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@PropertySource("classpath:config.properties")
@Slf4j
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
        List<BookEntity> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            BookEntity book = new BookEntity();
            book.setSubject("subject"+i);
            book.setAuthor("author");
            book.setBook_type(1);
            book.setFile_path("file_path");
            book.setFile_orign_name("file_original.pdf");
            book.setFuture_date(new Date());
            book.setReg_date(new Date());
            book.setRead_status(1);
            list.add(book);
        }
        bookRepository.save(list);
    }
}
