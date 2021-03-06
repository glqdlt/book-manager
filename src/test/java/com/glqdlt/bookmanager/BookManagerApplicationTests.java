package com.glqdlt.bookmanager;

import com.glqdlt.bookmanager.api.BookRestController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@Slf4j
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookManagerApplicationTests {


	@Autowired
	@InjectMocks
	private BookRestController bookRestController;
	private MockMvc mockMvc;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bookRestController).build();
	}

	@Test
	public void contextLoads() {
	}

//	@Test
	public void BookSearchAll() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/book/search/all")).andReturn();
		MockHttpServletResponse mockHttpServletResponse =result.getResponse();
		log.info(mockHttpServletResponse.getContentType());
		log.info(mockHttpServletResponse.getContentAsString());

		Assert.assertEquals("application/json;charset=UTF-8",mockHttpServletResponse.getContentType());
		Assert.assertNotSame(0,mockHttpServletResponse.getContentAsString().length());
	}

//	@Test
	public void BookSearchPage() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/book/search/1")).andReturn();
		MockHttpServletResponse mockHttpServletResponse =result.getResponse();
		log.info(mockHttpServletResponse.getContentAsString());

	}

}
