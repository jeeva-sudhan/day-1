/*
 * User class : This class gives the Object to Entry class whether the signed person is student or staff or admin.
 * Based on this Entry class show functionalities.
 */
package view;
import java.util.Scanner;

import controller.Authorization;
import model.Staff;
import model.Student;

public class User {
	private String emailId;
	private String password;
	Object user;
	Scanner input = new Scanner(System.in);
	Scanner input_string = new Scanner(System.in);
	Authorization verifier = new Authorization();
	public void consoleInput() {
		boolean emailStatus,passwordStatus;
		while(true)
		{
			System.out.println("PLEASE ENTER YOUR EMAILID");
			emailId = input_string.nextLine();
			emailStatus = Authorization.checkEmail(emailId);
			if(emailStatus == false)
			{
				System.out.println("PLEASE ENTER VALID EMAILID");
				continue;
			}
			break;
		}
		while(true)
		{
			System.out.println("PLEASE ENTER YOUR PASSWORD");
			password = input_string.nextLine();
			passwordStatus = Authorization.checkPassword(password);
			if(passwordStatus == false)
			{
				System.out.println("PLEASE ENTER VALID PASSWORD");
				continue;
			}
			break;
		}
	}
	public Object getUser(int loginStatus) {
		if(loginStatus == 1)
		{
			Admin admin = new Admin();
			consoleInput();
			if(emailId.equals(admin.getEmailId()))
			{
				if(password.equals(admin.getPassword()))
				{
					System.out.println("WELCOME ADMIN");
					return admin;
				}
				else
					System.out.println("NO MATCH FOUND");
			}
			else
				System.out.println("NO MATCH FOUND FOR EMAILID");
			return null;
		}
		else if(loginStatus == 2)
		{
			consoleInput();
			try {
				user = verifier.checkUser(emailId, password, "staff");
			}
			catch(Exception exception) {
				System.out.println("LOGIN ERROR OCCURRED");
			}
			if(user == null)
			{
				return null;
			}
			Staff staff = new Staff();
			staff = (Staff)user;
			System.out.println("WELCOME STAFF " + staff.staffName);
			return staff;
		}
		else
		{
			consoleInput();
			try {
				user = verifier.checkUser(emailId, password, "student");
			}
			catch(Exception exception) {
				System.out.println("LOGIN ERROR OCCURRED");
			}
			if(user == null)
			{
				return null;
			}
			Student student = new Student();
			student = (Student)user;
			System.out.println("WELCOME STUDENT " + student.name);
			return student;
		}
	}
}
