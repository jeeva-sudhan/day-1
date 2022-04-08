//inheritance 
package daytwointern;
import java.util.*;

class A 
{
	int a = 10;
	A()
	{
		System.out.println("parent class constructor");
	}
	
	A(A other)
	{
		this.a = other.a;
		//System.out.println(other.a);// 10
		System.out.println(this.a);
	}
}

class B extends A
{
	
	
	B()
	{
		System.out.println("child class constructor");
	}
	
	B(B b)
	{
		super(new B());
		b.a=20;
		//super(new B());//child class take care about parent class initialization first 
	}
	
}

public class inherit 
{
	public static void main(String[] args)
	{
		A a = new A();// only A class constructor invoked
		System.out.println();
		B b = new B();// both class A and B constructor invoked
		B d = new B(b);
		System.out.println(b.a);
		//System.out.println(d.a);// 10
	}
}
