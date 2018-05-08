public class Date {
	private int month;
	private int day;
	private int year;
	
	public Date() { // date constructor
		readData();
	}

	private void readData() { // reads month, day, and year from the console
		System.out.print("	Please input the month: ");
		month = Utilities.scanner.nextInt();
		System.out.print("	Please input the day: ");
		day = Utilities.scanner.nextInt();
		System.out.print("	Please input the year: ");
		year = Utilities.scanner.nextInt();
		Utilities.scanner.nextLine();
	}
	
	public void print() { // prints the date 
			System.out.print(month + "/" + day + "/" + year);
	}
}	
