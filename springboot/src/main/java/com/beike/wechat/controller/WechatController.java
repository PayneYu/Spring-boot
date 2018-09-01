package com.beike.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beike.wechat.service.MessageService;

import net.sf.json.JSONObject;

/**
 * @author yuhuizhe 与企业微信对接的API
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {
	
	@Autowired
	private MessageService messageService;

	@PostMapping("/message/text")
	public String sendMessage(@RequestParam("tousers") String tousers, @RequestParam("content") String content) {
		JSONObject object = messageService.sendTextMessage(tousers, content);
		return object.toString();
	}

}
