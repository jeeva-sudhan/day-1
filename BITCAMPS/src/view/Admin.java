/* ADMIN CLASS :
 * This class can add or remove staff or student in database and this class can update staff or student data in database.
 * This Admin class can approve or decline the leave applied by Staff.
 * This class can view staff and student details.
 */
package view;
import java.util.Scanner;
import controller.AccountGenerator;
import controller.Authorization;
import controller.InternalProcess;
import controller.Validate;
import controller.DateVerifier;
import java.util.ArrayList;
import model.Staff;
import model.Student;
import model.StaffLeaveInformation;
import model.StudentAcademicInformation;
import model.StudentFee;
public class Admin  extends AdminFunctionality {
	private String emailId = "admin.bit@gmail.com";
    private String password = "Admin@123";
    private int processStatus;
    private int studentId;
    private String mentorName;
    private boolean inputStatus;
    private boolean idStatus;
    private int adminchoice;
    private int LeaveStatus;
    Scanner input = new Scanner(System.in);
    Scanner inp_str = new Scanner(System.in);
    InternalProcess internalProcess = new InternalProcess();
    Validate validate = new Validate();
    Student user_student = new Student();
    Staff user_staff = new Staff();
    AccountGenerator accountGenerator = new AccountGenerator();
    ArrayList<Staff> staffList = new ArrayList<Staff>();
    ArrayList<Student> studentList = new ArrayList<Student>();
    ArrayList<StaffLeaveInformation> staffLeaveList= new ArrayList<StaffLeaveInformation>();
    public String getEmailId() {
    	return emailId;
    }
    public String getPassword() {
    	return password;
    }
    public void addStaff() {
    	System.out.println("ENTER STAFF NAME");
    	while(true) {
			System.out.println("ENTER STAFF NAME IN FORMAT --> NAME FOLLOWED BY INITIAL WHICH IS SEPERATED BY SPACE");
	    	System.out.println("EX: JEEVA S OR EX: JEEVA SK");
	    	user_staff.staffName = inp_str.nextLine();
	    	inputStatus = Authorization.checkUserName(user_staff.staffName);
	    	if(inputStatus == false) {
	    		continue;
	    	}
	    	break;
		}
    	System.out.println("ENTER STAFF BRANCH");
    	user_staff.subject_handled = input.nextLine();
    	System.out.println("ENTER STAFF DEGREE");
    	user_staff.degree = input.nextLine();
    	System.out.println("ENTER STAFF'S PHONE NUMBER");
    	System.out.println("PLEASE ENTER 10 DIGIT PHONE NUMBER");
    	while(true) {
    		user_staff.phoneNo = input.nextLine();
        	inputStatus = Authorization.checkPhoneNumber(user_staff.phoneNo);
        	if(inputStatus) {
        		break;
        	}
        	else {
        		System.out.println("PLEASE ENTER VALID 10 DIGIT PHONE NUMBER");
            	continue;
        	}
    	}
    	System.out.println("ENTER STAFF ADDRESS");
    	user_staff.address = input.nextLine();
    	user_staff.staffEmailId = accountGenerator.gmailGenerator(user_staff.staffName, "staff");
    	user_staff.password = accountGenerator.passwordGenerator(user_staff.staffName);
    	processStatus = internalProcess.addUser(user_staff, null);
    	if(processStatus == 0)
    		System.out.println("INSERTED FAILED");
    	else
    		System.out.println("STAFF NAME: " + user_staff.staffName +" INSERTED SUCCESSFULLY");
    }
	public void removeStaff() {
		while(true) {
			System.out.println("ENTER THE STAFF ID");
			int staffId = input.nextInt();
			inputStatus = validate.isValidId(staffId, "staff");
			if(inputStatus) {
				inputStatus = validate.isValidId(staffId, "staffleavestatus");
				if(inputStatus) {
					internalProcess.removeUser(staffId,"staffleavestatus");
				}
				internalProcess.removeUser(staffId, "staff");
				System.out.println("STAFFID: "+ staffId + " REMOVED SUCCESSFULLY");
				return;
			}
			else {
				System.out.println("PLEASE ENTER VALID ID");
				continue;
			}
		}
	}
	public void addStudent() {
    	System.out.println("ENTER STUDENT NAME");
    	while(true) {
			System.out.println("ENTER STUDENT NAME IN FORMAT --> NAME FOLLOWED BY INITIAL WHICH IS SEPERATED BY SPACE");
	    	System.out.println("EX: JEEVA S OR EX: JEEVA SK");
	    	user_student.name = inp_str.nextLine();
	    	inputStatus = Authorization.checkUserName(user_student.name);
	    	if(inputStatus == false) {
	    		continue;
	    	}
	    	break;
		}
    	inputStatus = false;
    	System.out.println("PLEASE ENTER DATE OF BIRTH IN FORMAT DATE/MONTH/YEAR");
       	while(true)
    	{
    		System.out.println("ENTER STUDENT DATE OF BIRTH");
    		user_student.dateOfBirth = input.nextLine();
    		inputStatus = DateVerifier.isValidDateOfBirth(user_student.dateOfBirth);
    		if(inputStatus)
    		{
    			break;
    		}
    		else
    		{
    			System.out.println("PLEASE ENTER VALID DATE OF BIRTH");
    			System.out.println("PLEASE ENTER DATE OF BIRTH IN FORMAT DATE/MONTH/YEAR");
    			continue;
    		}
    	}
    	System.out.println("ENTER STUDENT DEGREE");
    	user_student.degree = input.nextLine();
    	System.out.println("ENTER STUDENT BRANCH");
    	user_student.branch = input.nextLine();
    	System.out.println("ENTER STUDENT YEAR");
    	user_student.year = input.nextLine();
    	System.out.println("ENTER STUDENT BATCH");
    	user_student.batch = input.nextLine();
    	System.out.println("ENTER STUDENT ACCOMODATION MODE");
    	user_student.AccomodationMode = input.nextLine();
    	System.out.println("ENTER STUDENT NUMBER");
		System.out.println("PLEASE ENTER 10 DIGIT PHONE NUMBER");
    	while(true) {
    		user_student.studentNo  = input.nextLine();
        	inputStatus = Authorization.checkPhoneNumber(user_student.studentNo);
        	if(inputStatus) {
        		break;
        	}
        	else {
        		System.out.println("PLEASE ENTER VALID 10 DIGIT PHONE NUMBER");
            	continue;
        	}
    	}
    	System.out.println("ENTER PARENT NUMBER");
    	System.out.println("PLEASE ENTER 10 DIGIT PHONE NUMBER");
    	while(true) {
    		user_student.parentNo = input.nextLine();
        	inputStatus = Authorization.checkPhoneNumber(user_student.parentNo);
        	if(inputStatus) {
        		break;
        	}
        	else {
        		System.out.println("PLEASE ENTER VALID 10 DIGIT PHONE NUMBER");
            	continue;
        	}
    	}
    	System.out.println("ENTER STUDENT ADDRESS");
    	user_student.address = input.nextLine();
    	user_student.studentEmailId= accountGenerator.gmailGenerator(user_student.name, "student");
    	user_student.password = accountGenerator.passwordGenerator(user_student.name);
    	System.out.println("ENTER MENTOR Id");
    	user_student.staffId = input.nextInt();
    	studentId = internalProcess.addUser(null, user_student);
    	if(studentId == 0)
    		System.out.println("INSERTED FAILED");
        else {
    		mentorName = internalProcess.getMentorName(user_student.staffId);
    		StudentAcademicInformation academicInfo = new StudentAcademicInformation();
    		academicInfo.studentId = studentId;
    		System.out.println("WELCOME TO ACADEMIC PAGE");
    		System.out.println("ENTER THE SEMESTER AND RELATED SUBJECTS");
    		while(true) {
    			System.out.println("ENTER THE SEMESTER");
        		academicInfo.semester = inp_str.nextInt();
        		while(true) {
        			System.out.println("ENTER SUBJECT");
            		academicInfo.subject = inp_str.nextLine();
            		System.out.println("ENTER RERPRESENTATIVE FACULTY HANDLED FOR THE SUBJECT");
            		academicInfo.facultyHandled = inp_str.nextLine();
            		internalProcess.addStudentInfo(academicInfo, null);
            		System.out.println("DO YOU WANT TO ADD ANOTHER SUBJECT");
            		System.out.println("PRESS 1 TO ADD ANOTHER SUBJECT");
            		System.out.println("PRESS 2 TO GO BACK");
            		adminchoice = input.nextInt();
            		if(adminchoice == 1)
            			continue;
            		else
            			break;
        		}
        		System.out.println("DO YOU WANT TO ADD ANOTHER SEMESTER");
        		System.out.println("PRESS 1 TO ADD ANOTHER SEMESTER");
        		System.out.println("PRESS 2 TO ADD FEE INFO");
        		adminchoice = input.nextInt();
        		if(adminchoice == 1)
        			continue;
        		else
        			break;
    		}
    		System.out.println("WELCOME TO FEE PAGE");
    		System.out.println("ENTER THE FEE PAID OF RELATED SEMESTER");
    		StudentFee feeInfo = new StudentFee();    		
    		feeInfo.studentId = studentId;
    		feeInfo.name = user_student.name;
    		feeInfo.AccomodationMode = user_student.AccomodationMode;
    		if(feeInfo.AccomodationMode.equalsIgnoreCase("dayscholar")) {
    			feeInfo.hostelFEE = 0.0;
    			feeInfo.messFEE = 0.0;
    		    System.out.println("ENTER TUTION FEE");
    		    feeInfo.tutionFEE = input.nextDouble();
    		}
    		else {
    			System.out.println("ENTER HOSTEL FEE");
    			feeInfo.hostelFEE = input.nextDouble();
    			System.out.println("ENTER MESS FEE");
    			feeInfo.messFEE = input.nextDouble();
    		    System.out.println("ENTER TUTION FEE");
    		    feeInfo.tutionFEE = input.nextDouble();
    		}
    		System.out.println("ENTER THE SEMESTER");
    		feeInfo.semester = input.nextInt();
    		processStatus = internalProcess.addStudentInfo(null, feeInfo);
    		if(processStatus == 0)
        		System.out.println("INSERTED FAILED");
    		else
    		System.out.println("STUDENT NAME: " + user_student.name +" INSERTED SUCCESSFULLY...MENTOR NAME: " + mentorName);
    	}
	}
	public void removeStudent() {
		while(true) {
			System.out.println("ENTER THE STUDENT ID");
			int studentId = input.nextInt();
			inputStatus = validate.isValidId(studentId, "student");
			if(inputStatus) {
				inputStatus = validate.isValidId(studentId, "studentleavestatus");
				if(inputStatus) {
					internalProcess.removeUser(studentId,"studentleavestatus");
				}
				internalProcess.removeUser(studentId,"studentfee");
				internalProcess.removeUser(studentId,"academic");
				internalProcess.removeUser(studentId, "student");
				System.out.println("STAFFID: "+ studentId + " REMOVED SUCCESSFULLY");
				return;
			}
			else {
				System.out.println("PLEASE ENTER VALID ID");
				continue;
			}
		}
	}
	public void updateStaffInfo() {
		while(true) {
			System.out.println("PRESS 1 TO CHANGE STAFF NAME");
			System.out.println("PRESS 2 TO RESET STAFF PASSWORD");
			System.out.println("PRESS 3 TO CHANGE STAFF'S PHONE NUMBER");
			System.out.println("PRESS 4 TO CHANGE STAFF ADDRESS");
			System.out.println("PRESS 5 TO EXIT");
			System.out.println("ENTER THE FIELD OF STAFF TO UPGRADE STAFF DATA");
			int adminChoice = input.nextInt();
			switch(adminChoice) {
			    //modifying staff name
			    case 1: {
			    	System.out.println("ENTER THE STAFF ID FOR UPGRADES");
					user_staff.staffId = input.nextInt();
					validate.checkUserId(user_staff,null);
					while(true) {
						System.out.println("ENTER STAFF NAME IN FORMAT --> NAME FOLLOWED BY INITIAL WHICH IS SEPERATED BY SPACE");
				    	System.out.println("EX: JEEVA S OR EX: JEEVA SK");
				    	user_staff.staffName = inp_str.nextLine();
				    	inputStatus = Authorization.checkUserName(user_staff.staffName);
				    	if(inputStatus == false) {
				    		continue;
				    	}
				    	break;
					}
					user_staff.staffEmailId = accountGenerator.gmailGenerator(user_staff.staffName, "staff");
					user_staff.password = accountGenerator.passwordGenerator(user_staff.staffName );
					processStatus = internalProcess.updateUserInfo(user_staff, null, adminChoice+1,"staff");
					if(processStatus == 0)
			    		System.out.println("UPDATE FAILED !");
					else {
			    		idStatus = validate.isValidId(user_staff.staffId, "staffleavestatus");
			    		if(idStatus) {
			    			internalProcess.updateUserInfo(user_staff, null, adminChoice+1,"staffleavestatus");
				    		System.out.println("STAFF Id : " + user_staff.staffId +" NAME CHANGED SUCCESSFULLY");
				    		continue;
			    		}
			    		System.out.println("STAFF Id : " + user_staff.staffId +" NAME CHANGED SUCCESSFULLY");
			    	}
					continue;
			    }
			    //RESET STAFF PASSWORD
			    case 2: {
			    	System.out.println("ENTER THE STAFF ID FOR UPGRADES");
					user_staff.staffId = input.nextInt();
					validate.checkUserId(user_staff,null);
					//reset password
					System.out.println("ENTER THE STAFF PASSWORD");
					user_staff.password = inp_str.nextLine();
					processStatus = internalProcess.updateUserInfo(user_staff, null, adminChoice+1,"staff");
					if(processStatus == 0)
			    		System.out.println("UPDATE FAILED !");
			    	else
				    		System.out.println("STAFF Id : " + user_staff.staffId +" PASSWORD CHANGED SUCCESSFULLY");
					continue;
			    }
			    //CHANGE STAFF PHONE NUMBER
			    case 3: {
			    	System.out.println("ENTER THE STAFF ID FOR UPGRADES");
					user_staff.staffId = input.nextInt();
					validate.checkUserId(user_staff,null);
					//RESET STAFF MOBILE NUMBER
					System.out.println("ENTER THE STAFF MOBILE NUMBER");
					while(true) {
						user_staff.phoneNo= inp_str.nextLine();
						inputStatus = Authorization.checkPhoneNumber(user_staff.phoneNo);
						if(inputStatus) {
							processStatus = internalProcess.updateUserInfo(user_staff, null, adminChoice+1,"staff");
						}
						else {
							System.out.println("ENTER THE VALID MOBILE NUMBER");
							continue;
						}
						break;
					}
					if(processStatus == 0)
			    		System.out.println("UPDATE FAILED !");
			    	else
			    		System.out.println("STAFF Id : " + user_staff.staffId +"STAFF MOBILE NUMBER CHANGED SUCCESSFULLY");
					continue;
			    }
			    //CHANGE STAFF ADDRESS
			    case 4: {
			    	System.out.println("ENTER THE STAFF ID FOR UPGRADES");
					user_staff.staffId = input.nextInt();
					validate.checkUserId(user_staff,null);
					//MODIFY STAFF ADDRESS
					System.out.println("ENTER THE STAFF ADDRESS");
					user_staff.address= inp_str.nextLine();
					processStatus = internalProcess.updateUserInfo(user_staff, null, adminChoice+2,null);
					if(processStatus == 0)
			    		System.out.println("UPDATE FAILED !");
			    	else
			    		System.out.println("STAFF Id : " + user_staff.staffId +"STAFF ADDRESS CHANGED SUCCESSFULLY");
					continue;
			  }
			  case 5:
				  break;
			  default: {
				 System.out.println("PLEASE ENTER VALID CHOICE");
				 continue;
			  }
			}
			break;
		}
	}
	//To view user info
	public void viewStaffInfo() {
		while(true) {
			System.out.println("PRESS 1 TO VIEW ENTIRE STAFF DATA");
			System.out.println("PRESS 2 TO VIEW PARTICULAR STAFF DATA");
			System.out.println("PRESS 3 TO EXIT");
			adminchoice = input.nextInt();
			switch(adminchoice) {
			    case 1: {
			    	staffList = (ArrayList)internalProcess.viewEntireUserInfo(0, "staff", 0);
					for(int iterator = 0;iterator<staffList.size();iterator++) {
						user_staff = staffList.get(iterator);
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
					break;
			    }
			    case 2:
			    {
			    	while(true) {
						System.out.println("ENTER STAFF ID");
						int staffId = input.nextInt();
						idStatus = validate.isValidId(staffId, "staff");
						if(idStatus) {
							user_staff = (Staff)internalProcess.viewParticularUserInfo("staff", staffId);
							System.out.println();
							System.out.println("STAFF ID       : "+ user_staff.staffId); 
							System.out.println("STAFF NAME     : " + user_staff.staffName);
						    System.out.println("EMAIL ID       : " + user_staff.staffEmailId);
						    System.out.println("SUBJECT HANDLED: " + user_staff.subject_handled); 
						    System.out.println("DEGREE         : "+ user_staff.degree); 
						    System.out.println("PHONE NO       : "+ user_staff.phoneNo); 
						    System.out.println("ADDRESS        : "+ user_staff.address); 
						    System.out.println();
							break;
						}
						else {
							System.out.println("ENTER  VALID STAFF ID");
							continue;
						}
					}
			    }
			    case 3:
			    {
			    	break;
			    }
			    default: {
			    	System.out.println("PLEASE ENTER VALID CHOICE");
				    continue;
			    }
			}
			break;
		}
	}
	//method to approve/decline leave applied by staff
	public void LeaveDecision() {
		staffLeaveList = (ArrayList)internalProcess.viewUserLeaveInfo("staffleavestatus",0, 2);
	    if(staffLeaveList.size() == 0) {
			System.out.println("NO ONE APPLIED LEAVE/OD");
			return;
		}
		for(int iterator = 0;iterator<staffLeaveList.size();iterator++) {
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
		while(true) {
			System.out.println("PRESS 1 TO APPROVE/DECLINE LEAVE/OD FOR STAFF");
			System.out.println("PRESS 2 TO GO BACK");
			adminchoice = input.nextInt();
			switch(adminchoice) {
			    case 1: {
			    	System.out.println("ENTER THE STAFF ID");
					int staffId = input.nextInt();
					System.out.println("ENTER STAFF LEAVE ID");
					int staffLeaveId = input.nextInt();
					idStatus = validate.isValidLeaveId(staffId,staffLeaveId, "staffleavestatus");
					if(idStatus) {
						System.out.println("PRESS 1 TO APPROVE LEAVE/OD");
						System.out.println("PRESS 2 TO DECLINE LEAVE/OD");
						LeaveStatus = input.nextInt();
						if(LeaveStatus == 1) {
							internalProcess.LeaveApproval(staffId,"staffleavestatus","APPROVED",staffLeaveId);
							System.out.println("LEAVE/OD APPROVED FOR STAFF ID: "+staffId);
						}
						else {
							internalProcess.LeaveApproval(staffId,"staffleavestatus","DECLINED",staffLeaveId);
							System.out.println("LEAVE/OD DECLINED FOR STAFF ID: "+staffId);
						}
						break;
					}
					else {
						System.out.println("PLEASE ENTER VALID STAFF ID/STAFF LEAVE ID");
						continue;
					}
			    }
			    case 2:
			    {
			    	break;
			    }
			    default:
			    {
			    	System.out.println("PLEASE ENTER VALID CHOICE");
			    }
		    }
			break;
		}
	}
	//modifying student info
	public void updateStudentInfo() {
		while(true) {
			System.out.println("PRESS 1 TO CHANGE STUDENT'S MENTOR");
			System.out.println("PRESS 2 TO CHANGE STUDENT NAME");
			System.out.println("PRESS 3 TO RESET STUDENT PASSWORD");
			System.out.println("PRESS 4 TO CHANGE STUDENT'S PHONE NUMBER");
			System.out.println("PRESS 5 TO CHANGE STUDENT'S PARENT PHONE NUMBER");
			System.out.println("PRESS 6 TO CHANGE STUDENT ADDRESS");
			System.out.println("PRESS 7 TO CHANGE STUDENT ACCOMODATION MODE");
			System.out.println("PRESS 8 TO EXIT");
			System.out.println("ENTER THE FIELD OF STUDENT TO UPGRADE STUDENT DATA");
			int adminChoice = input.nextInt();
			switch(adminChoice) {
			    //CHANGING MENTOR FOR STUDENT
			    case 1: {
			    	System.out.println("ENTER THE STUDENT ID FOR UPGRADES");
					user_student.studentId = input.nextInt();
					validate.checkUserId(null,user_student);
					System.out.println("ENTER THE MENTOR ID");
					user_student.staffId = input.nextInt();
					processStatus = internalProcess.updateUserInfo(null, user_student, adminChoice,"student");
					if(processStatus == 0)
			    		System.out.println("UPDATE FAILED !");
			    	else {
			    		idStatus = validate.isValidId(user_student.studentId, "studentleavestatus");
			    		if(idStatus) {
			    			internalProcess.updateUserInfo(null, user_student, adminChoice,"studentleavestatus");
			    			System.out.println("STUDENT Id : " + user_student.studentId +"  MENTOR ASSIGNED SUCCESSFULLY");
				    		continue;
			    		}
			    		System.out.println("STUDENT Id : " + user_student.studentId +"  MENTOR ASSIGNED SUCCESSFULLY");
			    	}
					continue;
			    }
			    //MODIFYING STUDENT NAME
			    case 2: {
			    	System.out.println("ENTER THE STUDENT ID FOR UPGRADES");
					user_student.studentId = input.nextInt();
					validate.checkUserId(null,user_student);
					while(true) {
						System.out.println("ENTER STUDENT NAME IN FORMAT --> NAME FOLLOWED BY INITIAL WHICH IS SEPERATED BY SPACE");
				    	System.out.println("EX: JEEVA S OR EX: JEEVA SK");
				    	user_student.name = inp_str.nextLine();
				    	inputStatus = Authorization.checkUserName(user_student.name);
				    	if(inputStatus == false) {
				    		continue;
				    	}
				    	break;
					}
					user_student.studentEmailId= accountGenerator.gmailGenerator(user_student.name, "student");
			        user_student.password = accountGenerator.passwordGenerator(user_student.name);
					processStatus = internalProcess.updateUserInfo(null, user_student, adminChoice,"student");
					if(processStatus == 0)
			    		System.out.println("UPDATE FAILED !");
			    	else {
			    		idStatus = validate.isValidId(user_student.studentId, "studentleavestatus");
			    		if(idStatus) {
			    			internalProcess.updateUserInfo(null, user_student, adminChoice,"studentleavestatus");
			    			System.out.println("STUDENT Id : " + user_student.studentId +"NAME CHANGED SUCCESSFULLY");
			    			continue;
			    		}
			    		System.out.println("STUDENT Id : " + user_student.studentId +"NAME CHANGED SUCCESSFULLY");
			    	}
					continue;
			    }
			   //RESET PASSWORD
			    case 3: {
			    	System.out.println("ENTER THE STUDENT ID FOR UPGRADES");
					user_student.studentId = input.nextInt();
					validate.checkUserId(null,user_student);
					System.out.println("ENTER THE STUDENT PASSWORD");
					user_student.password = inp_str.nextLine();
					processStatus = internalProcess.updateUserInfo(null, user_student, adminChoice,"student");
					if(processStatus == 0)
			    		System.out.println("UPDATE FAILED !");
			    	else
			    		System.out.println("STUDENT Id : " + user_student.studentId +"PASSWORD CHANGED SUCCESSFULLY");
					continue;
			    }
			    //RESET STUDENT MOBILE NUMBER
			    case 4: {
			    	System.out.println("ENTER THE STUDENT ID FOR UPGRADES");
					user_student.studentId = input.nextInt();
					validate.checkUserId(null,user_student);
					System.out.println("ENTER THE STUDENT MOBILE NUMBER");
					while(true) {
						user_student.studentNo = inp_str.nextLine();
						inputStatus = Authorization.checkPhoneNumber(user_student.studentNo);
						if(inputStatus) {
							processStatus = internalProcess.updateUserInfo(null, user_student, adminChoice,"student");
						}
						else {
							System.out.println("ENTER THE VALID MOBILE NUMBER");
							continue;
						}
						break;
					}
					if(processStatus == 0)
			    		System.out.println("UPDATE FAILED !");
			    	else
			    		System.out.println("STUDENT Id : " + user_student.studentId +"STUDENT MOBILE NUMBER CHANGED SUCCESSFULLY");
					continue;
			    }
			  //RESET PARENT MOBILE NUMBER
			    case 5: {
			    	System.out.println("ENTER THE STUDENT ID FOR UPGRADES");
					user_student.studentId = input.nextInt();
					validate.checkUserId(null,user_student);
					System.out.println("ENTER THE PARENT MOBILE NUMBER");
					while(true) {
						user_student.parentNo = inp_str.nextLine();
						inputStatus = Authorization.checkPhoneNumber(user_student.parentNo);
						if(inputStatus) {
							processStatus = internalProcess.updateUserInfo(null, user_student, adminChoice,"student");
						}
						else {
							System.out.println("ENTER THE VALID MOBILE NUMBER");
							continue;
						}
						break;
					}
					if(processStatus == 0)
			    		System.out.println("UPDATE FAILED !");
			    	else
			    		System.out.println("STUDENT Id : " + user_student.studentId +"STUDENT'S PARENT MOBILE NUMBER CHANGED SUCCESSFULLY");
					continue;
			    }
			    //MODIFY STUDENT ADDRESS
			    case 6: {
			    	System.out.println("ENTER THE STUDENT ID FOR UPGRADES");
					user_student.studentId = input.nextInt();
					validate.checkUserId(null,user_student);	
					System.out.println("ENTER THE STUDENT ADDRESS");
					user_student.address = inp_str.nextLine();
					processStatus = internalProcess.updateUserInfo(null, user_student, adminChoice,"student");
					if(processStatus == 0)
			    		System.out.println("UPDATE FAILED !");
			    	else
			    		System.out.println("STUDENT Id : " + user_student.studentId +"STUDENT'S ADDRESS CHANGED SUCCESSFULLY");
					continue;
			    }
			   //MODIFY STUDENT ACCOMODATION MODE 
			    case 7: {
			    	System.out.println("ENTER THE STUDENT ID FOR UPGRADES");
					user_student.studentId = input.nextInt();
					validate.checkUserId(null,user_student);
					System.out.println("ENTER THE STUDENT ACCOMODATION MODE");
					user_student.AccomodationMode = inp_str.nextLine();
					StudentFee feeInfo = new StudentFee(); 
					feeInfo.studentId = user_student.studentId;
					feeInfo.AccomodationMode = user_student.AccomodationMode;
					if(user_student.AccomodationMode.equalsIgnoreCase("dayscholar"))
		    		{
		    			feeInfo.hostelFEE = 0.0;
		    			feeInfo.messFEE = 0.0;
		    		    System.out.println("ENTER TUTION FEE");
		    		    feeInfo.tutionFEE = input.nextDouble();
		    		}
		    		else
		    		{
		    			System.out.println("ENTER HOSTEL FEE");
		    			feeInfo.hostelFEE = input.nextDouble();
		    			System.out.println("ENTER MESS FEE");
		    			feeInfo.messFEE = input.nextDouble();
		    		    System.out.println("ENTER TUTION FEE");
		    		    feeInfo.tutionFEE = input.nextDouble();
		    		}
				    processStatus = internalProcess.modifyFeeInfo(feeInfo);
					if(processStatus == 0)
			    		System.out.println("UPDATE FAILED !");
			    	else
			    		System.out.println("STUDENT Id : " + user_student.studentId +"STUDENT'S ACCOMODATION MODE CHANGED SUCCESSFULLY");
					continue;
			    }
			    case 8:
			    	break;
			}
			break;
		}
	}
	//To view student info
    public void viewStudentInfo() {
		System.out.println("PRESS 1 TO VIEW ENTIRE STUDENT DATA");
		System.out.println("PRESS 2 TO VIEW PARTICULAR STUDENT DATA");
		System.out.println("PRESS 3 TO EXIT");
		int userChoice = input.nextInt();
		switch(userChoice) {
		    case 1: {
		    	studentList = (ArrayList)internalProcess.viewEntireUserInfo(0, "student", 0);
				for(int iterator = 0;iterator<studentList.size();iterator++) {
					user_student = studentList.get(iterator);
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
				break;
		    }
		    case 2: {
		    	while(true) {
					System.out.println("ENTER STUDENT ID");
					int studentId = input.nextInt();
					idStatus = validate.isValidId(studentId, "student");
					if(idStatus) {
						user_student = (Student)internalProcess.viewParticularUserInfo("student", studentId);
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
						break;
					}
					else {
						System.out.println("ENTER VALID STUDENT ID");
						continue;
					}
				}
		    }
		    case 3:
		    {
		    	break;
		    }
		    default:
		    {
		    	System.out.println("PLEASE ENTER VALID CHOICE");
		    }
		break;
		}
	}
}
