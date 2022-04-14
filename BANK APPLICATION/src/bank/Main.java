//BANK APPLICATION:
/*
 * In this Bank Application I have created an interface Atmgui which act as a contract between customer and service providers.
 * In this application I have used 7 classes:
 
         * MAIN - Execution starts from this class and which will communicate with the customer by providing required functions 
                  which provided by service providers(Bank People).
                  
         * CustomerAccount - This class gives the structure that how customer account(Object) and account type look like.
        
         * CustomerDetails - This class list out the customer details that are stored in bank.
         
         * BankProcess - This class consists of bank internal functions working process.
         
         *Otp - This class will generate Otp to customer to give information about the account status.
         
         *PasswordChecker - PasswordChecker class will give the information about that how the customer account password should be.
         
         *AccountSorting - This class will sort the account type of customer.
         
 * 
 */



package bank;
import java.util.Scanner;

public class Main implements AtmGui
{
	public void displayScreen() 
	{
		Scanner scanner = new Scanner(System.in);
		BankProcess b = new BankProcess(); //bank object created to call internal banking methods 
		
		while(true)
		{
			System.out.println();
			System.out.println("***SERVICE PROVIDED BY OUR BANK***");
			System.out.println("PRESS 1 TO CREATE ACCOUNT");//CREATE ACCOUNT BUTTON
			System.out.println("PRESS 2 TO DEPOSIT");//DEPOSIT BUTTON
			System.out.println("PRESS 3 TO WITHDRAW");//WITHDRAW BUTTON
			System.out.println("PRESS 4 TO CHECK BALANCE");//CHECK BALANCE BUTTON
			System.out.println("PRESS 5 TO TRANSFER MONEY");//RANSFER MONEY BUTTON
			System.out.println("PRESS 6 TO REMOVE ACCOUNT");//FOR REMOVING ACCOUNT
			System.out.println("PRESS 7 TO VIEW CUSTOMER DETAILS");//FOR VIEWING CUSTOMER DETAILS 
			System.out.println("PRESS 8 TO EXIT");//EXIT BUTTON
			System.out.println("ENTER YOUR CHOICE");
			
			int choice = scanner.nextInt();
			
			switch(choice)
			{
			    case 1:
			    {
			    	CustomerAccount cust_acc = new CustomerAccount();			
				    cust_acc.createAccount();
				    continue;
			    }
			
			    case 2:
			    {		
			    	b.deposit();
				    continue;
			    }
			    
			    case 3:
			    {	 
			    	 b.withDraw();
				     continue;
			    }
			    
			    case 4:
			    {	
			    	b.checkBalance();
				    continue;
			    }
			    
			    case 5:
			    {		
				    b.transferMoney();
				    continue;
			    }
			    
			    case 6:
			    {
			    	System.out.println("ARE YOU SURE WANT TO REMOVE YOUR ACCOUNT FROM OUR BANK");
		    		System.out.println("PRESS 1--> YES  2 --> NO");
		    		int wish = scanner.nextInt();
		    		if(wish == 1)
		    		{
		    			new CustomerAccount().removeAccount();
		    		}

				    continue;
			    }
			    
			    case 7:
			    {	
			    	new CustomerDetails().printCustDetails();	
		    		continue;
				}
			    
			    case 8:
			    {
			    	break;
			    }
			}
			System.out.println("*** THANK YOU ***");
			break;
		}
	}
	
	public static void main(String[] args) //Execution starts
	{
			Main main = new Main();
			main.displayScreen();// method invocation to get interaction with customer 
	}

}
