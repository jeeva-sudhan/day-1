//INTERFACE SEGREGRATION PRINCIPLE:we must don't force the clients to implement what they didn't want
package SolidPrinciples;
import java.util.ArrayList;
interface car
{
	public void engineOn();
	public void engineOff();
	public void radioOn();
	public void radioOff();
	public void lightsOn();
	public void lightsOff();
}

class yunova implements car
{
	public void engineOn() 
	{
		System.out.println("engine started");
	}
	public void engineOff() 
	{
		System.out.println("engine stopped");
	}
	public void radioOn()
	{
		System.out.println("radio turned on");
	}
	public void radioOff()
	{
		System.out.println("radio turned off");
	}
	public void lightsOn()
	{
		System.out.println("lights turned on");
	}
	public void lightsOff()
	{
		System.out.println("lights turned off");
	}
}


class ferrari extends RuntimeException implements car
{
	public void engineOn() 
	{
		System.out.println("engine started");
	}
	public void engineOff() 
	{
		System.out.println("engine stopped");
	}
	public void radioOn()throws RuntimeException//we must don't force the clients to implement what they didn't want
	{
		throw new RuntimeException("i have no radio to turn on");
	}
	public void radioOff()throws RuntimeException//we must don't force the clients to implement what they didn't want
	{
		throw new RuntimeException("i have no radio to turn off");
	}
	public void lightsOn()
	{
		System.out.println("lights turned on");
	}
	public void lightsOff()
	{
		System.out.println("lights turned off");
	}
}

public class isp {

	public static void main(String[] args)
	{
		yunova y = new yunova();
		
		ArrayList<car> c = new ArrayList();
		c.add(new yunova());
		c.add(new ferrari());
		
		for(car c1 : c)
		{
			c1.radioOn();
			c1.radioOff();
		}
		

	}

}
