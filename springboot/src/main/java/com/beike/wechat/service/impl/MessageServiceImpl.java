package com.beike.wechat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beike.wechat.config.WechatConfig;
import com.beike.wechat.pojo.message.BaseMessage;
import com.beike.wechat.pojo.message.Text;
import com.beike.wechat.pojo.message.TextMessage;
import com.beike.wechat.service.MessageService;
import com.beike.wechat.util.WechatUtil;

import net.sf.json.JSONObject;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
	
    private Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);
    
    private  String sendMessage_url="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
    
    @Autowired
    private WechatConfig wechatConfig;
    
    /**
     * 发送文本消息
     * @param tousers
     * @param content
     * @return
     */
    public JSONObject sendTextMessage(String tousers,String content){
    	 //1.创建文本消息对象
        TextMessage message=new TextMessage();
        //1.1非必需
        message.setTouser(tousers);  //不区分大小写
        //1.2必需
        message.setMsgtype("text");
        message.setSafe(1);
        message.setAgentid(wechatConfig.getAgentId());
        Text text=new Text();
        text.setContent(content);
        message.setText(text);
        //2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
        String accessToken= WechatUtil.getAccessToken(wechatConfig.getCorpId(), wechatConfig.getAgentSecret()).getToken();
        return sendMessage(accessToken, message);
    }
    
    /**
     * @desc ：0.公共方法：发送消息
     * @param accessToken
     * @param message void
     */
    public JSONObject sendMessage(String accessToken,BaseMessage message){

        //1.获取json字符串：将message对象转换为json字符串 
    	JSONObject json = JSONObject.fromObject(message);
        String jsonMessage =json.toString();      //使用gson.toJson(user)即可将user对象顺序转成json
        LOGGER.debug("jsonTextMessage:"+jsonMessage);
        //2.获取请求的url  
        String url=sendMessage_url.replace("ACCESS_TOKEN", accessToken);
        //3.调用接口，发送消息
        JSONObject jsonObject = WechatUtil.httpRequest(url, "POST", jsonMessage);
        //4.错误消息处理
        if (null != jsonObject) {  
            if (0 != jsonObject.getInt("errcode")) {  
            	LOGGER.error("消息发送失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
            }  
        }  
        return jsonObject;
    }

}
