package com.books.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
@Table(name = "author")
public class Author  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long author_id;
	
	@NotBlank(message = "Author name is mandatory")
	@Column(name = "aname", length = 100)
	private String aname;
	
	@NotBlank(message = "Description is mandatory") 
	@Column(name = "description", length = 400)
	private String description;

}
