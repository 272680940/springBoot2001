package com.hqyj.springBoot2001.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class UrlInterceptor implements HandlerInterceptor {

	private final static Logger LOGGER = LoggerFactory.getLogger(UrlInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.debug("Pre -------------------");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.debug("Post -------------------");

		//获取路径
		String uri = request.getServletPath();
		//获取参数template
		String template = (String) modelAndView.getModelMap().get("template");
		//判断template是否为空:
		if (template == null  || template == "") {
			if (uri.startsWith("/")) {
				uri = uri.substring(1);
			}
			modelAndView.getModelMap().put("template", uri);
		}

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LOGGER.debug("After -------------------");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}