package com.glqdlt.bookmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/book")
@Controller
public class BookController {

    private static final Logger log =
            LoggerFactory.getLogger(BookController.class);


    @RequestMapping(value = "/grid",method = RequestMethod.GET)
    public String dataGrid(){
        return "book/dataGrid";
    }

    @RequestMapping(value ="/new",method = RequestMethod.GET)
    public String newItem(){
        log.debug("call book new");
        return "book/newBook";
    }
}
