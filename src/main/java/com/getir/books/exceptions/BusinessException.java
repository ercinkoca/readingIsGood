package com.getir.books.exceptions;

public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1888950703972823915L;
	
	public BusinessException(int errCode,String errMessage) {
		super(errMessage);
		this.errCode = errCode;
		this.errMessage = errMessage;
	}
	
	private final int errCode;
	private final String errMessage;

	public int getErrCode() {
		return errCode;
	}
	public String getErrMessage() {
		return errMessage;
	}
	

}
