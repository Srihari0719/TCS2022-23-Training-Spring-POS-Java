/**
 * 
 */
package com.tcs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.tcs.constant.SQLConstants;
import com.tcs.exception.*;
import com.tcs.utils.DBUtils;

/**
 * @author Administrator
 *
 */
public class AdminDaoImp implements AdminDaoInterface{
	
	
	DBUtils cc = new DBUtils();
	Connection conn = null;
	PreparedStatement pr = null;
	Scanner sc = new Scanner(System.in);

	/*
	 * @method : approvalForStudent
	 * Admin will approve the student who are registered
	 * 
	 */
	
	@Override
	public void approvalForStudent() throws InvalidInputException {
		
		conn = cc.connect();
		try {
			pr = conn.prepareStatement(SQLConstants.SELECT_USER);
			ResultSet rs = pr.executeQuery();
			int i=0;
			while(rs.next()) {
				if(rs.getInt("isApproved")==0) {
					System.out.println(rs.getString("username"));
					String username  = rs.getString("username");
					System.out.println("1.Approve\n2.Deny");
					int n = sc.nextInt();sc.nextLine();
					i++;
					if(n == 1) {
						PreparedStatement pr1 = conn.prepareStatement(SQLConstants.APPROVED_LOGIN1);
						pr1.setString(1, rs.getString("username"));
						pr1.executeUpdate();
						String name =  username.substring(0, 1).toUpperCase() + username.substring(1, username.indexOf("@gmail.com"));
						
						try {
							PreparedStatement pr2 = conn.prepareStatement(SQLConstants.ADD_STUDENTID);
							ResultSet rs1 = pr2.executeQuery(SQLConstants.MAX_STUDENTID);
							int max1 = 0;
							while(rs1.next()) {
							max1 = rs1.getInt(1)+1;
							}
							pr2.setInt(1, max1);
							pr2.setString(2, username);
							pr2.setString(3, name);
							pr2.executeUpdate();
							pr2.close();
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
						System.out.println("Successfully Approved..");
						pr1.close();
						continue;
					}
					else if(n == 2) {
						PreparedStatement pr1 = conn.prepareStatement(SQLConstants.APPROVED_LOGIN2);
						pr1.setString(1, rs.getString("username"));
						pr1.executeUpdate();
						System.out.println("Denied Student : " + username);
						pr1.close();
						continue;
					}
					else
						throw new InvalidInputException("Please give valid input");
				}
			}
			if(i==0)
				System.out.println("No new Requests..");
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	/*
	 * @method : generateReportCard()
	 * Generates Report Card of every Student
	 * 
	 */
	
	@Override
	public void generateReportCard() {
		
		conn = cc.connect();
		
		try {
			PreparedStatement pr1 = conn.prepareStatement(SQLConstants.SELECT_FAIL);
			ResultSet rs = pr1.executeQuery();
			pr = conn.prepareStatement(SQLConstants.UPDATE_FAIL_RESULT);
			int i=0;
			while(rs.next()) {
				pr.setInt(1, rs.getInt("studentId"));
				pr.executeUpdate();
				i++;
			}
			PreparedStatement pr2 = conn.prepareStatement(SQLConstants.SELECT_PASS);
			int j = 0;
			ResultSet rs1 =  pr2.executeQuery();
			PreparedStatement pr3 = conn.prepareStatement(SQLConstants.UPDATE_PASS_RESULT);
			while(rs1.next()) {
				pr3.setInt(1, rs1.getInt("studentId"));
				j += pr3.executeUpdate();
			}
			System.out.println("Successfully Generated..");
			System.out.println("Number of FAIL Students : " + i);
			System.out.println("Number of PASS Students : " + j);
			pr1.close();
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	/*
	 * @method : addCourse()
	 * It adds courses in the Catalog
	 * 
	 * @param : int courseId,String course,String professor,int sem,float price
	 */
	
	@Override
	public void addCourse(int courseId,String course, String professor, int sem, float price) throws AlreadyExistsException {
		
		conn = cc.connect();
		
		try {
			PreparedStatement pr1 = conn.prepareStatement(SQLConstants.CATALOG_VALUES);
			ResultSet rs = pr1.executeQuery();
			while(rs.next()) {
				if(rs.getInt("courseId")==courseId)
					throw new AlreadyExistsException(courseId);
			}
			pr = conn.prepareStatement(SQLConstants.ADD_COURSE);
			pr.setInt(1, courseId);
			pr.setString(2, course);
			pr.setString(3, professor);
			pr.setFloat(4, price);
			pr.setInt(5, sem);
			pr.executeUpdate();
			System.out.println("Successfully Added.");
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
	}

	/*
	 * @method : addProfessor()
	 * It adds Professors in the Professor
	 * 
	 * @param : String usernameP,String passwordP
	 */
	
	@Override
	public void addProfessor(String usernameP, String passwordP) throws AlreadyExistsException {
		
		conn = cc.connect();
		
		try {
			PreparedStatement pr1 = conn.prepareStatement(SQLConstants.SELECT_USER);
			ResultSet rs1 = pr1.executeQuery();
			while(rs1.next()) {
				if(rs1.getString("username").equals(usernameP))
					throw new AlreadyExistsException("Professor Already Exists..");
			}
			pr = conn.prepareStatement(SQLConstants.ADD_PROFESSOR);
			pr.setString(1, usernameP);
			pr.setString(2, passwordP);
			pr.setString(3, "professor");
			pr.setInt(4, 1);
			pr.executeUpdate();
			System.out.println("Successfully Added..");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		
		String pName =  usernameP.substring(0, 1).toUpperCase() + usernameP.substring(1, usernameP.indexOf("@gmail.com"));
	
		try {
			pr = conn.prepareStatement(SQLConstants.PROFESSOR);
			ResultSet rs = pr.executeQuery();
			int max1 = 0;
			while(rs.next()) {
			max1 = rs.getInt(1)+1;
			}
			pr.setInt(1, max1);
			pr.setString(2, usernameP);
			pr.setString(3, pName);
			pr.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
	}

	/*
	 * @method : removeCourse()
	 * It removes courses in the Catalog
	 * 
	 * @param : String course,String professor
	 */
	
	@Override
	public void removeCourse(String course, String professor) throws CourseNotFoundException {
		
		conn = cc.connect();
		boolean rc = false;
		try {
			PreparedStatement pr1 = conn.prepareStatement(SQLConstants.CATALOG_VALUES);
			ResultSet rs = pr1.executeQuery();
			while(rs.next()) {
				if(rs.getString("courseName").equals(course) && rs.getString("profName").equals(professor))
					rc = true;
			}
			if(!rc)
				throw new CourseNotFoundException("Course Name '" + course + "' by professor '" + professor +"' Not Found");
			pr = conn.prepareStatement(SQLConstants.REMOVE_COURSE);
			pr.setString(1, course);
			pr.setString(2, professor);
			pr.executeUpdate();
			System.out.println("Successfully Removed.");
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
