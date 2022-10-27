package com.tecpal.interview.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecpal.interview.model.Book;
import com.tecpal.interview.service.BookService;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

	@MockBean 
	BookService bookService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void getBook_success() throws Exception {
		when(bookService.getBook(1L)).thenReturn(Optional.of(Book.builder().build()));
		mockMvc.perform(get("/api/books/1")).andExpect(status().isOk());
	}
	
	@Test
	void getBook_fail() throws Exception {
		when(bookService.getBook(1L)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/books/1")).andExpect(status().isNotFound());
	}
	
	@Test
	void getBooks() throws Exception {
		when(bookService.getBooks(any(), any(), any(), any())).thenReturn(List.of());
		mockMvc.perform(get("/api/books")).andExpect(status().isOk());
	}
	
	@Test
	void createBook() throws Exception {
		Book book = Book.builder()
				.author("test author")
				.description("test description")
				.title("test title")
				.publishDate(null).build();
		when(bookService.createBook(book)).thenReturn(book);
		mockMvc.perform(post("/api/books").content(new ObjectMapper().writeValueAsString(book)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}
	
	@Test
	void deleteBook_success() throws Exception {
		when(bookService.isBookExist(1L)).thenReturn(true);
		doNothing().when(bookService).deleteBook(1L);
		mockMvc.perform(delete("/api/books/1")).andExpect(status().isNoContent());
	}
	
	@Test
	void deleteBook_fail() throws Exception {
		when(bookService.isBookExist(1L)).thenReturn(false);
		mockMvc.perform(delete("/api/books/1")).andExpect(status().isNotFound());
	}
	
}
