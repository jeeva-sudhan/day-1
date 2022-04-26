//DEPENDENCY INVERSION PRINCIPLE : we should depend on abstractions (interfaces and abstract classes) instead of concrete implementations (classes). 
//The abstractions should not depend on details; instead, the details should depend on abstractions.
package SolidPrinciples;
//import java.util.*;
// this car class depends on engine class to start car . so this car class fails to follow DEPENDENCY INVERSION PRINCIPLE.
// to avoid these dependency between classes we go for DEPENDENCY INVERSION PRINCIPLE(to depend upon an common interface)
// here car class not directly depends on engine instead it depends on interface.so it can get whatever engine it wants.

interface VehicleEngine
{
	public void start();
}

class Vehicle
{
	private VehicleEngine engine;
	Vehicle(VehicleEngine engine)
	{
		this.engine = engine;
	}
	public void start()
	{
		engine.start();
		System.out.println("car is moving...");
	}
}

class PetrolEngine implements VehicleEngine
{
	public void start()
	{
		System.out.println("PetrolEngine started...");
	}
}

class DieselEngine implements VehicleEngine
{
	public void start()
	{
		System.out.println("DieselEngine started...");
	}
}

public class DependencyInversion 
{
	public static void main(String[] args) 
	{
		Vehicle petrolVehicle = new Vehicle(new PetrolEngine());
		petrolVehicle.start();
	    Vehicle dieselVehicle = new Vehicle(new DieselEngine());
	    dieselVehicle.start();
	}
}
