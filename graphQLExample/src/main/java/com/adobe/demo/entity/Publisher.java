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
@Table(name="publishers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Publisher {
	@Id
	@Column(name="publisher_id")
	private int id;
	private String name;
}
