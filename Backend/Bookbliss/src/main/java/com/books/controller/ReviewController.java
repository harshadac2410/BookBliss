package com.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.books.entities.Review;
import com.books.service.ReviewServices;

@RestController
@RequestMapping("/Review")
public class ReviewController 
{
	@Autowired
	private ReviewServices reviewServices;
	
	@GetMapping
	public ResponseEntity<?> getAllReviews()
	{
			List<Review> reviews = reviewServices.getAllReviews();
			// Manually initializing the lazy-loaded book entity
//	        Hibernate.initialize(reviews.ge);
			return ResponseEntity.ok(reviews);
	}
	
	@PostMapping
	public ResponseEntity<?> addReview(@RequestBody Review review)
	{
		try 
		{
			Review reviews = reviewServices.addReview(review);
			return new ResponseEntity<>(review,HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteReview(@PathVariable Long id)
	{
		try
		{
			Review review = reviewServices.deleteReview(id);
			return new ResponseEntity<>(review, HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
