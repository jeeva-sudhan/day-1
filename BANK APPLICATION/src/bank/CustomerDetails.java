package bank;
import java.util.*;
/*CustomerDetails - This class list out the customer details that are stored in bank.
 * The class to list out both business account and savings account of customers from bank
 */

public class CustomerDetails extends CustomerAccount
{
	public final void printCustDetails()//to print customer details from list
    {
		Collections.sort(customerDetailsList,new AccountIdSorting());//sorting based on customer account type
    	for(CustomerAccount customer : customerDetailsList)
    	{
    		if(customer.acc_type == 1)//for business account
    		{
				System.out.println("CUSTOMER ACCOUNT TYPE: CURRENT ACCOUNT account holder's name: " + customer.name);
				System.out.println("ACCOUNT NUMBER: " + customer.accountNumber + " " + "AGE: " + customer.age + " " + "GENDER: " + customer.gender + " " + "Phone Number: " + customer.phoneNumber);
				System.out.println();
    		}
		    
    		else//for savings account
		    {
		    	System.out.println("CUSTOMER ACCOUNT TYPE: SAVINGS ACCOUNT account holder's name: " + customer.name);
		    	System.out.println("ACCOUNT NUMBER: " + customer.accountNumber + " " + "AGE: " + customer.age + " " + "GENDER: " + customer.gender + " " + "Phone Number: " + customer.phoneNumber);
		    	System.out.println();
		    }
    	}
    	
    	System.out.println("BANK NAME: SBI   No.of customers: " + customerDetailsList.size());
    }
}
