public class MetalCrate extends Crate { // implements only cost
		
	public double cost() {
		return metalCrateCost = 1.3*weight;
	}
}
