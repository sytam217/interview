package com.tecpal.interview.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tecpal.interview.model.Book;
import com.tecpal.interview.repository.BookRepository;

@Service
public class BookService {
	
	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public Optional<Book> getBook(Long bookID) {
		Optional<Book> book = bookRepository.findById(bookID);
		return book;
	}
	
	public void deleteBook(Long bookID) {
		bookRepository.deleteById(bookID);
	}
	
	public boolean isBookExist(Long bookID) {
		return bookRepository.existsById(bookID);
	}
	
	public List<Book> getBooks(String title,
			  				   String description,
			  				   String author,
			  				   LocalDate publishDate) {
		Specification<Book> spec = (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if(author != null) {
				predicates.add(criteriaBuilder.like(root.get("author"), "%" + author + "%"));
			}
			if(publishDate != null) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("publishDate"), publishDate));
			}
			if(description != null) {
				predicates.add(criteriaBuilder.like(root.get("description"), "%" + description + "%"));
			}
			if(title != null) {
				predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
		
		List<Book> res = bookRepository.findAll(spec);
		return res;
	}
	
	public Book createBook(Book request) {
		Book res = bookRepository.save(request);
		return res;
	}
}
