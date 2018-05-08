public class Reactor implements Runnable{
	ReactionArea buff;
	
	public Reactor(ReactionArea buff) {	// Reactor constructor
		this.buff = buff;
	}	// end constructor
	
	public void run() {		
		try {
			Thread.sleep(2000);		// the reactor sleeps for 2 seconds once it starts
		} 
		catch (InterruptedException e1) {			
			e1.printStackTrace();
		}
		
		for(int i = 0; i < 10; i++) {	// take 2 hydrogen atoms and 1 oxygen atom every 50 ms
			try {
				buff.react(i);
				Thread.sleep(50);	
			} 
			catch(InterruptedException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}
