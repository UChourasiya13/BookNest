package com.api.book.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int authorId;
	private String name;
	private String language;
	
	@JsonBackReference
	@OneToOne(mappedBy = "author")
	private Book book;
	
	public Author(String name,String language)
	{
		this.name = name;
		this.language = language;
	}
}
