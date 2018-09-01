package com.beike.wechat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beike.wechat.pojo.message.Article;
import com.beike.wechat.pojo.message.News;
import com.beike.wechat.pojo.message.NewsMessage;
import com.beike.wechat.pojo.message.Text;
import com.beike.wechat.pojo.message.TextMessage;
import com.beike.wechat.pojo.message.Textcard;
import com.beike.wechat.pojo.message.TextcardMessage;
import com.beike.wechat.service.MessageService;
import com.beike.wechat.util.WechatUtil;

/**
 * @author yuhuizhe
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SendMessageTest {
	
	@Autowired
	private MessageService messageService;

	private static int agentId = 0;

	private static String corpId = "wxd12189970a0d915d";

	private static String agentSecret = "s2UM-y1x4muGVHOTZM90ne818xhPNiHN3xDq4xxL-_k";

	// 1.发送文本消息
	@Test
	@Ignore
	public void testSendTextMessage() {
		// 0.设置消息内容
		String content = "测试 by信息部。";
		// 1.创建文本消息对象
		TextMessage message = new TextMessage();
		// 1.1非必需
		message.setTouser("23035625"); // 不区分大小写
		// 1.2必需
		message.setMsgtype("text");
		message.setAgentid(agentId);
		Text text = new Text();
		text.setContent(content);
		message.setText(text);
		// 2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken = WechatUtil.getAccessToken(corpId, agentSecret).getToken();
		System.out.println("accessToken:" + accessToken);
		// 3.发送消息：调用业务类，发送消息
		messageService.sendMessage(accessToken, message);
	}

	// 2.发送文本卡片消息
	@Test
	@Ignore
	public void testSendTextcardMessage() {
		// 0.设置消息内容
		String title = "代办事宜";
		String description = "<div class=\"gray\">2017年8月18日</div> <div class=\"normal\">"
				+ "恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div class=\"highlight\">" + "请于2017年10月10日前联系行政同事领取</div>";
		String url = "http://www.cnblogs.com/shirui/p/7297872.html";
		// 1.创建文本卡片消息对象
		TextcardMessage message = new TextcardMessage();
		// 1.1非必需
		message.setTouser("23035625"); // 不区分大小写
		// message.setToparty("1");
		// message.setTotag(totag);
		// message.setSafe(0);

		// 1.2必需
		message.setMsgtype("textcard");
		message.setAgentid(agentId);

		Textcard textcard = new Textcard();
		textcard.setTitle(title);
		textcard.setDescription(description);
		textcard.setUrl(url);
		message.setTextcard(textcard);
		// 2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken = WechatUtil.getAccessToken(corpId, agentSecret).getToken();
		// 3.发送消息：调用业务类，发送消息
		messageService.sendMessage(accessToken, message);
	}

	// 3.发送图文消息
	@Test
	@Ignore
	public void testSendNewsMessage() {
		// 1.创建图文消息对象
		NewsMessage message = new NewsMessage();
		// 1.1非必需
		message.setTouser("23035625"); // 不区分大小写
		// textMessage.setToparty("1");
		// txtMsg.setTotag(totag);
		// txtMsg.setSafe(0);
		// 1.2必需
		message.setMsgtype("news");
		message.setAgentid(agentId);
		// 设置图文消息
		Article article1 = new Article();
		article1.setTitle("青年文摘");
		article1.setDescription("这是一个很特别的描述");
		article1.setPicurl("http://mat1.gtimg.com/fashion/images/index/2017/08/18/tpzs2.jpg");
		article1.setUrl("http://www.cnblogs.com/shirui/p/7297872.html");

		List<Article> articles = new ArrayList<Article>();
		articles.add(article1);

		News news = new News();
		news.setArticles(articles);
		message.setNews(news);
		// 2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken = WechatUtil.getAccessToken(corpId, agentSecret).getToken();
		// 3.发送消息：调用业务类，发送消息
		messageService.sendMessage(accessToken, message);

	}

}
