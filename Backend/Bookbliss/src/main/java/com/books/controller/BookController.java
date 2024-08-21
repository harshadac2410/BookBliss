package com.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.books.dto.BookDTO;
import com.books.entities.Book;
import com.books.service.BookServices;

@CrossOrigin
@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookServices bookServices;
	
//	@GetMapping
//	public ResponseEntity<?> getAll()
//	{
//		List<Book> books = bookServices.getAll();
//		return ResponseEntity.ok(books);
//	}
	@GetMapping
    public List<Book> getAllBooks() {
        return bookServices.getAll();
    }

	
	@PostMapping
	public ResponseEntity<?> addBook(@RequestBody Book book)
	{
		try 
		{
			Book books = bookServices.addBook(book);
			return new ResponseEntity<>(book, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
//	@PutMapping("/book/{id}")
//	public ResponseEntity<?> updateBook(@PathVariable long id,@RequestBody Book book){
//		try 
//		{
//			bookServices.updateBook(id, book);
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		catch(Exception e) 
//		{
//			return new ResponseEntity<>("An unexpected error occurred..",HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable long id, @RequestBody BookDTO bookUpdateDTO) {
        try {
            Book book = bookServices.findBookById(id);
            if (book == null) {
                return new ResponseEntity<>("Book not found.", HttpStatus.NOT_FOUND);
            }

            // Update only the fields that are present in the DTO
            book.setPrice(bookUpdateDTO.getPrice());
            book.setQuantity(bookUpdateDTO.getQuantity());

            bookServices.addBook(book); // Save the updated book
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		try 
		{
			Book books = bookServices.removeBook(id);
			return new ResponseEntity<>(books,HttpStatus.OK);
		}
		catch(Exception be) 
		{
			return new ResponseEntity<>(be.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			Book book = bookServices.findBookById(id);
			return new ResponseEntity<>(book,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/author/{aname}")
	public ResponseEntity<?> searchByAuthor(@PathVariable String aname)
	{
		try 
		{
			List<Book> authors= bookServices.searchByAuthor(aname);
			return new ResponseEntity<>(authors,HttpStatus.OK);
		}
		catch(Exception be) 
		{
			return new ResponseEntity<>(be.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
}
