package com.hqyj.springBoot2001.modules.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.springBoot2001.modules.account.entity.User;
import com.hqyj.springBoot2001.modules.account.service.UserService;
import com.hqyj.springBoot2001.modules.common.vo.Result;


@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
		新增：用@PostMapping
		post请求使用注解【@PostMapping】
		【consumes】设置进入接口的参数格式
		【application/json】表示json格式
		参数格式是json,则方法中接收参数的注解为【@RequestBody】
		localhost/api/user
	 */
	@PostMapping(value="/user",consumes="application/json")
	public Result<User> insertUser(@RequestBody User user) {
		return userService.insertUser(user);
	}
	
	
	/**
	 * 通过userId删除用户
	 * localhost/api/deleteUserByUserId/3
	 */
	@DeleteMapping("/deleteUserByUserId/{userId}")
	public Result<Object> deleteUserByUserId(@PathVariable int userId){
		return userService.deleteUserByUserId(userId);
	}
	
	
	//修改
	/**
		修改：使用注解【@PutMapping】
		【consumes】设置进入接口的参数格式
		【application/x-www-form-urlencoded】表示form表单提交
		参数格式是form表单，则方法中接收city的注解为【@ModelAttribute】
		localhost/api/user
	 */
	@PutMapping(value="/user",consumes="application/x-www-form-urlencoded")
	public Result<User> updateUser(@ModelAttribute User user) {
		return userService.updateUser(user);
	}
	
	/**
	 * 查询所有用户
	 * localhost/api/queryAllUser
	 */
	@RequestMapping("/queryAllUser")
	public List<User> queryAllUser(){
		return userService.queryAllUser();
	}
	
	
	/**
	 * 通过userId查询所有用户
	 * localhost/api/queryUserByUserId/3
	 */
	@RequestMapping("/queryUserByUserId/{userId}")
	public List<User> queryUserByUserId(@PathVariable int userId){
		return userService.queryUserByUserId(userId);
	}
	
	
}
