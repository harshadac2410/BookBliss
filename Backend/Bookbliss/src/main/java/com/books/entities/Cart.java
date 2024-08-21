package com.books.entities;

import java.time.LocalDate;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name="cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cart_id;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	/*
	 * @JsonFormat(pattern = "yyyy-MM-dd")
	 * 
	 * @Column private LocalDate order_date;
	 */
	
	/*
	 * @OneToOne(cascade =CascadeType.ALL,fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "address_id") private Address orderAddress;
	 */
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id")
	private Book orderBook;
	
	/*
	 * @Enumerated(EnumType.STRING)
	 * 
	 * @Column(length = 20) private Status status = Status.PENDING;
	 */
	
	@Column
	private float total_price;
	/*
	 * @Column private float discount;
	 */ 
	 @Column 
	 private int quantity;
	
}
