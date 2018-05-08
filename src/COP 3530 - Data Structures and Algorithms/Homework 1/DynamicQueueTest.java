import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class DynamicQueueTest {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = 4;	// length of the array
		int QUEUESIZE = 4;	// max number of elements in a queue
		DynamicQueue[] queues = new DynamicQueue[N];  // declare an array of N DynamicQueues
		for(int i = 0; i < N; i++) {
			queues[i] = new DynamicQueue();
		}
		
		try {
			String line;
			char key = 0;
			int index = 0;
			
			// read the keys and indexes from the file
			File infile = new File("data.txt");
			BufferedReader reader = new BufferedReader(new FileReader(infile));
			
			while((line = reader.readLine()) != null) {	// while there is a line to read
				StringTokenizer tokenizer = new StringTokenizer(line, "[ .,';!(){]");
				while(tokenizer.hasMoreTokens()) {					
					String word = tokenizer.nextToken();
					if(word.matches("[A-Za-z]")) {
						key = word.charAt(0);
					}
					else if(word.matches("^[0-9]")) {
						index = Integer.parseInt(word);
					}
				}
				System.out.print("Read key " + key + " for queue " + index + ". ");
				
				// if key does not exist in the queue
				if(queues[index].contains(key) == false) {
					// if the queue is not full
					if(queues[index].getSize() != QUEUESIZE - 1) {
						// insert new node with key in rear of queue
						queues[index].insert(key);
						System.out.print("Inserting " + key + " in rear. ");
						System.out.print("Q" + index + ": ");
						queues[index].printList();
					}
					// if the queue is full, or contains QUEUESIZE elements
					else if(queues[index].getSize() == QUEUESIZE - 1){
						// remove front element of the queue
						queues[index].remove();
						System.out.print("Q is full, removing front. ");
						// insert new node with key in rear
						queues[index].insert(key);
						System.out.print("Inserting " + key + " in rear. ");
						System.out.print("Q" + index + ": ");
						queues[index].printList();
					}
				}
				// if key already exists in the queue
				else {
					// move the node with key to the rear
					queues[index].moveFrontToBack(key);
					System.out.print("Q" + index + ": ");
					queues[index].printList();
				}
			}
			System.out.print("\n");
			System.out.print("..Final Queues..\n");
			for(int i = 0; i < N; i++) {
				System.out.print("Q" + i + ": ");
				queues[i].printList();
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}