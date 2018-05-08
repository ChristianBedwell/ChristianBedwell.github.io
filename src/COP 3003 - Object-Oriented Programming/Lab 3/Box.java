import java.util.Scanner;

public class Box implements Package { // implements both cost and input
	double weight;
	double boxCost;
	
	public double cost() {
		 return boxCost = 1.2*weight;
	}
	public void input(Scanner scanner) {		
		System.out.print("Please input the weight for the box (lbs) : ");
		weight = scanner.nextDouble();
	}
}
