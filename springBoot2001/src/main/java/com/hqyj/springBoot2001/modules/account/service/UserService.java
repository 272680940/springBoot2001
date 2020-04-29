package com.hqyj.springBoot2001.modules.account.service;

import java.util.List;

import com.hqyj.springBoot2001.modules.account.entity.User;
import com.hqyj.springBoot2001.modules.common.vo.Result;


public interface UserService {
	
	//添加User
	Result<User> insertUser(User user);
	
	//删除User
	Result<Object> deleteUserByUserId(int userId);
	
	//修改User
	Result<User> updateUser(User user);

	//查询User
	List<User> queryAllUser();

	//通过userId查询
	List<User> queryUserByUserId(int userId);

	
}
