package com.beike.wechat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beike.wechat.config.WechatConfig;
import com.beike.wechat.pojo.CheckInRequest;
import com.beike.wechat.service.CheckInService;
import com.beike.wechat.util.WechatUtil;

import net.sf.json.JSONObject;

@Service
public class CheckInServiceImpl implements CheckInService {

	private Logger LOGGER = LoggerFactory.getLogger(CheckInServiceImpl.class);

	private String URL = "https://qyapi.weixin.qq.com/cgi-bin/checkin/getcheckindata?access_token=ACCESS_TOKEN";
	
	@Autowired
    private WechatConfig wechatConfig;

	public void getCheckInData(CheckInRequest request) {
		
		//1.获取请求的url  
		String accessToken= WechatUtil.getAccessToken(wechatConfig.getCorpId(), wechatConfig.getCheckInSecret()).getToken();
		JSONObject json = JSONObject.fromObject(request);
		String jsonMessage = json.toString();
		LOGGER.debug("jsonTextMessage:" + jsonMessage);
		String url = URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = WechatUtil.httpRequest(url, "POST", jsonMessage);
		LOGGER.info("请求结果"+jsonObject);
	}

}
