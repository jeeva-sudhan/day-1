package bank;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*CustomerAccount - This class gives the structure that how customer account(Object) and account type look like.
 * By using this class states and properties we can create account , remove account.
 * CustomerAccount - based on Customer Account.
 */
//to describe that how account details and account type of customer.
public class CustomerAccount {
    static long acc_number = 383421241L;
	protected long accountNumber =0;
	protected double balance = 0;
	protected int age = 0;
	protected String name = "";
	protected String gender = "";
	protected String password = "";
	protected long phoneNumber = 0;
	//to describe customer account type(CurrentAccount or SavingsAccount)
	protected int acc_type = 0;
	Scanner inputScanner = new Scanner(System.in);
	BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	//to get customer details for creating account
	public final void getUserInformation() {
		try {
			System.out.println("Enter Your Good Name");
		    this.name = inputReader.readLine();
		    System.out.println("Enter Your Age");
		    this.age = inputScanner.nextInt();
		    if(age < 18) {
		    	System.out.println("MEMBERS WHO HAVING AGE EQUAL (OR) ABOVE 18 ONLY ELIGIBLE FOR OPENING BANK ACCOUNT");
		    	return;
		    }
		    System.out.println("Enter Your gender");
		    this.gender = inputScanner.next();
		}
		catch(Exception exception) {
			--acc_number;
	    	System.out.println("*** Please Enter the Valid Data ***");
	    	return;
	    }
		System.out.println("Enter Your Phone Number");
	    this.phoneNumber = inputScanner.nextLong();
	    boolean check;
	    //loop used to re-enter the password if the customer doesn't the meet the bank requirement password type
	    while(true) {
	    	System.out.println("Set the Password for Your Account");
	    	System.out.println("*** please enter your password that must atleast contain one Upper Case Letter,one special character('$','_') and one number ***");
	    	this.password = inputScanner.next();
	    	//method invocation to check customer entered password meet the bank requirement or not
	    	check = PasswordDescriber.password_check(password);
		    if(check == false)
		    	continue;
		    else 
		    	break;
	    }
	    //to give confirmation to customer about account creation.
	    if(this.acc_type == 1)
			System.out.println("YOUR ACCOUNT TYPE: CURRENT ACCOUNT account holder's name: " + this.name);
	    if(this.acc_type == 2)
	    	System.out.println("YOUR ACCOUNT TYPE: SAVINGS ACCOUNT account holder's name: " + this.name);
	    // to add customer details to list
	    BankProcess.store(this);
	    System.out.println("Your Account Number: " + this.accountNumber);
	}
	//creating account by choosing account type
    public final void createAccount() {
		System.out.println("CHOOSE YOUR ACCOUNT TYPE:");
		System.out.println("Enter C --> currentAccount S --> savingsAccount");
		char account_type = inputScanner.next().charAt(0);
		if(account_type == 'C' || account_type == 'c') {
			this.accountNumber = ++acc_number;
	        System.out.println("You have chosen currentAccount type - MAXIMUM BALANCE BUSINESS ACCOUNT");
	        System.out.println("SO PLEASE DEPOSIT atleast Rs.60,0000 TO YOUR ACCOUNT AFTER CREATING YOUR ACCOUNT!!!");
	        this.acc_type = 1;
	        this.getUserInformation();
		}
		else if(account_type == 'S' || account_type == 's') {
			this.accountNumber = ++acc_number;
	        System.out.println("You have chosen savingsAccount type - MINIMAL BALANCE ACCOUNT");
	        this.acc_type = 2;
	        this.getUserInformation();
		}
		else
			System.out.println("Please Enter Valid Account Type");
	}
}

