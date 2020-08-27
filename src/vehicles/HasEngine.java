package vehicles;
import graphics.CityPanel;

public abstract class HasEngine extends Vehicle {
	private Engine engine;
	private float currentFuel;

	/**
	 * Constructor
	 */
	public HasEngine(CityPanel pan,Location l,int size,int id,int numWheels,float km,boolean Lights,Color col,int fuelConsumption,Engine engine, int fuel) {
		super(l,size,id,numWheels,km,Lights,col,fuelConsumption,pan);
		this.engine = engine;
		this.currentFuel = fuel;
	}
	
	
	public int CurrentFuel() {
		return (int) currentFuel;
	}
	/**
	 * Checking the fuel, if the fuel is full will update to max fuel, else change
	 * to fuel
	 * 
	 * @return true/false
	 */
	public boolean Refuel() {
		if (this.currentFuel == engine.getTank())
			return false;
		else {
			this.currentFuel = engine.getTank();
			return true;
		}
	}


	/**
	 * Setter to fuel
	 * 
	 * @param fuel
	 * @return true if set function work
	 */
	public boolean setCFuel(float fuel) {
		this.currentFuel = fuel;
		return true;
	}

	public Engine getEngine() {
		return this.engine;
	}
	
	

	/**
	 * returns a textual representation of HasEngine
	 */
	public String toString() {
		return super.toString() + "The fuel is:" + getCFuel() + "\n" + "The minumun age is:" + this.minAge + "\n"
				+ "The tank is: " + getEngine().getTank() + "\n";
	}
}
