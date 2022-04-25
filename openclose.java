//OPENCLOSED PRINCIPLE: OPEN FOR EXTENSION BUT CLOSED FOR MODIFICATION
package SolidPrinciples;

class cuboid
{
	double length;
	double breadth;
	double height;
}

class sphere 
{
	double radius;
}

class Application//Application(software)changes based on requirement - bad thing
{
	double volume = 0;
	
	public double cuboid_volume(cuboid global)
	{
		volume = global.length*global.breadth*global.height; 
		return volume;
	}
	
	public double sphere_volume(sphere global)
	{
		volume = 4/3 * Math.PI * global.radius; 
		return volume;
	}
}

public class openclose 
{
	public static void main(String[] args)
	{
		cuboid c = new cuboid();
		c.length = 10;
		c.breadth = 20;
		c.height = 30;
		
		sphere s = new sphere();
		s.radius = 5;
		
		Application ap = new Application();
		System.out.println(ap.cuboid_volume(c));
		System.out.println(ap.sphere_volume(s));
	}
}
