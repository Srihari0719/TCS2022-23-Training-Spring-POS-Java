/**
 * 
 */
package com.tcs.dao;

import com.tcs.exception.AlreadyExistsException;
import com.tcs.exception.CourseNotFoundException;
import com.tcs.exception.GradeNotFoundException;
import com.tcs.exception.InvalidInputException;
import com.tcs.exception.SemesterNotFoundException;

/**
 * @author Administrator
 *
 */
public interface StudentDaoInterface {

	public void addCourse(int courseId,String courseName,String professor,String username) throws SemesterNotFoundException, AlreadyExistsException;
	public void dropCourse(String courseName,String professor,String username) throws CourseNotFoundException;
	public void viewReportCard(String username) throws GradeNotFoundException;
	public void semesterRegistration(String username,int semester) throws SemesterNotFoundException;
	public void payFee(String username) throws InvalidInputException, CourseNotFoundException;
	
}
