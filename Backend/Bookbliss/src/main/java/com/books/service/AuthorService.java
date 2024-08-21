package com.books.service;

import java.util.List;

import com.books.entities.Author;
import com.books.exception.AuthorException;

public interface AuthorService {

	List<Author> getAllAuthors();
	
	Author addAuthor(Author author);
	
	Author updateAuthor(Author author) throws AuthorException;
	
	Author deleteById(Long id);
}
