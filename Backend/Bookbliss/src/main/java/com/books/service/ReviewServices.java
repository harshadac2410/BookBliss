package com.books.service;

import java.util.List;

import com.books.entities.Review;

public interface ReviewServices 
{
	Review addReview(Review review);
	Review deleteReview(Long id);
	List<Review> getAllReviews();
}
