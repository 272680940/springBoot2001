package com.hqyj.springBoot2001.modules.account.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

//添加注解【@Entity】，表示它是一个实体bean
@Entity
//指定表名称【@Table(name="表名")】
@Table(name = "user")
public class User {
	@Id // 设置主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 设置增长方式
	private int userId;
	private String userName;
	private String password;
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;

	@Transient // 该注解修饰的属性不参与自动生成表里面
	private boolean rememberMe;
	@Transient // 该注解修饰的属性不参与自动生成表里面
	private List<Role> roles;

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
