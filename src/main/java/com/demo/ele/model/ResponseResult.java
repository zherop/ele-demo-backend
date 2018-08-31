package com.demo.ele.model;

/**
 * 接口返回响应结果值对象
 * 
 * @author 曾鹏
 * @mail zp@zving.com
 * @date 2018年4月22日
 */
public class ResponseResult {
	public static int SUCCESS = 1;
	public static int FAIL = 0;

	private int status;
	private String message;
	private Object data;

	public ResponseResult(int status, String message) {
		this(status, message, null);
	}

	public ResponseResult(Object data) {
		this(SUCCESS, "操作成功！", data);
	}

	public ResponseResult(int status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
