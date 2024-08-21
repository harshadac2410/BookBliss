package com.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.books.dto.OrderDTO;
import com.books.entities.Order;
import com.books.exception.OrderException;
import com.books.service.OrderServices;

@RestController
@RequestMapping("/order")
public class OrderController {

//	https://chatgpt.com/share/76b74316-9315-492e-b8eb-28b841eee6ff

	@Autowired
	private OrderServices orderServices;
	
	@GetMapping
	public List<Order> getAllOrders(){
		return orderServices.getOrderDetails();
	}
	
//	@PostMapping
//	public ResponseEntity<?> addOrder(@RequestBody Order order) {
//		try {
//			Order o = orderServices.addOrderDetails(order);
//			return new ResponseEntity<>(o,HttpStatus.CREATED);
//		}catch(Exception e) {
//			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//		}
//	}
//	 @PostMapping
//	    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
//	        OrderDTO createdOrder = orderServices.addOrderDetails(orderDTO);
//	        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
//	    }
	@PostMapping
	public OrderDTO addOrder(@RequestBody OrderDTO odto){
		System.out.println("in order "+odto);
		return orderServices.addOrderDetails(odto);
	}
	
	@PutMapping("/{id}")
		public ResponseEntity<?> updateOrder(@RequestBody Order order){
			try {
				Order or = orderServices.updateOrderD(order);
				return new ResponseEntity<>(or,HttpStatus.OK);
			}catch(OrderException oe) {
				return new ResponseEntity<>(oe.getMessage(),HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>("Something went wrong..",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@GetMapping("/{userId}")
//	public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
//	    List<Order> orders = orderServices.getOrdersByUserId(userId);
//	    return ResponseEntity.ok(orders);
//	}
}
