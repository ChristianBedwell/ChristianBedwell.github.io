
public class DynamicQueue {
	private DynamicNode front, rear;
	
	// DynamicQueue constructor
	public DynamicQueue() {
		front = rear = null;
	}
	
	// checks for an empty queue
	public boolean empty() {
		return (front == null);
	}
	
	// inserts a new node at the end of the queue
	public void insert(Object x) {
	     DynamicNode p = new DynamicNode(x, null);

	     if(empty()) {
	    	 front = rear = p;
	     }
	     else {
	    	 rear.setNext(p); 	   
	     	 rear = p;
	     }
	}
	
	// removes the first element of the queue
	public Object remove() {
	     if(empty()){
	    	 System.out.println("Queue underflow");
	    	 System.exit(1);
	     }
	     DynamicNode p = front;
	     Object temp = p.getInfo();
	     front = p.getNext();
	   
	     if(front == null) {
	    	 rear = null;
	     }
	    	
	     	return temp;
	}
	
	// traverses and prints all elements of the queue
	public void printList() {
		if(empty()) {
			System.out.print("Empty");
		}
		DynamicNode p;
		for(p = front; p != null; p = p.getNext()) {
			System.out.print(p.getInfo());
			if(p.getNext() != null) {
				System.out.print("->");
			}
		}
		System.out.print("\n");
	}

	// checks to see if the queue contains a node with the key
	public boolean contains(Object x) {
		if(empty()) {
			return false;
		}
		DynamicNode p;
		for(p = front; p != null; p = p.getNext()) {
			if(p.getInfo() == x) {
				return true;
			}
		}
		return false;
	}

	// returns the length of the queue
	public int getSize() {
		int size = 0;
		if(empty()) {
			size = 0;
			return size;
		}
		DynamicNode current = front;
		while(current.next != null) {
			current = current.next;
			size++;     
		}
		return size;
	}
	
	// move the front node to the rear by manipulating pointers
	public DynamicNode moveFrontToBack(Object x) {
		DynamicNode p;
		DynamicNode q = front.getNext();
		DynamicNode r = front.getNext().getNext();
		// traverse the queue to find the key
		for(p = front; p != null; p = p.getNext()) {
			// if key is found in queue
			if(p.getInfo() == x) {
				// if the key is found at the rear of the queue
				if(p.getNext() == null) {
					System.out.print(x + " is already in rear. ");
				}
				// if key is in front of the queue, move it to rear
				else if(front.getInfo() == x) {
					rear.setNext(p);
					p.setNext(null);
					front = q;
					System.out.print("Moving " + x + " to rear. ");
				}
				// if key is found between the front and rear of the queue ???
				else {
					rear.setNext(p);
					p.setNext(null);
					front = r;
					System.out.print("Moving " + x + " to rear. ");
				}
			}
		}		
		return p;	
	}
}
	

