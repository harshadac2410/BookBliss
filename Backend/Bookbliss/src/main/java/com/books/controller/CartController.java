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

import com.books.dto.CartDTO;
import com.books.entities.Cart;
import com.books.exception.CartException;
import com.books.service.CartServices;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController 
{
	@Autowired
	private CartServices orderServices;
	
	@Autowired
	private CartServices cartService;
	
	@GetMapping
	public List<Cart> displayAllOrders()
	{
		return orderServices.displayAllOrders();

	}
	
	@PostMapping
	public ResponseEntity<?> addOrder(@RequestBody Cart order)
	{
		try
		{
			Cart orders = orderServices.addOrder(order);
			return new ResponseEntity<>(order,HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(order, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable Long id)
	{
		try
		{
			Cart orders=orderServices.deleteOrder(id);
			return new ResponseEntity<>(orders, HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
//	@PutMapping("/order/{id}")
//	public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody Cart order)
//	{
//		try
//		{
//			Cart orders = orderServices.updateOrder(id, order);
//			return new ResponseEntity<>(order,HttpStatus.OK);
//		}
//		catch(Exception e)
//		{
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}
	@PutMapping("/cart/{id}")
	public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody Cart order) {
	    try {
	        Cart updatedOrder = orderServices.updateOrder(id, order);
	        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
	    } catch (CartException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}
	
   

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long id) {
        try {
            CartDTO cartDTO = cartService.getCartById(id);
            return ResponseEntity.ok(cartDTO);
        } catch (CartException e) {
            // Handle cart not found
            return ResponseEntity.notFound().build();
        }
    }

}
