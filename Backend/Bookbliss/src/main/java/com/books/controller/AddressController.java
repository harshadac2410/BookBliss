package com.books.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.books.entities.Address;
import com.books.exception.AddressException;
import com.books.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public List<Address> getAll(){
		return addressService.getAll();
	}
	
	@PutMapping
	public ResponseEntity<?> updateAddress(@RequestBody Address address){
		try {
			addressService.updateAddress(address);
			return new ResponseEntity<>("Updated...",HttpStatus.OK);
		}catch(AddressException ae) {
			return new ResponseEntity<>(ae.getMessage(),HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
