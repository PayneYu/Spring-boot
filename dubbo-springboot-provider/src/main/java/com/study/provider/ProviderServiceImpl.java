package com.study.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.study.service.DubboService;

@Service(version = "1.0.0")
public class ProviderServiceImpl implements DubboService {
	public String sayHello(String name) {
		System.out.println("Dubbo Provider:"+name);
		return "Hello:"+name+"你好，你好~~";  
	}
}
