package com.payne.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@RequestMapping("/user/date")
    @ResponseBody
	public List<User> getUser(){
		return mongoTemplate.find(Query.query(Criteria.where("borthDate")
				.gte(getStartTime())
				.lte(getEndTime())),User.class);
	}
	
	private static Date getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY,0);
		todayStart.set(Calendar.MINUTE,0);
		todayStart.set(Calendar.SECOND,0);
		todayStart.set(Calendar.MILLISECOND,0);
		return todayStart.getTime();
	}
	 
	private static Date getEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR_OF_DAY,23);
		todayEnd.set(Calendar.MINUTE,59);
		todayEnd.set(Calendar.SECOND,59);
		todayEnd.set(Calendar.MILLISECOND,999);
		return todayEnd.getTime();
	}
	
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
		user.setBorthDate(new Date());
		return userRepository.save(user);
	}

}
