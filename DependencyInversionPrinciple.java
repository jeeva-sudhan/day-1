//DEPENDENCY INVERSION PRINCIPLE : we should depend on abstractions (interfaces and abstract classes) instead of concrete implementations (classes). 
//The abstractions should not depend on details; instead, the details should depend on abstractions.
package SolidPrinciples;
//import java.util.*;
// this car class depends on engine class to start car . so this car class fails to follow DEPENDENCY INVERSION PRINCIPLE.
// to avoid these dependency between classes we go for DEPENDENCY INVERSION PRINCIPLE(to depend upon an common interface)

class Car
{
	private Engine engine;
	Car(Engine engine)
	{
		this.engine = engine;
	}
	public void start()
	{
		engine.start();
		System.out.println("car is moving...");
	}
}

class Engine 
{
	public void start()
	{
		System.out.println("engine started...");
	}
}

public class DependencyInversionPrinciple 
{
	public static void main(String[] args) 
	{
	    Car car = new Car(new Engine());
	    car.start();
	}
}
