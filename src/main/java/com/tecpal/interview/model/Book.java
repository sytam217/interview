package com.tecpal.interview.model;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long bookID;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "publish_date")
	private LocalDate publishDate;
	
	public Long getBookId() {
		return bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

}
