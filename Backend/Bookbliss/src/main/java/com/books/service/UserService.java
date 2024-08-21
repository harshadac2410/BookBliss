package com.books.service;

import java.util.List;

import com.books.entities.User;
import com.books.exception.UserException;

public interface UserService {

	List<User> getAllUsers();
	
	User registerUsers(User user) throws UserException;
	
	void updateUser(User user) throws UserException;
	
	User getUserById(Long id);
	
	User deleteById(Long id);
	
	User loginUser(String emailId, String password);
	
}
