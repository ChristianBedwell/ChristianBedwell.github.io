public class GradStudent extends Student {
	private String researchTopic; // research topic
	private String advisor; // advisor
	
	// GradStudent constructor
	public GradStudent() {
		readData();
	}
	
	private void readData() { // reads researchTopic and advisor from the console
		if(gender == 'm') {
			System.out.print("Please input his research topic: ");
			researchTopic = Utilities.scanner.nextLine();
			System.out.print("Please input his research advisor: ");
			advisor = Utilities.scanner.nextLine();
		}
		else {
			System.out.print("Please input her research topic: ");
			researchTopic = Utilities.scanner.nextLine();
			System.out.print("Please input her research advisor: ");
			advisor = Utilities.scanner.nextLine();
		}
	}
	
	public void print() { // prints researchTopic and advisor
		super.print();
		if(gender == 'm') {
			System.out.println();
			System.out.println("His research topic is " + researchTopic + ".");
			System.out.println("His advisor is " + advisor + ".");
		}
		
		else {
			System.out.println();
			System.out.println("Her research topic is " + researchTopic + ".");
			System.out.println("Her advisor is " + advisor + ".");
		}
	}
}