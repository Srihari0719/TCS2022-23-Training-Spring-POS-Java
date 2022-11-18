/**
 * 
 */
package com.tcs.service;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.tcs.constant.SQLConstants;
import com.tcs.dao.UserDaoImp;
import com.tcs.dao.UserDaoInterface;
import com.tcs.exception.*;
import com.tcs.utils.DBUtils;

/**
 * @author Administrator
 *
 */
public class UserServiceImp implements UserServiceInterface{
	Scanner sc = new Scanner(System.in);
	AdminServiceInterface asi = new AdminServiceImp();
	StudentServiceInterface ssi = new StudentServiceImp();
	ProfessorServiceInterface psi = new ProfessorServiceImp();
	UserDaoInterface udi = new UserDaoImp();
	DBUtils cc = new DBUtils();
	
	/*
	 * @method : adminLogin
	 * This method checks for admin credentials and display Admin menu if login successful
	 * 
	 * @param : String username, String password
	 */
	@Override
	public void adminLogin(String username, String password) {

		try {
			System.out.println("Login Successful.");
			boolean a = true;
			while(a) {
			System.out.println("--Admin Menu--");
			System.out.println();
			System.out.println("1.Approval of Student\n2.Generate Report Card\n3.Add Professor\n4.Add Course\n5.Remove Course\n6.Log Out");
			int op = sc.nextInt();sc.nextLine();
			switch(op) {
			case 1: asi.approvalForStudent();
					break;
			case 2: asi.generateReportCard();
					break;
			case 3: System.out.println("Enter Username : ");
					String usernameP = sc.nextLine();
					System.out.println("Enter Password : ");
					String passwordP = sc.nextLine();
					asi.addProfessor(usernameP,passwordP);
					break;
			case 4: System.out.println("Enter Course ID : ");
					int courseId = sc.nextInt();sc.nextLine();
					System.out.println("Enter Course Name : ");
					String course = sc.nextLine();
					System.out.println("Enter Professor Name : ");
					String professor = sc.nextLine();
					System.out.println("Enter Semester : ");
					int sem = sc.nextInt();
					System.out.println("Enter Course Price : ");
					float coursePrice = sc.nextInt();
					asi.addCourse(courseId,course,professor, sem, coursePrice);
					break;
			case 5: System.out.println("Enter Course Name : ");
					String coursename = sc.nextLine();
					System.out.println("Enter Professor Name : ");
					String prof = sc.nextLine();
					asi.removeCourse(coursename, prof);
					break;
			case 6: a = false;
					break;
			default:throw new InvalidInputException("Please give valid input..");
		}
		}
		}
		catch(InvalidInputException e) {
			//Displays InvalidInputException if user selects other options
			System.out.println(e.getMsg());
		}
		catch(InputMismatchException e1) {
			System.out.println(e1.getMessage());
		} 
	}

	/*
	 * @method : userLogin
	 * This method checks for login credentials and implements required if matches the role
	 * 
	 * @param : String username, String password, String role
	 */
	@Override
	public void userLogin(String username, String password, String role) {
		// TODO Auto-generated method stub
		try {
			if(role.equalsIgnoreCase("admin"))  {
				udi.userLogin(username, password, role);
				adminLogin(username,password);
			}
			else if(role.equalsIgnoreCase("professor")) {
				udi.userLogin(username, password, role);
				professorLogin(username,password);
			}
			else if(role.equalsIgnoreCase("student")) {
				udi.userLogin(username, password, role);
				studentLogin(username,password);
			}
			else {
				throw new CredentialMismatchException("Please give a valid role..");
		}
		} 
		catch (CredentialMismatchException e) {
			//Displays CredentialMismatchException if Credentials are incorrect
			System.out.println(e.getMsg());
		} 
		catch (UserNotFoundException e) {
			//Displays UserNotFoundException Message if User not found
			System.out.println(e.getMsg());
		} 
		catch (InvalidInputException e) {
			//Displays InvalidInputException if user selects other options
			System.out.println(e.getMsg());
		}
		catch(InputMismatchException e1) {
			//Displays InputMismatchException Message if input type not maths with input
			System.out.println(e1.getMessage());
		} 
	}

	/*
	 * @method : professorLogin
	 * This method checks for professor credentials and display professor menu if login successful
	 * 
	 * @param : String username, String password
	 */
	@Override
	public void professorLogin(String username, String password) throws InvalidInputException{
		// TODO Auto-generated method stub
		
			System.out.println("Login Successful.");
			boolean p = true;
			while(p) {
			try {
			System.out.println("--Professor Menu--");
			System.out.println();
			System.out.println("1.Add Grades\n2.View Course Enrolled Students\n3.Log Out");
			int op = sc.nextInt();sc.nextLine();
			switch(op) {
			case 1: psi.addGrades(username);
					break;
			case 2: psi.viewCourseEnrolledStudents(username);
					break;
			case 3: p = false;
					break;
			default:throw new InvalidInputException("Please select valid option..");
			}
		}
		catch(InvalidInputException e) {
			//Displays InvalidInputException if user selects other options
			System.out.println(e.getMsg());
		}
		catch(InputMismatchException e1) {
			//Displays InputMismatchException Message if input type not maths with input
			System.out.println("Input Mismatch. Please give valid input..");
			sc.nextLine();
			continue;
		} 
		}
	}

	/*
	 * @method : studentLogin
	 * This method checks for student credentials and display student menu if login successful
	 * 
	 * @param : String username, String password
	 */
	
	@Override
	public void studentLogin(String username, String password) {
		
			System.out.println("Login Successful.");
			boolean s = true;
			while(s) {
			System.out.println("--Student Menu--");
			System.out.println();
			System.out.println("1.Add Course\n2.Drop Course\n3.View Report Card\n4.Make Payment\n5.Semester Registration\n6.Log Out");
			try {
			int op = sc.nextInt();sc.nextLine();
			switch(op) {
			case 1: Connection conn = cc.connect();
				try {
					PreparedStatement pr = conn.prepareStatement(SQLConstants.CATALOG);
					pr.setString(1, username);
					ResultSet rs = pr.executeQuery();
					while(rs.next()) {
						System.out.println(rs.getInt("courseId") + "-" + rs.getString("courseName")+"-"+rs.getString("profName")+"-"+rs.getFloat("coursePrice"));
					}
					pr.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
					System.out.println("Enter course ID");
					int courseId = sc.nextInt();sc.nextLine();
					System.out.println("Enter courseName :");
					String courseName = sc.nextLine();
					System.out.println("Enter Professor :");
					String professor = sc.nextLine();
					ssi.addCourse(courseId,courseName, professor,username);
					break;
			case 2: PreparedStatement pr;
				try {
					Connection conn1  = cc.connect();
					pr = conn1.prepareStatement(SQLConstants.REGISTERED_COURSES);
					pr.setString(1,username);
					ResultSet rs1 = pr.executeQuery();
					int a=0;
					while(rs1.next()) {
						a++;
						System.out.println(rs1.getInt("courseId") + "-" + rs1.getString("courseName")+"-"+rs1.getString("pName"));
					}
					if(a==0)
						throw new SemesterNotFoundException("Please Update Semester..");
					pr.close();
					conn1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (SemesterNotFoundException e) {
					//Displays SemesterNotFoundException if Student not registered for Semester
					System.out.println(e.getMsg());
				}
					System.out.println("Enter courseName :");
					String course = sc.nextLine();
					System.out.println("Enter Professor :");
					String professorName = sc.nextLine();
					
					ssi.dropCourse(course, professorName,username);
					break;
			case 3: ssi.viewReportCard(username);
					break;
			case 4: ssi.payFee(username);
					break;
			case 5: System.out.println("Enter Semester : ");
					int semesterNumber = sc.nextInt();sc.nextLine();
					ssi.semesterRegistration(username,semesterNumber);
					break;
			case 6: s=false;
					break;
			default:throw new InvalidInputException("----Invalid Choice----");
			}
			}
			catch(InputMismatchException e1) {
				//Displays InputMismatchException Message if input type not maths with input
				System.out.println(e1.getMessage());
				continue;
			} catch (InvalidInputException e) {
				//Displays InvalidInputException if user selects other options
				System.out.println(e.getMsg());;
			}
			
			}
	}
}
