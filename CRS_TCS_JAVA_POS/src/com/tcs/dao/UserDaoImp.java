/**
 * 
 */
package com.tcs.dao;

import java.sql.*;

import com.tcs.constant.SQLConstants;
import com.tcs.exception.AlreadyExistsException;
import com.tcs.exception.CredentialMismatchException;
import com.tcs.exception.UserNotFoundException;
import com.tcs.utils.DBUtils;


/**
 * @author Administrator
 *
 */
public class UserDaoImp implements UserDaoInterface{
	
	DBUtils cc = new DBUtils();
	Connection conn = null;
	PreparedStatement pr = null;
	
	/*
	 * @method : userLogin()
	 * To very the credentials of the User
	 * 
	 * @method : String username, String Password, String role)
	 */
	@Override
	public void userLogin(String username, String password, String role) throws UserNotFoundException, CredentialMismatchException {
		// TODO Auto-generated method stub
		
		boolean ul = false;
		conn = cc.connect();
		try {
			pr = conn.prepareStatement(SQLConstants.USER_LOGIN);
			ResultSet rs = pr.executeQuery();
			while(rs.next()) {
				if(rs.getString("username").equals(username) && rs.getString("password").equals(password) && rs.getString("role").equalsIgnoreCase(role) && rs.getInt("isApproved")==1) {
					ul = true;
					break;
				}
				else if(rs.getInt("isApproved")==2 && rs.getString("username").equals(username)) {
					throw new UserNotFoundException("You are denied by Admin. Contact Admin for further details..");
				}
			}
			if(!ul)
				throw new CredentialMismatchException("Invalid Credentials. Please give valid input");
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	/*
	 * @method : addStudent()
	 * To register the Students and sending for approval to Admin
	 * 
	 * @param : String username, String Password
	 */
	@Override
	public void addStudent(String username, String password) throws AlreadyExistsException {
		
		conn = cc.connect();
		try {
			PreparedStatement pr1 = conn.prepareStatement(SQLConstants.SELECT_USER);
			ResultSet rs1 = pr1.executeQuery();
			while(rs1.next()) {
				if(rs1.getString("username").equals(username))
					throw new AlreadyExistsException("Username Already Exists..");
			}
			pr = conn.prepareStatement(SQLConstants.ADD_STUDENT);
			pr.setString(1, username);
			pr.setString(2, password);
			pr.setString(3, "student");
			pr.setInt(4, 0);
			pr.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	/*
	 * @method : updatePassword()
	 * To change the password of a User
	 * 
	 * @param : String username, String oldPassword, String newPassword, String role
	 */
	@Override
	public void updatePassword(String username, String oldPassword, String newPassword, String role) {
		
		conn = cc.connect();
		try {
			pr = conn.prepareStatement(SQLConstants.UPDATE_PASSWORD);
			pr.setString(1, newPassword);
			pr.setString(2, username);
			pr.setString(3, oldPassword);
			pr.setString(4, role);
			pr.executeUpdate();
			System.out.println("Successfully Updated..");
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
