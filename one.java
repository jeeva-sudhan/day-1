//ABSTRACTION 
package interndayone;
import java.util.*;


class customer 
{
	private long balance=500;
	customer(long amount)
	{
		this.balance +=  amount;//backend calculation not needed to visible to user2
	}
	public void checkbalance()//user to checkbalance
	{
		System.out.println(balance);
	}
}

public class one 
{
    static long amount;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		customer c;
		System.out.println("enter your amount to deposite");
		amount = sc.nextInt();
		c = new customer(amount);
		System.out.println("press 1 to checkbalance");
		int n = sc.nextInt();
		if(n == 1)
		c.checkbalance();
	}
}
