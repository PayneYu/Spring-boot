package com.beike.wechat.service;

import com.beike.wechat.pojo.message.BaseMessage;

import net.sf.json.JSONObject;

public interface MessageService {
	
	public JSONObject sendTextMessage(String tousers,String content);
	
	public JSONObject sendMessage(String accessToken,BaseMessage message);

}
