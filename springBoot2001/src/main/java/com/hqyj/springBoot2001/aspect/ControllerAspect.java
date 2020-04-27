package com.hqyj.springBoot2001.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


//1、加上注解【@Aspect】、【@Component】
@Aspect		//表示切面
@Component	//注册为springboot组件
public class ControllerAspect {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
	
	/**
	 * 2、注解【@Pointcut】
	 * 关联在方法上的切点
	 * 第一个【*】表示返回类型不限
	 * 第二个【*】表示test下的所有子包
	 * 第三个【*】表示所有类
	 * 第四个【*】表示所有方法
	 *(..)表示参数不限
	 * 注解【@Order】代表优先级，数字越小优先级越高
	 */
	@Pointcut("execution(public * com.hqyj.springBoot2001.test.*.controller.*.*(..))")
	@Order(1)
	public void  controllerPointCut() {}
	
	//此处的注解【@Before】要与上方的controllerPointCut()方法绑定
	@Before(value = "com.hqyj.springBoot2001.aspect.ControllerAspect.controllerPointCut()")
	public void beforeController(JoinPoint joinPoint) {
		LOGGER.debug("=======================");
		LOGGER.debug("Before controller");
		ServletRequestAttributes attributes = 
				(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		LOGGER.debug("请求来源：" + request.getRemoteAddr());
		LOGGER.debug("请求URL：" + request.getRequestURL().toString());
		LOGGER.debug("请求方式：" + request.getMethod());
		LOGGER.debug("响应方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." + 
				joinPoint.getSignature().getName());
		LOGGER.debug("请求参数：" + Arrays.toString(joinPoint.getArgs()));
	}
	
	/**
	 * -环绕通知 = 前置 + 目标方法执行 + 后置通知
	 * ProceedingJoinPoint 继承了 JoinPoint，是在 JoinPoint 的基础上暴露出  proceed 这个方法，
	 * -这个是 aop 代理链执行的方法， 执行proceed方法的作用是让目标方法执行，这也是环绕通知和前置、后置通知方法的一个最大区别;
	 */
	//此处的注解【@Around】要与上方的controllerPointCut()方法绑定
	@Around(value="com.hqyj.springBoot2001.aspect.ControllerAspect.controllerPointCut()")
	public Object aroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		LOGGER.debug("Around controller");
		return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
	}

	/**
	 * -后置通知
	 */
	//此处的注解【@After】要与上方的controllerPointCut()方法绑定
	@After(value="com.hqyj.springBoot2001.aspect.ControllerAspect.controllerPointCut()")
	public void afterController() {
		LOGGER.debug("After Controller.");
		LOGGER.debug("=======================");
	}
}
