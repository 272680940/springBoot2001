package com.hqyj.springBoot2001.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用注解【@WebFilter】设置过滤URL 
 * 注解参数：
 * 	 filterName 过滤器名称 
 * 	 urlPatterns 指定过滤的URL
 */
@WebFilter(filterName = "parameterFilter", urlPatterns = "/**")
public class ParameterFilter implements Filter {
	// 日志打印
	private final static Logger LOGGER = LoggerFactory.getLogger(ParameterFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("Init ParameterFilter.");
		Filter.super.init(filterConfig);
	}

	@Override
	public void destroy() {
		LOGGER.debug("Destroy ParameterFilter.");
		Filter.super.destroy();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.debug("ParameterFilter Action.");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		//测试
		//Map<String, String[]> maps = httpRequest.getParameterMap();
		//maps.put("key", new String[]{"*****"});

		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest) {

			@Override
			public String getParameter(String name) {
				String value = httpRequest.getParameter(name);
				if (value != null && value != "") {
					return value.replaceAll("fuck", "***");
				}
				return super.getParameter(name);
			}

			@Override
			public String[] getParameterValues(String name) {
				String[] values = httpRequest.getParameterValues(name);
				if (values != null && values.length > 0) {
					values[0] = values[0].replaceAll("fuck", "***");
					return values;
				}
				return super.getParameterValues(name);
			}

		};

		chain.doFilter(wrapper, response);
	}

}
