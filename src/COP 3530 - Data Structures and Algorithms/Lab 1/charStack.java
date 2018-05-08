class charStack {
	private final int STACKSIZE = 100;
	private int top;
	private char [] items;
	
	public charStack() {
	 	items = new char [STACKSIZE];
	 	top = -1; 
	} // end constructor
	
	// if the top of the stack is -1, then the stack is empty
	public boolean empty() { 
	  	if(top == -1) {
	 		return true;
	  	}
	  	else {
	 		return false;
	  	}
	} // end empty
	
	public char pop() {
		// if the stack is empty, than an underflow has occurred
	 	if(empty()) {
	 		System.out.println("Stack Underflow");
	 		System.exit(1);
	 	}
	 	return items[top--];
	} // end pop

	public void push(char x) {
		// if the stack is full, than an overflow has occurred
	 	if(top == STACKSIZE + 1) {
	 		System.out.println("Stack Overflow");
	 		System.exit(1);
	 	}
	  	items[++top] = x;    
	} // end push
	
	public char peek() {
		// if the stack is empty, than an underflow has occurred
	 	if(empty()) {
	 		System.out.println("Stack Underflow");
	 		System.exit(1);
	 	}
	 	return items[top];
	} // end peek	
}

