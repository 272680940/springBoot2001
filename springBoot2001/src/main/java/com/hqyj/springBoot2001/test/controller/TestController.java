package com.hqyj.springBoot2001.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@RequestMapping("/test/getAppDesc")
	@ResponseBody
	public String getAppDesc() {
		
		return "Hello,this is my first create SpringBoot project!";
	}
}
