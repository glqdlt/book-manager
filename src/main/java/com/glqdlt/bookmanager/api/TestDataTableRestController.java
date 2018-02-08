package com.glqdlt.bookmanager.api;

import com.glqdlt.bookmanager.persistence.entity.BookEntity;
import com.glqdlt.bookmanager.persistence.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Slf4j
@RestController
@CrossOrigin
public class TestDataTableRestController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value = "/jquery/data/", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> jqueryAll(@RequestParam int draw, @RequestParam int length,
                                                         @RequestParam int start, @RequestParam Object searchForm) {
        int page = 0;
        if (start != 0) {
            page = (start / length);
        }
        Map<String, Object> map = new HashMap<>();
        Pageable pageable = new PageRequest(page, length, new Sort(Sort.Direction.DESC, "no"));
        Page<BookEntity> entityPage = bookRepository.findAll(pageable);
        List<BookEntity> data = new ArrayList<>();
        entityPage.forEach(x -> data.add(x));

        log.debug("totalPages:"+entityPage.getTotalPages()+", total elements: "+entityPage.getTotalElements());
        map.put("draw", draw);
        map.put("recordsFiltered", entityPage.getTotalPages());
        map.put("recordsTotal", entityPage.getTotalElements());
        map.put("data", data);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
