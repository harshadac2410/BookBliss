package com.books.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.dto.OrderDTO;
import com.books.entities.Order;
import com.books.repository.OrderRepository;

@Service
@Transactional
public class OrderServicesImpl implements OrderServices 
{
	@Autowired
	private OrderRepository orderRepository;
	
	
	
	@Override
	public List<Order> getOrderDetails()
	{
		List<Order> orderd = orderRepository.findAll();
		return orderd;
	}
	
	@Override
	public Order updateOrderD(Order orderdetail)
	{
		Optional<Order> optionalD = orderRepository.findById(orderdetail.getOrder_id());
		if(optionalD.isPresent())
		{
//			Order orderdetails = optionalD.get();
//			orderdetail.setId(orderdetail.getId());
//			orderdetail.setFirstName(orderdetail.getFirstName());
//			orderdetail.setLastName(orderdetail.getLastName());
//			orderdetail.setEmailId(orderdetail.getEmailId());
//			orderdetail.setPassword(orderdetail.getPassword());
//			orderdetail.setMobileNo(orderdetail.getMobileNo());
//			orderdetail.setMyAddress(orderdetail.getMyAddress());
//			orderdetail.setRole(orderdetail.getRole());
			return orderRepository.save(orderdetail);
		}
		else
		{
			throw new RuntimeException("Book not found with this id");
		}
	}

	@Override
	public OrderDTO addOrderDetails(OrderDTO orderDetails) {
		Order order = new Order();
		order.setStatus(orderDetails.getStatus());;
		order.setPrice(orderDetails.getPrice());
		Order savedOrder = orderRepository.save(order);
		return mapToDTO(savedOrder);
		//return orderRepository.save(orderDetails);
	}

	private OrderDTO mapToDTO(Order order) {
		    OrderDTO dto = new OrderDTO();
		    dto.setOrderId(order.getOrder_id());
		    dto.setStatus(order.getStatus());
		    dto.setCartId(order.getCart().getCart_id()); // Assuming Cart has a getId() method
		    dto.setPrice(order.getPrice());
		    return dto;
	}

//	@Override
//	public List<Order> getOrdersByUserId(Long userId) {
//		// TODO Auto-generated method stub
//		return orderRepository.findByUserId(userId);
//	}
	
}
