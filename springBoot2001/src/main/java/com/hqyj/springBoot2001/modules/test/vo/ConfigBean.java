package com.hqyj.springBoot2001.modules.test.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//将该类注册为spring的组件
@Component
//与对应的配置文件相绑定
@PropertySource("classpath:config/applicationTest.properties")
//前缀
@ConfigurationProperties(prefix="com.config")
public class ConfigBean {
	private String name;
	private String psd;
	private String sex;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPsd() {
		return psd;
	}
	public void setPsd(String psd) {
		this.psd = psd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
