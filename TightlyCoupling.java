package couplings;

interface specs
{
	void processor();
}

class Samsung implements specs
{
	String phoneProcessor;
	
	public void processor()
	{
		this.phoneProcessor = "Qualcomm";
		System.out.println("PROCESSOR --> " + phoneProcessor);
	}
}

class Poco implements specs
{
	String phoneProcessor;
	public void processor()
	{
		this.phoneProcessor = "snapDragon";
		System.out.println("PROCESSOR --> " + phoneProcessor);
	}
}

class Phone
{
	public void getSpec(Samsung samsung)
	{
		samsung.processor();
	}
	//so we want to declare another method for poco 
	//so we want another method if we go for another phone 
	//this is tightly coupling.
	public void getSpec(Poco poco)
	{
		poco.processor();
	}
}

public class TightlyCoupling {
	public static void main(String[] args)
	{
		Samsung samsung = new Samsung();
		Phone phone = new Phone();
		phone.getSpec(samsung);
		
		Poco poco = new Poco();
		Phone phones = new Phone();
		phones.getSpec(poco);
	}
}
