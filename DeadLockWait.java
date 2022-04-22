package multithread;

class Test
{
	public synchronized void call(Demo demo)
	{
		System.out.println("I'm in call method of Test class");
		try
		{
			Thread.sleep(2000);
		}
		catch(InterruptedException exception)
		{
			System.out.println("Exception occurred!!");
		}
		System.out.println("I'm about to call last method of Demo class");
		demo.last();
	}
	
	public synchronized void last()
	{
		System.out.println("I'm in last method of Test class");
	}
}

class Demo
{
	public synchronized void call(Test test)
	{
		System.out.println("I'm in call method of Demo class");
		try
		{
			Thread.sleep(2000);
		}
		catch(InterruptedException exception)
		{
			System.out.println("Exception occurred!!");
		}
		System.out.println("I'm about to call last method of Test class");
		test.last();
	}
	
	public synchronized void last()
	{
		System.out.println("I'm in last method of Demo class");
	}
}

class Temp extends Thread
{
	Test test = new Test();
	Demo demo = new Demo();
	Temp(){
		this.start();
		test.call(demo);
	}
	public void run()
	{
		demo.call(test);
	}
}

public class DeadLockWait {

	public static void main(String[] args) {
		new Temp();
	}
}
