package com.books.dto;

import lombok.Getter;
import lombok.Setter;

public class BookDTO {

	private Float price;
	
	private Integer quantity;
	
	public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}

