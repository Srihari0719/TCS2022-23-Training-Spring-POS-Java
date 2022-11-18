/**
 * 
 */
package com.tcs.exception;

/**
 * @author Administrator
 *
 */
public class CourseNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	private String msg;
	private int id;
	
	public CourseNotFoundException(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public CourseNotFoundException(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
}
