/**
 * 
 */
package com.tcs.bean;

/**
 * @author Administrator
 *
 */
public class Course {
	
	private int courseID;
	private String courseName;
	private int noOfStudentsRegistered;
	private String prerequistes;
	private String professor;
	private String status;

	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public int getNoOfStudentsRegistered() {
		return noOfStudentsRegistered;
	}
	public void setNoOfStudentsRegistered(int noOfStudentsRegistered) {
		this.noOfStudentsRegistered = noOfStudentsRegistered;
	}
	public String getPrerequistes() {
		return prerequistes;
	}
	public void setPrerequistes(String prerequistes) {
		this.prerequistes = prerequistes;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
