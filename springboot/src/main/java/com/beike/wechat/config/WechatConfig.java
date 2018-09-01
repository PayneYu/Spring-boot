package com.beike.wechat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yuhuizhe
 * 配置类
 */
@Component
public class WechatConfig {
	
	@Value("${wechat.agentId}")
	private int agentId ;
	
	@Value("${wechat.corpId}")
	private String corpId;
	
	@Value("${wechat.agentSecret}")
	private String agentSecret;
	
	@Value("${wechat.addressSecret}")
	private String addressSecret;
	
	@Value("${wechat.checkInSecret}")
	private String checkInSecret;

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getAgentSecret() {
		return agentSecret;
	}

	public void setAgentSecret(String agentSecret) {
		this.agentSecret = agentSecret;
	}

	public String getAddressSecret() {
		return addressSecret;
	}

	public void setAddressSecret(String addressSecret) {
		this.addressSecret = addressSecret;
	}

	public String getCheckInSecret() {
		return checkInSecret;
	}

	public void setCheckInSecret(String checkInSecret) {
		this.checkInSecret = checkInSecret;
	}
	
	
}
