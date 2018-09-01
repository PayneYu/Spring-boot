package com.beike.wechat.service;

import com.beike.wechat.pojo.org.Department;

public interface DepartmentService {
	
	// 1.创建部门
	public void createDepartment(Department department);
	
	// 2.更新部门
	public void updateDepartment(Department department);
	
	// 3.删除部门
	public void deleteDepartment(String departmentId);
	
	// 4.获取部门列表
	public void getDepartmentList(String departmentId);

}
