/*
 * STUDENT DATABASE APPLICATION:
 * This application splitted into Four packages as model ,view, control and app_data
 * Model package classes which act as a template for database tables.
 * view package classes will communicate with the user(Staff/Student) or admin.
 * control package classes will act as the 2nd layer for this application and it communicate with the view class and based on the requirements of view class control package classes will give data from database(updating or inserting or retrieval etc.,)
 *  app_data package has class which establish or close the database connection and executes the queries.
 */
//MAIN CLASS : This class provides an GUI like screen for the user or Admin to login or to goback.
package view;
import app_data.DataBaseRepository;
import controller.Authorization;
public class Main {
	public static void main(String[] args) {
		System.out.println();
		System.out.println(" *** Welcome to BIT CAMPS *** ");
		do {
			System.out.println();
			System.out.println("+--------------------+");
		    System.out.println("| PRESS 1 TO LOGIN   |");
		    System.out.println("+--------------------+");
		    System.out.println("| PRESS 2 TO EXIT    |");
		    System.out.println("+--------------------+");
		    int userChoice = Authorization.checkInput();
			if(userChoice == 1) {
				new Entry().signIn();
			}
			else if(userChoice == 2) {
				new DataBaseRepository().closeConnection();
				break;
			}
			else {
				System.out.println("PLEASE ENTER VALID INPUT");
				continue;
			}
		}while(true);
		System.out.println("*** THANK YOU ***");
	}
}

