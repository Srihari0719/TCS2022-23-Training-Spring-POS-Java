/**
 * 
 */
package com.tcs.service;

/**
 * @author Administrator
 *
 */
public interface AdminServiceInterface {
	public void approvalForStudent();
	public void generateReportCard();
	public void addCourse(int courseId,String course,String professor,int sem,float price);
	public void addProfessor(String usernameP,String passwordP);
	public void removeCourse(String course,String professor);
}
