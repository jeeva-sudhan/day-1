package bank;
import java.util.ArrayList;
import java.util.Scanner;

/*CustomerAccount - This class gives the structure that how customer account(Object) and account type look like.
 * By using this class states and properties we can create account , remove account.
 * CustomerAccount - based on Customer Account.
 */

public class CustomerAccount //to describe that how account details and account type of customer.
{
    static long acc_number = 383421241L;
    
	protected long accountNumber =0;
	protected double balance = 0;
	protected int age = 0;
	protected String name = "";
	protected String gender = "";
	protected String password = "";
	protected long phoneNumber = 0;
	protected int acc_type = 0;//to describe customer account type(CurrentAccount or SavingsAccount)
	
	static protected ArrayList<CustomerAccount> customerDetailsList = new ArrayList<CustomerAccount>();
	//because all customers details are going to store in this ArrayList so i declared it as static(common for all objects)
	
	Scanner scanner = new Scanner(System.in);
	
	public final void getUserInformation()//to get customer details for creating account
	{
		System.out.println("Enter Your Good Name");
	    this.name = scanner.nextLine();
	    System.out.println("Enter Your Age");
	    this.age = scanner.nextInt();
	    if(age < 18)
	    {
	    	System.out.println("MEMBERS WHO HAVING AGE EQUAL (OR) ABOVE 18 ONLY ELIGIBLE FOR OPENING BANK ACCOUNT");
	    	return;
	    }
	    System.out.println("Enter Your gender");
	    this.gender = scanner.next();
	    System.out.println("Enter Your Phone Number");
	    this.phoneNumber = scanner.nextLong();
	    boolean check;
	    
	    while(true) //loop used to re-enter the password if the customer doesn't the meet the bank requirement password type
	    {
	    	System.out.println("Set the Password for Your Account");
	    	System.out.println("*** please enter your password that must atleast contain one Upper Case Letter,one special character('$','_') and one number ***");
	    	this.password = scanner.next();
	    	check = PasswordDescriber.password_check(password);//method invocation to check customer entered password meet the bank requirement or not
		    
	    	if(check == false)
		    	continue;
		    else 
		    	break;
	    }
	    
	    //to give confirmation to customer about account creation
	    
	    if(this.acc_type == 1)
			System.out.println("YOUR ACCOUNT TYPE: CURRENT ACCOUNT account holder's name: " + this.name);
	    if(this.acc_type == 2)
	    	System.out.println("YOUR ACCOUNT TYPE: SAVINGS ACCOUNT account holder's name: " + this.name);
	    
	    System.out.println("Your Account Number: " + this.accountNumber);
	}
	
    public final void createAccount()//creating account by choosing account type
	{
		System.out.println("CHOOSE YOUR ACCOUNT TYPE:");
		System.out.println("Enter C --> currentAccount S --> savingsAccount");
		char account_type = scanner.next().charAt(0);
		
		if(account_type == 'C' || account_type == 'c')
		{
		    CurrentAccount currentaccount = new CurrentAccount();
		    currentaccount.currentAccount();
		    
		}
		
		else if(account_type == 'S' || account_type == 's')
		{
		    SavingsAccount savingsaccount = new SavingsAccount();
		    savingsaccount.savingsAccount();
		    
		}
		
		else
			System.out.println("Please Enter Valid Account Type");
	}
    
    public final void removeAccount()//to remove customer account from list
    {
    	while(true)//loop to repeat when customer doesn't enter valid account number/password
    	{
    			System.out.println("ENTER YOUR ACCOUNT NUMBER");
    			long inp_acc_number = scanner.nextLong();
        		System.out.println("ENTER YOUR ACCOUNT PASSWORD");
        		String inp_password = scanner.next();
        		
        		for(int iterator = 0;iterator<customerDetailsList.size();iterator++)
        		{
        			CustomerAccount customer = (CustomerAccount)customerDetailsList.get(iterator);
        			if(customer.accountNumber == inp_acc_number)
        			{
        				if((customer.password).equals(inp_password))
        				{
        					//to give confirmation about the account type
        					if(customer.acc_type == 1)
        						System.out.println("YOUR ACCOUNT TYPE: CURRENT ACCOUNT account holder's name: " + customer.name);
        					
        				    if(customer.acc_type == 2)
        				    	System.out.println("YOUR ACCOUNT TYPE: SAVINGS ACCOUNT account holder's name: " + customer.name);
        				    
        				    System.out.println("YOUR ACCOUNT " + customer.accountNumber + "REMOVED SUCCESFULLY FROM BANK");
        				    customerDetailsList.remove(iterator);//removing customer account from bank
        					return;//to avoid running of infinite loop and to return when function completes
        				}
        			}
        		}
        		System.out.println("INVALID ACCOUNT NUMBER/PASSWORD TRY AGAIN!!!");
        		continue;//for repeating loop when user enters INVALID ACCOUNT NUMBER/PASSWORD
    	}
    }
	
    
}

class CurrentAccount extends CustomerAccount
{
    
    public final void currentAccount()//method for Business account(current account)
    {
    	this.accountNumber = ++acc_number;
        System.out.println("You have chosen currentAccount type - MAXIMUM BALANCE BUSINESS ACCOUNT");
        System.out.println("SO PLEASE DEPOSIT atleast Rs.60,0000 TO YOUR ACCOUNT AFTER CREATING YOUR ACCOUNT!!!");
        this.acc_type = 1;
        this.getUserInformation();
        
        if(age >= 18)
        {
        	customerDetailsList.add(this);// to add customer details to list
        }
        else
        	--acc_number;
    }
}

class SavingsAccount extends CustomerAccount
{
    
    public final void savingsAccount()//method for savings account
    {
    	this.accountNumber = ++acc_number;
        System.out.println("You have chosen savingsAccount type - MINIMAL BALANCE ACCOUNT");
        this.acc_type = 2;
        this.getUserInformation();
        
        if(age >= 18)
        {
        	customerDetailsList.add(this);// to add customer details to list
        }
        else
        	--acc_number;
    }
}



