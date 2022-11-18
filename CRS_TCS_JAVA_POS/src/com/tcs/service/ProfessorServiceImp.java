/**
 * 
 */
package com.tcs.service;

import com.tcs.dao.ProfessorDaoImp;
import com.tcs.dao.ProfessorDaoInterface;
import com.tcs.exception.GradeNotFoundException;
import com.tcs.exception.UserNotFoundException;

/**
 * @author Administrator
 *
 */
public class ProfessorServiceImp implements ProfessorServiceInterface{
	
	ProfessorDaoInterface pdi = new ProfessorDaoImp();
	
	/*
	 * @method : addGrades()
	 * It add grades to the respective Students by the professor for his courses
	 * 
	 * @param : String username
	 */
	
	@Override
	public void addGrades(String username) {
	
		try {
			pdi.addGrades(username);
		} catch (GradeNotFoundException e) {
			// Displays GradeNotFoundException Message if other grades are selected not in the Grade table
			System.out.println(e.getMsg());
		} catch (UserNotFoundException e) {
			// Displays UserNotFoundException Message if Students without grades are not found
			System.out.println(e.getMsg());
		}
	}
	
	/*
	 * @method : viewCourseEnrolledStudents
	 * Displays the List of Students who enrolled for the courses taught by professor
	 * 
	 * @param : String username
	 */
	
	@Override
	public void viewCourseEnrolledStudents(String username) {
		
		try {
			pdi.viewCourseEnrolledStudents(username);
		} catch (UserNotFoundException e) {
			// Displays UserNotFoundException Message if no students are enrolled
			System.out.println(e.getMsg());
		}
	}
}
