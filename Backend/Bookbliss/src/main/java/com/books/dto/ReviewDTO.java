package com.books.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {

	private Long reviewID;
	
	private String description;
	
	private int rating;
	
    private Long userId;  
	 
	private Long bookId; 
}
