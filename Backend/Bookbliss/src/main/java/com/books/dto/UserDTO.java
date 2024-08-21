package com.books.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String password;
	private String role;
	private Long addressId;

}
