package com.payne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.payne.entity.User;
import com.payne.repository.UserRepository;

@SpringBootApplication
public class SpringbootMongodbApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();

        // save a couple of customers
		User user = new User();
		user.setAge(2);
		user.setFirstName("Alice");
		user.setLastName("Smith");
        userRepository.save(user);

        // fetch all customers
        System.out.println("Customers found with findAll():");

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(userRepository.findByFirstName("Alice"));
		
	}
}
