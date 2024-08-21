package com.books.dto;

import com.books.entities.Order;
import com.books.entities.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
	    private long orderId;
	    private Status status;
	    private long cartId;
	    private float price;
		
}

