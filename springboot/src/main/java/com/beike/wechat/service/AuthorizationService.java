package com.beike.wechat.service;

import com.beike.wechat.pojo.UserTicket;

import net.sf.json.JSONObject;

public interface AuthorizationService {
	
	public JSONObject getUserInfo(String code);
	
	public JSONObject getUserDetail(UserTicket userTicket);

}
