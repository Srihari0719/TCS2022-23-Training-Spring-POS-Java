/*
 * 
 */
package com.tcs.service;

import com.tcs.dao.*;
import com.tcs.exception.AlreadyExistsException;
import com.tcs.exception.CourseNotFoundException;
import com.tcs.exception.GradeNotFoundException;
import com.tcs.exception.InvalidInputException;
import com.tcs.exception.SemesterNotFoundException;

/**
 * @author Administrator
 *
 */
public class StudentServiceImp implements StudentServiceInterface{
	
	StudentDaoInterface sdi = new StudentDaoImp();
	
	/*
	 * @method : addCourse()
	 * It add course in the course list of enrolled courses of the Student
	 * 
	 * @param : int courseId,String courseName,String professor,String username
	 */
	
	@Override
	public void addCourse(int courseId,String courseName,String professor,String username) {
		
		try {
			sdi.addCourse(courseId,courseName,professor,username);
		}
		catch(SemesterNotFoundException e) {
			//Displays SemesterNotFoundException Message if semester not registered by Student
			System.out.println(e.getMsg());
		} 
		catch (AlreadyExistsException e) {
			//Displays AlreadyExistsException if the Student try to register for already registered courses
			System.out.println(e.getId() + " Course Already Exists");
		}
	}
	
	/*
	 * @method : dropCourse()
	 * It drops course in the course list of enrolled courses of the student
	 * 
	 * @param : String courseName,String professor,String username
	 */
	
	@Override
	public void dropCourse(String courseName,String professor,String username) {
		
		try {
			sdi.dropCourse(courseName, professor,username);
		} catch (CourseNotFoundException e) {
			//Displays CourseNotFoundException if the course not found in Student registered courses
			System.out.println(e.getMsg());
		}
	}

	/*
	 * @method : viewReportCard()
	 * It displays Report Card of particular student
	 * 
	 * @param : String username
	 */
	
	@Override
	public void viewReportCard(String username) {
		
		try {
			sdi.viewReportCard(username);
		} catch (GradeNotFoundException e) {
			//Displays GradeNotFoundException  if no results are found
			System.out.println(e.getMsg());
		}
	}

	/*
	 * @method : SemesterRegistration()
	 * To register or update the semester for Student 
	 * 
	 * @param : String username, int semester
	 */
	
	@Override
	public void semesterRegistration(String username,int semester) {
		
		try {
			sdi.semesterRegistration(username, semester);
		} catch (SemesterNotFoundException e) {
			// Displays SemesterNotFoundException Message if Student try to register invalid semester
			System.out.println(e.getMsg());
		}
		
	}

	/*
	 * @method : payFee()
	 * The method is used to make payment of Student for registered courses
	 * 
	 * @param : String username
	 */
	
	@Override
	public void payFee(String username) {
		// TODO Auto-generated method stub
		try {
			sdi.payFee(username);
		} catch (InvalidInputException e) {
			// Displays InvalidInputException Message if Student select other payment modes
			System.out.println(e.getMsg());
		} catch (CourseNotFoundException e) {
			// Displays CourseNotFoundException Message if Student not registered for any course
			System.out.println(e.getMsg());
		}
	}

}
