package com.glqdlt.bookmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@RequestMapping("/book")
@Controller
public class BookController {

    private final String VIEW_PREFIX = "book/";

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String listBook(){
        return VIEW_PREFIX +"book_list";
    }

    @RequestMapping(value ="/new",method = RequestMethod.GET)
    public String createBook(){
        return VIEW_PREFIX +"book_create";
    }


    @RequestMapping(value = "/config",method=RequestMethod.GET)
    public String bookConfig(){
        return VIEW_PREFIX +"config";
    }
}
