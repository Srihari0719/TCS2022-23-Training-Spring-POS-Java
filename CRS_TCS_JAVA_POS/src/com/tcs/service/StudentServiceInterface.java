package com.tcs.service;

public interface StudentServiceInterface {
	
	public void addCourse(int courseId,String courseName,String professor,String username);
	public void dropCourse(String courseName,String professor,String username);
	public void viewReportCard(String username);
	public void semesterRegistration(String username,int semester);
	public void payFee(String username);
	
}
