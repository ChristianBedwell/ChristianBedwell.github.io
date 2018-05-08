public class WoodCrate extends Crate { // implements only cost
	
	public double cost() {
		return woodCrateCost = 1.4*weight;
	}
}
