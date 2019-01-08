// 7495
// Assignment #3
// COP 4610 - CRN 80604

import java.util.concurrent.locks.*;

// create monitor for Garden
public class Garden {
	
	// counters for number of holes dug, planted, and filled
	int numOfDugHoles = 0;
	int numOfPlantedHoles = 0;
	int numOfFilledHoles = 0;
	
	// counters for number of holes not dug, planted, and filled
	int numOfUndugHoles = 0;
	int numOfUnplantedHoles = 0;
	int numOfUnfilledHoles = 0;
		
	// instantiate the lock for digging, planting, and filling holes
	final Lock lock = new ReentrantLock();
	
	// condition instances for digging, planting, and filling holes
	final Condition digHoles = lock.newCondition();
	final Condition plantHoles = lock.newCondition();  
	final Condition fillHoles = lock.newCondition(); 
	
	// Jordan will wait if there are five unfilled holes.
	public void waitToDig() throws InterruptedException {
		lock.lock(); // acquire lock
		try {			
			// while five or more holes are unfilled, Jordan waits
			while (numOfDugHoles - numOfFilledHoles >= 5) {				
				// notify other gardeners that five or more holes are unfilled				
				System.out.println("Jordan is waiting to dig a hole.");				
				digHoles.await();
			}
			plantHoles.signal(); // signal to start planting holes				
		} 
		finally {
			lock.unlock();	// release lock
		}
	}
	
	// Charles will wait to plant if all holes have plants in them
	public void waitToPlant() throws InterruptedException {
		lock.lock(); // acquire lock
		try {
			// while all holes are empty or planted, Charles waits
			while (numOfDugHoles == 0 || numOfPlantedHoles == 10 
					|| numOfDugHoles == numOfUndugHoles) {
				// notify other gardeners that all holes are planted
				System.out.println("Tracy is waiting to fill a hole.");
				plantHoles.await();
			}
			fillHoles.signal();	// signal to start filling holes			
		} 
		finally {
			lock.unlock();	// release lock
		}		
	}
	
	// Tracy will wait if all holes are either empty or already filled
	public void waitToFill() throws InterruptedException {	
		lock.lock(); // acquire lock
		try {
			// while all holes are either empty or filled, Tracy waits
			while (numOfPlantedHoles == 0 || numOfFilledHoles == 10 
					|| numOfPlantedHoles == numOfFilledHoles) {
				// notify other gardeners that all holes are filled
				System.out.println("Tracy is waiting to fill a hole.");
				fillHoles.await();
			}
			digHoles.signal();		
		} 
		finally {
			lock.unlock();	// release lock
		}
	}

	// Jordan is in charge of digging the holes
	public void dig() throws InterruptedException {
		lock.lock(); // acquire lock		
		try {			
			// increment the number of holes dug as they are dug
			System.out.println("Jordan dug a hole." + "\t\t\t\t\t" + ++numOfDugHoles);	
			// wake up any waiting gardeners
			digHoles.signal(); 
		} 
		finally {
			lock.unlock();	// release lock
		}
	}
	
	// Charles is in charge of planting the holes
	public void plant() throws InterruptedException {
		lock.lock(); // acquire lock		
		try {			
			// increment the number of holes planted as they are planted
			System.out.println("Charles planted a hole." + "\t\t\t" + ++numOfPlantedHoles);	
			// decrement the number of holes dug as they are planted
			int temp = numOfDugHoles;
			numOfUndugHoles = temp;
			numOfUndugHoles--;
			// wake up any waiting gardeners
			plantHoles.signal(); 
		} 
		finally {
			lock.unlock();	// release lock
		}
	}
	
	// Tracy is in charge of filling the holes
	public void fill() throws InterruptedException {
		lock.lock(); // acquire lock		
		try {			
			// increment the number of holes filled as they are filled
			System.out.println("Tracy filled a hole." + "\t\t\t\t" + ++numOfFilledHoles);	
			// decrement the number of holes planted as they are filled
			int temp = numOfPlantedHoles;
			numOfUnplantedHoles = temp;
			numOfUnplantedHoles--;
			// wake up any waiting gardeners
			fillHoles.signal();
		} 
		finally {
			lock.unlock();	// release lock
		}
	}	
}