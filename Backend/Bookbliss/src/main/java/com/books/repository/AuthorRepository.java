package com.books.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.books.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

	

}
