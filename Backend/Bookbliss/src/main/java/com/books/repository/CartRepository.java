package com.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.books.entities.Book;
import com.books.entities.Cart;
import com.books.entities.Role;
import com.books.entities.User;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
	
}
