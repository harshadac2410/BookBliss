package com.books.service;

import java.util.List;

import com.books.dto.OrderDTO;
import com.books.entities.Order;

public interface OrderServices 
{
	OrderDTO addOrderDetails(OrderDTO orderDetails);
	List<Order> getOrderDetails();
	Order updateOrderD(Order orderdetail);
//	List<Order> getOrdersByUserId(Long userId);
}
