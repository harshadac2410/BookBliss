package com.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.books.entities.Review;
import com.books.repository.ReviewRepository;

@Service
@Transactional
public class ReviewServicesImpl implements ReviewServices
{
	@Autowired
	private ReviewRepository reviewRepository;
	
	
	public List<Review> getAllReviews()
	{
		List<Review> reviews = reviewRepository.findAll();
		return reviews;
	}
	
	
	public Review addReview(Review review)
	{
		Review reviews = reviewRepository.save(review);
		return reviews;
	}
	
	public Review deleteReview(Long id)
	{
		Optional<Review> optionalReview =reviewRepository.findById(id);
		if(optionalReview.isPresent())
		{
			reviewRepository.deleteById(id);
			return optionalReview.get();
		}
		else
		{
			throw new RuntimeException("ReviewID "+id +" not found");
		}
	}
}
