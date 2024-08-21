package com.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.books.dto.CartDTO;
import com.books.entities.Cart;
import com.books.exception.CartException;
import com.books.repository.BookRepository;
import com.books.repository.CartRepository;
import com.books.repository.UserRepository;

@Service
@Transactional
public class CartServicesImpl implements CartServices {
	
	@Autowired
	private CartRepository orderRepository;
	
	@Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

	@Override
	public List<Cart> displayAllOrders()
	{
		return orderRepository.findAll();
	}
	
	@Override
	public Cart addOrder(Cart order)
	{
		return orderRepository.save(order);
	}
	
//	@Override
//	public Cart updateOrder(Long id, Cart orderDetails) throws CartException 
//	{
//		Optional<Cart> optionalOrder = orderRepository.findById(orderDetails.getCart_id());
//		if(optionalOrder.isPresent())
//		{
////			Cart order = optionalOrder.get();
////			order.setId(orderDetails.getId());
////			order.setOrder_date(orderDetails.getOrder_date());
////			order.setOrderAddress(orderDetails.getOrderAddress());
////			order.setStatus(orderDetails.getStatus());
////			order.setPayment(orderDetails.getPayment());
////			order.setUser(orderDetails.getUser());
//			return orderRepository.save(orderDetails);
//		}
//		else
//		{
//			throw new CartException("Book not found with id"+id);
//		}
//	}
	
	@Override
	public Cart updateOrder(Long id, Cart orderDetails) throws CartException {
	    Optional<Cart> optionalOrder = orderRepository.findById(id);
	    if (optionalOrder.isPresent()) {
	        Cart existingOrder = optionalOrder.get();
	        
	        // Update the existing order's fields with the new details
	        existingOrder.setOrderBook(orderDetails.getOrderBook());
	        existingOrder.setUser(orderDetails.getUser());
	        existingOrder.setQuantity(orderDetails.getQuantity());
	        existingOrder.setTotal_price(orderDetails.getTotal_price());
	        
	        return orderRepository.save(existingOrder);
	    } else {
	        throw new CartException("Book not found with id " + id);
	    }
	}

	
	public Cart deleteOrder(Long id)
	{
		Optional<Cart> orders = orderRepository.findById(id);
		if(orders.isPresent())
		{
			orderRepository.deleteById(id);
			return orders.get();
		}
		else
		{
			throw new RuntimeException("Order not found with id"+id);
		}
	}
	
	

    public CartDTO getCartById(Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            return convertToDTO(cart);
        } else {
            // Handle cart not found
            throw new CartException("Cart not found with id: " + id);
        }
    }

    private CartDTO convertToDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setCart_id(cart.getCart_id());
        dto.setUser_id(cart.getUser().getUser_id());
        dto.setBook_id(cart.getOrderBook().getBook_id());
        dto.setTotal_price(cart.getTotal_price());
        dto.setQuantity(cart.getQuantity());
        return dto;
    }

	
	
}
