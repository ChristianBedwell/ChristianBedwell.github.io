public class GradTA extends GradStudent {
	private String taCourse; // TA Course
	private String taInstructor; // TA Instructor
	private Date hireDate; // hire date
	
	// GradTA constructor
	public GradTA() {
		readData();
	}
	
	private void readData() { // reads taCourse, taInstructor, and hireDate from the console
		if(gender == 'm') {
			System.out.print("Please input his TA course: ");
			taCourse = Utilities.scanner.nextLine();
			System.out.print("Please input his TA instructor: ");
			taInstructor = Utilities.scanner.nextLine();
			System.out.println("Please input his hire date: ");
			hireDate = new Date(); // create new date object
		}
		else {
			System.out.print("Please input her TA course: ");
			taCourse = Utilities.scanner.nextLine();
			System.out.print("Please input her TA instructor: ");
			taInstructor = Utilities.scanner.nextLine();
			System.out.println("Please input her hire date: ");
			hireDate = new Date(); // create new date object
		}
	}	
	
	public void print() { // prints taCourse, taInstructor, and hireDate
		super.print();
		if(gender == 'm') {
			System.out.println("His TA course is " + taCourse + ".");
			System.out.println("His TA instructor is " + taInstructor + ".");
			System.out.print("His hire date is " );
			hireDate.print();
		}
		
		else {
			System.out.println("Her TA course is " + taCourse + ".");
			System.out.println("Her TA instructor is " + taInstructor + ".");
			System.out.print("Her hire date is " );
			hireDate.print();
		}
	}	
}