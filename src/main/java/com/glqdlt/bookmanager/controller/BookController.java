package com.glqdlt.bookmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("/book")
public class BookController {

    @RequestMapping(value = "/data-grid",method = RequestMethod.GET)
    public String dataGrid(){
        return "book/data-grid";
    }

    @RequestMapping(value ="/new-item",method = RequestMethod.GET)
    public String newItem(){
        return "book/new-item";
    }
}
