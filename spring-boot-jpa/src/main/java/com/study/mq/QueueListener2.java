package com.study.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class QueueListener2 {
	
	@JmsListener(destination = "publish.queue", containerFactory = "jmsListenerContainerQueue")
    public void receive(String text){
        System.out.println("QueueListener: consumer-b 收到一条信息: " + text);
    }
}
