/**
 * 
 */
package com.tcs.exception;

/**
 * @author Administrator
 *
 */
public class GradeNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public GradeNotFoundException(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
}
