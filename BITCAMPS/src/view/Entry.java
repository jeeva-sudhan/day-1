/*
 * Entry class : This class act as a view class and it will communicate with admin or  user 
 * Based on admin or user it will show functionality and admin or user can use the functinality.
 */
package view;
import java.util.Scanner;
import controller.Authorization;
import model.Staff;
import model.Student;
public class Entry implements Login {
	Scanner input = new Scanner(System.in);
	public void signIn() {
		while(true) {
			System.out.println();
			System.out.println("PRESS 1 FOR ADMIN LOGIN");
			System.out.println("PRESS 2 FOR USER LOGIN --> (Staff/Student)");
			System.out.println("PRESS 3 GO BACK");
			User user = new User();
			int choice = Authorization.checkInput();
			if(choice == 1) {
				AdminFunctionality admin = (Admin)user.getUser(1);
				if(admin != null) {
					System.out.println("SIGNED IN AS ADMIN");
					while(true) {
						System.out.println();
						System.out.println("PRESS 1  --> ADD STAFF");
						System.out.println("PRESS 2  --> REMOVE STAFF");
						System.out.println("PRESS 3  --> ADD STUDENT");
						System.out.println("PRESS 4  --> REMOVE STUDENT");
						System.out.println("PRESS 5  --> UPDATE STAFF INFO");
						System.out.println("PRESS 6  --> UPDATE STUDENT INFO");
						System.out.println("PRESS 7  --> APPROVE STAFF LEAVE");
						System.out.println("PRESS 8  --> VIEW STAFF INFO");
						System.out.println("PRESS 9  --> VIEW STUDENT INFO");
						System.out.println("PRESS 10 --> LOGOUT");
						int adminChoice = Authorization.checkInput();
						switch(adminChoice) {
						    case 1:
						    	admin.addStaff();
							    continue;
						    case 2:
						    	admin.removeStaff();
							    continue;
							case 3:
							    admin.addStudent();
							    continue;
						    case 4:
							    admin.removeStudent();
							    continue;
						    case 5:
							    admin.updateStaffInfo();
							    continue;
						    case 6:
							    admin.updateStudentInfo();
							    continue;
						    case 7:
							    admin.LeaveDecision();
							    continue;
						    case 8:
							    admin.viewStaffInfo();
							    continue;
						    case 9:
							    admin.viewStudentInfo();
							    continue;
						    case 10:
							    break;
						    default:
							    System.out.println("PLEASE ENTER VALID CHOICE");
							    continue;
						}
						break;
					}
				}
			}
			else if(choice == 2) {
				while(true) {
					System.out.println("SIGN IN AS STUDENT OR STAFF");
					System.out.println("PRESS 1 FOR STAFF");
					System.out.println("PRESS 2 FOR STUDENT");
					System.out.println("PRESS 3 GO BACK");
					int userPreference = input.nextInt();
					if(userPreference == 1) {
						Staff staff = (Staff)user.getUser(2);
						if(staff != null) {
							 UserFunctionality user_staff = new StaffDataUI();
							 StaffFunctionality staffControl = new StaffDataUI();
							System.out.println();
							System.out.println("SIGNED IN AS STAFF - USER");
							while(true) {
								System.out.println();
								System.out.println("PRESS 1 TO VIEW YOUR DETAILS");
								System.out.println("PRESS 2 TO APPLY LEAVE");
								System.out.println("PRESS 3 TO VIEW STUDENT DETAILS");
								System.out.println("PRESS 4 TO VIEW STUDENT	LEAVE TABLE");
								System.out.println("PRESS 5 TO VIEW LEAVE STATUS");
								System.out.println("PRESS 6 TO VIEW UPDATE STUDENT ACADEMIC INFO");
								System.out.println("PRESS 7 TO LOGOUT");
								int staffChoice = Authorization.checkInput();
								switch(staffChoice) {
								    case 1:
								    	user_staff.viewUserInfo(staff);
									    continue;
								    case 2:
								    	user_staff.LeaveApply(staff);
									    continue;
								    case 3:
								    	staffControl.viewStudentInfo(staff);
									    continue;
								    case 4:
								    	staffControl.LeaveDecision(staff);
									    continue;
								    case 5:
								    	user_staff.viewLeaveStatus(staff);
									    continue;
								    case 6:
								    	staffControl.updateStudentAcademicInfo(staff);
								    	continue;
								    case 7: 
								    	break;
								    default:
								    	System.out.println("PLEASE ENTER VALID CHOICE");
									    continue;
								}
								break;
							}
						}
						else {
							System.out.println("No User Found");
							System.out.println("PLEASE ENTER VALID EMAILID/PASSWORD");
							continue;
						}	
					}
					else if(userPreference == 2) {
						Student student = (Student)user.getUser(3);
						if(student != null) {
							UserFunctionality user_student = new StaffDataUI();
							StudentFunctionality studentControl = new StudentDataUI();
							System.out.println();
							System.out.println("SIGNED IN AS STUDENT - USER");
							while(true) {
								System.out.println();
								System.out.println("PRESS 1 TO VIEW YOUR DETAILS");
								System.out.println("PRESS 2 TO APPLY LEAVE");
								System.out.println("PRESS 3 TO VIEW LEAVE STATUS");
								System.out.println("PRESS 4 TO VIEW FEE INFO");
								System.out.println("PRESS 5 TO DOWNLOAD FEE RECEIPT");
								System.out.println("PRESS 6 TO VIEW ACADEMIC INFO");
								System.out.println("PRESS 7 TO LOGOUT");
								int studentChoice = Authorization.checkInput();
								switch(studentChoice) {
								    case 1:
								    	user_student.viewUserInfo(student);
									    continue;
								    case 2:
								    	user_student.LeaveApply(student);
									    continue;
								    case 3: 
								    	user_student.viewLeaveStatus(student);
									    continue;
								    case 4:
								       studentControl.studentFeeInfo(student);
								       continue;
								    case 5:
								    	studentControl.studentFeeReceiptDownload(student);
									    continue;
									case 6:
										studentControl.studentAcademicInfo(student);
										continue;
									case 7:
										break;
									default:
										System.out.println("PLEASE ENTER VALID CHOICE");
										continue;
									}
									break;
								}
							}
							else {
								System.out.println("No User Found");
								System.out.println("PLEASE ENTER VALID EMAILID/PASSWORD");
								continue;
							}
						}
						else if(userPreference == 3)
							break;
						else {
							System.out.println("PLEASE ENTER VALID CHOICE");
							continue;
						}
					}
			    }
				else if(choice == 3)
					break;
				else {
					System.out.println("PLEASE ENTER VALID INPUT");
					continue;
				}
			}
		}
}
