package bank;
import java.util.Scanner;

/*BankProcess - This class consists of bank internal functions working process.
 * Using this class properties we can do normal functions like deposit,withdraw,transferMoney,checkBlance of customer account.
 */


public class BankProcess extends CustomerAccount implements NormalBankFunction //inheriting states(variables) from parent for bank internal process(eg: amount transferring.,)
{
	Scanner scanner = new Scanner(System.in);

	public final void deposit()//Deposit method to deposit money 
	{
		while(true)//loop to repeat when customer doesn't enter valid account number
		{
			System.out.println("ENTER YOUR ACCOUNT NUMBER");
			long inp_acc_number = scanner.nextLong();
			
			for(int iterator =0;iterator<customerDetailsList.size();iterator++)
			{
				CustomerAccount customer = (CustomerAccount)customerDetailsList.get(iterator);//type casting
				if(customer.accountNumber == inp_acc_number)
				{
					if(customer.acc_type == 1)
						System.out.println("YOUR ACCOUNT TYPE: CURRENT ACCOUNT account holder's name: " + customer.name);
					
				    if(customer.acc_type == 2)
				    	System.out.println("YOUR ACCOUNT TYPE: SAVINGS ACCOUNT account holder's name: " + customer.name);
				    
				    while(true)
				    {
				    	System.out.println("Enter the amount to deposit");
					    double deposit_amount = scanner.nextDouble();
					   
					    if(customer.acc_type == 1 && deposit_amount >= 60000 && customer.balance == 0)//if account type is Business account
					    {
					    	customer.balance += deposit_amount;
					        GenerateOtp.generateDepositOtp(customer,deposit_amount);
					        return;
					    }
					
					    else if(customer.acc_type == 2)//if account type is savings account
					    {
					    	customer.balance += deposit_amount;
						    GenerateOtp.generateDepositOtp(customer,deposit_amount);//to generate otp for confirmation about deposit
					        return;
					    }
					
					    else //if business account doesn't meet the bank requirement
					    {
					    	System.out.println("You Account type - MAXIMUM BALANCE BUSINESS ACCOUNT");
				            System.out.println("SO PLEASE DEPOSIT atleast Rs.60,0000 TO YOUR ACCOUNT!!");
				            iterator = 0;//re-initializing to start for loop from starting point for proper execution
						    continue;
					    }
					 }
			     }
			}
			
			System.out.println("PLEASE ENTER THE VALID ACCOUNT NUMBER!!!");
			continue;//for repeating loop when user enters INVALID ACCOUNT NUMBER
		}
	}
	
	
	 public final void withDraw()// to withdraw some amount from account
     {
    	while(true)//loop to repeat when customer doesn't enter valid account number/password
    	{
    		System.out.println("ENTER YOUR ACCOUNT NUMBER");
    		long inp_acc_number = scanner.nextLong();
    		System.out.println("ENTER YOUR ACCOUNT PASSWORD");
    		String inp_password = scanner.next();
    		
    		for(CustomerAccount customer : customerDetailsList)
    		{
    			if(customer.accountNumber == inp_acc_number)
    			{
    				if((customer.password).equals(inp_password))
    				{
    					if(customer.acc_type == 1)
    						System.out.println("YOUR ACCOUNT TYPE: CURRENT ACCOUNT account holder's name: " + customer.name);
    				    if(customer.acc_type == 2)
    				    	System.out.println("YOUR ACCOUNT TYPE: SAVINGS ACCOUNT account holder's name: " + customer.name);
    				    
    				    System.out.println("Enter the amount to withdraw");
    					double withdraw_amount = scanner.nextDouble();
    					
    					if(withdraw_amount > customer.balance)//condition to check for insufficient balance
    					{
    						System.out.println("INSUFFICIENT BALANCE");
    						return;
    					}
    					
    					customer.balance -= withdraw_amount;
    					if(customer.acc_type == 1 && customer.balance <= 60000)//condition for banking account
    					{
    						customer.balance += withdraw_amount;//if given withdrawn amount is beyond the limit then we have to add the reduced balance.
    						System.out.println("You Account type - MAXIMUM BALANCE BUSINESS ACCOUNT");
    				        System.out.println("SO YOUR ACCOUNT MUST CONTAIN atleast Rs.60,0000!!");
    				        System.out.println("UNABLE TO WITHDRAW!!");
    				        return;
    					}
    					
    					else
    					{
    						GenerateOtp.generateWithdrawOtp(customer,withdraw_amount);//to generate otp for confirmation about withdraw
    					    return;
    					}
    				}
    			}
    		}
    		System.out.println("INVALID ACCOUNT NUMBER/PASSWORD TRY AGAIN!!!");
    		continue;//for repeating loop when user enters INVALID ACCOUNT NUMBER/PASSWORD
    	}
    }
    
	public final void checkBalance()
    {
    	while(true)//loop to repeat when customer doesn't enter valid account number/password
    	{
    		System.out.println("ENTER YOUR ACCOUNT NUMBER");
    		long inp_acc_number = scanner.nextLong();
    		System.out.println("ENTER YOUR ACCOUNT PASSWORD");
    		String inp_password = scanner.next();
    		
    		for(CustomerAccount customer : customerDetailsList)
    		{
    			if(customer.accountNumber == inp_acc_number)
    			{
    				if((customer.password).equals(inp_password))
    				{
    					//to give confirmation about the account type
    					if(customer.acc_type == 1)
    						System.out.println("YOUR ACCOUNT TYPE: CURRENT ACCOUNT account holder's name: " + customer.name);
    					
    				    if(customer.acc_type == 2)
    				    	System.out.println("YOUR ACCOUNT TYPE: SAVINGS ACCOUNT account holder's name: " + customer.name);
    				  
    				    System.out.println("YOUR CURRENT ACCOUNT BALANCE" + customer.balance);
    					return;//to avoid running of infinite loop and to return when function completes
    				}
    			}
    		}
    		
    		System.out.println("INVALID ACCOUNT NUMBER/PASSWORD TRY AGAIN!!!");
    		continue;//for repeating loop when user enters INVALID ACCOUNT NUMBER/PASSWORD
    	}
    }
    
    public final void transferMoney()//method for transferring money from one account to another account.
    {
    	while(true)//loop to repeat when customer doesn't enter valid account password
    	{
    		int sender_flag = 0,receiver_flag = 0;//flag values to check whether the sender and receiver account number is valid or not.
        	System.out.println("ENTER YOUR ACCOUNT NUMBER");
    		long sender_acc_number = scanner.nextLong();
    		System.out.println("ENTER THE RECEIPIENT ACCOUNT NUMBER");
    		long receiver_acc_number =  scanner.nextLong();
    		CustomerAccount receiver = null;
    		
    		for(CustomerAccount customer : customerDetailsList)//for checking valid sender receiver account numbers
    		{
    			if(customer.accountNumber == sender_acc_number)
    			{
    				sender_flag = 1;
    				
    			}
    			else if(customer.accountNumber == receiver_acc_number)
    			{
    				receiver = customer;
    				receiver_flag = 1;
    			}
    		}
    		
    		if(sender_flag == 0 | receiver_flag == 0)
    		{
    			System.out.println("INVALID Transpient Account/Receipient Account Number");
    			continue;//for repeating loop when user enters INVALID ACCOUNT NUMBER
    		}
    		
    		System.out.println("ENTER YOUR ACCOUNT PASSWORD");
    		String sender_password = scanner.next();
    		
    		for(CustomerAccount sender : customerDetailsList)
    		{
    			if((sender.password).equals(sender_password))
    			{
    				System.out.println("Enter the amount to transfer");
    				long transfer_amount = scanner.nextLong();
    				
    				if(transfer_amount > sender.balance)//condition to check insufficient balance
					{
						System.out.println("INSUFFICIENT BALANCE");
						return;
					}
    				
    				sender.balance -= transfer_amount;
    				
    				if(sender.acc_type == 1 && sender.balance <= 60000)//condition for business account to meet bank requirements
					{
    					sender.balance += transfer_amount;
						System.out.println("You Account type - MAXIMUM BALANCE BUSINESS ACCOUNT");
				        System.out.println("SO YOUR ACCOUNT MUST CONTAIN atleast Rs.60,0000!!");
				        System.out.println("UNABLE TO TRANSFER!!");
				        return;
					}
    				
    				else
    				{
    					receiver.balance += transfer_amount;
    					
    					//to generate otp to both sender and receiver to give amount is transferred and received.
    					GenerateOtp.generateOtpSenderOtp(sender, transfer_amount, receiver);
    					GenerateOtp.generateOtpReceiverOtp(sender, transfer_amount, receiver);
    		            return;
    			    }
    				
    			}
    		}
    		
    		System.out.println("INVALID ACCOUNT PASSWORD TRY AGAIN!!");
    		continue;//for repeating loop when user enters INVALID ACCOUNT PASSWORD
    	}
    }
    
}
