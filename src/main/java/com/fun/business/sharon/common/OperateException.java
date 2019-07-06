package com.fun.business.sharon.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OperateException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;
	
	public OperateException(String message){
		super(message);
	}
	
	public OperateException(String message, int code){
		super(message);
		this.code = code;
	}
	
	public OperateException(String message, int code,Throwable e) {
		super(message,e);
		this.code = code;
	}
	
	public OperateException(Throwable e){
		super(e);
	}
	
	public OperateException(String message, Throwable e){
		super(message, e);
	}
	
	
}
