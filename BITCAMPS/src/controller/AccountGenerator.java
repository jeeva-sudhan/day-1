/*
 * AccountGenerator:
 * This class will generate the id ,email and password for student/staff when they are get added in database.
 */
package controller;
import app_data.DataBaseRepository;
import java.sql.ResultSet;
public class AccountGenerator {
	ResultSet resultSet;
	String query;
	int staffIdGenerator = 0;
	int studentIdGenerator = 0;
	int userLeaveId = 0;
	DataBaseRepository databaseRepository = new DataBaseRepository();
	//Generating ID for user(staff/student) when new user get created(ex:lateral entries)
	public int IdGenerator(String tableName) {
		try {
			query = "SELECT * FROM " + tableName + "" ;
			resultSet = databaseRepository.select(query);
			if((tableName).equalsIgnoreCase("staff")) {
				while(resultSet.next()) {
					staffIdGenerator = resultSet.getInt(1);
				}
				return staffIdGenerator;
			}
			else {
				while(resultSet.next()) {
					studentIdGenerator = resultSet.getInt(2);
				}
				return studentIdGenerator;
			}
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	//Generating leave id for user(staff/student) when they applyleave
	public int LeaveIdGenerator(String tableName)
	{
		try {
			query = "SELECT * FROM " + tableName + "" ;
			resultSet = databaseRepository.select(query);
			while(resultSet.next()) {
				userLeaveId = resultSet.getInt(1);
			}
			return userLeaveId;
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	//Generating gmail and password for user when new user get created(ex:lateral entries)
	public static String gmailGenerator(String userName,String user) {
		String newName = new String();
		String splitArr[] = new String[20];
		if(userName.contains(" ")) {
			splitArr = userName.split(" ");
			for(String each_array : splitArr) {
				if(each_array.length() == 1 || each_array.length() == 2) {
					continue;
				}
				newName += each_array;
			}
			if(user.equalsIgnoreCase("staff")) {
				newName = newName.toLowerCase();
				newName += ".bit@gmail.com";
				return newName; 
			}
			else {
				newName = newName.toLowerCase();
				newName += "bit@gmail.com";
				return newName; 
			}
		}
		else {
			if(user.equalsIgnoreCase("staff")) {
				newName = newName.toLowerCase();
				userName += ".bit@gmail.com";
				return newName; 
			}
			else {
				newName = newName.toLowerCase();
				newName += "bit@gmail.com";
				return newName; 
			}
		}
	}
	public static String passwordGenerator(String userName) {
		String newName = new String();
		String splitArr[] = new String[20];
		if(userName.contains(" ")) {
			splitArr = userName.split(" ");
			for(String each_array : splitArr) {
				if(each_array.length() == 1 || each_array.length() == 2) {
					continue;
				}
				newName += each_array;
			}
			newName += "@23";
			return newName; 
		}
		return null;
	}
}
