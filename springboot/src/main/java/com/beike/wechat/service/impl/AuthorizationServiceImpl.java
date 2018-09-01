package com.beike.wechat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beike.wechat.config.WechatConfig;
import com.beike.wechat.pojo.UserTicket;
import com.beike.wechat.service.AuthorizationService;
import com.beike.wechat.util.WechatUtil;

import net.sf.json.JSONObject;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{
	
	private static Logger log = LoggerFactory.getLogger(AuthorizationServiceImpl.class);  

	public static final String GET_USERINFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";  
	
	public static final String GET_USERDETAIL_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?access_token=ACCESS_TOKEN";
	
	@Autowired
    private WechatConfig wechatConfig;

	/** 1.根据code获取成员信息
	 * @desc ：GET请求、
	 * 成员信息包括
	 * UserId	成员UserID
	 * DeviceId	手机设备号(由企业微信在安装时随机生成，删除重装会改变，升级不受影响)
	 * user_ticket	成员票据，最大为512字节。scope为snsapi_userinfo或snsapi_privateinfo，且用户在应用可见范围之内时返回此参数。
	 *              后续利用该参数可以获取用户信息或敏感信息。
	 * expires_in	user_token的有效时间（秒），随user_ticket一起返回
	 *  
	 * @param accessToken
	 * @param code void
	 */
	public JSONObject getUserInfo(String code) {
		String accessToken= WechatUtil.getAccessToken(wechatConfig.getCorpId(), wechatConfig.getAgentSecret()).getToken();
		//1.获取请求的url  
		String get_userInfo_url=GET_USERINFO_URL.replace("ACCESS_TOKEN", accessToken)
				.replace("CODE", code);

		//2.调用接口，发送请求，获取成员信息
		JSONObject jsonObject = WechatUtil.httpRequest(get_userInfo_url, "GET", null);  
		System.out.println("jsonObject:"+jsonObject.toString());

		//3.错误消息处理
		if (null != jsonObject) {  
			if (0 != jsonObject.getInt("errcode")) {  
				log.error("获取成员信息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
			}  
		}  
		return jsonObject;
		
	}
	/** 2.使用userTicket获取成员详情
	 * @desc ：POST请求
	 *  
	 * @param accessToken
	 * @param userTicket
	 * @return JSONObject
	 */
	public JSONObject getUserDetail(UserTicket userTicket) {
		String accessToken= WechatUtil.getAccessToken(wechatConfig.getCorpId(), wechatConfig.getAgentSecret()).getToken();
		//1.获取请求地址  
		String get_userDetail_url=GET_USERDETAIL_URL.replace("ACCESS_TOKEN", accessToken);

		//2.准备好请求包体
		JSONObject json = JSONObject.fromObject(userTicket);
		
		//2.调用接口，发送请求，获取成员信息
		JSONObject jsonObject = WechatUtil.httpRequest(get_userDetail_url, "POST", json.toString());  
		System.out.println("jsonObject:"+jsonObject.toString());

		//3.错误消息处理
		if (null != jsonObject) {  
			if (0 != jsonObject.getInt("errcode")) {  
				log.error("获取成员信息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
			}  
		}  
		
		return jsonObject;
		
	}
	

}
