package vehicles;

public class SolarEngine extends Engine {
	/**
	 * Constructor
	 * 
	 * @param fuel
	 * @param tank
	 */
	public SolarEngine(int fuel, int tank) {
		super(fuel = 1, tank);
	}

	/**
	 * returns a textual representation of an solarengine
	 */
	public String toString() {
		return super.toString() + "Solar Engine";
	}
}
