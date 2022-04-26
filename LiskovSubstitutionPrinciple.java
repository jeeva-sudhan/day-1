//liskov substitution principle:
package SolidPrinciples;
import java.util.*;

class Rectangle 
{
	int height;
	int width;
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

class Square extends Rectangle//square is a (extends) rectangle when height and width are same.
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

public class LiskovSubstitutionPrinciple 
{
	public static void main(String[] args)
	{
		
		Rectangle r1 = new Square();//parent object replaced by child object.
		r1.height = 30;
		r1.width = 40;
		System.out.println(r1.height);
		System.out.println(r1.width);
		r1.setHeight(10);
		r1.setWidth(20);
		System.out.println(r1.height);
		System.out.println(r1.width);// not follows liskov substitution principle
		
		
		/*ArrayList<Rectangle> rl = new ArrayList();
		rl.add(new Square());
		
		rl.get(0).setHeight(30)*/
		
		/*for(Rectangle r1 : rl)
		{
			 r1.setHeight(20);
		}*/
		
	}
}
