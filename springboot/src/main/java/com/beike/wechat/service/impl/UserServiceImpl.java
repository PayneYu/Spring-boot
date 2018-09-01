package com.beike.wechat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beike.wechat.config.WechatConfig;
import com.beike.wechat.pojo.org.User;
import com.beike.wechat.service.UserService;
import com.beike.wechat.util.WechatUtil;

import net.sf.json.JSONObject;

@Service
public class UserServiceImpl implements UserService{
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);  

	private  static  String CREATE_USER_URL="https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN";  
	private  static  String GET_USER_URL="https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";  
	private  static  String UPDATE_USER_URL="https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=ACCESS_TOKEN";  
	private  static  String DELETE_USER_URL="https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=USERID";  
	private  static  String BATCH_DELETEUSER_URL="https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=ACCESS_TOKEN";  
	private  static  String GET_DEPARTMENTUSER_URL="https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";  
	private  static  String GET_DEPARTMENTUSERDETAILS_URL="https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";  

	@Autowired
    private WechatConfig wechatConfig;
	
	//1.创建成员
	public void createUser(User user) {
		//1.获取json字符串：将user对象转换为json字符串	
		JSONObject json = JSONObject.fromObject(user);
		//2.获取请求的url  
        String accessToken= WechatUtil.getAccessToken(wechatConfig.getCorpId(), wechatConfig.getAddressSecret()).getToken();
        CREATE_USER_URL = CREATE_USER_URL.replace("ACCESS_TOKEN", accessToken);
		//3.调用接口，发送请求，创建成员
		JSONObject jsonObject = WechatUtil.httpRequest(CREATE_USER_URL, "POST", json.toString());  
		System.out.println("jsonObject:"+jsonObject.toString());
		//4.错误消息处理
		if (null != jsonObject) {  
			if (0 != jsonObject.getInt("errcode")) {  
				log.error("创建成员失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
			}  
		}  
	}

	//2.获取成员
	public void getUser(String userId) {
		//1.获取请求的url  
		String accessToken= WechatUtil.getAccessToken(wechatConfig.getCorpId(), wechatConfig.getAddressSecret()).getToken();
		GET_USER_URL = GET_USER_URL.replace("ACCESS_TOKEN", accessToken);
		GET_USER_URL = GET_USER_URL.replace("USERID", userId);
		//2.调用接口，发送请求，获取成员
		JSONObject jsonObject = WechatUtil.httpRequest(GET_USER_URL, "GET", null);  
		System.out.println("jsonObject:"+jsonObject.toString());

		//3.错误消息处理
		if (null != jsonObject) {  
			if (0 != jsonObject.getInt("errcode")) {  
				log.error("获取成员失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
			}  
		}  
	}

	//3.更新成员
	public void updateUser(User user) {
		JSONObject json = JSONObject.fromObject(user);
		String accessToken= WechatUtil.getAccessToken(wechatConfig.getCorpId(), wechatConfig.getAddressSecret()).getToken();
		UPDATE_USER_URL=UPDATE_USER_URL.replace("ACCESS_TOKEN", accessToken);
		//3.调用接口，发送请求，创建成员
		JSONObject jsonObject = WechatUtil.httpRequest(UPDATE_USER_URL, "POST", json.toString());  
		System.out.println("jsonObject:"+jsonObject.toString());

		//4.错误消息处理
		if (null != jsonObject) {  
			if (0 != jsonObject.getInt("errcode")) {  
				log.error("更新成员失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
			}  
		}  
	}

	//4.删除成员
	public void deleteUser(String userId) {	
		String accessToken= WechatUtil.getAccessToken(wechatConfig.getCorpId(), wechatConfig.getAddressSecret()).getToken();
		DELETE_USER_URL=DELETE_USER_URL.replace("ACCESS_TOKEN", accessToken)
				.replace("USERID", userId);
		//2.调用接口，发送请求，删除成员
		JSONObject jsonObject = WechatUtil.httpRequest(DELETE_USER_URL, "GET", null);  
		System.out.println("jsonObject:"+jsonObject.toString());
		//3.错误消息处理
		if (null != jsonObject) {  
			if (0 != jsonObject.getInt("errcode")) {  
				log.error("删除成员失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
			}  
		}  
	}

	//5.批量删除成员
	public void batchdeleteUser(List<String> userIdList){
		//1.获取json字符串：将user对象转换为json字符串	
		Map<String, Object> content = new HashMap<String, Object>();  
		content.put("useridlist", userIdList);  
		JSONObject json = JSONObject.fromObject(content);
		//2.获取请求的url  
		String accessToken= WechatUtil.getAccessToken(wechatConfig.getCorpId(), wechatConfig.getAddressSecret()).getToken();
		BATCH_DELETEUSER_URL=BATCH_DELETEUSER_URL.replace("ACCESS_TOKEN", accessToken);

		//3.调用接口，发送请求，创建成员
		JSONObject jsonObject = WechatUtil.httpRequest(BATCH_DELETEUSER_URL, "POST", json.toString());  
		System.out.println("jsonObject:"+jsonObject.toString());

		//4.错误消息处理
		if (null != jsonObject) {  
			if (0 != jsonObject.getInt("errcode")) {  
				log.error("批量删除成员失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
			}  
		}  
	}

	//6.获取部门成员
	public void getDepartmentUser(String departmentId,String fetchChild) {
		String accessToken= WechatUtil.getAccessToken(wechatConfig.getCorpId(), wechatConfig.getAddressSecret()).getToken(); 
		GET_DEPARTMENTUSER_URL=GET_DEPARTMENTUSER_URL.replace("ACCESS_TOKEN", accessToken)
				.replace("DEPARTMENT_ID", departmentId)
				.replace("FETCH_CHILD", fetchChild);

		//2.调用接口，发送请求，获取部门成员
		JSONObject jsonObject = WechatUtil.httpRequest(GET_DEPARTMENTUSER_URL, "GET", null);  
		System.out.println("jsonObject:"+jsonObject.toString());

		//3.错误消息处理
		if (null != jsonObject) {  
			if (0 != jsonObject.getInt("errcode")) {  
				log.error("获取部门成员失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
			}  
		}  
	}


	//7.获取部门成员详情
	public void getDepartmentUserDetails(String departmentId,String fetchChild) {
		//1.获取请求的url
		String accessToken= WechatUtil.getAccessToken(wechatConfig.getCorpId(), wechatConfig.getAddressSecret()).getToken();
		GET_DEPARTMENTUSERDETAILS_URL=GET_DEPARTMENTUSERDETAILS_URL.replace("ACCESS_TOKEN", accessToken)
				.replace("DEPARTMENT_ID", departmentId)
				.replace("FETCH_CHILD", fetchChild);

		//2.调用接口，发送请求，获取部门成员
		JSONObject jsonObject = WechatUtil.httpRequest(GET_DEPARTMENTUSERDETAILS_URL, "GET", null);  
		System.out.println("jsonObject:"+jsonObject.toString());

		//3.错误消息处理
		if (null != jsonObject) {  
			if (0 != jsonObject.getInt("errcode")) {  
				log.error("获取部门成员详情失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
			}  
		}  
	}

}