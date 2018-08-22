package com.payne.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.payne.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	public List<User> findByFirstName(String firstName);
	
	public List<User> findAll();

}
