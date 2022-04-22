package multithread;

class Mythread extends Thread
{
	Thread thread;
	Mythread(Thread thread)
	{
		this.thread = thread;
	}
	public void run()
	{
		try
		{
			//child thread entered into waiting state(waiting for main thread to die)
			thread.join();
			//so both the threads will wait forever --> DEAD LOCK.
		}
		catch(InterruptedException exception)
		{
			System.out.println("Exception occurred!!");
		}
	}
}

public class DeadLock {

	public static void main(String[] args)throws InterruptedException {
		Thread thread = Thread.currentThread();
		Mythread mt = new Mythread(thread);
		mt.start();
		//main thread entered into waiting state(waiting for child thread to die)
		mt.join();
	}
}
