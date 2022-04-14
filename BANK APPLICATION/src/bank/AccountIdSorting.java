package bank;
import java.util.*;

/*AccountSorting - This class will sort the account type of customer.
 * Inorder to perform customized sorting order to make business account first then followed by savings account.
 */
public class AccountIdSorting extends CustomerAccount implements Comparator<CustomerAccount>//for cso(customized sorting order)
{
	public int compare(CustomerAccount received_customer_one,CustomerAccount received_customer_two)
	{
		CustomerAccount customer_one = (CustomerAccount)received_customer_one;
		CustomerAccount customer_two = (CustomerAccount)received_customer_two;
		Integer customer_id1 = customer_one.acc_type;
		Integer customer_id2 = customer_two.acc_type;
		
		return customer_id1.compareTo(customer_id2);//Ascending order sorting based on customer account type
	}
}

