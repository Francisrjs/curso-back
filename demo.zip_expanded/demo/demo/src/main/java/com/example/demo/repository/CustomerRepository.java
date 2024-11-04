package com.example.demo.repository;
import java.util.List;

import com.example.demo.entity.Customer;
public class CustomerRepository extends JpaRepository<Customer, Long>{
	public List<Customer> findAllByCity(String city)
}
