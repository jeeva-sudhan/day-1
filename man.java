//INHERITANCE 
package interndayone;
import java.util.*;

class human 
{
	public void speak()
	{
		System.out.println("parent voice");
	}
	
	public void eat()
	{
		System.out.println("parent behaviour");
	}
}
public class man extends human
{
	public void speak()//not satisfy with parent method so overriding
	{
		System.out.println("child voice");
	}
	
	public static void main(String[] args)
	{
		man m = new man();
		m.speak();
		m.eat();
	}
}
