package com.beike.wechat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beike.wechat.config.WechatConfig;
import com.beike.wechat.pojo.org.Department;
import com.beike.wechat.service.DepartmentService;
import com.beike.wechat.util.WechatUtil;

import net.sf.json.JSONObject;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private static Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);  

	private  static  String CREATE_DEPARTMENT_URL="https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=ACCESS_TOKEN";  
	private  static  String UPDATE_DEPARTMENT_URL="https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=ACCESS_TOKEN";  
	private  static  String DELETE_DEPARTMENT_URL="https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=ACCESS_TOKEN&id=ID";  
	private  static  String GET_DEPARTMENTLIST_URL="https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN&id=ID";  
	
	@Autowired
    private WechatConfig wechatConfig;
	
	// 1.创建部门
	public void createDepartment(Department department) {

		// 1.获取json字符串：将Department对象转换为json字符串
		JSONObject json = JSONObject.fromObject(department);
        String jsonDepartment =json.toString(); 
		// 2.拼接请求的url
        String accessToken= WechatUtil.getAccessToken(wechatConfig.getCorpId(), wechatConfig.getAddressSecret()).getToken();
		CREATE_DEPARTMENT_URL = CREATE_DEPARTMENT_URL.replace("ACCESS_TOKEN", accessToken);

		// 3.调用接口，发送请求，创建部门
		JSONObject jsonObject = WechatUtil.httpRequest(CREATE_DEPARTMENT_URL, "POST", jsonDepartment);
		System.out.println("jsonObject:" + jsonObject.toString());
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {

				log.error("创建部门失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
	}

	// 2.更新部门
	public void updateDepartment(Department department) {

		// 1.获取json字符串：将Department对象转换为json字符串
		JSONObject json = JSONObject.fromObject(department);
        String jsonDepartment =json.toString();
		// 2.拼接请求的url
        String accessToken= WechatUtil.getAccessToken(wechatConfig.getCorpId(), wechatConfig.getAddressSecret()).getToken();
		UPDATE_DEPARTMENT_URL = UPDATE_DEPARTMENT_URL.replace("ACCESS_TOKEN", accessToken);

		// 3.调用接口，发送请求，更新部门
		JSONObject jsonObject = WechatUtil.httpRequest(UPDATE_DEPARTMENT_URL, "POST", jsonDepartment);
		System.out.println("jsonObject:" + jsonObject.toString());
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {

				log.error("更新部门失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
	}

	// 3.删除部门
	public void deleteDepartment(String departmentId) {

		// 1.获取请求的url
		String accessToken= WechatUtil.getAccessToken(wechatConfig.getCorpId(), wechatConfig.getAddressSecret()).getToken();
		DELETE_DEPARTMENT_URL = DELETE_DEPARTMENT_URL.replace("ACCESS_TOKEN", accessToken).replace("ID", departmentId);

		// 2.调用接口，发送请求，删除部门
		JSONObject jsonObject = WechatUtil.httpRequest(DELETE_DEPARTMENT_URL, "GET", null);
		System.out.println("jsonObject:" + jsonObject.toString());

		// 3.错误消息处理
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				log.error("删除部门失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
	}

	// 4.获取部门列表
	public void getDepartmentList(String departmentId) {

		// 1.获取请求的url
		String accessToken= WechatUtil.getAccessToken(wechatConfig.getCorpId(), wechatConfig.getAddressSecret()).getToken();
		GET_DEPARTMENTLIST_URL = GET_DEPARTMENTLIST_URL.replace("ACCESS_TOKEN", accessToken).replace("ID", departmentId);

		// 2.调用接口，发送请求，获取成员
		JSONObject jsonObject = WechatUtil.httpRequest(GET_DEPARTMENTLIST_URL, "GET", null);
		System.out.println("jsonObject:" + jsonObject.toString());

		// 3.错误消息处理
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				log.error("获取部门列表 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
	}


}