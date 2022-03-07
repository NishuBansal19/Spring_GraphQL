package com.adobe.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "authors")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "author_id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	@Column(name = "middle_name")
	private String middleName;
}
