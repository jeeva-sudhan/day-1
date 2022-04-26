//INTERFACE SEGREGERATION PRINCIPLE:we must don't force the clients to implement what they didn't want
package SolidPrinciples;
import java.util.ArrayList;
interface carone
{
	public void engineOn();
	public void engineOff();
	public void lightsOn();
	public void lightsOff();
}

interface Radio
{
	public void radioOn();
	public void radioOff();
}

class yunovaone implements carone,Radio 
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

class ferrarione implements carone
{
	public void engineOn() 
	{
		System.out.println("engine started");
	}
	public void engineOff() 
	{
		System.out.println("engine stopped");
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
public class InterfaceSegregation {

	public static void main(String[] args) 
	{
       yunovaone y1 = new yunovaone();
		y1.radioOn();
		y1.radioOff();
		ArrayList<carone> c1 = new ArrayList();
		c1.add(new yunovaone());
		c1.add(new ferrarione());
		
		for(carone c2 : c1)
		{
			c2.lightsOn();
			c2.lightsOff();
		}
	}

}
