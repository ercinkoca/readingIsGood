package com.getir.books.model;

import java.io.Serializable;

public class ApiResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -905907618272233471L;

	private int code;
	private String message;
	private Object data;

	public ApiResponse(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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
