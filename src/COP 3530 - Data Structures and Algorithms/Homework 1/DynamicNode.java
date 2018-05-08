
public class DynamicNode {
	Object info;
	DynamicNode next;

	// DynamicNode constructor
	public DynamicNode(Object x, DynamicNode n) {
		info = x;
		next = n;
	}

	// getter for info
	public Object getInfo() {
		return info;
	}
	
	// getter for next
	public DynamicNode getNext() {
		return next;
	}
	
	// setter for info
	public void setInfo(Object x) {
		info = x;
	}
	
	// setter for next
	public void setNext(DynamicNode n) {
		next = n;
	}
}
