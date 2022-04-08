// ABSTRACTION & ENCAPSULATION
package daytwointern;
import java.util.*;

abstract class AtmGui//design(external)--> abstraction 
{
	public abstract void setbalance(int b);
	public abstract void getbalance();
	
}

class internal extends AtmGui//internal implementation --> encapsulation(data hiding-protection)
{
	private int user_balance;//data hiding-protection
	
	public void setbalance(int dep_amount)
	{
		user_balance += dep_amount;
	}
	
	public void getbalance()
	{
		System.out.println(user_balance);
	}
}

public class ab 
{
	public  static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int dep_amount;
		AtmGui it = new internal();// creating object indirectly
		System.out.println("enter the amount to deposit");
		dep_amount = sc.nextInt();
		it.setbalance(dep_amount);
		System.out.println("your account balance");
		it.getbalance();
		
	}
}
