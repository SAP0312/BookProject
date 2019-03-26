package com.mockitoTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bookCRUD.BookAPIApp;
import com.bookCRUD.entities.Book;
import com.bookCRUD.services.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=BookAPIApp.class)
//@WebMvcTest(value=BookController.class,secure=false)
public class BookTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	@Qualifier("bookCRUDService")
	private BookService bookService;
	
	@Before
	public void initialize() {
	
	Book book = new Book(9446764,"Language","Title","Publisher","Genre","Author");
	}
	@Test
	public void retrieveDetailsOfBook() throws Exception {
		Book bookOptional = null;
		Mockito.when(bookService.getBookbyId(Mockito.anyInt())).thenReturn(bookOptional);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("books/944676").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse());
		
		String expectedString = "{id:9446764,title:Languag,author:Title,publisher:Publisher,language:Genre,category:Author}";
			
		JSONAssert.assertEquals(expectedString, result.getResponse().getContentAsString(), false);
		
	}
    
	
	
	

}
