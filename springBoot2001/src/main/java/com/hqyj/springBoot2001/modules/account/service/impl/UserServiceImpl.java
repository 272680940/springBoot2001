package com.hqyj.springBoot2001.modules.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqyj.springBoot2001.modules.account.dao.UserDao;
import com.hqyj.springBoot2001.modules.account.entity.User;
import com.hqyj.springBoot2001.modules.account.service.UserService;
import com.hqyj.springBoot2001.modules.common.vo.Result;
import com.hqyj.springBoot2001.modules.common.vo.Result.ResultEnum;
import com.hqyj.springBoot2001.modules.test.entity.City;

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

	
	@Override
	public Result<User> queryUserByUsernameAndPassword(String userName, String password) {
		Result<User> result = new Result<>(ResultEnum.SUCCESS.status,"select success!");
		userDao.queryUserByUsernameAndPassword(userName,password);
		
		return null;
	}


	@Override
	public Result<User> queryUserByUsernameAndPassword2(User user) {
		Result<User> result = new Result<>(ResultEnum.SUCCESS.status,"select success.");
		try {
			userDao.queryUserByUsernameAndPassword2(user.getUserName(), user.getPassword());
			result.setObject(user);
		} catch (Exception e) {
			result.setStatus(ResultEnum.FAILD.status);
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
