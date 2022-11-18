/**
 * 
 */
package com.tcs.dao;

import com.tcs.exception.AlreadyExistsException;
import com.tcs.exception.CourseNotFoundException;
import com.tcs.exception.InvalidInputException;

/**
 * @author Administrator
 *
 */
public interface AdminDaoInterface {

	public void approvalForStudent() throws InvalidInputException;
	public void generateReportCard();
	public void addCourse(int courseId,String course,String professor,int sem,float price) throws AlreadyExistsException;
	public void addProfessor(String usernameP,String passwordP) throws AlreadyExistsException;
	public void removeCourse(String course,String professor) throws CourseNotFoundException;
	
}
