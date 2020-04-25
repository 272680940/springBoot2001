package com.hqyj.springBoot2001.test.model.account.entity;

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
@Table(name="resource")
public class Resource {
	@Id //设置主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) //设置增长方式
	private int resourceId;
	private String resourceUri;
	private String resourceName;
	private String permission;

	@Transient //该注解修饰的属性不参与自动生成表里面
	private List<Role> roles;

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceUri() {
		return resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
