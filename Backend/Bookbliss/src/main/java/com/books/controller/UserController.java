package com.books.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.books.entities.User;
import com.books.exception.UserException;
import com.books.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			User u = userService.registerUsers(user);
			return new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	 
	
	 @DeleteMapping("/{id}")
	 public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		 try {
			 User ur = userService.deleteById(id);
			 return new ResponseEntity<>(ur,HttpStatus.OK);
		 }catch(Exception e) {
			 return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		 }
	 }
	 
	 @PutMapping
	 public ResponseEntity<?> updateUser(@RequestBody User user) {
		 try {
			 userService.updateUser(user);
			 return new ResponseEntity<>("Updated.....",HttpStatus.OK);
		 }catch(UserException ue) {
			 return new ResponseEntity<>(ue.getMessage(),HttpStatus.NOT_FOUND);
		 }
		 catch(Exception e) {
			 return new ResponseEntity<>("An unexpected error occurred...",HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<?> getUserById(@PathVariable Long id){
		 try {
			 User user = userService.getUserById(id);
			 return new ResponseEntity<>(user,HttpStatus.OK);
		 }catch(Exception e) {
			 return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		 }
	 }
	 
	// Login User
	 @PostMapping("/login")
	 public ResponseEntity<Map<String, String>> loginUser(@RequestParam String emailId, @RequestParam String password, @RequestParam String role) {
	     Map<String, String> response = new HashMap<>();
	     try {
	         User loggedInUser = userService.loginUser(emailId, password);
	         if (loggedInUser != null) {
	             String userRole = loggedInUser.getRole().toString();
	             if (userRole.equals("ADMIN") || userRole.equals("CUSTOMER")) {
	                 if (role.equals(userRole)) {
	                     response.put("redirectUrl", userRole.equals("ADMIN") ? "/admin-dashboard" : "/customer");
	                 } else {
	                     return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	                 }
	             } else {
	                 return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	             }
	             return new ResponseEntity<>(response, HttpStatus.OK);
	         } else {
	             return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	         }
	     } catch (IllegalArgumentException e) {
	         response.put("message", "Unauthorized");
	         return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	     }
	 }

	   
}
