// 7495
// Assignment #3
// COP 4610 - CRN 80604

import java.util.concurrent.*;

// Test driver for Garden class
public class Test{

	public static void main(String args[]) throws InterruptedException  {
		ExecutorService threadExecutor = Executors.newCachedThreadPool();
		Garden garden=new Garden();
		threadExecutor.execute(new Jordan(garden));
		threadExecutor.execute(new Charles(garden));
		threadExecutor.execute(new Tracy(garden));
		threadExecutor.shutdown();
		threadExecutor.awaitTermination(30, TimeUnit.SECONDS);     
	}
}