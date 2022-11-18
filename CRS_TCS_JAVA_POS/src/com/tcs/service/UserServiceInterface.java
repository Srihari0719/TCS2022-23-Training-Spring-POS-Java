/**
 * 
 */
package com.tcs.service;

import com.tcs.exception.InvalidInputException;

/**
 * @author Administrator
 *
 */
public interface UserServiceInterface {
	public void userLogin(String username, String password, String role);
	public void adminLogin(String username, String password);
	public void professorLogin(String username, String password)throws InvalidInputException;
	public void studentLogin(String username, String password);
}
