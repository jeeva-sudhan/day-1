/* DateVerifier : The class to give constraints about date format 
 * And To check the date provided by admin/user is valid or not.
 */
package controller;
import java.sql.ResultSet;
import model.StaffLeaveInformation;
import model.StudentLeaveInformation;
import app_data.DataBaseRepository;
public class DateVerifier {
	private boolean inputStatus;
	private String query;
	private ResultSet resultSet;
	Validate verify = new Validate();
	DataBaseRepository databaseRepository = new DataBaseRepository();
	//method to avoid leave/OD applying by user on same date which already the applied LEAVE/OD.
	public boolean checkDate(StaffLeaveInformation staffLeaveInfo,StudentLeaveInformation studentLeaveInfo) {
		if(studentLeaveInfo == null) {
			inputStatus = verify.isValidId(staffLeaveInfo.staffId,"staffleavestatus");
			if(inputStatus) {
				query = "SELECT * FROM staffleavestatus WHERE staffId = "+staffLeaveInfo.staffId+"";
				resultSet = databaseRepository.select(query);
				try {
					while(resultSet.next()) {
						if(staffLeaveInfo.fromDate.equalsIgnoreCase(resultSet.getString(4))) {
							return false;
						}
					}
					return true;
				}
				catch(Exception exception) {
					exception.printStackTrace();
				}
			}
			else {
				return true;
			}
		}
		else {
			inputStatus = verify.isValidId(studentLeaveInfo.studentId,"studentleavestatus");
			if(inputStatus) {
				query = "SELECT * FROM studentleavestatus WHERE studentId = "+studentLeaveInfo.studentId+"";
				resultSet = databaseRepository.select(query);
				try {
					while(resultSet.next()) {
						if(studentLeaveInfo.fromDate.equalsIgnoreCase(resultSet.getString(5))) {
							return false;
						}
					}
					return true;
				}
				catch(Exception exception) {
					exception.printStackTrace();
				}
			}
			else {
				return true;
			}
		}
		return false;
	}
	//method to check that constraints of date provided by service provider and user is valid or not
	public static boolean isValidDate(String Date) {
		int count = 0;
		if(Date.contains("/")) {
			for(int iterator = 0;iterator<Date.length();iterator++) {
				int check = Character.compare(Date.charAt(iterator), '/');
			    if(check == 0) {
					++count;
				}
			}
			if(count == 2) {
				String date[] = Date.split("/");
				if(date[0].length() == 4 && (date[1].length() == 2 || date[1].length() == 1) && (date[2].length() == 2 || date[2].length() == 1)) {
					return true;
				}
				return false;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	//method to get max_days based on month and year
	private static int getDate(int month,int year) {
		int max_days = 0;
		if(month <= 7) {
			if(month == 2) {
				if((year%4 == 0 && year%100 != 0) || year%400 == 0) {
					max_days = 29;
					return max_days;
				}
				else {
					max_days = 28;
					return max_days;
				}
			}
		    else if(month%2==0) {
		    	max_days = 30;
				return max_days;
			}
			else {
				max_days = 31;
				return max_days;
			}
		}
		else {
			if(month % 2 == 0) {
				max_days = 31;
				return max_days;
			}
			else {
				max_days = 30;
				return max_days;
			}
		}
	}
	//method to check the leave date entered by user is valid or not
	public static boolean isValidLeaveDate(String fromDate,String toDate) {
		int startMonth_max_date = 0;
		int endMonth_max_date = 0;
		int startingYear = 0;
		int endYear = 0;
		int startDate = 0;
		int endDate = 0;
		int startMonth = 0;
		int endMonth = 0;
		String startingDate[] = fromDate.split("/");
		String endingDate[] = toDate.split("/");
		startingYear = Integer.parseInt(startingDate[0]);
	    endYear = Integer.parseInt(endingDate[0]);
		if(startingYear == endYear){
		    startDate = Integer.parseInt(startingDate[2]);
		    endDate = Integer.parseInt(endingDate[2]);
			startMonth = Integer.parseInt(startingDate[1]);
			endMonth = Integer.parseInt(endingDate[1]);
			if(endDate < startDate || endMonth < startMonth)
				return false;
			else {
				startMonth_max_date = getDate(startMonth,startingYear);
				endMonth_max_date = getDate(endMonth,endYear);
				if((endMonth >= 1 && endMonth <=12) && (startMonth >= 1 && startMonth <= 12)) {
					if(startDate <= startMonth_max_date && endDate <= endMonth_max_date) {
						return true;
					}
					else
						return false;
				}
				else
					return false;
			}
		}
		return false;
	}
	//To check date of birth is valid or not based on contraints
	public static boolean isValidDateOfBirth(String BirthDate)
	{
		int count = 0;
		if(BirthDate.contains("/")) {
			for(int iterator = 0;iterator<BirthDate.length();iterator++) {
				int check = Character.compare(BirthDate.charAt(iterator), '/');
			    if(check == 0) {
					++count;
				}
			}
			if(count == 2) {
				String splittedBirthDate[] = BirthDate.split("/");
				if((splittedBirthDate[0].length() == 2 || splittedBirthDate[0].length() == 1) && (splittedBirthDate[1].length() == 2 || splittedBirthDate[1].length() == 1) && (splittedBirthDate[2].length() == 4)) {
					return true;
				}
				return false;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
}
