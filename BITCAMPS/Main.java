// STUDENT DATABASE APPLICATION:
//MAIN CLASS : This is the class where application starts and based on the INPUT IT DIVIDED INTO ADMIN , STAFF AND USER.

package view;
import java.util.Scanner;
import app_data.DataBaseRepository;
import control.Authorization;

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
				new DataBaseRepository().ConnectionClose();
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

