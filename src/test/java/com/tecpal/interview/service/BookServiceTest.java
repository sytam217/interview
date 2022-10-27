package com.tecpal.interview.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecpal.interview.model.Book;
import com.tecpal.interview.repository.BookRepository;

@SpringBootTest
public class BookServiceTest {

	private BookService bookService;
	
	@Autowired
	BookRepository bookRepository;
	
	@BeforeEach
	void init() {
		bookService = new BookService(bookRepository);
	}
	
	@Test
	void getBook() {
		Book expected = Book.builder()
				.author("Test author 2")
				.bookID(2L)
				.description("This is book 2")
				.title("Book 2")
				.publishDate(LocalDate.of(2022, 10, 28))
				.build();
		Optional<Book> actual = bookService.getBook(2L);
		assertEquals(expected.getBookId(), actual.get().getBookId());
		assertEquals(expected.getPublishDate(), actual.get().getPublishDate());
	}
	
	@Test
	void getBooks() {
		List<Book> expected = List.of(
			Book.builder()
			.author("Test author")
			.bookID(2L)
			.description("This is story book 2")
			.title("Story Book 2")
			.publishDate(LocalDate.of(2022, 10, 28))
			.build()
		);
		List<Book> actual = bookService.getBooks("Story", "story", "author", LocalDate.of(2022, 10, 28));
		assertEquals(Set.copyOf(expected), Set.copyOf(actual));
	}
	
	@Test
	void createBook() {
		Book create = Book.builder()
				.author("Author create")
				.description("Create case")
				.title("Create case")
				.publishDate(LocalDate.of(2022, 10, 28))
				.build();
		assertDoesNotThrow(() -> bookService.createBook(create));
	}
	
	@Test 
	void deleteBook() {
		assertDoesNotThrow(() -> bookService.deleteBook(3L));
	}
	
	@Test
	void isBookExist() {
		Boolean actual1 = bookService.isBookExist(4L);
		assertEquals(true, actual1);
		Boolean actual2 = bookService.isBookExist(5L);
		assertEquals(false, actual2);
	}
}
