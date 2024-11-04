package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

@RestController
public class indexController {
	@Autowired
	CustomerRepository service;
	@Override
		@GetMapping("/")
	public String index() {
		return "hola";
	}

	@GetMapping("/customer")
	public List<Customer> findCustomers(){
		return service.findAll();
	}
}

