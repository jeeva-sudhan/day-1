/* 
 * StaffDataUI : 
 * This class have functionalities of which staff can use, using this staff can view their leave status and can view their info as well as student info belonged to them.
 * This class provides functionality to approve or decline student leave to staff of belonged students.
 * This class also provides functionality for staff to apply leave.
 */
package view;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import controller.InternalProcess;
import controller.Validate;
import controller.DateVerifier;
import model.Staff;
import model.StaffLeaveInformation;
import model.StudentLeaveInformation;
import model.Student;
import model.StudentAcademicInformation;
public class StaffDataUI implements UserFunctionality,StaffFunctionality {
	private int processStatus;
    private int staffChoice;
    private int LeaveStatus;
    private boolean idStatus;
    private boolean dateStatus;
    ResultSet resultSet;
    Scanner input = new Scanner(System.in);
    Scanner inp_str = new Scanner(System.in);
    InternalProcess internalProcess = new InternalProcess();
    Validate validate = new Validate();
    Staff user_staff = new Staff();
    DateVerifier verifyDate = new DateVerifier();
    StudentAcademicInformation academicInfo = new StudentAcademicInformation();
    ArrayList<Student> studentList = new ArrayList<Student>();
    ArrayList<StudentLeaveInformation> studentLeaveList = new ArrayList<StudentLeaveInformation>();
    ArrayList<StaffLeaveInformation> staffLeaveList = new ArrayList<StaffLeaveInformation>();
    ArrayList<StudentAcademicInformation> academicList = new ArrayList<StudentAcademicInformation>();
    //LeaveApply() : method for staff to apply leave
	public void LeaveApply(Object user) {
		user_staff = (Staff)user;
		StaffLeaveInformation staffLeaveInfo = new StaffLeaveInformation();
		System.out.println("WELCOME TO LEAVE/OD PAGE");
		staffLeaveInfo.staffId = user_staff.staffId ;
		staffLeaveInfo.staffName = user_staff.staffName;
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a");
		while(true) {
			while(true) {
				System.out.println("PLEASE ENTER DATE AFTER: " + dateFormat.format(currentDate));
				System.out.println("ENTER FROM DATE IN FORMAT YEAR/MONTH/DATE");
				staffLeaveInfo.fromDate = inp_str.nextLine();
				dateStatus = false;
				dateStatus = DateVerifier.isValidDate(staffLeaveInfo.fromDate);
				if(dateStatus == false) {
					System.out.println("PLEASE ENTER VALID DATE");
					continue;
				}
				dateStatus = false;
				dateStatus = verifyDate.checkDate(staffLeaveInfo, null);
				if(dateStatus == false) {
					System.out.println("YOU HAVE ALREADY APPLIED LEAVE/OD ON THAT DATE");
					System.out.println("PLEASE ENTER VALID DATE");
					continue;
				}
				break;
			}
			while(true) {
				System.out.println("ENTER TO DATE IN FORMAT YEAR/MONTH/DATE");
				staffLeaveInfo.toDate = inp_str.nextLine();
				dateStatus = false;
				dateStatus = DateVerifier.isValidDate(staffLeaveInfo.fromDate);
				if(dateStatus == false) {
					System.out.println("PLEASE ENTER VALID DATE");
					continue;
				}
				break;
			}
			dateStatus = false;
			dateStatus = DateVerifier.isValidLeaveDate(staffLeaveInfo.fromDate,staffLeaveInfo.toDate);
			if(dateStatus == false) {
				System.out.println("PLEASE ENTER VALID FROM DATE/TO DATE");
				continue;
			}
			break;
		}
		System.out.println("ENTER YOUR LEAVE TYPE --> (LEAVE/OD)");
		staffLeaveInfo.LeaveType = inp_str.nextLine();
		processStatus = internalProcess.LeaveApply(staffLeaveInfo, null);
		if(processStatus == 0) {
    		System.out.println("UPDATE FAILED !");
		}
    	else {
    		System.out.println("STAFF Id : " + user_staff.staffId + " " +staffLeaveInfo.LeaveType +" APPLIED SUCCESSFULLY");
    	}
	}
	//To view user(staff) info
    public void viewUserInfo(Object user) {
    	user_staff = (Staff)user;
    	System.out.println();
		System.out.println("STAFF ID       : "+ user_staff.staffId); 
		System.out.println("STAFF NAME     : " + user_staff.staffName);
	    System.out.println("EMAIL ID       : " + user_staff.staffEmailId);
	    System.out.println("SUBJECT HANDLED: " + user_staff.subject_handled); 
	    System.out.println("DEGREE         : "+ user_staff.degree); 
	    System.out.println("PHONE NO       : "+ user_staff.phoneNo); 
	    System.out.println("ADDRESS        : "+ user_staff.address); 
	    System.out.println();

	}
    //To approve/decline leave/Od applied by student
	public void LeaveDecision(Staff staff) {
		idStatus = validate.isValidMentorId(staff.staffId, "studentleavestatus");
		if(idStatus) {
			studentLeaveList = (ArrayList)internalProcess.viewUserLeaveInfo("studentleavestatus",staff.staffId, 0);
			if(studentLeaveList.size() == 0) {
				System.out.println("NO ONE APPLIED LEAVE/OD");
				return;
			}
			for(int iterator = 0;iterator<studentLeaveList.size();iterator++) {
				StudentLeaveInformation  studentLeaveInfo = studentLeaveList.get(iterator);
				System.out.println();
    			System.out.println("STUDENT LEAVE ID: "+ studentLeaveInfo.studentLeaveId);
        		System.out.println("STAFF ID        : " + studentLeaveInfo.staffId);
        		System.out.println("STUDENT ID      : " + studentLeaveInfo.studentId);
        		System.out.println("STUDENT NAME    : " + studentLeaveInfo.name); 
        		System.out.println("FROM DATE       : " + studentLeaveInfo.fromDate);
        		System.out.println("TO DATE         : " + studentLeaveInfo.toDate);
        		System.out.println("LEAVE TYPE      : " + studentLeaveInfo.LeaveType);
        		System.out.println("LEAVE STATUS    : " + studentLeaveInfo.LeaveStatus);
        		System.out.println();
			}
			while(true) {
				System.out.println("PRESS 1 TO APPROVE/DECLINE LEAVE/OD FOR STUDENTS");
				System.out.println("PRESS 2 TO GO BACK");
				staffChoice = input.nextInt();
				switch(staffChoice) {  
				   case 1: {
					    System.out.println("ENTER THE STUDENT ID");
						int studentId = input.nextInt();
						System.out.println("ENTER STUDENT LEAVE ID");
						int studentLeaveId = input.nextInt();
						idStatus = validate.isValidMentorIdStudentID(staff.staffId, studentId);
						if(idStatus) {
							idStatus = false;
							idStatus = validate.isValidLeaveId(studentId, studentLeaveId, "studentleavestatus");
							if(idStatus) {
								System.out.println("PRESS 1 TO APPROVE LEAVE/OD");
								System.out.println("PRESS 2 TO DECLINE LEAVE/OD");
								LeaveStatus = input.nextInt();
								if(LeaveStatus == 1) {
									internalProcess.LeaveApproval(studentId,"studentleavestatus","APPROVED",studentLeaveId);
									System.out.println("LEAVE/OD APPROVED FOR STUDENT ID: "+studentId);
								}
								else {
									internalProcess.LeaveApproval(studentId,"studentleavestatus","DECLINED",studentLeaveId);
									System.out.println("LEAVE/OD DECLINED FOR STUDENT ID: "+studentId);
								}
							}
							else {
								System.out.println("PLEASE ENTER VALID STUDENT ID/STUDENT LEAVE ID");
								continue;
							}
						}
						else {
							System.out.println("PLEASE ENTER VALID STUDENT ID");
							continue;
						}
					   break;
				    }
				    case 2: {
				    	break;
				    }
				    default: {
				    	System.out.println("PLEASE ENTER VALID CHOICE");
				    }
			    }
				break;
			}
		}
		else {
			System.out.println("NO ONE APPLIED LEAVE/OD");
			return;
		}
	}
	//To view respective student details by staff 
	public void viewStudentInfo(Staff staff) {
		System.out.println("PRESS 1 TO VIEW ENTIRE STUDENT DATA");
		System.out.println("PRESS 2 TO VIEW PARTICULAR STUDENT DATA");
		System.out.println("PRESS 3 TO EXIT");
		int userChoice = input.nextInt();
		switch(userChoice) {
		    case 1: {
		    	studentList = (ArrayList)internalProcess.viewEntireUserInfo(staff.staffId,"student",0);
				for(int iterator = 0;iterator<studentList.size();iterator++) {
					Student student = studentList.get(iterator);
					System.out.println();
					System.out.println("MENTOR ID        : "+ student.staffId); 
					System.out.println("STUDENT ID       : " + student.studentId); 
					System.out.println("NAME             : " + student.name);
					System.out.println("DATE OF BIRTH    : " + student.dateOfBirth);
				    System.out.println("EMAIL ID         : " + student.studentEmailId);
				    System.out.println("DEPARTMENT       : " + student.branch); 
				    System.out.println("YEAR             : "+ student.year); 
				    System.out.println("ACCOMODATION MODE: "+ student.AccomodationMode); 
				    System.out.println("DEGREE           : "+ student.degree); 
				    System.out.println("BATCH            : "+ student.batch); 
				    System.out.println("STUDENT NO       : "+ student.studentNo); 
				    System.out.println("PARENT NO        : "+ student.parentNo); 
				    System.out.println("ADDRESS          : "+ student.address); 
				    System.out.println();
				}
				break;
		    }
		    case 2: {
		    	while(true) {
					System.out.println("ENTER STUDENT ID");
					int studentId = input.nextInt();
					idStatus = validate.isValidMentorIdStudentID(staff.staffId, studentId);
					if(idStatus) {
						studentList = (ArrayList)internalProcess.viewEntireUserInfo(staff.staffId,"student",studentId);
						for(int iterator = 0;iterator<studentList.size();iterator++) {
							Student student = studentList.get(iterator);
							System.out.println();
							System.out.println("MENTOR ID        : "+ student.staffId); 
							System.out.println("STUDENT ID       : " + student.studentId); 
							System.out.println("NAME             : " + student.name);
							System.out.println("DATE OF BIRTH    : " + student.dateOfBirth);
						    System.out.println("EMAIL ID         : " + student.studentEmailId);
						    System.out.println("DEPARTMENT       : " + student.branch); 
						    System.out.println("YEAR             : "+ student.year); 
						    System.out.println("ACCOMODATION MODE: "+ student.AccomodationMode); 
						    System.out.println("DEGREE           : "+ student.degree); 
						    System.out.println("BATCH            : "+ student.batch); 
						    System.out.println("STUDENT NO       : "+ student.studentNo); 
						    System.out.println("PARENT NO        : "+ student.parentNo); 
						    System.out.println("ADDRESS          : "+ student.address); 
						    System.out.println();
						}
						break;
					}
					else {
						System.out.println("ENTER VALID STUDENT ID / VALID STAFF ID");
						continue;
					}
				}
		    }
		    case 3: {
		    	break;
		    }
		    default: {
		    	System.out.println("PLEASE ENTER VALID CHOICE");
		    }
			break;
		    }
	}
	//To view leave status applied by staff
	public void viewLeaveStatus(Object user) {
		user_staff = (Staff)user;
		idStatus = validate.isValidId(user_staff.staffId, "staffleavestatus");
		if(idStatus) {
			staffLeaveList = (ArrayList)internalProcess.viewUserLeaveInfo("staffleavestatus", user_staff.staffId, 1);
			if(staffLeaveList.size() == 0) {
				System.out.println("NO ONE APPLIED LEAVE/OD");
				return;
			}
			for(int iterator = 0;iterator<staffLeaveList.size();iterator++)
			{
				StaffLeaveInformation  staffLeaveInfo = staffLeaveList.get(iterator);
				System.out.println();
				System.out.println("STAFF LEAVE ID: " + staffLeaveInfo.staffLeaveId);
				System.out.println("STAFF ID      : " + staffLeaveInfo.staffId);
				System.out.println("Staff NAME    : " + staffLeaveInfo.staffName); 
				System.out.println("FROM DATE     : " + staffLeaveInfo.fromDate);
				System.out.println("TO DATE       : " + staffLeaveInfo.toDate);
				System.out.println("LEAVE TYPE    : " + staffLeaveInfo.LeaveType);
				System.out.println("LEAVE STATUS  : " + staffLeaveInfo.LeaveStatus);
				System.out.println();
			}
		}
		else {
			System.out.println("YOU HAVEN'T APPLIED LEAVE");
		}
	}
	//To update academic info of student based on semester
	public void updateStudentAcademicInfo(Staff staff) {
		double mark = 0;
		int fieldStatus =0;
		int studentId = 0;
		System.out.println("PRESS 1 TO ENTER SUBJECT MARKS");
		System.out.println("PRESS 2 TO ENTER ATTENDANCE");
		System.out.println("PRESS 3 TO ENTER SGPA");
		int staffChoice = input.nextInt();
		switch(staffChoice) {
		     case 1: {
		    	 do {
		 			System.out.println("ENTER STUDENT ID");
		 			studentId = input.nextInt();
		 			idStatus = validate.isValidMentorIdStudentID(staff.staffId,studentId);
		 			if(idStatus) {
		 				System.out.println("ENTER SEMESTER");
		 				academicInfo.semester = input.nextInt();
		 				academicList = (ArrayList)internalProcess.viewAcademicInfo(studentId,academicInfo.semester,"subject");
		 				System.out.println("SEMESTER :" + academicInfo.semester);
		 				System.out.println("SUBJECTS : ");
		 				for(int iterator = 0;iterator<academicList.size();iterator++) {
		 					academicInfo = academicList.get(iterator);
		 					academicInfo.studentId = studentId;
		 					System.out.println(academicInfo.subject);
		 					System.out.println("ENTER MARK: ");
		 					mark = input.nextDouble();
		 					System.out.println("PRESS 1 FOR PERIODICAL TEST - 1");
		 					System.out.println("PRESS 2 FOR PERIODICAL TEST - 2");
		 					fieldStatus = input.nextInt();
		 					internalProcess.addAcademicInfo(academicInfo, mark, fieldStatus);
		 				}
		 				break;
		 			}
		 			else {
		 				System.out.println("PLEASE ENTER VALID STUDENT ID");
		 				continue;
		 			}
		 		}while(true);
		     }
		     case 2: {
		    	 fieldStatus = 3;
		    	 do {
		    		 System.out.println("ENTER STUDENT ID");
			    	 studentId = input.nextInt();
			    	 idStatus = validate.isValidMentorIdStudentID(staff.staffId,studentId);
			    	 if(idStatus) {
			    		 System.out.println("ENTER SEMESTER");
			 			 academicInfo.semester = input.nextInt();
			 			 academicInfo.studentId = studentId;
			 			 System.out.println("ENTER STUDENT ATTENDANCE");
			 			 int attendance = input.nextInt();
			 			 internalProcess.addAcademicInfo(academicInfo,attendance, fieldStatus);
			 			 break;
			    	 }
			    	 else {
			    		 System.out.println("PLEASE ENTER VALID STUDENT ID");
			 			 continue;
			 		}
		    	 }while(true);
		     }
		     case 3: {
		    	 fieldStatus = 4;
		    	 do {
		    		 System.out.println("ENTER STUDENT ID");
			    	 studentId = input.nextInt();
			    	 idStatus = validate.isValidMentorIdStudentID(staff.staffId,studentId);
			    	 if(idStatus) {
			    		 System.out.println("ENTER SEMESTER");
			 			 academicInfo.semester = input.nextInt();
			 			 academicInfo.studentId = studentId;
			 			 System.out.println("ENTER STUDENT CGPA");
			 			 double grade = input.nextDouble();
			 			 internalProcess.addAcademicInfo(academicInfo, grade, fieldStatus);
			 			 break;
			    	 }
			    	 else {
			    		 System.out.println("PLEASE ENTER VALID STUDENT ID");
			 			 continue;
			 		}
		    	 }while(true);
		     }
		}
	}
}