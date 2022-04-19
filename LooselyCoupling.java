package Looselycoupling;

interface Specs
{
	void processor();
}

class Samsung implements Specs
{
	String phoneProcessor;
	
	public void processor()
	{
		this.phoneProcessor = "Qualcomm";
		System.out.println("PROCESSOR --> " + phoneProcessor);
	}
}

class Poco implements Specs
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
	//loosely coupled
	//whatever phone object comes we can access it through interface Specs.
	//in the above program we want to change the Phone class every time when want new phone 
	//but here using specs interface we can access any phone (code line reduced as compared to above program)
	public void getSpec(Specs spec)
	{
		spec.processor();
	}
}
	

public class LooselyCoupling {
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