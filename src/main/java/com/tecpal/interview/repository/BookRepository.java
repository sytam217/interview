package com.tecpal.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.tecpal.interview.model.Book;

@Repository 
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book>{

}
