package com.glqdlt.bookmanager.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

public class BookControllerTest {

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(BookController.class).build();
    }

    @Test
    public void listBook() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/book/search/all")).andReturn();
    }

    @Test
    public void createBook() {
    }

    @Test
    public void bookConfig() {
    }
}