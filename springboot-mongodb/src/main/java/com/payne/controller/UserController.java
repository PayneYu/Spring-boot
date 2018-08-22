package com.payne.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.payne.entity.User;
import com.payne.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/user/{firstName}")
    @ResponseBody
	public List<User> getUser(@PathVariable String firstName){
		return userRepository.findByFirstName(firstName);
	}
	
	@RequestMapping("/add/{firstName}")
    @ResponseBody
	public User addUser(@PathVariable String firstName){
		User user = new User();
		user.setAge(5);
		user.setId("1");
		user.setFirstName(firstName);
		return userRepository.save(user);
	}

}
