/**
 * 
 */
package com.tcs.exception;

/**
 * @author Administrator
 *
 */
public class PasswordMatchException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public PasswordMatchException(String msg) {
		super();
		this.msg = msg;
	}
	
}
