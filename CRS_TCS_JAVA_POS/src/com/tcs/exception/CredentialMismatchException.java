/**
 * 
 */
package com.tcs.exception;

/**
 * @author Administrator
 *
 */
public class CredentialMismatchException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public CredentialMismatchException(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
}
