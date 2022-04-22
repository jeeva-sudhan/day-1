package multithread;

public class ThreadSync implements Runnable {

	public void run() {
		Demo();
	}
	
	/*Object level lock:(synchronized(this) or synchronized(particular object))
	public synchronized void Demo() {
    		System.out.println(Thread.currentThread().getName());
        	System.out.println(Thread.currentThread().getName() + "Begining");
        	System.out.println(Thread.currentThread().getName() + "Ending");
    }*/
	
	//class level lock:(synchronized(ThreadSync.class))
    public void Demo() {
    		System.out.println(Thread.currentThread().getName());
        	System.out.println(Thread.currentThread().getName() + "Begining");
        	System.out.println(Thread.currentThread().getName() + "Ending");	
    }
    
	public static void main(String[] args) {
		ThreadSync threadsync = new ThreadSync();
		Thread t1 = new Thread(threadsync);
		t1.setName("Thread-1");
		Thread t2 = new Thread(threadsync);
		t2.setName("Thread-2");
		
		ThreadSync threadsync1 = new ThreadSync();
		Thread t3 = new Thread(threadsync1);
		t3.setName("Thread-3");
		Thread t4 = new Thread(threadsync1);
		t4.setName("Thread-4");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
