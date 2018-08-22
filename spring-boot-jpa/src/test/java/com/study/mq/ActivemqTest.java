package com.study.mq;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivemqTest {
	
	@Autowired
	private Producer producer;
	@Test
	public void contextLoads() throws InterruptedException {
		Destination destination = new ActiveMQQueue("single.queue");
		for(int i=0; i<1; i++){
			producer.sendMessage(destination, "myname is payne!!!");
		}
	}
}
