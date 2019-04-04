package com.spring.association.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.association.entity.Customer;
import com.spring.association.entity.ShippingAddress;
import com.spring.association.repository.OneToOneBiDirCustRepository;
import com.spring.association.repository.OneToOneBiDirShippRepository;


@RestController
public class OneToOneBiDirController {
	
	@Autowired
	private OneToOneBiDirCustRepository oneToOneBiDirCustRepository;
	
	@Autowired
	private OneToOneBiDirShippRepository oneToOneBiDirShippRepository;
	
	@RequestMapping(value="/customer", method=RequestMethod.POST)
	public String createUser(@RequestBody Customer customer) {
		oneToOneBiDirCustRepository.save(customer);
		return "User created Successfully";
	}
	
	@RequestMapping(value="/customer", method=RequestMethod.GET)
	public List<Customer> getAllUser() {
		return oneToOneBiDirCustRepository.findAll();
	}
	
	@RequestMapping(value="/customer/{custId}", method=RequestMethod.GET)
	public Customer getCustomerById(@PathVariable("custId") Long custId) {
		return oneToOneBiDirCustRepository.findOne(custId);
	}
	
	@RequestMapping(value="/shipping", method=RequestMethod.GET)
	public List<ShippingAddress> getAllUserShipping() {
		return oneToOneBiDirShippRepository.findAll();
	}
	
	@RequestMapping(value="/shipping/{shipId}", method=RequestMethod.GET)
	public ShippingAddress getShippingById(@PathVariable("shipId") Long shippingId) {
		
		ShippingAddress findOne = oneToOneBiDirShippRepository.findOne(shippingId);
		
		return findOne;
	}
}
