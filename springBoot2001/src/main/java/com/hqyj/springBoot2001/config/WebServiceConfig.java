package com.hqyj.springBoot2001.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//表示一个配置类
@Configuration
//指定顺序
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebServiceConfig{
	//调用全局配置信息
	@Value("${http.port}")
	private int httpPort;
	@Bean
	//重新创建连接器
	public Connector connector(){
		Connector connector = new Connector();
		//采取哪种协议
		connector.setScheme("http");
		//设置端口
		connector.setPort(httpPort);
		return connector;
	}
	@Bean
	//将连接加入
	public ServletWebServerFactory servletWebServerFactory(){
		//实例化
		TomcatServletWebServerFactory tomcatFactory = new TomcatServletWebServerFactory();
		//添加另外的连接器
		tomcatFactory.addAdditionalTomcatConnectors(connector());
		return tomcatFactory;
	}
}