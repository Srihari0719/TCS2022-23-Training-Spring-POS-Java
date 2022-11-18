/**
 * 
 */
package com.tcs.exception;

/**
 * @author Administrator
 *
 */
public class SemesterNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int semester;
	private String msg;
	
	public SemesterNotFoundException(int semester) {
		this.semester = semester;
	}
	
	public int getSemester() {
		return semester;
	}

	public SemesterNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
	
}
