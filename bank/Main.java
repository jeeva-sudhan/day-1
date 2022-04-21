//BANK APPLICATION:
/*
 * In this Bank Application I have created an abstract class AtmGui which gives the required data and hides the internal implementation.
 * In this application I have used 7 classes:
 
         * AtmGuiScreen - Execution starts from this class and which will communicate with the customer by providing required functions 
                          which provided by service providers(Bank People).
         * CustomerAccount - This class gives the structure that how customer account(Object) and account type look like.
         * CustomerDetails - This class list out the customer details that are stored in bank.
         * BankProcess - This class consists of bank internal functions working process.
         * Otp - This class will generate Otp to customer to give information about the account status.
         * PasswordChecker - PasswordChecker class will give the information about that how the customer account password should be.
         * AccountSorting - This class will sort the account type of customer.
 */

package bank;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		//bank object created to call internal banking methods 
		BankProcess internalProcess = new BankProcess(); 
		while(true) {
			System.out.println();
			System.out.println("***SERVICE PROVIDED BY OUR BANK***");
			System.out.println("PRESS 1 TO CREATE ACCOUNT");
			System.out.println("PRESS 2 TO DEPOSIT");
			System.out.println("PRESS 3 TO WITHDRAW");
			System.out.println("PRESS 4 TO CHECK BALANCE");
			System.out.println("PRESS 5 TO TRANSFER MONEY");
			System.out.println("PRESS 6 TO REMOVE ACCOUNT");
			System.out.println("PRESS 7 TO VIEW CUSTOMER DETAILS");
			System.out.println("PRESS 8 TO EXIT");
			System.out.println("ENTER YOUR CHOICE");
			int customerChoice = inputScanner.nextInt();
			switch(customerChoice) {
			    case 1: 
			    {
			    	CustomerAccount cust_acc = new CustomerAccount();			
				    cust_acc.createAccount();
				    continue;
			    }
			    case 2: {		
			    	internalProcess.deposit();
				    continue; 
				}
			    case 3: {	 
			    	internalProcess.withDraw();
				     continue;
			    }
			    case 4: {	
			    	internalProcess.checkBalance();
				    continue;
			    }
			    case 5: {		
			    	internalProcess.transferMoney();
				    continue;
			    }
			    case 6: {
			    	System.out.println("ARE YOU SURE WANT TO REMOVE YOUR ACCOUNT FROM OUR BANK");
		    		System.out.println("PRESS 1--> YES  2 --> NO");
		    		int wish = inputScanner.nextInt();
		    		if(wish == 1) {
		    			internalProcess.removeAccount();
		    		}
		    		continue;
			    }
			    case 7: {	
			    	internalProcess.printCustDetails();	
		    		continue;
				}
			    case 8: {
			    	break;
			    }
			}
			System.out.println("*** THANK YOU ***");
			break;
		}
	}
}
