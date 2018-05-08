// Testing the reaction between hydrogen and oxygen
public class ReactionTest {
	
	public static void main(String[] args) {
		ReactionArea reactionArea = new ReactionArea();
		Thread oxygen = new Thread(new Oxygen(reactionArea));
		Thread hydrogen = new Thread(new Hydrogen(reactionArea));		// create each process
		Thread reactor = new Thread(new Reactor(reactionArea));
		oxygen.start();
		hydrogen.start();		// start each process						
		reactor.start();		
	}
}
