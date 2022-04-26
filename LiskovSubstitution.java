//liskov substitution principle:
package SolidPrinciples;
import java.util.*;

abstract class Shape
{
	int height;
	int width;
	public abstract void setHeight(int height);
	public abstract void setWidth(int width);
	
}

class Rectangletwo extends Shape
{
	public void setHeight(int value)
	{
		this.height = value;
		//System.out.println(height);
	}
	public void setWidth(int value)
	{
		this.width = value;
		//System.out.println(width);
	}
}

class Squaretwo extends Shape
{
	public void setHeight(int value)
	{
		this.height = value;
		this.width = value;
		//System.out.println(height);
	}
	public void setWidth(int value)
	{
		this.height = value;
		this.width = value;
		//System.out.println(width);
	}
}

public class LiskovSubstitution
{
	public static void main(String[] args) 
	{
		Shape s = new Rectangletwo();
		s.setHeight(20);
		s.setWidth(30);
		System.out.println(s.height);
		System.out.println(s.width);
		
		Shape s1 = new Squaretwo();
		s1.setHeight(40);
		System.out.println(s1.height);
		System.out.println(s1.width);
		
		//s & s1 defines the shape s - values different ->rectangle,s1 - values same -> square.
	}

}
