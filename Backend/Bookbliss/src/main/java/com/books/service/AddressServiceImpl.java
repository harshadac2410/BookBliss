package com.books.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.entities.Address;
import com.books.exception.AddressException;
import com.books.repository.AddressRepository;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public List<Address> getAll() {
		return addressRepository.findAll();
	}

	@Override
	public Address updateAddress(Address address) {
		Optional<Address> addr = addressRepository.findById(address.getAddress_id());
		if(addr.isPresent()) {
			return addressRepository.save(address);
		}else {
			throw new AddressException("Id not found....Enter valid ID");
		}
	}

}
