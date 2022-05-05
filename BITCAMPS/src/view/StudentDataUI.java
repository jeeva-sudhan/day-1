/*
 * StudentDataUI:
 * This class have functionalities of which student can use,using this student can view his/her info
 * And student can view subjects ,marks ,cgpa based on semester and they can view their semester fee and can download the receipt also.
 * This class provides functionality for student to apply leave and can view his/her leave status.
 */
package view;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import controller.DateVerifier;
import controller.InternalProcess;
import controller.Validate;
import model.Student;
import model.StudentFee;
import model.StudentLeaveInformation;
import model.StudentAcademicInformation;
public class StudentDataUI implements UserFunctionality,StudentFunctionality {
	private int processStatus;
    private boolean idStatus;
    private boolean dateStatus;
    Scanner input = new Scanner(System.in);
    Scanner inp_str = new Scanner(System.in);
    InternalProcess internalProcess = new InternalProcess();
    Validate validate = new Validate();
    DateVerifier verifyDate = new DateVerifier();
    Student user_student = new Student();
    ArrayList<StudentLeaveInformation> studentLeaveList = new ArrayList<StudentLeaveInformation>();
    ArrayList<StudentAcademicInformation> academicList = new ArrayList<StudentAcademicInformation>();
    ArrayList<StudentFee> feeList = new ArrayList<StudentFee>();
    StudentAcademicInformation studentAcademicInfo = new StudentAcademicInformation();
    StudentFee feeInfo = new StudentFee();
    //method for students to apply leave
    public void LeaveApply(Object user) {
    	user_student = (Student)user;
		StudentLeaveInformation studentLeaveInfo = new StudentLeaveInformation();
		System.out.println("WELCOME TO LEAVE/OD PAGE");
		studentLeaveInfo.studentId = user_student.studentId ;
		studentLeaveInfo.name = user_student.name;
		while(true) {
			System.out.println("ENTER YOUR MENTOR ID");
			user_student.staffId = input.nextInt();
			idStatus = validate.isValidMentorId(user_student.staffId, user_student.name);
			studentLeaveInfo.staffId = user_student.staffId;
			if(idStatus) {
				Date currentDate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a");
				while(true)
				{
					while(true)
					{
						System.out.println("PLEASE ENTER DATE AFTER: " + dateFormat.format(currentDate));
						System.out.println("ENTER FROM DATE IN FORMAT YEAR/MONTH/DATE");
						studentLeaveInfo.fromDate = inp_str.nextLine();
						dateStatus = false;
						dateStatus = DateVerifier.isValidDate(studentLeaveInfo.fromDate);
						if(dateStatus == false) {
							System.out.println("PLEASE ENTER VALID DATE");
							continue;
						}
						dateStatus = false;
						dateStatus = verifyDate.checkDate(null, studentLeaveInfo);
						if(dateStatus == false) {
							System.out.println("YOU HAVE ALREADY APPLIED LEAVE/OD ON THAT DATE");
							System.out.println("PLEASE ENTER VALID DATE");
							continue;
						}
						break;
					}
					while(true)
					{
						System.out.println("ENTER TO DATE IN FORMAT YEAR/MONTH/DATE");
						studentLeaveInfo.toDate = inp_str.nextLine();
						dateStatus = false;
						dateStatus = DateVerifier.isValidDate(studentLeaveInfo.fromDate);
						if(dateStatus == false) {
							System.out.println("PLEASE ENTER VALID DATE");
							continue;
						}
						break;
					}
					dateStatus = false;
					dateStatus = DateVerifier.isValidLeaveDate(studentLeaveInfo.fromDate,studentLeaveInfo.toDate);
					if(dateStatus == false) {
						System.out.println("PLEASE ENTER VALID FROM DATE/TO DATE");
						continue;
					}
					break;
				}
				System.out.println("ENTER YOUR LEAVE TYPE --> (LEAVE/OD)");
				studentLeaveInfo.LeaveType = inp_str.nextLine();
				processStatus = internalProcess.LeaveApply(null, studentLeaveInfo);
				if(processStatus == 0) {
		    		System.out.println("UPDATE FAILED !");
				}
		    	else {
		    		System.out.println("STUDENT Id : " + studentLeaveInfo.studentId + " " +studentLeaveInfo.LeaveType +" APPLIED SUCCESSFULLY");
		    		break;
		    	}
			}
			else {
				System.out.println("PLEASE ENTER VALID MENTOR ID");
				continue;
			}
		}
	}
    //To view user(student)info
	public void viewUserInfo(Object user) {
		user_student = (Student)user;
		System.out.println();
		System.out.println("MENTOR ID        : "+ user_student.staffId); 
		System.out.println("STUDENT ID       : " + user_student.studentId); 
		System.out.println("NAME             : " + user_student.name);
		System.out.println("DATE OF BIRTH    : " + user_student.dateOfBirth);
		System.out.println("EMAIL ID         : " + user_student.studentEmailId);
		System.out.println("DEPARTMENT       : " + user_student.branch); 
		System.out.println("YEAR             : "+ user_student.year); 
		System.out.println("ACCOMODATION MODE: "+ user_student.AccomodationMode); 
	    System.out.println("DEGREE           : "+ user_student.degree); 
	    System.out.println("BATCH            : "+ user_student.batch); 
	    System.out.println("STUDENT NO       : "+ user_student.studentNo); 
		System.out.println("PARENT NO        : "+ user_student.parentNo); 
		System.out.println("ADDRESS          : "+ user_student.address); 
		System.out.println();
	}
	//To view leaveStatus of leave/od applied by student
    public void viewLeaveStatus(Object user) {
    	user_student = (Student)user;
    	idStatus = validate.isValidId(user_student.studentId, "studentleavestatus");
    	if(idStatus) {
    		studentLeaveList = (ArrayList)internalProcess.viewUserLeaveInfo("studentleavestatus", user_student.studentId, 1);
    		for(int iterator = 0;iterator<studentLeaveList.size();iterator++)
    		{
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
    	}
    	else {
    		System.out.println("YOU HAVEN'T APPLIED LEAVE");
    	}
    }
    //To view student fee info based on semester
    public void studentFeeInfo(Student student) {
		while(true) {
			System.out.println("ENTER SEMESTER");
			int semester = input.nextInt();
			idStatus = validate.isValidIdSemester(student.studentId,semester);
			if(idStatus) {
				feeList = (ArrayList)internalProcess.viewStudentFeeInfo(student.studentId,semester);
				feeInfo = feeList.get(0);
				System.out.println();
				System.out.println("STUDENT ID        : " + feeInfo.studentId);
				System.out.println("STUDENT NAME      : " + feeInfo.name);
				System.out.println("SEMESTER          : " + feeInfo.semester);
				System.out.println("ACCOMODATION MODE : " + feeInfo.AccomodationMode);
				if(feeInfo.AccomodationMode.equalsIgnoreCase("hosteller")) {
					System.out.println("MESS FEE          : " + feeInfo.messFEE);
					System.out.println("HOSTEL FEE        : " + feeInfo.hostelFEE);
					System.out.println();
				}
				System.out.println("TUTION FEE        : " + feeInfo.tutionFEE);
				return;
			}
			else {
				System.out.println("PLEASE ENTER VALID ID/SEMESTER");
				continue;
			}
		}
	}
    //To download FEE receipt of FEES paid by student based on semester
    public void studentFeeReceiptDownload(Student student) {
		while(true) {
			System.out.println("ENTER SEMESTER");
			int semester = inp_str.nextInt();
			idStatus = validate.isValidIdSemester(student.studentId,semester);
			if(idStatus) {
				feeList = (ArrayList)internalProcess.viewStudentFeeInfo(student.studentId,semester);
				for(int iterator =0;iterator<86;iterator++) {
					if(iterator < 40)
						System.out.print(" ");
					else
					   System.out.print("-");
				}
				feeInfo = feeList.get(0);
				System.out.printf("\n%-40s|   %-41s|","","BANNARI AMMAN INSTITUTE OF TECHNOLOGY");
				System.out.printf("\n\n%-40s| %-20s %-9sROLL NO: %-1s |","","FEE RECEIPT","",feeInfo.studentId);
				System.out.printf("\n%-40s| %-20s: %-20s |\n","","SEMESTER",feeInfo.semester);
				for(int iterator =0;iterator<86;iterator++) {
					if(iterator < 40)
						System.out.print(" ");
					else
					System.out.print("-");
				}
				System.out.printf("%-40s| %-40s |","","");
				System.out.printf("\n%-40s| %-20s: %-20s |","","STUDENT ID ",feeInfo.studentId);
				System.out.printf("\n%-40s| %-20s: %-20s |","","STUDENT NAME",feeInfo.name);
				System.out.printf("\n%-40s| %-20s: %-20s |","","ACCOMODATION MODE",feeInfo.AccomodationMode);
				if(feeInfo.AccomodationMode.equalsIgnoreCase("hosteller")) {
					System.out.printf("\n%-40s| %-20s: %-20s |","","MESS FEE ",feeInfo.messFEE);
					System.out.printf("\n%-40s| %-20s: %-20s |","","HOSTEL FEE ",feeInfo.hostelFEE);
				}
				System.out.printf("\n%-40s| %-20s: %-20s |\n","","TUTION FEE ",feeInfo.tutionFEE);
				for(int iterator =0;iterator<86;iterator++) {
					if(iterator < 40)
						System.out.print(" ");
					else
					System.out.print("-");
				}
				System.out.println();
				return;
			}
			else {
				System.out.println("PLEASE ENTER VALID ID/SEMESTER");
				continue;
			}
		}
    }
    //To view subjects ,attendance,CGPA based on semester
    public void studentAcademicInfo(Student student) {
    	StudentFee feeInfo = new StudentFee();
    	System.out.println("PRESS 1 TO VIEW SUBJECTS");
    	System.out.println("PRESS 2 TO VIEW ATTENDANCE");
    	System.out.println("PRESS 3 TO VIEW GRADE");
    	int studentChoice = input.nextInt();
    	switch(studentChoice) {
    	    case 1: {
    	    	System.out.println("PRESS 1 TO VIEW FOR CURRENT SEMESTER SUBJECTS");
        		System.out.println("PRESS 2 TO VIEW FOR FORTHCOMING SEMESTER SUBJECTS");
        		int choice = input.nextInt();
        		if(choice == 1) {
        			academicList = (ArrayList)internalProcess.viewAcademicInfo(student.studentId,6,"subject");
        			System.out.printf("\n%-65s %-20s\n","","SEMESTER: " + 6);
        			for(int iterator =0;iterator<117;iterator++) {
    					if(iterator < 20)
    						System.out.print(" ");
    					else
    					System.out.print("-");
    				}
        			System.out.println();
        			System.out.printf("%-20s|   %-20s|   %-20s|   %-20s|   %-20s|\n","","SUBJECT","FACULTY HANDLED","PERIODICAL TEST - 1","PERIODICAL TEST - 2");
        			for(int iterator =0;iterator<117;iterator++) {
    					if(iterator < 20)
    						System.out.print(" ");
    					else
    					System.out.print("-");
    				}
        			System.out.println();
        			for(int iterator = 0;iterator<academicList.size();iterator++) {
        				studentAcademicInfo = academicList.get(iterator);
        				System.out.printf("%-20s|   %-20s|   %-20s|   %-20s|   %-20s|\n","",studentAcademicInfo.subject,studentAcademicInfo.facultyHandled,studentAcademicInfo.periodicalOneMark,studentAcademicInfo.periodicalTwoMark);
        				for(int innerloop_iterator =0;innerloop_iterator<117;innerloop_iterator++) {
        					if(innerloop_iterator < 20)
        						System.out.print(" ");
        					else
        						System.out.print("-");
        				}
        				System.out.println();
        			}
        			break;
        		}
        		else if(choice == 2) {
        			academicList = (ArrayList)internalProcess.viewAcademicInfo(student.studentId,7,"subject");
        			System.out.printf("%-25s %-20s\n","","SEMESTER: " + 7);
        			for(int iterator =0;iterator<44;iterator++) {
    					if(iterator < 20)
    						System.out.print(" ");
    					else
    					System.out.print("-");
    				}
        			System.out.println();
        			System.out.printf("%-20s|   %-20s|\n","","SUBJECT");
        			for(int iterator =0;iterator<44;iterator++) {
    					if(iterator < 20)
    						System.out.print(" ");
    					else
    					System.out.print("-");
    				}
        			System.out.println();
        			for(int iterator = 0;iterator<academicList.size();iterator++) {
        				studentAcademicInfo = academicList.get(iterator);
        				System.out.printf("%-20s|   %-20s|\n","",studentAcademicInfo.subject);
        				for(int innerloop_iterator =0;innerloop_iterator<44;innerloop_iterator++) {
        					if(innerloop_iterator < 20)
        						System.out.print(" ");
        					else
        						System.out.print("-");
        				}
        				System.out.println();
        			}
        			break;
        		}
        		else {
        			System.out.println("PLEASE ENTER VALID CHOICE");
        			break;
        		}
    	    }
    	    case 2: {
    	    	academicList = (ArrayList)internalProcess.viewAcademicInfo(student.studentId,6, "attendance");
        		studentAcademicInfo = academicList.get(0);
        		System.out.println("YOUR CURRENT SEMESTER ATTENDANCE: "+ studentAcademicInfo.Attendance);
        		break;
    	    }
    	    case 3: {
    	    	academicList = (ArrayList)internalProcess.viewAcademicInfo(student.studentId,6, "");
        		studentAcademicInfo = academicList.get(0);
        		System.out.println("YOUR CURRENT CUMULATIVE GPA: "+studentAcademicInfo.overallGrade);
    		    break;
    	    }
    	    default: {
        		System.out.println("PLEASE ENTER VALID CHOICE");
        		break;
        	}
    	}
    }  
}
