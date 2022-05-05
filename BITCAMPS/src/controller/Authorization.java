/*
 * Authorization : 
 * This class will check whether the user(Staff/student) is a valid user and check his emailId,password are valid or not.
 * This class also provides constraints for phone number and userName.
 * This class also handles the error made by user while using application by giving messages if they didn't enter the valid input.
 */
package controller;
import java.util.Scanner;
import app_data.DataBaseRepository;
import java.sql.ResultSet;
import model.Staff;
import model.Student;
public class Authorization {
	String emailId;
	String password;
	String processStatus;
	String query;
	ResultSet resultSet;
	static String userName;
	static Scanner input_string = new Scanner(System.in);
	static Scanner input = new Scanner(System.in);
	Object obj;
	DataBaseRepository databaseRepository = new DataBaseRepository();
	//To check the input entered by admin/user is valid or not 
	public static int checkInput() {
		boolean checker;
	    int Choice = 0;
	    do {
	    	checker = false;
	    	System.out.println("PLEASE ENTER YOUR CHOICE");
		    try {
		    	 Scanner input = new Scanner(System.in);
				 Choice = input.nextInt();
		    }
		    catch(Exception exception) {
		    	System.out.println("PLEASE ENTER VALID CHOICE");
		        checker = true;
		    }
	     }while(checker);
	     return Choice;  
	}
	//To check emailId ,userName,phoneNumber,password provided by user/admin is valid or not
    public static boolean checkEmail(String emailId) {
		int uprFlag = 1,emailFlag = 0;
		if(emailId.contains("bit") && emailId.contains("gmail") && emailId.contains("com") && emailId.contains(".") && emailId.contains("@")) {
			emailFlag = 1;
		}
		for(int iterator = 0;iterator<emailId.length();iterator++) {
			if(Character.isUpperCase(emailId.charAt(iterator))) {
				uprFlag = 0;
			}
		}
		if(emailFlag == 1 && uprFlag == 1) {
			return true;
		}
        return false;
	}
	public static boolean checkPassword(String password) {
		int uprFlag = 0,specialCharFlag = 0,numberFlag = 0;
		for(int iterator = 0;iterator<password.length();iterator++) {
			if(Character.isUpperCase(password.charAt(iterator))) 
				uprFlag = 1;
			if(password.contains("@")) 
				specialCharFlag = 1;
			if(Character.isDigit(password.charAt(iterator)))
				numberFlag = 1;
		}
		if(uprFlag == 1 && specialCharFlag == 1 && numberFlag == 1) {
			return true;
		}
        return false;
	}
	public static boolean checkPhoneNumber(String phoneNumber)
	{
		if(phoneNumber.length() == 10)
			return true;
		
		return false;
	}
	public static boolean checkUserName(String userName) {
		if(userName.contains(" ")) {
			String arr[] = userName.split(" ");
			if(arr[(arr.length -1)].length() == 1 || arr[(arr.length -1)].length() == 2) {
				return true;
			}
			return false;
		}
		return false;
	}
	//To check the user is valid or not while they're in login page and if they're a valid user then their information stored as a object and using that they can access only their data's.
	public Object checkUser(String emailId,String password,String tableName) {
		query = "SELECT * FROM " + tableName + "" ;
		resultSet = databaseRepository.select(query);
		if(tableName.equalsIgnoreCase("staff")) {
			try {
				while(resultSet.next()) {
					if(resultSet.getString(3).equals(emailId)) {
						if(resultSet.getString(4).equals(password)) {
							Staff staff = new Staff();
							staff.staffId = resultSet.getInt(1);
							staff.staffName = resultSet.getString(2);
							staff.staffEmailId = resultSet.getString(3);
							staff.password = resultSet.getString(4);
							staff.subject_handled = resultSet.getString(5);
							staff.degree = resultSet.getString(6);
							staff.phoneNo = resultSet.getString(7);
							staff.address = resultSet.getString(8);
							return staff;
					    }
				     }
				}
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
			return null;
		}
		else {
			try {
				while(resultSet.next()) {
					if(resultSet.getString(4).equals(emailId)) {
						if(resultSet.getString(5).equals(password)) {
							Student student = new Student();
							student.staffId = resultSet.getInt(1);
							student.studentId = resultSet.getInt(2);
							student.name = resultSet.getString(3);
							student.studentEmailId = resultSet.getString(4);
							student.password = resultSet.getString(5);
							student.branch = resultSet.getString(6);
							student.year = resultSet.getString(7);
							student.AccomodationMode = resultSet.getString(8);
							student.degree = resultSet.getString(9);
							student.batch = resultSet.getString(10);
							student.studentNo = resultSet.getString(11);
							student.parentNo = resultSet.getString(12);
							student.address = resultSet.getString(13);
							return student;
					    }
				     }
				}
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
			return null;
		}
	}
}
