/**
 * 
 */
package com.tcs.constant;

/**
 * @author Administrator
 *
 */
public class SQLConstants {
	
	public static final String SELECT_USER = "SELECT username,isApproved FROM Login";
	public static final String APPROVED_LOGIN1 = "UPDATE Login SET isApproved=1 where username=?";
	public static final String APPROVED_LOGIN2 = "UPDATE Login SET isApproved=2 where username=?";	
	public static final String UPDATE_PASSWORD = "UPDATE Login SET password=? where username=? AND password=? AND role=? AND isApproved=1";
	public static final String ADD_STUDENT = "INSERT INTO Login VALUES (?,?,?,?)";
	public static final String USER_LOGIN = "SELECT username,password,role,isApproved FROM Login";
	public static final String SELECT_FAIL = "SELECT DISTINCT(studentId) FROM GradeCard WHERE gradeId=6 OR gradeId=7";
	public static final String SELECT_PASS = "SELECT DISTINCT(studentId) FROM GradeCard WHERE gradeId<6";
	public static final String UPDATE_FAIL_RESULT = "UPDATE Student SET result='fail' WHERE Id =?";
	public static final String UPDATE_PASS_RESULT = "UPDATE Student SET result='pass' WHERE Id=? AND result != 'fail'";
	public static final String ADD_COURSE = "INSERT INTO Catalog Values(?,?,?,?,?)";
	public static final String ADD_PROFESSOR = "INSERT INTO Login VALUES(?,?,?,?)";
	public static final String PROFESSOR = "INSERT INTO Professor VALUES(?,?,?)";
	public static final String MAX_PROFESSORID = "SELECT MAX(professorId) FROM Professor";
	public static final String REMOVE_COURSE = "DELETE FROM Catalog WHERE courseName=? AND profName=?";
	public static final String ADD_GRADES = "SELECT Id,name,courseId,courseName FROM Student s INNER JOIN CourseRegistration cr ON s.Id=cr.studentId WHERE cr.pName= (SELECT professorName FROM Professor WHERE username=?)";
	public static final String LIST_ENROLLED_STUDENTS = "SELECT Id,name,courseId,courseName FROM Student s INNER JOIN CourseRegistration cr ON s.Id=cr.studentId WHERE cr.pName= (SELECT professorName FROM Professor WHERE username=?)";
	public static final String GRADECARD = "INSERT INTO GradeCard VALUES(?,?,?)";
	public static final String STUDENT = "SELECT Id,username,name,result,semester FROM Student where username=?";
	public static final String COURSE_REGISTRATION = "INSERT INTO CourseRegistration VALUES(?,?,?,?)";
	public static final String DROP_STUDENT_COURSE = "DELETE FROM CourseRegistration WHERE courseName=? AND pName=? AND studentId = (SELECT Id FROM Student WHERE username=?)";
	public static final String VIEW_REPORT = "SELECT gc.courseId,c.courseName,p.professorName,grade FROM GradeCard gc,Grade g, Course c,professor p WHERE c.courseId=gc.courseId AND  gc.gradeId=g.gradeId AND p.professorId=c.professorId AND gc.studentId=(SELECT Id FROM Student WHERE username=?)";
	public static final String SEMESTER_UPDATE = "UPDATE Student SET semester=? WHERE username=?";
	public static final String FETCH_BILL = "SELECT SUM(coursePrice) AS bill FROM CourseRegistration cr INNER JOIN Catalog c ON cr.courseId=c.courseId WHERE studentId=(SELECT Id FROM Student WHERE username=?)";
	public static final String PAYMENT = "INSERT INTO Payment(studentId,bill,paymentMode) VALUES(?,?,?)";
	public static final String ADD_STUDENTID = "INSERT INTO Student(Id,username,name) VALUES(?,?,?)";
	public static final String MAX_STUDENTID = "SELECT MAX(Id) FROM Student";
	public static final String REGISTERED_COURSES = "SELECT courseId,courseName,pName FROM CourseRegistration WHERE studentId = (SELECT Id FROM Student WHERE username=?)";
	public static final String CATALOG = "SELECT courseId,courseName,profName,coursePrice FROM Catalog WHERE semester=(SELECT semester FROM Student WHERE username=?)";
	public static final String CATALOG_VALUES = "SELECT courseId,courseName,profName,coursePrice FROM Catalog";
	public static final String LIST_GRADE = "SELECT count(*) FROM GradeCard WHERE studentId=? AND courseId=?";

}
