package com.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.books.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	
}
