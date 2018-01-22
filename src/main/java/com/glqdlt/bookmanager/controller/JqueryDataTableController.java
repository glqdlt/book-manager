package com.glqdlt.bookmanager.controller;

import com.glqdlt.bookmanager.persistence.entity.BookEntity;
import com.glqdlt.bookmanager.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class JqueryDataTableController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value = "/jquery/data",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> jqueryAll(@RequestParam int draw, @RequestParam int length){
        Map<String,Object> map = new HashMap<>();
        Pageable pageable = new PageRequest(draw, length, new Sort(Sort.Direction.DESC, "no"));
        Page<BookEntity> entityPage = bookRepository.findAll(pageable);
        List<BookEntity> data = new ArrayList<>();
        entityPage.forEach(x -> data.add(x));
        map.put("draw", draw);
        map.put("recordsFiltered",entityPage.getTotalElements());
        map.put("recordsTotal",entityPage.getTotalPages());
        map.put("data",data);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
