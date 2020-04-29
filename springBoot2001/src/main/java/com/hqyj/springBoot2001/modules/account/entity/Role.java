package com.hqyj.springBoot2001.modules.account.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

//添加注解【@Entity】，表示它是一个实体bean
@Entity
//指定表名称【@Table(name="表名")】
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	private String roleName;
	@Transient // 该注解修饰的属性不参与自动生成表里面
	private List<User> users;
	@Transient // 该注解修饰的属性不参与自动生成表里面
	private List<Resource> resource;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Resource> getResource() {
		return resource;
	}

	public void setResource(List<Resource> resource) {
		this.resource = resource;
	}
}
