package bank;

/*PasswordDescriber class follows SINGLE RESPONSIBILITY PRINCIPLE
 * PasswordChecker - PasswordChecker class will give the information about that how the customer account password should be.
 */

public class PasswordDescriber //class to give information to customer that how the password of account must be
{
	public static boolean password_check(String password) 
	{
		int digit_flag = 0,uprcase_char_flag = 0,spclchar_flag = 0;//flag values to check whether the password meets the bank requirement or not
		
		for(int i =0;i<password.length();i++)
		{
			if(Character.isDigit(password.charAt(i)))
				digit_flag = 1;
			else if((password.charAt(i) == 36) || (password.charAt(i) == 95))
				spclchar_flag = 1;
			else if(Character.isUpperCase(password.charAt(i)))	
				uprcase_char_flag = 1;
		}
		if( digit_flag==1 && uprcase_char_flag==1 && spclchar_flag==1)
			return true;
		else
		{
			if(digit_flag == 0)
				System.out.println("please enter atleast one number");
			if(uprcase_char_flag == 0)
				System.out.println("please enter atleast one Upper case character");
			if(spclchar_flag == 0)
				System.out.println("please enter atleast one special character");
			
			return false;
		}
		
		//PIN NUMBER(4-DIGITS):
		/*Scanner sc = new Scanner(System.in);
		   long pin_number = 1234;
		   System.out.println("enter the input");
		   //long pin = 0;
		   long pin = sc.nextLong();

		   long a = pin_number;
		   long n = pin;
		   
		   while(n!= 0 && a!=0)
		   {
		       if(n%10 != a%10)
		       {
		           System.out.println("not same");
		           break;
		           	
		       }
		       n /= 10;
		       a /= 10;
		   }
		   System.out.println("same");*/
	}
}
