package com.books.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.entities.Role;
import com.books.entities.User;
import com.books.exception.UserException;
import com.books.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void updateUser(User user) throws UserException{
		Optional<User> use = userRepository.findById(user.getUser_id());
		if(use.isPresent()) {
			userRepository.save(user);
			return;
		}else {
			throw new UserException("Id not found!!!");
		}
	}

	@Override
	public User getUserById(Long id) {
		Optional<User> i = userRepository.findById(id);
		return i.orElseThrow(() -> new UserException("Id not found!!"));
	}

	@Override
	public User deleteById(Long id) throws UserException{
		Optional<User> d = userRepository.findById(id);
		if(d.isPresent()) {
			userRepository.deleteById(id);
			return d.get();
		}else {
			throw new UserException("Id not found!!!");
		}
	}

	@Override
	public User registerUsers(User user) throws UserException{
		if(user.getRole() == Role.ADMIN) {
			throw new UserException("cannot add admin user...");
		}
		Optional<User> existingUser = userRepository.findByEmailId(user.getEmailId());
		if(existingUser.isPresent()) {
			throw new UserException("Email is already registered.");
		}
		user.setRole(Role.CUSTOMER);
		return userRepository.save(user);
	}

	// Login User
    public User loginUser(String emailId, String password) {
        User user = userRepository.findByEmailIdAndPassword(emailId, password)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials."));
       
        if (user.getRole() == Role.CUSTOMER) {
            // Redirect to customer page logic
        } else if (user.getRole() == Role.ADMIN) {
            // Redirect to admin page logic
        }
        return user;
    }
}
