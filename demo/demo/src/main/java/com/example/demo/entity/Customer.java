package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer {
	@Id
	private Long id;
	@Column(name="fist_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="adress")
	private String adress;
	@Column(name="city")
	private String city;
	public Customer() {}
		public Customer(Long id, String firstName, String lastName, String adress, String city) {
			this.id=id;
			this.firstName= firstName;
			this.lastName= lastName;
			this.city=city;
			this.adress=adress;
			
	
	}

}
