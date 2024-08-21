package com.books.dto;

public class CartDTO {

	 private long cart_id;
	    private long user_id;
	    private long book_id;
	    private float total_price;
	    private int quantity;

	    // Getters and Setters

	    public long getCart_id() {
	        return cart_id;
	    }

	    public void setCart_id(long cart_id) {
	        this.cart_id = cart_id;
	    }

	    public long getUser_id() {
	        return user_id;
	    }

	    public void setUser_id(long user_id) {
	        this.user_id = user_id;
	    }

	    public long getBook_id() {
	        return book_id;
	    }

	    public void setBook_id(long book_id) {
	        this.book_id = book_id;
	    }

	    public float getTotal_price() {
	        return total_price;
	    }

	    public void setTotal_price(float total_price) {
	        this.total_price = total_price;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }
}
