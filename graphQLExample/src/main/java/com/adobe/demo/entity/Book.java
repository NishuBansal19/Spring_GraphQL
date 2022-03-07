package com.adobe.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="books")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Book {
	@Id
	@Column(name="book_id")
	private int id;
	private String title;
	@Column(name="total_pages")
	private Integer totalPages;
	private double rating;
	private String isbn;
}
