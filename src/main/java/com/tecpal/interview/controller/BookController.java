package com.tecpal.interview.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecpal.interview.model.Book;
import com.tecpal.interview.service.BookService;


@RestController
@RequestMapping("/api")
public class BookController {

	private BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/books/{bookID}")
	public ResponseEntity<Book> getBook(@PathVariable(value = "bookID") Long bookID) {
		Optional<Book> res = bookService.getBook(bookID);
		if(res.isPresent() ) {
			return new ResponseEntity<>(res.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks(@RequestParam(name = "title", required = false) String title,
											   @RequestParam(name = "description", required = false) String description,
											   @RequestParam(name = "author", required = false) String author,
											   @RequestParam(name = "publishDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishDate) {
		List<Book> res = bookService.getBooks(title, description, author, publishDate);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		Book res = bookService.createBook(book);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/books/{bookID}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable(value = "bookID") Long bookID) {
		if(bookService.isBookExist(bookID)) {
			bookService.deleteBook(bookID);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
