package com.glqdlt.bookmanager.controller;


import com.glqdlt.bookmanager.persistence.entity.BookEntity;
import com.glqdlt.bookmanager.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/")
    public String home(Model model){

        List<BookEntity> list = bookRepository.findAll();

        model.addAttribute("list", list);
        return "index";
    }

    @RequestMapping("/write")
    public String search(){
        return "write";
    }

}
