package com.hqyj.springBoot2001.test.servce;

import java.util.List;

import com.hqyj.springBoot2001.common.Result;
import com.hqyj.springBoot2001.test.model.account.entity.User;

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
