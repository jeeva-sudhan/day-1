/*InternalProcess : 
 * This class is a control class which get communicate with view class in order to get input and based on the requirement of view class it fetch and retrieve data from database
   and throw back the output to view class.
 * This class provide the info about how the data's of user fetched or retrieved from database.
 * Based on the request from view class this class will perform tasks in database.
 */
package controller;
import java.util.ArrayList;
import java.sql.ResultSet;
import model.Staff;
import model.StaffLeaveInformation;
import model.Student;
import model.StudentAcademicInformation;
import model.StudentFee;
import model.StudentLeaveInformation;
import app_data.DataBaseRepository;
public class InternalProcess {
	String query;
	int status;
	int staffIdGenerator = 0;
	int studentIdGenerator = 0;
	int iterator = 0;
    int staffLeaveId = 0;
    int studentLeaveId = 0;
	DataBaseRepository databaseRepository = new DataBaseRepository();
	AccountGenerator accountGenerator = new AccountGenerator();
	ArrayList<Object> userList= new ArrayList<Object>();
	ResultSet resultSet;
	//method to return mentor name
	public String getMentorName(int staffId) {
		query = "SELECT * FROM staff WHERE staffId = " + staffId;
		resultSet = databaseRepository.select(query);
		try {
			resultSet.next();
			return resultSet.getString(2);
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}
	//method to add user(staff/student) in database
	public int addUser(Staff staff,Student student) {
		try {
			if(student == null) {
				staffIdGenerator = accountGenerator.IdGenerator("staff");
				if(staffIdGenerator == 0) {
					staffIdGenerator = 9001;
				}
				else {
					++staffIdGenerator;
				}
				query = "INSERT INTO staff (staffId, staffName, emailId, password, subjectHandled, degree,phoneNo, address) VALUES (" + staffIdGenerator +",'"+staff.staffName +"','"+staff.staffEmailId+"','"+staff.password+"','"+staff.subject_handled+"','"+staff.degree+"','"+ staff.phoneNo+"','"+  staff.address +"')";
				status = databaseRepository.insert(query);
				return staffIdGenerator;
			}
			else {
				studentIdGenerator = accountGenerator.IdGenerator("student");
				if(studentIdGenerator == 0) {
					studentIdGenerator = 101;
				}
				else {
					++studentIdGenerator;
				}
				query = "INSERT INTO student(staffId, studentId, name ,dateOfBirth,emailId, password, department,year,AccomodationMode, degree,batch,studentNo,parentNo, address) VALUES (" + student.staffId +","+studentIdGenerator +",'"+student.name+"','"+student.dateOfBirth+"','"+student.studentEmailId+"','"+student.password+"','"+student.branch+"','"+student.year +"','"+student.AccomodationMode+"','"+student.degree+"','"+student.batch+"','"+student.studentNo+"','"+student.parentNo+"','"+student.address +"')";
				status = databaseRepository.insert(query);
				return studentIdGenerator;
			}
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	//method to return user info
	public ArrayList<Object> viewEntireUserInfo(int staffId,String tableName,int studentId) {
		if(staffId ==0 && studentId == 0) {
			query = "SELECT * FROM "+tableName+" ";
			resultSet = databaseRepository.select(query);
			//to view entire staff info
			if(tableName.equalsIgnoreCase("staff")) {
				if(userList.size() != 0) {
					userList.clear();
				}
				try {
					while(resultSet.next()) {
						Staff staff = new Staff();
						staff.staffId = resultSet.getInt(1);
						staff.staffName = resultSet.getString(2);
						staff.staffEmailId = resultSet.getString(3);
						staff.password = resultSet.getString(4);
						staff.subject_handled = resultSet.getString(5);
						staff.degree = resultSet.getString(6);
						staff.phoneNo = resultSet.getString(7);
						staff.address = resultSet.getString(8);
						userList.add(staff);
					}
				}
				catch(Exception exception) {
					exception.printStackTrace();
				}
			}
			//to view entire student info
			else {
				if(userList.size() != 0) {
					userList.clear();
				}
				try {
					while(resultSet.next()) {
						Student student = new Student();
						student.staffId = resultSet.getInt(1);
						student.studentId = resultSet.getInt(2);
						student.name = resultSet.getString(3);
						student.dateOfBirth = resultSet.getString(4);
						student.studentEmailId = resultSet.getString(5);
						student.password = resultSet.getString(6);
						student.branch = resultSet.getString(7);
						student.year = resultSet.getString(8);
						student.AccomodationMode = resultSet.getString(9);
						student.degree = resultSet.getString(10);
						student.batch = resultSet.getString(11);
						student.studentNo = resultSet.getString(12);
						student.parentNo = resultSet.getString(13);
						student.address = resultSet.getString(14);
						userList.add(student);
					}
				}
				catch(Exception exception) {
					exception.printStackTrace();
				}
			}
		}
		//to view entire student info of belonged staff
		else if(studentId == 0) {
			query = "SELECT * FROM "+tableName+" WHERE staffId= "+staffId+"";
			resultSet = databaseRepository.select(query);
			if(userList.size() != 0) {
				userList.clear();
			}
			try {
				while(resultSet.next()) {
					Student student = new Student();
					student.staffId = resultSet.getInt(1);
					student.studentId = resultSet.getInt(2);
					student.name = resultSet.getString(3);
					student.dateOfBirth = resultSet.getString(4);
					student.studentEmailId = resultSet.getString(5);
					student.password = resultSet.getString(6);
					student.branch = resultSet.getString(7);
					student.year = resultSet.getString(8);
					student.AccomodationMode = resultSet.getString(9);
					student.degree = resultSet.getString(10);
					student.batch = resultSet.getString(11);
					student.studentNo = resultSet.getString(12);
					student.parentNo = resultSet.getString(13);
					student.address = resultSet.getString(14);
					userList.add(student);
				}
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		//to view particular staff's particular student info
		else {
			query = "SELECT * FROM "+tableName+" WHERE staffId= "+staffId+" and studentId = "+studentId+" ";
			resultSet = databaseRepository.select(query);
			if(userList.size() != 0) {
				userList.clear();
			}
			try {
				resultSet.next();
				Student student = new Student();
				student.staffId = resultSet.getInt(1);
				student.studentId = resultSet.getInt(2);
				student.name = resultSet.getString(3);
				student.dateOfBirth = resultSet.getString(4);
				student.studentEmailId = resultSet.getString(5);
				student.password = resultSet.getString(6);
				student.branch = resultSet.getString(7);
				student.year = resultSet.getString(8);
				student.AccomodationMode = resultSet.getString(9);
				student.degree = resultSet.getString(10);
				student.batch = resultSet.getString(11);
				student.studentNo = resultSet.getString(12);
				student.parentNo = resultSet.getString(13);
				student.address = resultSet.getString(14);
				userList.add(student);
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		return userList;
	}
	//method to apply leave by user
	public int LeaveApply(StaffLeaveInformation staffLeaveInfo,StudentLeaveInformation studentLeaveInfo) {
		if(studentLeaveInfo == null) {
			staffLeaveInfo.LeaveStatus = "PENDING";
			staffLeaveId = accountGenerator.LeaveIdGenerator("staffleavestatus");
			if(staffLeaveId == 0) {
				staffLeaveId = 6001;
			}
			else {
				++staffLeaveId;
			}
			staffLeaveInfo.staffLeaveId = staffLeaveId;
			query = "INSERT INTO staffleavestatus (staffLeaveId,staffId,staffName,fromDate,toDate,LeaveType,LeaveStatus) VALUES("+ staffLeaveInfo.staffLeaveId  + ","+staffLeaveInfo.staffId+",'"+staffLeaveInfo.staffName+"','"+staffLeaveInfo.fromDate+"','"+staffLeaveInfo.toDate+"','"+staffLeaveInfo.LeaveType+"','"+staffLeaveInfo.LeaveStatus+"')";
			status = databaseRepository.insert(query);
			return status;
		}
		else {
			studentLeaveInfo.LeaveStatus = "PENDING";
			studentLeaveId = accountGenerator.LeaveIdGenerator("studentleavestatus");
			if(studentLeaveId == 0) {
				studentLeaveId = 5001;
			}
			else {
				++studentLeaveId;
			}
			studentLeaveInfo.studentLeaveId = studentLeaveId;
			query = "INSERT INTO studentleavestatus (studentLeaveId,staffId,studentId,studentName,fromDate,toDate,LeaveType,LeaveStatus) VALUES("+studentLeaveInfo.studentLeaveId+","+studentLeaveInfo.staffId+","+studentLeaveInfo.studentId+",'"+studentLeaveInfo.name+"','"+studentLeaveInfo.fromDate+"','"+studentLeaveInfo.toDate+"','"+studentLeaveInfo.LeaveType+"','"+studentLeaveInfo.LeaveStatus+"')";
			status = databaseRepository.update(query);
			return status;
		}
	}
	//method to approve/decline leave/od applied by user
	public int LeaveApproval(int userId,String tableName,String LeaveStatus,int LeaveId) {
		if(tableName.equalsIgnoreCase("studentleavestatus")) {
			query = "UPDATE "+tableName+" SET LeaveStatus ='"+LeaveStatus+"' WHERE studentId="+userId+" AND studentLeaveId = "+LeaveId+"";
			status = databaseRepository.update(query);
			return status;
		}
		else {
			query = "UPDATE "+tableName+" SET LeaveStatus ='"+LeaveStatus+"' WHERE staffId="+userId+" AND staffLeaveId = "+LeaveId+"";
			status = databaseRepository.update(query);
			return status;
		}
	}
	//method to view particular user info
	public Object viewParticularUserInfo(String tableName,int userId) {
		if(tableName.equalsIgnoreCase("student")) {
			query = "SELECT * FROM "+tableName+" WHERE studentId = "+userId+"";
			resultSet = databaseRepository.select(query);
			Student student = new Student();
			try {
				resultSet.next();
				student.staffId = resultSet.getInt(1);
				student.studentId = resultSet.getInt(2);
				student.name = resultSet.getString(3);
				student.dateOfBirth = resultSet.getString(4);
				student.studentEmailId = resultSet.getString(5);
				student.password = resultSet.getString(6);
				student.branch = resultSet.getString(7);
				student.year = resultSet.getString(8);
				student.AccomodationMode = resultSet.getString(9);
				student.degree = resultSet.getString(10);
				student.batch = resultSet.getString(11);
				student.studentNo = resultSet.getString(12);
				student.parentNo = resultSet.getString(13);
				student.address = resultSet.getString(14);
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
			return student;
		}
		else {
			query = "SELECT * FROM "+tableName+" WHERE staffId = "+userId+"";
			resultSet = databaseRepository.select(query);
			Staff staff = new Staff();
			try {
				resultSet.next();
				staff.staffId = resultSet.getInt(1);
				staff.staffName = resultSet.getString(2);
				staff.staffEmailId = resultSet.getString(3);
				staff.password = resultSet.getString(4);
				staff.subject_handled = resultSet.getString(5);
				staff.degree = resultSet.getString(6);
				staff.phoneNo = resultSet.getString(7);
				staff.address = resultSet.getString(8);
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
			return staff;
		}
	}
	//method to view leaveStatus of user
	public ArrayList<Object> viewUserLeaveInfo(String tableName,int userId,int fieldStatus) {
		if(fieldStatus == 1) {
			if(userList.size() != 0) {
				userList.clear();
			}
			//to view entire leaveStatus of student
			if(tableName.equalsIgnoreCase("studentleavestatus")) {
				query = "SELECT * FROM "+tableName+" WHERE studentId = "+userId+" ";
				resultSet = databaseRepository.select(query);
				try {
					while(resultSet.next()) {
						StudentLeaveInformation studentLeaveInfo = new StudentLeaveInformation();
						studentLeaveInfo.studentLeaveId = resultSet.getInt(1);
						studentLeaveInfo.staffId = resultSet.getInt(2);
						studentLeaveInfo.studentId = resultSet.getInt(3);
						studentLeaveInfo.name = resultSet.getString(4);
						studentLeaveInfo.fromDate = resultSet.getString(5);
						studentLeaveInfo.toDate = resultSet.getString(6);
						studentLeaveInfo.LeaveType = resultSet.getString(7);
						studentLeaveInfo.LeaveStatus = resultSet.getString(8);
						userList.add(studentLeaveInfo);
					}
				}
				catch(Exception exception) {
					exception.printStackTrace();
				}
				return userList;
			}
			//to view entire leaveStatus of staff
			else {
				query = "SELECT * FROM "+tableName+" WHERE staffId = "+userId+" ";
				resultSet = databaseRepository.select(query);
				try {
					while(resultSet.next())
					{
						StaffLeaveInformation staffLeaveInfo = new StaffLeaveInformation();
						staffLeaveInfo.staffLeaveId = resultSet.getInt(1);
						staffLeaveInfo.staffId = resultSet.getInt(2);
						staffLeaveInfo.staffName = resultSet.getString(3);
						staffLeaveInfo.fromDate = resultSet.getString(4);
						staffLeaveInfo.toDate = resultSet.getString(5);
						staffLeaveInfo.LeaveType = resultSet.getString(6);
						staffLeaveInfo.LeaveStatus = resultSet.getString(7);
						userList.add(staffLeaveInfo);
					}
				}
				catch(Exception exception) {
					exception.printStackTrace();
				}
				return userList;
			}
		}
		//to view entire staff leaveStatus 
		else if(fieldStatus == 2) {
			query = "SELECT * FROM "+tableName+" ";
			resultSet = databaseRepository.select(query);
			if(userList.size() != 0) {
				userList.clear();
			}
			try {
				while(resultSet.next())
				{
					StaffLeaveInformation staffLeaveInfo = new StaffLeaveInformation();
					staffLeaveInfo.staffLeaveId = resultSet.getInt(1);
					staffLeaveInfo.staffId = resultSet.getInt(2);
					staffLeaveInfo.staffName = resultSet.getString(3);
					staffLeaveInfo.fromDate = resultSet.getString(4);
					staffLeaveInfo.toDate = resultSet.getString(5);
					staffLeaveInfo.LeaveType = resultSet.getString(6);
					staffLeaveInfo.LeaveStatus = resultSet.getString(7);
					userList.add(staffLeaveInfo);
				}
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
			return userList;
		}
		//to view entire student leaveStatus of belonged staff
		else {
			if(userList.size() != 0) {
				userList.clear();
			}
			query = "SELECT * FROM "+tableName+" WHERE staffId = "+userId+" ";
			resultSet = databaseRepository.select(query);
			try {
				while(resultSet.next()) {
					StudentLeaveInformation studentLeaveInfo = new StudentLeaveInformation();
					studentLeaveInfo.studentLeaveId = resultSet.getInt(1);
					studentLeaveInfo.staffId = resultSet.getInt(2);
					studentLeaveInfo.studentId = resultSet.getInt(3);
					studentLeaveInfo.name = resultSet.getString(4);
					studentLeaveInfo.fromDate = resultSet.getString(5);
					studentLeaveInfo.toDate = resultSet.getString(6);
					studentLeaveInfo.LeaveType = resultSet.getString(7);
					studentLeaveInfo.LeaveStatus = resultSet.getString(8);
					userList.add(studentLeaveInfo);
				}
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
			return userList;
		}
	}	
	//updating/modifying user info by admin
	public int updateUserInfo(Staff staff,Student student,int fieldStatus,String tableName) {
		//to modify mentor for students
		if(fieldStatus == 1 && staff == null) {
			if(tableName.equalsIgnoreCase("studentleavestatus")) {
				query = "UPDATE studentleavestatus SET staffId = "+ student.staffId +" WHERE studentId = "+ student.studentId +" ";
			    status = databaseRepository.update(query);
			}
			else {
				query = "UPDATE student SET staffId = "+ student.staffId +" WHERE studentId = "+ student.studentId +" ";
		        status = databaseRepository.update(query);
			}
		    return status;
		}
		//to modify name of user
		if(fieldStatus == 2) {
			if(staff == null) {
				if(tableName.equalsIgnoreCase("studentleavestatus")) {
					query = "UPDATE studentleavestatus SET studentName = '"+ student.name +"' WHERE studentId = "+ student.studentId +" ";
				    status = databaseRepository.update(query);
				}
				else {
					query = "UPDATE student SET name = '"+ student.name +"',emailId = '"+student.studentEmailId+"',password = '"+student.password+"' WHERE studentId = "+ student.studentId +" ";
				    status = databaseRepository.update(query);
				    query = "UPDATE studentfee SET name = '"+ student.name +"' WHERE studentId = "+ student.studentId +" ";
				    status = databaseRepository.update(query);
				}
			    return status;
			}
			else {
				if(tableName.equalsIgnoreCase("staff")) {
					query = "UPDATE staff SET staffName = '"+ staff.staffName +"' ,emailId = '"+staff.staffEmailId+"' ,password = '"+staff.password+"' WHERE staffId = "+ staff.staffId+" ";
			        status = databaseRepository.update(query);
			       return status;
				}
			    else {
			    	query = "UPDATE staffleavestatus SET staffName = '"+ staff.staffName +"' WHERE staffId = "+ staff.staffId+" ";
			        status = databaseRepository.update(query);
			        return status;
			    }
			}
		}
		//to modify password of user
		if(fieldStatus == 3) {
			if(staff == null) {
				query = "UPDATE student SET password = '"+student.password+"' WHERE studentId = "+student.studentId+"";
				status = databaseRepository.update(query);
			    return status;
			}
			else {
				query = "UPDATE staff SET password = '"+staff.password+"' WHERE staffId = "+staff.staffId+"";
				status = databaseRepository.update(query);
			    return status;
			}
		}
		//changing user's phone number
		if(fieldStatus == 4) {
			if(staff == null) {
				query = "UPDATE student SET studentNo = '"+student.studentNo+"' WHERE studentId = "+student.studentId+"";
				status = databaseRepository.update(query);
			    return status;
			}
			else {
				query = "UPDATE staff SET phoneNo = '"+staff.phoneNo+"' WHERE staffId = "+staff.staffId+"";
				status = databaseRepository.update(query);
			    return status;
			}
		}
		//changing parent's phone number of user
		if(fieldStatus == 5) {
			query = "UPDATE student SET parentNo = '"+student.parentNo+"' WHERE studentId = "+student.studentId+"";
			status = databaseRepository.update(query);
		    return status;
		}
		//changing address of user
		if(fieldStatus == 6) {
			if(staff == null) {
				query = "UPDATE student SET  address = '"+student.address+"' WHERE studentId = "+student.studentId+"";
				status = databaseRepository.update(query);
			    return status;
			}
			else {
				query = "UPDATE staff SET  address = '"+staff.address+"' WHERE staffId = "+staff.staffId+"";
				status = databaseRepository.update(query);
			    return status;
			}
		}
		return 0;
	}
	//modifying feeInfo of student
	public int modifyFeeInfo(StudentFee feeInfo) {
		query = "UPDATE student SET AccomodationMode = '"+feeInfo.AccomodationMode+"' WHERE studentId = "+feeInfo.studentId+"";
		status = databaseRepository.update(query);
		query = "UPDATE studentfee SET Accomodation = '"+feeInfo.AccomodationMode+"',messFee = "+feeInfo.messFEE+",hostelFee ="+feeInfo.hostelFEE+",tutionFee = "+feeInfo.tutionFEE+" WHERE studentId = "+feeInfo.studentId+"";
		status = databaseRepository.update(query);
		return status;
	}
	//method to remove user by admin
	public int removeUser(int userId,String tableName) {
		if((tableName).equalsIgnoreCase("staff") || (tableName).equalsIgnoreCase("staffleavestatus")) {
			query = "DELETE FROM "+tableName+" WHERE staffId = "+userId+"";
			status = databaseRepository.delete(query);
			return status;
		}
		else {
			query = "DELETE FROM "+tableName+" WHERE studentId = "+userId+"";
			status = databaseRepository.delete(query);
			return status;
		}
	}
	// method to view academic info of student
	public ArrayList<Object> viewAcademicInfo(int studentId,int semester,String field) {
		//to view subject info
		if(field.equalsIgnoreCase("subject")) {
			query = "SELECT * FROM academic WHERE studentId ="+studentId+" and semester ="+semester+"";
			resultSet = databaseRepository.select(query);
			if(userList.size() != 0) {
				userList.clear();
			}
			try {
		    	while(resultSet.next()) {
		    		StudentAcademicInformation studentAcademicInfo = new StudentAcademicInformation();
		    		studentAcademicInfo.subject = resultSet.getString(3);
		    		studentAcademicInfo.facultyHandled = resultSet.getString(4);
		    		studentAcademicInfo.periodicalOneMark = resultSet.getDouble(5);
		    		studentAcademicInfo.periodicalTwoMark = resultSet.getDouble(6);
		    		userList.add(studentAcademicInfo);
		    	}
		    }
		    catch(Exception exception) {
		    	exception.printStackTrace();
		    }
			return userList;
		}
		//to view attendance info
		else if(field.equalsIgnoreCase("attendance")) {
			query = "SELECT Attendance FROM academic WHERE studentId ="+studentId+" and semester = "+semester+"";
			resultSet = databaseRepository.select(query);
			if(userList.size() != 0) {
				userList.clear();
			}
		    try {
		    	resultSet.next();
		        StudentAcademicInformation studentAcademicInfo = new StudentAcademicInformation();
		    	studentAcademicInfo.Attendance = resultSet.getInt(1);
		    	userList.add(studentAcademicInfo);
		    }
		    catch(Exception exception) {
		    	exception.printStackTrace();
		    }
			return userList;
		}
		//to view grade info
		else {
			query = "SELECT CGPA FROM academic WHERE studentId ="+studentId+" ";
			resultSet = databaseRepository.select(query);
			if(userList.size() != 0) {
				userList.clear();
			}
			try {
		    	resultSet.next();
		        StudentAcademicInformation studentAcademicInfo = new StudentAcademicInformation();
		    	studentAcademicInfo.overallGrade = resultSet.getDouble(1);
		    	userList.add(studentAcademicInfo);
		    }
		    catch(Exception exception) {
		    	exception.printStackTrace();
		    }
			return userList;
		}
	}
	//method to view student fee info
	public ArrayList<Object> viewStudentFeeInfo(int studentId,int semester) {
		query = "SELECT * FROM studentfee WHERE studentId ="+studentId+" and semester = "+semester+"";
		resultSet = databaseRepository.select(query);
		if(userList.size() != 0) {
			userList.clear();
		}
		try {
	    	resultSet.next();
	    	StudentFee studentFeeInfo = new StudentFee();
	    	studentFeeInfo.studentId = resultSet.getInt(1);
	    	studentFeeInfo.name = resultSet.getString(2);
	    	studentFeeInfo.semester = resultSet.getInt(3);
	    	studentFeeInfo.AccomodationMode = resultSet.getString(4);
	    	if(studentFeeInfo.AccomodationMode.equalsIgnoreCase("hosteller")) {
	    		studentFeeInfo.messFEE = resultSet.getDouble(5);
	    		studentFeeInfo.hostelFEE = resultSet.getDouble(6);
	    	}
	    	studentFeeInfo.tutionFEE = resultSet.getDouble(6);
	    	userList.add(studentFeeInfo);
	    }
	    catch(Exception exception) {
	    	exception.printStackTrace();
	    }
		return userList;
	}
	//method to add student academic and fee info when new student get created.
    public int addStudentInfo(StudentAcademicInformation academicInfo,StudentFee feeInfo) {
    	if(feeInfo == null) {
    		query = "INSERT INTO academic (studentId,semester,subject,facultyHandled) VALUES ("+academicInfo.studentId+","+academicInfo.semester+",'"+academicInfo.subject+"','"+academicInfo.facultyHandled+"')";
    		status = databaseRepository.insert(query);
    		return status;
    	}
    	else {
    		query = "INSERT INTO studentfee (studentId,name,semester,Accomodation,messFee,hostelFee,tutionFee) VALUES ("+feeInfo.studentId+",'"+feeInfo.name+"',"+feeInfo.semester+",'"+feeInfo.AccomodationMode+"',"+feeInfo.messFEE+","+feeInfo.hostelFEE+","+feeInfo.tutionFEE+")";
    		status = databaseRepository.insert(query);
    		return status;
    	}
    }
    public int addAcademicInfo(StudentAcademicInformation academicInfo,double value,int fieldStatus) {
    	if(fieldStatus == 1) {
    		query = "UPDATE academic SET PERIODICAL_TEST_1 = "+value+" WHERE studentId = "+academicInfo.studentId+" AND subject = '"+academicInfo.subject+"' ";
    		status = databaseRepository.insert(query);
    		return status;
    	}
    	else if(fieldStatus == 2) {
    		query = "UPDATE academic SET PERIODICAL_TEST_2 = "+value+" WHERE studentId = "+academicInfo.studentId+" AND subject = '"+academicInfo.subject+"' ";
    		status = databaseRepository.insert(query);
    		return status;
    	}
    	else if(fieldStatus == 3) {
    		query = "UPDATE academic SET Attendance  = "+value+" WHERE studentId = "+academicInfo.studentId+" AND semester = "+academicInfo.semester+" ";
    		status = databaseRepository.insert(query);
    		return status;
    	}
    	else {
    		query = "UPDATE academic SET CGPA = "+value+" WHERE studentId = "+academicInfo.studentId+" AND semester = "+academicInfo.semester+" ";
    		status = databaseRepository.insert(query);
    		return status;
    	}
    }
}
