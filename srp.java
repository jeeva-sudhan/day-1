//SINGLE RESPONSIBILITY PRINCIPLE: CLASS WILL TAKE SINGLE RESPONSIBILITY(ONLY ONE REASON TO CHANGE)

package SolidPrinciples;
import java.util.Scanner;

class Mailid
{
	public static void mailidverify()
	{
		System.out.println("your mailid verified");
	}
}

class ATM
{
	int savings = 0;
	
	ATM()
	{
		Mailid.mailidverify();
	}
	
	public void deposit(int dep_amount)
	{
		this.savings += dep_amount;
	}
	
	public void withdraw(int with_amount)
	{
		this.savings -= with_amount;
	}
	
	public int balancecheck()
	{
		return savings;
	}
	
	/*public void mailidverify()
	{
		System.out.println("your mailid verified");
	}*/
}


public class srp 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		ATM g = new ATM();
		System.out.println("youe're all set");
		
		while(true)
		{
			System.out.println("Enter 1 to deposit , 2 to withdraw, 3 to check balance");
			int choice = sc.nextInt();
			int amount = 0;
			switch(choice)
			{
			case 1:
				System.out.println("Enter the amount to deposit");
			     amount = sc.nextInt();
				g.deposit(amount);
				continue;
				
			case 2:
				System.out.println("Enter the amount to withdraw");
			     amount = sc.nextInt();
				g.withdraw(amount);
				continue;
				
			case 3:
				System.out.println("your balance: " + g.balancecheck());		
				
			}
		}
	}
}
