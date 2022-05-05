/*
 * Validate class:
 * This class will check the user details provided by the users/admin is valid or not.
 * so the user can access their data's or belonged data's.
 * This class provides security by avoiding other user from access.
 */
package controller;
import java.sql.ResultSet;
import java.util.ArrayList;
import app_data.DataBaseRepository;
import model.Staff;
import model.Student;
import model.StaffLeaveInformation;
import model.StudentLeaveInformation;
public class Validate {
	private String query;
	private ResultSet resultSet;
	private boolean inputStatus;
	DataBaseRepository databaseRepository = new DataBaseRepository();
	ArrayList<ResultSet> dateList = new ArrayList<ResultSet>();
	//isValidId : To check user id is valid or not while accessing data.
	public  boolean isValidId(int userId,String tableName) {
		if(tableName.equalsIgnoreCase("staff")) {
			query = "SELECT * FROM "+tableName+" ";
			resultSet = databaseRepository.select(query);
			try {
				while(resultSet.next()) {
		    		if(userId == resultSet.getInt(1)) {
		    			databaseRepository.closeConnection();
						return true;
					}
		    	}
				databaseRepository.closeConnection();
				return false;
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		else if(tableName.equalsIgnoreCase("staffleavestatus")) {
			query = "SELECT * FROM "+tableName+" ";
			resultSet = databaseRepository.select(query);
			try {
				while(resultSet.next()) {
		    		if(userId == resultSet.getInt(2)) {
		    			databaseRepository.closeConnection();
						return true;
					}
		    	}
				databaseRepository.closeConnection();
				return false;
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		else if(tableName.equalsIgnoreCase("studentleavestatus")) {
			query = "SELECT * FROM "+tableName+" ";
			resultSet = databaseRepository.select(query);
			try {
				while(resultSet.next()) {
		    		if(userId == resultSet.getInt(3)) {
		    			databaseRepository.closeConnection();
						return true;
					}
		    	}
				databaseRepository.closeConnection();
				return false;
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		else {
			query = "SELECT * FROM "+tableName+" ";
		    resultSet = databaseRepository.select(query);
		    try {
		    	while(resultSet.next()) {
		    		if(userId == resultSet.getInt(2)) {
		    			databaseRepository.closeConnection();
						return true;
					}
		    	}
		    	databaseRepository.closeConnection();
				return false;
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		return false;
	}
	//isValidMentorIdStudentID : To check that student is belonging to mentor or not
	public boolean isValidMentorIdStudentID(int staffId,int studentId) {
		query = "SELECT * FROM student WHERE studentId = '"+studentId+"' ";
		resultSet = databaseRepository.select(query);
		try {
			while(resultSet.next()) {
				if(staffId == resultSet.getInt(1)) {
					databaseRepository.closeConnection();
					return true;
				}
		    }
			databaseRepository.closeConnection();
			return false;
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return false;
    }
	public boolean isValidMentorId(int userId,String name) {
		if(name.equalsIgnoreCase("studentleavestatus")) {
			query = "SELECT * FROM studentleavestatus";
			resultSet = databaseRepository.select(query);
			try {
				while(resultSet.next()) {
					if(userId == resultSet.getInt(2)) {
						databaseRepository.closeConnection();
						return true;
					}
			    }
				databaseRepository.closeConnection();
				return false;
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		else {
			query = "SELECT staffId FROM student WHERE name = '"+name+"' ";
			resultSet = databaseRepository.select(query);
			try {
				while(resultSet.next()) {
					if(userId == resultSet.getInt(1)) {
						databaseRepository.closeConnection();
						return true;
					}
			    }
				databaseRepository.closeConnection();
				return false;
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		return false;
	} 	
	public boolean isValidIdSemester(int userId,int semester) {
		query = "SELECT * FROM studentfee WHERE semester ="+semester+" ";
		resultSet = databaseRepository.select(query);
		try {
			while(resultSet.next()) {
				if(userId == resultSet.getInt(1)) {
					return true;
				}
			}
			return false;
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}
	//isValidLeaveId : 	To check leaveId entered by staff/admin is valid or not while approving/declining leave.
	public boolean isValidLeaveId(int userId,int userLeaveId,String tableName) {
		if(tableName.equalsIgnoreCase("staffleavestatus")) {
			query = "SELECT * FROM "+tableName+" WHERE staffLeaveId = "+userLeaveId+" AND staffId = "+userId+"";
			resultSet = databaseRepository.select(query);
			try {
				while(resultSet.next()) {
		    		if(userLeaveId == resultSet.getInt(1) && userId == resultSet.getInt(2)) {
		    			databaseRepository.closeConnection();
						return true;
					}
		    	}
				databaseRepository.closeConnection();
				return false;
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		else {
			query = "SELECT * FROM "+tableName+" WHERE studentLeaveId = "+userLeaveId+" AND studentId = "+userId+"";
			resultSet = databaseRepository.select(query);
			try {
				while(resultSet.next()) {
		    		if(userLeaveId == resultSet.getInt(1) && userId == resultSet.getInt(3)) {
		    			databaseRepository.closeConnection();
						return true;
					}
		    	}
				databaseRepository.closeConnection();
				return false;
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		return false;
	}
	//checkUserId : To check the userId is valid user or not when the id entered by admin
	public void checkUserId(Staff staff,Student student) {
    	while(true) {
    		if(student == null) {
        		inputStatus = isValidId(staff.staffId, "staff");
        		if(inputStatus) {
            		return;
            	}
            	else {
            		System.out.println("ENTER THE VALID STAFF ID");
            		continue;
            	}
        	}
    		else {
    			inputStatus = isValidId(student.studentId, "student");
    			if(inputStatus) {
            		return;
            	}
            	else {
            		System.out.println("ENTER THE VALID STUDENT ID");
            	}
    		}
    	}
    }
}
