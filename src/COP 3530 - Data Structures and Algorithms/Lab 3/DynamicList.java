
public class DynamicList {
	DynamicNode front;
	public DynamicList() {
		front = null;
	}
	
	// for the case that a list is empty
	public boolean isEmpty() {
		return front == null;
	}
	
	// inserts an element to the front of the list
	public void insertFirst(Object x) {
		DynamicNode q = new DynamicNode(x, null);
		if(!isEmpty()) 
			q.setNext(front);
			front = q;		
	}
	
	// traverses and prints each element of the list
	public void printList() {
		DynamicNode p;
		for(p = front; p != null; p = p.getNext()) {
			System.out.print(p.getInfo() + " ");
		}
	}

	// Searches the list for a node equal to parameter x, and 
	// returns the list node containing x, if x is found.
	// If x is not found in the list, it inserts x in the list
	// and returns the node containing x.
	public DynamicNode searchInsert(Object x) {
		DynamicNode p;
		if(isEmpty()) {
			front = new DynamicNode(x, null);
			return front;
		}
		for(p = front; p != null; p = p.getNext()) {
			if(p.getInfo() == x) {
				return p;
			}
			if(p.getNext() == null) {
				DynamicNode q = new DynamicNode(x, p.getNext());
				p.setNext(q);
				return p;
			}
		}
		return p;		
	}
	
	// reverse the nodes of a list
	public void reverse() {
		DynamicNode rear = null;
		DynamicNode current = front;
		if(isEmpty()) {
			System.out.println("Error: List is empty.");
		}	
		while(current != null) {
			DynamicNode next = current.next;
			current.next = rear;
			rear = current;
			current = next;
		}
		front = rear;			
	}
	
	// Appends all elements in the parameter list othrList 
	// to the end of this list.
	// Returns true if the list was changed, false otherwise.
	// Please note that NO new list is created. 
	// Also, it is wrong to (repeatedly) insert new nodes to the list. 
	public boolean addAllElements(DynamicList othrList) {
		DynamicNode p;
		DynamicNode q;
		q = othrList.front;
		// loop through the first list
		for(p = front; p != null; p = p.getNext()) {
			// if the list front is equal to null,
			// set the pointer to the front of the other list
			if(p.getNext() == null) {
				p.setNext(q);
				return true;
			}
		}
		// returns false if no changes were made
		return false;
	}	

	// Deletes the middle node in the list IF it exists.
	// If the list has an EVEN number of nodes, there is NO middle node.
	// Constraint: you cannot use a counter or a boolean.
	// Given a list a-->b-->c-->d-->e, c is deleted
	// Given a list a-->b-->c-->d, nothing is deleted.	 
	// Returns: info in the middle node if it exists; null otherwise
	public Object deleteMid() {
		DynamicNode p = front;
		DynamicNode q = front;
		DynamicNode r = null;
		if(isEmpty()) {
			// return null if the list is empty
			System.out.print("Error: The list is empty");
			return null;
		}
		
		while(q != null && q.getNext() != null) {
			q = q.next.next;	// increment q by intervals of two
			r = p;
			p = p.next;	// increment p by intervals of one
		}
		
		if(q == null) {
			// return null if list is even, since mid doesn't exist
			System.out.print("Error: The list is even, no middle node to remove.");
			return null;		
		}
		// else, return the info in the middle node
		r.next = p.next;
		DynamicNode next = p.next.next;
		p.next = next;
		return p.info;
	}
}
