/**
 * 
 */
package com.tcs.client;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * @author Administrator
 *
 */
import java.util.*;

import com.tcs.dao.*;
import com.tcs.exception.AlreadyExistsException;
import com.tcs.exception.PasswordMatchException;
import com.tcs.service.*;

public class CRSApplication {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		try (Scanner sys = new Scanner(System.in)) {
			UserDaoInterface udi = new UserDaoImp();
			UserServiceInterface usi = new UserServiceImp();
			
			boolean a = true;
			while(a) {
				System.out.println("Welcome to the CRS Application - " + LocalDate.now() + " " + LocalTime.now());
				System.out.println("-----Main Menu----");
				System.out.println("1.Login");
				System.out.println("2.Student Registration");
				System.out.println("3.Update Password");
				System.out.println("4.Exit");
				System.out.println("Choose your option: ");
				try {
				int n =sys.nextInt();sys.nextLine();
				switch(n) {
				case 1: System.out.println("Enter Username: ");
						String username = sys.nextLine();
						System.out.println("Enter Password: ");
						String password = sys.nextLine();
						System.out.println("Enter Role: ");
						String role = sys.nextLine();
						usi.userLogin(username, password, role);
						break;
				case 2: System.out.println("Enter Username :");
						String studentUsername = sys.nextLine();
						System.out.println("Enter Password :");
						String studentPassword = sys.nextLine();
						udi.addStudent(studentUsername, studentPassword);
						System.out.println("Approval pending at Admin.");
						break;
				case 3: System.out.println("Enter Username: ");
						String user = sys.nextLine();
						System.out.println("Enter Old Password: ");
						String pass = sys.nextLine();
						System.out.println("Enter New Password : ");
						String newPass = sys.nextLine();
						if(pass.equals(newPass))
							throw new PasswordMatchException("Old password cannot be same as new password.");
						System.out.println("Enter Role: ");
						String roleU = sys.nextLine();
						udi.updatePassword(user, pass, newPass, roleU);
						break;
				case 4: a=false;
						break;
				default:throw new NullPointerException("----Invalid Choice----");
				}
				}
				catch (PasswordMatchException e) {
					//Displays PasswordMatchException Message if old password and new password are equal
					System.out.println(e.getMsg());
					continue;
				}
				catch (AlreadyExistsException e) {
					//Displays AlreadyExistsException Message if user already exists
					System.out.println(e.getMsg());
					continue;
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
					sys.nextLine();
					continue;
				}
			}
		}
	}

}
