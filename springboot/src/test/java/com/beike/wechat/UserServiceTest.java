package com.beike.wechat;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beike.wechat.pojo.org.User;
import com.beike.wechat.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService userService;

	//1.创建成员
	@Test
	@Ignore
	public void testCreateUser() {
		//1.创建user对象
		User user = new User("6", "Mike", 3,"14686086294","9057046319@qq.com","项目经理","1");
		//3.创建成员
		userService.createUser(user);
	}

	//2.获取成员
	@Test
	@Ignore
	public void testGetUser() {
		//1.获取userId
		//String userId="ShiRui";
		String userId="6";
		//3.获取成员
		userService.getUser(userId);
	}

	//3.更新成员
	@Test
	@Ignore
	public void testUpdateUser() {
		//1.更改user对象信息
		User user = new User("6", "ray", 3,"13889086292","3057946319@qq.com","咨询顾问","1");  
		//3.创建成员
		userService.updateUser(user);

	}

	//4.删除成员
	@Test
	@Ignore
	public void testDeleteUser() {
		//1.获取userId
		//String userId="ShiRui";
		String userId="4";

		//3.创建成员
		userService.deleteUser(userId);
	}


	//5.批量删除成员
	@Test
	@Ignore
	public void testbatchdeleteUser() {
		//1.获取userIdList
		String userId1="3";
		String userId2="4";
		List<String> userIdList = Arrays.asList(userId1, userId2);  //此时将userIdList转json,则结果为：["3","4"],会报错：{"errcode":40063,"errmsg":"some parameters are empty"}

		//3.批量删除成员
		userService.batchdeleteUser(userIdList);
	}


	//6.获取部门成员
	@Test
	@Ignore
	public void testGetDepartmentUser() {
		//1.获取部门ID以及是否获取子部门成员
		String departmentId="1";
		String fetchChild="0";

		//3.获取部门成员
		userService.getDepartmentUser(departmentId, fetchChild);
	}


	//7.获取部门成员详情
	@Test 
	@Ignore
	public void testGetDepartmentUserDetails() {
		//1.获取部门ID以及是否获取子部门成员
		String departmentId="1";
		String fetchChild="0";

		//3.获取部门成员
		userService.getDepartmentUserDetails(departmentId, fetchChild);
	}
}
