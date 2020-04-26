package com.hqyj.springBoot2001.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hqyj.springBoot2001.filter.ParameterFilter;
import com.hqyj.springBoot2001.interceptor.UrlInterceptor;



@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private UrlInterceptor urlInterceptor;

	//过滤器的注册器
	@Bean
	public FilterRegistrationBean<ParameterFilter> filterRegistrationBean () {
		FilterRegistrationBean<ParameterFilter> filterRegister = new FilterRegistrationBean<>();
		filterRegister.setFilter(new ParameterFilter());
		return filterRegister;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(urlInterceptor).addPathPatterns("/**");
	}
}