package com.books.service;

import com.books.dto.BookDTO;
import com.books.entities.Book;

import java.util.List;

public interface BookServices 
{
	Book addBook(Book book);
	
	List<Book> getAll();
	
	Book removeBook(Long id);
	
	Book updateBook(long id,BookDTO  book);
//	List<Book> SortBook();
	
	Book findBookById(long id);
	
	List<Book> searchByAuthor(String name);
	
}
