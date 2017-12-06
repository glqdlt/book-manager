package com.glqdlt.bookmanager.controller;


import com.glqdlt.bookmanager.persistence.entity.BookEntity;
import com.glqdlt.bookmanager.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    /**
     * 토이 프로젝트라서 서비스 없이 바로 persistence에서 접근해서 조회,
     */
    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value="/book/search/all", method= RequestMethod.GET)
    public ResponseEntity<List<BookEntity>> bookSearch(){
        List<BookEntity> list = bookRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/write", method = RequestMethod.PUT)
    public ResponseEntity<Integer> bookWrite(){
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
