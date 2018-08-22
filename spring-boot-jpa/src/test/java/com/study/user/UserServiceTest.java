package com.study.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.study.user.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testGetUserAll(){
		userService.getUserList();
	}
	
	@Test
	public void testGetUserById(){
		userService.getUserById("1");
	}

	
	@Test
	public void testFindUserById(){
		userService.findUserById(1);
	}

}
