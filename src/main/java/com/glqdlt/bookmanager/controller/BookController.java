package com.glqdlt.bookmanager.controller;

import com.glqdlt.bookmanager.persistence.entity.BookEntity;
import com.glqdlt.bookmanager.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class BookController {

    private static final Integer PAGE_COUNT = 10;
    /**
     * 토이 프로젝트라서 서비스 없이 바로 접근해서 조회,
     */
    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value = "/book/search/all", method = RequestMethod.GET)
    public ResponseEntity<List<BookEntity>> bookSearch() {
        List<BookEntity> list = bookRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/search/{page}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> bookSearchPage(@PathVariable Integer page) {
        Pageable pageable = new PageRequest(page, PAGE_COUNT);
        Page<BookEntity> p1 = bookRepository.findAll(pageable);

        List<BookEntity> list = new ArrayList<>();
        // 이것은 좋지 않다. 테스트용이니 확인되면 TODO hashmap 쓰지마라
        Map<String, Object> map = new HashMap<>();
            p1.forEach(x -> list.add(x));
        map.put("count", p1.getTotalPages());
        map.put("data", list);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/write", method = RequestMethod.PUT)
    public ResponseEntity<Integer> bookWrite() {
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
