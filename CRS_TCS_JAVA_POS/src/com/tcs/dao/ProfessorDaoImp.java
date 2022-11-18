/**
 * 
 */
package com.tcs.dao;

import java.sql.*;
import java.util.Scanner;

import com.tcs.constant.SQLConstants;
import com.tcs.exception.GradeNotFoundException;
import com.tcs.exception.UserNotFoundException;
import com.tcs.utils.DBUtils;

/**
 * @author Administrator
 *
 */
public class ProfessorDaoImp implements ProfessorDaoInterface{
	
	DBUtils cc = new DBUtils();
	Connection conn = null;
	PreparedStatement pr = null;
	Scanner sc = new Scanner(System.in);
	
	/*
	 * @method : addGrades()
	 * It add grades to the respective Students by the professor for his courses
	 * 
	 * @param : String username
	 */
	
	@Override
	public void addGrades(String username) throws GradeNotFoundException, UserNotFoundException {
		// TODO Auto-generated method stub
		conn = cc.connect();
		boolean a = false;
		PreparedStatement pr1 = null;
		int i=0;
		try {
			pr = conn.prepareStatement(SQLConstants.LIST_ENROLLED_STUDENTS);
			pr.setString(1, username);
			ResultSet rs = pr.executeQuery();
			while(rs.next()) {
				PreparedStatement stmt = conn.prepareStatement(SQLConstants.LIST_GRADE);
				stmt.setInt(1, rs.getInt("Id"));
				stmt.setInt(2, rs.getInt("courseId"));
				ResultSet rs3 = stmt.executeQuery();
				while(rs3.next()) {
					if(rs3.getInt(1)>0) {
						a=true;
					}
				}
				if(a) {
					continue;
				}
				System.out.println("Student ID : " + rs.getInt("Id")+"\tStudent Name : " + rs.getString("name")+"\tCourse Name : "+rs.getString("courseName"));
				System.out.println("Enter Grade option(1.A+,2.A,3.B+,4.B,5.C,6.F,7.AB) : ");
				int grade = sc.nextInt();sc.nextLine();
				if(grade>7)
					throw new GradeNotFoundException("Grade not exists");
				pr1 = conn.prepareStatement(SQLConstants.GRADECARD);
				pr1.setInt(1, rs.getInt("Id"));
				pr1.setInt(2, rs.getInt("courseId"));
				pr1.setInt(3, grade);
				pr1.executeUpdate();
				i++;
			}
			if(i==0)
				throw new UserNotFoundException("No Students found");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * @method : viewCourseEnrolledStudents
	 * Displays the List of Students who enrolled for the courses taught by professor
	 * 
	 * @param : String username
	 */
	
	@Override
	public void viewCourseEnrolledStudents(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		conn = cc.connect();
		try {
			boolean isThere = false;
			pr = conn.prepareStatement(SQLConstants.LIST_ENROLLED_STUDENTS);
			pr.setString(1, username);
			ResultSet rs = pr.executeQuery();
			while(rs.next()) {
				isThere = true;
				System.out.println("Student ID : " + rs.getInt("Id")+"\tStudent Name : " + rs.getString("name")+"\tCourse Name : "+rs.getString("courseName"));
			}
			if(!isThere)
				throw new UserNotFoundException("No students enrolled for the teaching courses");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
