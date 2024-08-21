package com.books.service;

import java.util.List;

import com.books.entities.Address;

public interface AddressService {

	List<Address> getAll();
	
	Address updateAddress(Address address);
}
