package com.hqyj.springBoot2001.test.servce.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqyj.springBoot2001.common.Result;
import com.hqyj.springBoot2001.common.Result.ResultEnum;
import com.hqyj.springBoot2001.test.dao.account.user.UserDao;
import com.hqyj.springBoot2001.test.model.account.entity.User;
import com.hqyj.springBoot2001.test.servce.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional //开启事务
	public Result<User> insertUser(User user) {
		Result<User> result = new Result<>(ResultEnum.SUCCESS.status,"Insert success!");
		userDao.insertUser(user);//调用Dao层方法
		result.setObject(user);//返回对象
		return result;
	}

	
	@Override
	@Transactional //开启事务
	public Result<User> updateUser(User user) {
		Result<User> result = new Result<>(ResultEnum.SUCCESS.status,"Update success!");
		userDao.updateUser(user);
		return result;
	}


	@Override
	public List<User> queryAllUser() {
		return userDao.queryAllUser();
	}


	@Override
	public List<User> queryUserByUserId(int userId) {
		return userDao.queryUserByUserId(userId);
	}


	@Override
	@Transactional //开启事务
	public Result<Object> deleteUserByUserId(int userId) {
		Result<Object> result = new Result<>(ResultEnum.SUCCESS.status,"Delete success!");
		userDao.deleteUser(userId);
		return result;
	}
	
	

}
