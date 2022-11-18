/**
 * 
 */
package com.tcs.exception;

/**
 * @author Administrator
 *
 */
public class AlreadyExistsException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String msg;
	private int id;
	
	public AlreadyExistsException(String msg) {
		this.msg=msg;
	}
	
	public AlreadyExistsException(int id) {
		super();
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public int getId() {
		return id;
	}
}
