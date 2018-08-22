package com.study.user.service;

import java.util.List;

import com.study.user.entiry.User;

public interface UserService {
	
	public User getUserById(String id);

    public List<User> getUserList();

    public User findUserById(long id);

    public void save(User user);

    public void edit(User user);

    public void delete(long id);


}
