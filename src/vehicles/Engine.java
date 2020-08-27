package vehicles;

public abstract class Engine implements Cloneable {
	private int fuel;
	private int tank;

	/**
	 * Constructor
	 * 
	 * @param fuel
	 * @param tank
	 */
	public Engine(int fuel, int tank) {
		this.setFuel(fuel);
		this.tank = 40;
	}

	/**
	 * Getting the tank volume
	 * 
	 * @return tank
	 */
	public int getTank() {
		return this.tank;
	}

	/**
	 * Getting the fuel
	 * 
	 * @return fuel
	 */
	public int getFuel() {
		return this.fuel;
	}

	/**
	 * Set Fuel
	 * 
	 * @param fuel
	 */
	public void setFuel(int fuel) {
		this.fuel = fuel;
	}

	/**
	 * returns a textual representation of engine
	 */
	public String toString() {
		return super.toString() + "The fuel per km is:" + this.getFuel() + "\n" + "The volume of tank is:" + this.tank
				+ "\n";
	}
}
