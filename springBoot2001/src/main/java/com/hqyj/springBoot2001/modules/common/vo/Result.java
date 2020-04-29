package com.hqyj.springBoot2001.modules.common.vo;

/**
 * 将增删改查的返回信息封装
 */
public class Result<T> {
	//状态值，如200,404,500
	private int status;
	//回馈信息
	private String message;
	//将对象传到前端
	private T object;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}

	public Result(int status, String message, T object) {
		this.status = status;
		this.message = message;
		this.object = object;
	}
	
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Result(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}




	//枚举
	public enum ResultEnum {
		SUCCESS(200), FAILD(500);
		
		public int status;

		private ResultEnum(int status) {
			this.status = status;
		}
	}
}
