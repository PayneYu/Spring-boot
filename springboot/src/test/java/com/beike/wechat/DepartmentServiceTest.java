package com.beike.wechat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beike.wechat.pojo.org.Department;
import com.beike.wechat.service.DepartmentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;

	// 1.创建部门
	@Test
	@Ignore
	public void testCreateDepartment() {
		Department department = new Department(3, "信息中心", 1);
		departmentService.createDepartment(department);
	}

	// 2.更新部门
	@Test
	@Ignore
	public void testUpdateDepartment() {
		// 1.创建Department对象，并将对象转换成json字符串
		Department department = new Department(3, "HEC技术服务中心", 1);
		departmentService.updateDepartment(department);
	}

	// 3.删除部门
	@Test
	@Ignore
	public void testDeleteDepartment() {
		String departmentId = "3";
		departmentService.deleteDepartment(departmentId);
	}

	/**
	 * 4.获取部门列表 参数 必须 说明 access_token 是 调用接口凭证 id 否 部门id。获取指定部门及其下的子部门。
	 * 如果不填，默认获取全量组织架构
	 */
	@Test
	@Ignore
	public void testGetDepartmentList() {
		// 1.获取departmentId
		String departmentId = "1";
		departmentService.getDepartmentList(departmentId);
	}

}
