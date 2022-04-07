//FINAL MODIFIER
package interndayone;

final class custom
{
	public final void m1()
	{
		System.out.println("final method");
	}
}

public class variables //extends custom // we cannot inherit final class
{
	/*public void final void m1()//we cannot override final method 
	{
		
	}*/
	
	public static void main(String[] args)
	{
		final int x = 10;
		//x = x + 1;//we cannot reassign final(constant) variable
		
		custom c = new custom();// we can create object for final method and we can access
		c.m1();
	}
}
