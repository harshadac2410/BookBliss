package com.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.books.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{
	 List<Book> findAll();
	 
	// Example to search by author name
	    @Query("SELECT b FROM Book b WHERE b.author.aname = :authorName")
	    List<Book> searchByAuthor(@Param("authorName") String authorName);

}
