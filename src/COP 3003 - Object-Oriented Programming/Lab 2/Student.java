public class Student extends Object {
	private String name; // name
	private int ssn; // social security number
	private int numOfCourses; // number of courses taken
	private Date birthDate; // date of birth
	protected char gender; // male or female
	
	public Student() { // Student constructor
		readData();
	}
	
	private void readData() { // reads name, ssn, numOfCourses, birthDate, and gender from the console
		System.out.print("Please input the name: ");
		name = Utilities.scanner.nextLine();
		System.out.print("Please input the ssn: ");
		ssn = Utilities.scanner.nextInt();
		System.out.print("Male/Female (m/f) : ");
	    gender = Utilities.scanner.next().charAt(0);
	    
		if(gender == 'm') { // user input only accepts 'm' or 'f'
			System.out.print("How many courses he is taking: ");
			numOfCourses = Utilities.scanner.nextInt();
			System.out.println("Please input his birth date: ");
			birthDate = new Date(); // create new date object
		}
		
		else if(gender == 'f') {
			System.out.print("How many courses she is taking: ");
			numOfCourses = Utilities.scanner.nextInt();
			System.out.println("Please input her birth date: ");
			birthDate = new Date(); // create new date object
		}
		
		else { // if user input is invalid, exit the program
			System.exit(0);
		}
	}
	
	public void print() { // prints name, ssn, numOfCourses, and birthDate
		if(gender == 'm') {
			System.out.println();
			System.out.println("The information you input was");
			System.out.println(name + "'s ssn is " + ssn + ".");
			System.out.println("He is taking " + numOfCourses + " courses.");
			System.out.print("His birth date is " );
			birthDate.print();
		}
		
		else {
			System.out.println();
			System.out.println("The information you input was");
			System.out.println(name + "'s ssn is " + ssn + ".");
			System.out.println("She is taking " + numOfCourses + " courses.");
			System.out.print("Her birth date is " );
			birthDate.print();
		}		
	}
}







