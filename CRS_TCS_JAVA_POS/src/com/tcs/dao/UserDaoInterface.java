/**
 * 
 */
package com.tcs.dao;

import com.tcs.exception.AlreadyExistsException;
import com.tcs.exception.CredentialMismatchException;
import com.tcs.exception.UserNotFoundException;

/**
 * @author Administrator
 *
 */
public interface UserDaoInterface {

	public void userLogin(String username, String password, String role) throws UserNotFoundException, CredentialMismatchException;
	public void addStudent(String username,String password) throws AlreadyExistsException;
	public void updatePassword(String username,String oldPassword,String newPassword,String role);
}
