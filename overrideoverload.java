//overriding(runtime polymorphism) & overloading(complie time polymorphism - methodhiding)
package interndayone;
import java.util.*;
class simple 
{
	int y;
	public void methodone()
	{
		System.out.println("methodone-overloading");
	}
	
	public void methodone(int x)
	{
		this.y = x;
		System.out.println(y);
	}
	
	public void methodtwo()
	{
		System.out.println("methodtwo-overriding");
	}
	
	public static void methodthree()
	{
		System.out.println("method hiding-parent class");
	}
}

public class overrideoverload extends simple
{
	public void methodtwo()
	{
		System.out.println("child overriding");
	}
	
	public static void methodthree()//method hiding
	{
		System.out.println("method hiding - child class");
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); 
		
		simple s = new simple();
		s.methodone();
		s.methodone(n);//overloading based on reference type.
		s.methodtwo();//overriding based on runtime object.
		s.methodthree();
		
		simple s1 = new overrideoverload();
		s1.methodone();
		s1.methodone(n);
		s1.methodtwo();
		
		overrideoverload l = new overrideoverload();//method hiding based on reference type
		l.methodthree();
	}
}
