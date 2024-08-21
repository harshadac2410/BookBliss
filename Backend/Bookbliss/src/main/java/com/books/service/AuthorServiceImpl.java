package com.books.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.entities.Author;
import com.books.exception.AuthorException;
import com.books.repository.AuthorRepository;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	private AuthorRepository authorRepository;
	
	
	
	@Override
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public Author addAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public Author updateAuthor(Author author) throws AuthorException{
		Optional<Author> auth = authorRepository.findById(author.getAuthor_id());
		if(auth.isPresent()) {
			return authorRepository.save(author);
		}else {
			throw new AuthorException("Invalid ID...");
		}
	}

	@Override
	public Author deleteById(Long id) {
		Optional<Author> au = authorRepository.findById(id);
		if(au.isPresent()) {
			authorRepository.deleteById(id);
			return au.get();
		}else {
			throw new AuthorException("Id not present...");
		}
	}

	
}
