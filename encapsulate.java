package interndayone;
import java.util.*;

class capsule
{
	private int balance;
	
	public void setBalance(int amount,int flag)
	{
		if(flag == 1)
			this.balance += amount;
		else
			this.balance -= amount; 
	}
	
	public void getBalance()
	{
		System.out.println(balance);
	}
}

public class encapsulate 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		capsule c = new capsule();
		int choice,flag;
		while(true)
		{
			System.out.println("press 1 --> deposit 2 --> withdraw 3 --> check balance 4 --> exit");
			choice = sc.nextInt();
			switch(choice)
			{
			     case 1:
			    	 flag = 1;
			    	 System.out.println("enter amount to deposit");
			    	 int dep_amount = sc.nextInt();
			    	 c.setBalance(dep_amount,flag);
			    	 continue;
			    
			     case 2:
			    	 flag = 0;
			    	 System.out.println("enter amount to withdraw");
			    	 int with_amount = sc.nextInt();
			    	 c.setBalance(with_amount,flag);
			    	 continue;
			    
			     case 3:
			    	 c.getBalance();
			    	 continue;
			    	 
			     case 4:
			    	 break;
			}
			System.out.println("Thank You");
			break;
			
		}
	}
}
