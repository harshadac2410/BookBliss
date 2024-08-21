package com.books.service;

import java.util.List;

import com.books.dto.CartDTO;
import com.books.entities.Cart;

public interface CartServices {

	List<Cart> displayAllOrders();
	
	Cart addOrder(Cart order);
	
	Cart deleteOrder(Long id);
	
	Cart updateOrder(Long id,Cart orders);
	
	CartDTO getCartById(Long id);
}
