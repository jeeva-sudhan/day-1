//OPENCLOSED PRINCIPLE: OPEN FOR EXTENSION BUT CLOSED FOR MODIFICATION
package SolidPrinciples;

abstract class global_objects
{
	public abstract double volume();
}

class cuboidone extends global_objects
{
	double length;
	double breadth;
	double height;
	public double volume()
	{
		return length*breadth*height;
	}
}

class sphereone extends global_objects
{
	double radius;
	public double volume()
	{
		return 4/3 * Math.PI * radius;
	}
}

class Applicationone//Application(software) not changes based on requirement
{
	double volume = 0;
	public void get_volume(global_objects[] global)
	{
		for(global_objects globalvalues : global)
		{
			System.out.print(globalvalues.getClass().getName());
			System.out.println(globalvalues.volume());
		}
	}
	
}


public class openclosetwo 
{
	public static void main(String[] args)
	{
		cuboidone c = new cuboidone();
		c.length = 10;
		c.height = 20;
		c.breadth = 30;
		
		sphereone s = new sphereone();
		s.radius = 5;
		
		global_objects[] global = new global_objects[2];
		global[0] = c;
		global[1] = s;
		
		Applicationone ap = new Applicationone();
		ap.get_volume(global);
	}
}
