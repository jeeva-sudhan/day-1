package bank;
import java.util.*;
/*AccountSorting - This class will sort the account type of customer.
 * Inorder to perform customized sorting order to make business account first then followed by savings account.
 */
//for cso(customized sorting order)
public class AccountIdSorting implements Comparator<CustomerAccount> 
{
	public int compare(CustomerAccount received_customerone,CustomerAccount received_customertwo) {
		CustomerAccount customer_one = (CustomerAccount)received_customerone;//type casting
		CustomerAccount customer_two = (CustomerAccount)received_customertwo;
		Integer customer_id1 = customer_one.acc_type;
		Integer customer_id2 = customer_two.acc_type;
		//Ascending order sorting based on customer account type
		return customer_id1.compareTo(customer_id2);
	}
}

