package com.spring.association.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.association.entity.User;
import com.spring.association.repository.OneTwoOneRepository;

@RestController
public class OneToOneController {
	
	@Autowired
	private OneTwoOneRepository oneTwoOneRepository;
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public String createUser(@RequestBody User user) {
		oneTwoOneRepository.save(user);
		return "User created Successfully";
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<User> getAllUser() {
		return oneTwoOneRepository.findAll();
	}
}
