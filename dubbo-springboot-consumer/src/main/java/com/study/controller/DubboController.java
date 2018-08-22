package com.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.study.service.DubboService;

@RestController
@RequestMapping("/dubbo")
public class DubboController {
	
	@Reference(version = "1.0.0")
    DubboService dubboService;

    @RequestMapping("sayHello")
    public void sayHello() {      
        dubboService.sayHello("consumer 请求服务");
        
    }
}
