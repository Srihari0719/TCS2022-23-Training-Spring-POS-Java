/**
 * 
 */
package com.tcs.dao;

import com.tcs.exception.GradeNotFoundException;
import com.tcs.exception.UserNotFoundException;

/**
 * @author Administrator
 *
 */

public interface ProfessorDaoInterface {

	public void addGrades(String username) throws GradeNotFoundException, UserNotFoundException;
	public void viewCourseEnrolledStudents(String username) throws UserNotFoundException;
	
}
