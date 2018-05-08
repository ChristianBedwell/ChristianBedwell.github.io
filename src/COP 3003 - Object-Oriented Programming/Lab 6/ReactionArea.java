public class ReactionArea {
	public int waitingHydrogen = 0;		// number of un-reacted hydrogens
	public int waitingOxygen = 0;		// number of un-reacted oxygens

 	public synchronized void increWHydrogen(int index) throws InterruptedException {  
 		while(waitingHydrogen > 5) {		// the hydrogen thread waits when there are more than 5 waiting hydrogen atoms
			try {
				wait();		// throws InterruptedException
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
 		waitingHydrogen++;
		System.out.println("The " + index + "th Hydrogen atom was added.");
		notifyAll();		// tell threads to enter runnable state
 	}
 	
 	public synchronized void increWOxygen(int index) throws InterruptedException { 
 		while(waitingOxygen > 2) {		// the oxygen thread waits when there are more than 2 waiting oxygen atoms
			try {
				wait();		// throws InterruptedException
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		waitingOxygen++;		
		System.out.println("The " + index + "th Oxygen atom was added.");
		notifyAll();		// tell threads to enter runnable state
 	}
 	
 	public synchronized void react(int index) throws InterruptedException {  
 		while(waitingHydrogen < 2 || waitingOxygen < 1) {		// the reactor thread waits when there is not enough material to produce a water molecules
			try {
				wait();		// throws InterruptedException
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		waitingHydrogen -= 2;		
		waitingOxygen -= 1;		
		System.out.println("The " + index + "th water molecule was formed.");
		notifyAll();		// tell threads to enter runnable state
 	}
}
