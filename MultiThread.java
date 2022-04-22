package multithread;

class ThreadOne extends Thread
{
	public void run()
	{
		Thread.currentThread().setName("Thread-1");
		try
		{
			for(int i =0;i<5;i++)
			{
				Thread.sleep(500);
				System.out.println(Thread.currentThread().getName());
			}
		}
		catch(InterruptedException exception)
		{
			System.out.println(exception);
		}
	}
}

class ThreadTwo extends Thread
{
	public void run()
	{
		Thread.currentThread().setName("Thread-2");
		try
		{
			for(int i =0;i<5;i++)
			{
				Thread.sleep(500);
				System.out.println(Thread.currentThread().getName());
			}
		}
		catch(InterruptedException exception)
		{
			System.out.println(exception);
		}
	}
}

public class MultiThread {
	public static void main(String[] args)throws InterruptedException {
		ThreadOne threadone = new ThreadOne();
		System.out.println(Thread.currentThread().getName());
		ThreadTwo threadtwo = new ThreadTwo();
		System.out.println(Thread.currentThread().getName());
		threadone.start();
		//threadone.start();//IllegalThreadStateException
		Thread.sleep(3000);
		threadtwo.start();
	}
}
