//HAS-A-relationship
package interndayone;
import java.util.*;

class Address
{
	String city;
	String street;
	Address(String c,String st)
	{
		this.city = c;
		this.street = st;
	}
	
}

class User 
{
	int age;
	String name;
	String city;
	String street;
	Address a;//aggregation
	
	User(int as,String s)
	{
		this.age = as;
		this.name = s;
		a = new Address("AAA","BBB");
		this.city = a.city;
		this.street = a.street;
	}
	
	public void userDetails()
	{
		System.out.println("age: " + age+ " " + "name: " + name+ " " + "city: " + city + " " +"street: " + street);
	}
}

public class relate 
{
	public static void main(String[] args)
	{
		User u = new User(50,"aaa");
		u.userDetails();
	}
}
