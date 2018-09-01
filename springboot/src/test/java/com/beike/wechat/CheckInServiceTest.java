package com.beike.wechat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beike.util.DateUtil;
import com.beike.wechat.pojo.CheckInRequest;
import com.beike.wechat.service.CheckInService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckInServiceTest {
	
	@Autowired
	private CheckInService checkInService;
	
	@Test
	public void testCreateUser() {
		CheckInRequest request = new CheckInRequest();
		request.setOpencheckindatatype(3);
		request.setStarttime(DateUtil.getStartTime());
		request.setEndtime(DateUtil.getEndTime());
		request.getUseridlist().add("23035625");
		checkInService.getCheckInData(request);
	}

}
