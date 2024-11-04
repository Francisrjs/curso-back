package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

public interface CustomerService implements CustomerService {
	@Autowired
	CustomerRepository repository;
	@Override
	public List<Customer> findAll(){
		return Repo;
	}
}
