//DEPENDENCY INVERSION PRINCIPLE : we should depend on abstractions (interfaces and abstract classes) instead of concrete implementations (classes). 
//The abstractions should not depend on details; instead, the details should depend on abstractions.
// here we introduced the interface engine that whatever engine can implement it and here car class didn't directly depend on class.
package SolidPrinciples;

interface engine
{
	public void start();
}

class car 
{
	private engine e;
	car(engine e)
	{
		this.e = e;
	}
	public void start()
	{
		e.start();
		System.out.println("car is moving...");
	}
}

class DieselEngine implements engine
{
	public void start()
	{
		System.out.println("DieselEngine started...");
	}
}

class PetrolEngine implements engine
{
	public void start()
	{
		System.out.println("PetrolEngine started...");
	}
}

public class DependencyInversionPrinciple {

	public static void main(String[] args) {
		car obj = new car(new DieselEngine());
		obj.start();
		//car obj1 = new car(new PetrolEngine());
		//obj1.start();
	}
}
