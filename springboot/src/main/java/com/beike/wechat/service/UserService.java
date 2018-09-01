package com.beike.wechat.service;

import java.util.List;

import com.beike.wechat.pojo.org.User;

public interface UserService {
	
	public void createUser(User user);
	
	public void getUser(String userId);
	
	public void updateUser(User user);
	
	public void deleteUser(String userId);
	
	public void batchdeleteUser(List<String> userIdList);
	
	public void getDepartmentUser(String departmentId,String fetchChild);
	
	public void getDepartmentUserDetails(String departmentId,String fetchChild);

}
