package com.hqyj.springBoot2001.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.springBoot2001.test.vo.ConfigBean;

@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping("/getAppDesc")
	@ResponseBody
	public String getAppDesc() {
		return "Hello,this is my first create SpringBoot project!";
	}

	/* 读取全局配置文件的信息 */
	@Value("${com.config.login_name}")
	private String login_name;
	@Value("${com.config.password}")
	private int password;

	@RequestMapping("/getConfigTest")
	@ResponseBody
	public String getConfigTest() {
		String username = login_name;
		int psd = password;
		return username + "----" + psd;
	}

	/* 读取非全局配置文件的信息 */
	@Autowired
	private ConfigBean configBean;

	@RequestMapping("/getConfigBeanTest")
	@ResponseBody
	public String getConfigBeanTest() {
		String name = configBean.getName();
		String psd = configBean.getPsd();
		String sex = configBean.getSex();
		int age = configBean.getAge();
		return "姓名：" + name + "<br>" + "密码：" + psd + "<br>" + "性别：" + sex + "<br>" + "年龄：" + age + "<br>";
	}

	/* 测试日志文件 */
	// 1、引入Logger，注意jar包：org.slf4j.Logger;
	// 2、变量名称全大写，如：LOGGER
	// 3、LoggerFactory 注意jar包：org.slf4j.LoggerFactory;
	// 4、getLogger(当前类.class)
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/getLogTest")
	@ResponseBody
	public String getLogTest() {
		/*
		 * 	level: TRACE<DEBUG<INFO<WARN<ERROR 
		 * 	我们在 appender file 的时候，并没有指定日志级别，在此由 root来控制，输出指定级别及之上级别的日志
		 */
		LOGGER.trace("this is TRACE log.");
		LOGGER.debug("this is DEBUG log.");
		LOGGER.info("this is INFO log.");
		LOGGER.warn("this is WARN log.");
		LOGGER.error("this is ERROR log.");
		return "this is log test!!!";
	}
}
