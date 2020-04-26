package com.hqyj.springBoot2001.test.dao.account.user;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.springBoot2001.test.model.account.entity.User;

@Mapper
public interface UserDao {
	
	//添加User
	@Insert("insert into user(user_id,user_name,password,create_date)"
			+ " values(#{userId},#{userName},#{password},#{createDate})")
	//添加数据后将其userId返回
	@Options(useGeneratedKeys = true, keyColumn = "user_id", keyProperty = "userId")
	void insertUser(User user);
	
	
	//删除User
	@Delete("delete from user where user_id=#{userId}")
	void deleteUser(int userId);
	
	
	//修改User
	@Update("update user set user_name=#{userName},password=#{password},"
			+ "create_date=#{createDate} where user_id=#{userId}")
	void updateUser(User user);

	
	
	//查询所有的用户
	@Select("select * from user")
	List<User> queryAllUser();

	//通过userId查询
	@Select("select * from user where user_id = #{userId}")
	List<User> queryUserByUserId(int userId);
	
}
