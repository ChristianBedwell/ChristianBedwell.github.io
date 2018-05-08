public class Hydrogen implements Runnable {
	ReactionArea buff;
	
	public Hydrogen(ReactionArea buff) {	// Hydrogen constructor
		this.buff = buff;
	}	// end constructor
	
	public void run() {
		for(int i = 0; i < 20; i++) {	// add one hydrogen atom once every 100 ms
			try {
				buff.increWHydrogen(i);
				Thread.sleep(100);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
				System.exit(1);			
			}
		}		
	}
}
