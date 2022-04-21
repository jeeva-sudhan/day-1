package bank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*BankProcess - This class consists of bank internal functions working process.
 * Using this class properties we can do normal functions like deposit,withdraw,transferMoney,checkBalance of customer account.
 */

//inheriting states(variables) from parent for bank internal process(eg: amount transferring.,)
public class BankProcess implements AtmGui {
	//because all customers details are going to store in this ArrayList so i declared it as static(common for all objects)
	static private List<CustomerAccount> customerDetailsList = new ArrayList<CustomerAccount>();	
	Scanner inputScanner = new Scanner(System.in);
	//write-only
	//data binding
	public static void store(CustomerAccount customer) {
		customerDetailsList.add(customer);
	}
	//Deposit method to deposit money 
	public final void deposit() {//data hiding
		//loop to repeat when customer doesn't enter valid account number
		while(true) {
			System.out.println("ENTER YOUR ACCOUNT NUMBER");
			long inp_acc_number = inputScanner.nextLong();
			for(int iterator =0;iterator<customerDetailsList.size();iterator++) {
				CustomerAccount customer = (CustomerAccount)customerDetailsList.get(iterator);//type casting
				if(customer.accountNumber == inp_acc_number) {
					if(customer.acc_type == 1)
						System.out.println("YOUR ACCOUNT TYPE: CURRENT ACCOUNT account holder's name: " + customer.name);
					if(customer.acc_type == 2)
				    	System.out.println("YOUR ACCOUNT TYPE: SAVINGS ACCOUNT account holder's name: " + customer.name);
				    while(true) {
				    	System.out.println("Enter the amount to deposit");
					    double deposit_amount = inputScanner.nextDouble();
					    //if account type is Business account 
					    if(customer.acc_type == 1 && deposit_amount >= 60000 && customer.balance == 0) {
					    	customer.balance += deposit_amount;
					        GenerateOtp.generateDepositOtp(customer,deposit_amount);
					        return;
					    }
					    //if account type is savings account
					    else if(customer.acc_type == 2) {
					    	customer.balance += deposit_amount;
						    GenerateOtp.generateDepositOtp(customer,deposit_amount);//to generate otp for confirmation about deposit
					        return;
					    }
					    //if business account doesn't meet the bank requirement
					    else {
					    	System.out.println("You Account type - MAXIMUM BALANCE BUSINESS ACCOUNT");
				            System.out.println("SO PLEASE DEPOSIT atleast Rs.60,0000 TO YOUR ACCOUNT!!");
				            iterator = 0;//re-initializing to start for loop from starting point for proper execution
						    continue;
					    }
					 }
			     }
			}
			System.out.println("PLEASE ENTER THE VALID ACCOUNT NUMBER!!!");
			//for repeating loop when user enters INVALID ACCOUNT NUMBER
			continue;
		}
	}
	//withdraw function to withdraw amount from account
	 public final void withDraw() {
		//loop to repeat when customer doesn't enter valid account number/password
    	while(true) {
    		System.out.println("ENTER YOUR ACCOUNT NUMBER");
    		long inp_acc_number = inputScanner.nextLong();
    		System.out.println("ENTER YOUR ACCOUNT PASSWORD");
    		String inp_password = inputScanner.next();
    		for(CustomerAccount customer : customerDetailsList) {
    			if(customer.accountNumber == inp_acc_number) {
    				if((customer.password).equals(inp_password)) {
    					if(customer.acc_type == 1)
    						System.out.println("YOUR ACCOUNT TYPE: CURRENT ACCOUNT account holder's name: " + customer.name);
    				    if(customer.acc_type == 2)
    				    	System.out.println("YOUR ACCOUNT TYPE: SAVINGS ACCOUNT account holder's name: " + customer.name);
    				    System.out.println("Enter the amount to withdraw");
    					double withdraw_amount = inputScanner.nextDouble();
    					//condition to check for insufficient balance
                        if(withdraw_amount > customer.balance) {
    						System.out.println("INSUFFICIENT BALANCE");
    						return;
    					}
    					customer.balance -= withdraw_amount;
    					//condition for banking account
    					if(customer.acc_type == 1 && customer.balance <= 60000) {
    						//if given withdrawn amount is beyond the limit then we have to add the reduced balance.
    						customer.balance += withdraw_amount;
    						System.out.println("You Account type - MAXIMUM BALANCE BUSINESS ACCOUNT");
    				        System.out.println("SO YOUR ACCOUNT MUST CONTAIN atleast Rs.60,0000!!");
    				        System.out.println("UNABLE TO WITHDRAW!!");
    				        return;
    					}
    					else
    					{
    						//to generate otp for confirmation about withdraw
    						GenerateOtp.generateWithdrawOtp(customer,withdraw_amount);
    					    return;
    					}
    				}
    			}
    		}
    		System.out.println("INVALID ACCOUNT NUMBER/PASSWORD TRY AGAIN!!!");
    		//for repeating loop when user enters INVALID ACCOUNT NUMBER/PASSWORD
    		continue;
    	}
    }
	// to check balance
    public final void checkBalance() {
    	//loop to repeat when customer doesn't enter valid account number/password
    	while(true) {
    		System.out.println("ENTER YOUR ACCOUNT NUMBER");
    		long inp_acc_number = inputScanner.nextLong();
    		System.out.println("ENTER YOUR ACCOUNT PASSWORD");
    		String inp_password = inputScanner.next();
    		for(CustomerAccount customer : customerDetailsList) {
    			if(customer.accountNumber == inp_acc_number) {
    				if((customer.password).equals(inp_password)) {
    					//to give confirmation about the account type
    					if(customer.acc_type == 1)
    						System.out.println("YOUR ACCOUNT TYPE: CURRENT ACCOUNT account holder's name: " + customer.name);
    					if(customer.acc_type == 2)
    				    	System.out.println("YOUR ACCOUNT TYPE: SAVINGS ACCOUNT account holder's name: " + customer.name);
    				    System.out.println("YOUR CURRENT ACCOUNT BALANCE" + customer.balance);
    				    //to avoid running of infinite loop and to return when function completes
    					return;
    				}
    			}
    		}
    		System.out.println("INVALID ACCOUNT NUMBER/PASSWORD TRY AGAIN!!!");
    		//for repeating loop when user enters INVALID ACCOUNT NUMBER/PASSWORD
    		continue;
    	}
    }
    //method for transferring money from one account to another account.
    public final void transferMoney() {
    	//loop to repeat when customer doesn't enter valid account password
    	while(true) {
    		//flag values to check whether the sender and receiver account number is valid or not.
    		int sender_flag = 0,receiver_flag = 0;
        	System.out.println("ENTER YOUR ACCOUNT NUMBER");
    		long sender_acc_number = inputScanner.nextLong();
    		System.out.println("ENTER THE RECEIPIENT ACCOUNT NUMBER");
    		long receiver_acc_number =  inputScanner.nextLong();
    		CustomerAccount receiver = null;
    		//for checking valid sender receiver account numbers
    		for(CustomerAccount customer : customerDetailsList) {
    			if(customer.accountNumber == sender_acc_number) {
    				sender_flag = 1;
    				
    			}
    			else if(customer.accountNumber == receiver_acc_number) {
    				receiver = customer;
    				receiver_flag = 1;
    			}
    		}
    		if(sender_flag == 0 | receiver_flag == 0) {
    			System.out.println("INVALID Transpient Account/Receipient Account Number");
    			//for repeating loop when user enters INVALID ACCOUNT NUMBER
    			continue;
    		}
    		System.out.println("ENTER YOUR ACCOUNT PASSWORD");
    		String sender_password = inputScanner.next();
    		for(CustomerAccount sender : customerDetailsList) {
    			if((sender.password).equals(sender_password)) {
    				System.out.println("Enter the amount to transfer");
    				long transfer_amount = inputScanner.nextLong();
    				//condition to check insufficient balance
    				if(transfer_amount > sender.balance) {
						System.out.println("INSUFFICIENT BALANCE");
						return;
					}
    				sender.balance -= transfer_amount;
    				//condition for business account to meet bank requirements
    				if(sender.acc_type == 1 && sender.balance <= 60000) {
    					sender.balance += transfer_amount;
						System.out.println("You Account type - MAXIMUM BALANCE BUSINESS ACCOUNT");
				        System.out.println("SO YOUR ACCOUNT MUST CONTAIN atleast Rs.60,0000!!");
				        System.out.println("UNABLE TO TRANSFER!!");
				        return;
					}
    				else {
    					receiver.balance += transfer_amount;
    					//to generate otp to both sender and receiver to give amount is transferred and received.
    					GenerateOtp.generateOtpSenderOtp(sender, transfer_amount, receiver);
    					GenerateOtp.generateOtpReceiverOtp(sender, transfer_amount, receiver);
    		            return;
    			    }
    		    }
    		}
    	    System.out.println("INVALID ACCOUNT PASSWORD TRY AGAIN!!");
    		//for repeating loop when user enters INVALID ACCOUNT NUMBER/PASSWORD
    		continue;
    	}
    }
  //to remove customer account from customer details list
    public final void removeAccount() {   
    	//loop to repeat when customer doesn't enter valid account number/password
    	while(true) {
    			System.out.println("ENTER YOUR ACCOUNT NUMBER");
    			long inp_acc_number = inputScanner.nextLong();
        		System.out.println("ENTER YOUR ACCOUNT PASSWORD");
        		String inp_password = inputScanner.next();
        		for(int iterator = 0;iterator<customerDetailsList.size();iterator++) {
        			CustomerAccount customer = (CustomerAccount)customerDetailsList.get(iterator);
        			if(customer.accountNumber == inp_acc_number) {
        				if((customer.password).equals(inp_password)) {
        					//to give confirmation about the account type
        					if(customer.acc_type == 1)
        						System.out.println("YOUR ACCOUNT TYPE: CURRENT ACCOUNT account holder's name: " + customer.name);
        				    if(customer.acc_type == 2)
        				    	System.out.println("YOUR ACCOUNT TYPE: SAVINGS ACCOUNT account holder's name: " + customer.name);
        				    System.out.println("YOUR ACCOUNT " + customer.accountNumber + "REMOVED SUCCESFULLY FROM BANK");
        				    //removing customer account from bank
        				    customerDetailsList.remove(iterator);
        				    //to avoid running of infinite loop and to return when function completes
        					return;
        				}
        			}
        		}
        		System.out.println("INVALID ACCOUNT NUMBER/PASSWORD TRY AGAIN!!!");
        		//for repeating loop when user enters INVALID ACCOUNT NUMBER/PASSWORD
        		continue;
        }
    }
    //to print customer details from list
    //read-only
  	public final void printCustDetails() {
  		Collections.sort(customerDetailsList,new AccountIdSorting());//sorting based on customer account type
      	for(CustomerAccount customer : customerDetailsList) {
      		//for business account
      		if(customer.acc_type == 1) {
  				System.out.println("CUSTOMER ACCOUNT TYPE: CURRENT ACCOUNT account holder's name: " + customer.name);
  				System.out.println("ACCOUNT NUMBER: " + customer.accountNumber + " " + "AGE: " + customer.age + " " + "GENDER: " + customer.gender + " " + "Phone Number: " + customer.phoneNumber);
  				System.out.println();
      		}
      		//for savings account
  		    else {
  		    	System.out.println("CUSTOMER ACCOUNT TYPE: SAVINGS ACCOUNT account holder's name: " + customer.name);
  		    	System.out.println("ACCOUNT NUMBER: " + customer.accountNumber + " " + "AGE: " + customer.age + " " + "GENDER: " + customer.gender + " " + "Phone Number: " + customer.phoneNumber);
  		    	System.out.println();
  		    }
      	}
      	System.out.println("BANK NAME: SBI   No.of customers: " + customerDetailsList.size());
      }
}
