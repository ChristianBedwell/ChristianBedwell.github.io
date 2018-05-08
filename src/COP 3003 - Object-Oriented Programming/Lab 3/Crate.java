import java.util.Scanner;

public abstract class Crate implements Package { // defines only input
	double weight;
	double metalCrateCost;
	double woodCrateCost;

	public void input (Scanner scanner) {
		System.out.print("Please input the weight for the " + this.getClass().getSimpleName() +  " (lbs) : ");
		weight = scanner.nextDouble();
	} 
}