/**
 * 
 */
package com.tcs.service;

import java.util.Scanner;

import com.tcs.dao.AdminDaoImp;
import com.tcs.dao.AdminDaoInterface;
import com.tcs.exception.AlreadyExistsException;
import com.tcs.exception.CourseNotFoundException;
import com.tcs.exception.InvalidInputException;

/**
 * @author Administrator
 *
 */
public class AdminServiceImp implements AdminServiceInterface {
	
	Scanner s = new Scanner(System.in);
	AdminDaoInterface adi = new AdminDaoImp();
	
	/*
	 * @method : approvalForStudent
	 * Admin will approve the student who are registered
	 * 
	 */
	
	@Override
	public void approvalForStudent() {
		
		try {
			adi.approvalForStudent();
		} catch (InvalidInputException e) {
			// Displays InvalidInputException if valid option is not selected
			System.out.println("Course Already Exists : " + e.getMsg());
		}
	}

	/*
	 * @method : generateReportCard()
	 * Generates Report Card of every Student
	 * 
	 */
	
	@Override
	public void generateReportCard() {

		adi.generateReportCard();
	}
	
	/*
	 * @method : addCourse()
	 * It adds courses in the Catalog
	 * 
	 * @param : int courseId,String course,String professor,int sem,float price
	 */
	
	@Override
	public void addCourse(int courseId,String course,String professor,int sem,float price) {
		
		try {
			adi.addCourse(courseId,course, professor, sem, price);
		} catch (AlreadyExistsException e) {
			// Displays message from AlreadyExistsException if Course not found
			System.out.println(e.getId() + " Course already exists..");
		}
	}

	/*
	 * @method : addProfessor()
	 * It adds Professors in the Professor
	 * 
	 * @param : String usernameP,String passwordP
	 */
	
	@Override
	public void addProfessor(String usernameP,String passwordP) {
		// TODO Auto-generated method stub
		try {
			adi.addProfessor(usernameP, passwordP);
		} catch (AlreadyExistsException e) {
			//Displays AlreadyExistsException Message if Professor already exists
			System.out.println(e.getMsg());
		}
		
	}

	/*
	 * @method : removeCourse()
	 * It removes courses in the Catalog
	 * 
	 * @param : String course,String professor
	 */
	
	@Override
	public void removeCourse(String course,String professor) {
		// TODO Auto-generated method stub
		try {
			adi.removeCourse(course, professor);
		} catch (CourseNotFoundException e) {
			// Displays CourseNotFoundException Message if course not found in the Catalog 
			System.out.println(e.getMsg());
		}
	}
}
