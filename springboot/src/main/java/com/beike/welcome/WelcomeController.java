package com.beike.welcome;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@Value("${welcome.message}")
    private String message = "Hello World";

	@GetMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "welcome";
    }
}
