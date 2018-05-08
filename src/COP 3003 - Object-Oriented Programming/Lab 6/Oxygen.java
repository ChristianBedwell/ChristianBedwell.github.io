public class Oxygen implements Runnable{
	ReactionArea buff;
	
	public Oxygen(ReactionArea buff) {	// Oxygen constructor
		this.buff = buff;
	}	// end constructor
	
	public void run() {
		for(int i = 0; i < 10; i++) {	// add one oxygen atom once every 200 ms
			try {
				buff.increWOxygen(i);
				Thread.sleep(200);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
				System.exit(1);			
			}
		}		
	}
}

