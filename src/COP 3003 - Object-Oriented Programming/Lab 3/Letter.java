import java.util.Scanner;

public class Letter implements Package { // implements both cost and input
	double numOfPages;
	double letterCost;
	
	public double cost() {
		 return letterCost = (5*numOfPages)/100;
	}
	public void input(Scanner scanner) {
		System.out.print("Please input the number of pages in the letter: ");
		numOfPages = scanner.nextDouble();
	}
}
