/**
 * 
 */
package com.tcs.dao;

import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;

import com.tcs.constant.SQLConstants;
import com.tcs.exception.*;
import com.tcs.utils.DBUtils;

/**
 * @author Administrator
 *
 */
public class StudentDaoImp implements StudentDaoInterface{
	
	DBUtils cc = new DBUtils();
	Connection conn = null;
	PreparedStatement pr = null;
	Scanner sc = new Scanner(System.in);

	/*
	 * @method : addCourse()
	 * It add course in the course list of enrolled courses of the Student
	 * 
	 * @param : int courseId,String courseName,String professor,String username
	 */
	
	@Override
	public void addCourse(int courseId,String courseName, String professor, String username) throws SemesterNotFoundException, AlreadyExistsException {
		
		conn = cc.connect();
		int sId =0,semester=0;
		try {
			PreparedStatement pr1 = conn.prepareStatement(SQLConstants.STUDENT);
			PreparedStatement pr2 = conn.prepareStatement(SQLConstants.CATALOG);
			pr1.setString(1, username);
			pr2.setString(1, username);
			ResultSet rs = pr1.executeQuery();
			ResultSet rs1 = pr2.executeQuery();
			
			while(rs.next()) {
				sId = rs.getInt(1);
				semester = rs.getInt("semester");
				if(semester == 0)
					throw new SemesterNotFoundException("Please Update Semester in Semester Registration");
			}
			while(rs1.next()) {
				if(rs.getInt("courseId")==courseId)
					throw new AlreadyExistsException(courseId);
			}
			pr = conn.prepareStatement(SQLConstants.COURSE_REGISTRATION);
			pr.setInt(1, courseId);
			pr.setString(2, courseName);
			pr.setString(3, professor);
			pr.setInt(4, sId);
			pr.executeUpdate();
			System.out.println("Successfully Added.");
			pr1.close();
			pr2.close();
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/*
	 * @method : dropCourse()
	 * It drops course in the course list of enrolled courses of the student
	 * 
	 * @param : String courseName,String professor,String username
	 */
	
	@Override
	public void dropCourse(String courseName, String professor, String username) throws CourseNotFoundException {
		
		conn = cc.connect();
		boolean dc = false;
		try {
			PreparedStatement pr1 = conn.prepareStatement(SQLConstants.REGISTERED_COURSES);
			pr1.setString(1, username);
			ResultSet rs1 = pr1.executeQuery();
			while(rs1.next()) {
				if(rs1.getString("courseName").equals(courseName) && rs1.getString("pName").equals(professor))
					dc = true;
			}
			if(!dc)
				throw new CourseNotFoundException("Course " +rs1.getString("courseName")+ " with " + rs1.getString("pName") +" Not Found");
			pr = conn.prepareStatement(SQLConstants.DROP_STUDENT_COURSE);
			pr.setString(1, courseName);
			pr.setString(2, professor);
			pr.setString(3, username);
			pr.executeUpdate();
			System.out.println("Successfully Removed.");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * @method : viewReportCard()
	 * It displays Report Card of particular student
	 * 
	 * @param : String username
	 */
	
	@Override
	public void viewReportCard(String username) throws GradeNotFoundException {
		
		conn=cc.connect();

		try {
			pr = conn.prepareStatement(SQLConstants.VIEW_REPORT);
			pr.setString(1, username);
			ResultSet rs = pr.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
				System.out.println(rs.getInt("courseId")+"-" + rs.getString("courseName") + "-" + rs.getString("professorName")+"-"+rs.getString("grade"));
			}
			if(i==0)
				throw new GradeNotFoundException("No Grades are Found for you..");
			PreparedStatement pr1 = conn.prepareStatement(SQLConstants.STUDENT);
			pr1.setString(1, username);
			rs = pr1.executeQuery();
			while(rs.next()) {
				System.out.println("Overall Result : " + rs.getString("result"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * @method : SemesterRegistration()
	 * To register or update the semester for Student 
	 * 
	 * @param : String username, int semester
	 */
	
	@Override
	public void semesterRegistration(String username, int semester) throws SemesterNotFoundException {
		
		conn = cc.connect();
		try {
			if(semester>4 && semester<1)
				throw new SemesterNotFoundException("Invalid Semster. Enter valid semster..");
			pr = conn.prepareStatement(SQLConstants.SEMESTER_UPDATE);
			pr.setInt(1, semester);
			pr.setString(2, username);
			pr.executeUpdate();
			System.out.println("Successfully Updated..");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * @method : payFee()
	 * The method is used to make payment of Student for registered courses
	 * 
	 * @param : String username
	 */
	
	@Override
	public void payFee(String username) throws InvalidInputException, CourseNotFoundException {
		
		conn = cc.connect();
		float bill = 0;
		int sId=0;
		
		try {
			PreparedStatement pr1 = conn.prepareStatement(SQLConstants.STUDENT);
			pr1.setString(1, username);
			ResultSet rs1 = pr1.executeQuery();
			while(rs1.next()) {
				sId = rs1.getInt("Id");
			}
			pr1.close();
			pr = conn.prepareStatement(SQLConstants.FETCH_BILL);
			pr.setString(1, username);
			ResultSet rs = pr.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getFloat("bill"));
				bill = rs.getFloat(1);
			}
			System.out.println("Your Bill : " + bill);
			if(bill==0)
				throw new CourseNotFoundException("No Courses Registered..");
			System.out.println("Choose Payment Mode : ");
			System.out.println("1.Online/UPI Payment\n2.Credit/Debit Card\n3.Netbanking\n4.Pay by Cash");
			int n = sc.nextInt();sc.nextLine();
			String mode = null;
			switch(n) {
			case 1: mode = "Online/UPI Payment";break;
			case 2: mode = "Credit/Debit Card";break;
			case 3: mode = "Netbanking";break;
			case 4: mode = "Pay by Cash";break;
			default:throw new InvalidInputException("Invalid Payment");
			}
			PreparedStatement pr2 = conn.prepareStatement(SQLConstants.PAYMENT);
			pr2.setInt(1, sId);
			pr2.setFloat(2, bill);
			pr2.setString(3, mode);
			pr2.executeUpdate();
			pr2.close();
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
