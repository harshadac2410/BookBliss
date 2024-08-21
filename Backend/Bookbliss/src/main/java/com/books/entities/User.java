package com.books.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id;
	
	@Column(length = 20, name = "first_name")
	private String firstName;
	
	@Column(length = 20, name = "last_name")
	private String lastName;
	
	@Column(length = 30, unique = true, nullable = false)
	private String emailId;
	
	@Column(length = 20, unique = true, nullable = false)
	private String password;
	
	@Column(length = 13, unique = true, name = "mobile_no")
	private String mobileNo;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Role role;
	
	@OneToOne(cascade =CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id")
	private Address myAddress;
	
//	public User() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	public User(String firstName, String lastName, String emailId, String password, String mobileNo, Role role,
//			Address myAddress) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.emailId = emailId;
//		this.password = password;
//		this.mobileNo = mobileNo;
//		this.role = role;
//		this.myAddress = myAddress;
//	}
//
//
//	public User(String emailId, String password, Role role) {
//		super();
//		this.emailId = emailId;
//		this.password = password;
//		this.role = role;
//	}

}