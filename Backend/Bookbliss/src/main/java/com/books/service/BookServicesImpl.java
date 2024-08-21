package com.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.books.dto.BookDTO;
import com.books.entities.Book;
import com.books.repository.BookRepository;

@Service
@Transactional
public class BookServicesImpl implements BookServices{

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> getAll()
	{
		return bookRepository.findAll();
	}
	
//	@Override
//	public Book updateBook(Long id, Book bookDetails)
//	{
//		Optional<Book> optionalBook = bookRepository.findById(id);
//		if(optionalBook.isPresent())
//		{
//			Book book = optionalBook.get();
//			book.setBookName(bookDetails.getBookName());
//			book.setAuthor(bookDetails.getAuthor());
//			book.setDescription(book.getDescription());
//			book.setPrice(bookDetails.getPrice());
//			return bookRepository.save(book);
//		}
//		else
//		{
//			throw new RuntimeException("Book not found with id"+id);
//		}
//	}
	
	 public Book findBookById(long id) {
	        return bookRepository.findById(id).orElse(null);
	    }

	    public Book addBook(Book book) {
	        return bookRepository.save(book);
	    }
	
	@Override
	public Book removeBook(Long id)
	{
		Optional<Book> optionalBook= bookRepository.findById(id);
		if(optionalBook.isPresent())
		{
			bookRepository.deleteById(id);
			return optionalBook.get();
		}
		else
		{
			throw new RuntimeException("Book not found with id"+id);
		}
		
	}


	  public void updateBook(Long id, BookDTO bookUpdateDTO) {
	        Book book = findBookById(id);
	        if (book != null) {
	            // Update only the fields provided
	            if (bookUpdateDTO.getPrice() != null) {
	                book.setPrice(bookUpdateDTO.getPrice());
	            }
	            if (bookUpdateDTO.getQuantity() != null) {
	                book.setQuantity(bookUpdateDTO.getQuantity());
	            }
	            addBook(book);
	        }
	    }

	@Override
	public Book updateBook(long id, BookDTO book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> searchByAuthor(String name) 
	{
		return bookRepository.searchByAuthor(name);
	}
	
}
