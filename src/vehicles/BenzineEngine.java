/**
 * Author : Bar Ilan Kimbarovski 205618457 , Shay Manasherov 311332597
 * Hw 1
 */

package vehicles;

public class BenzineEngine extends Engine {
	/**
	 * Constructor
	 * 
	 * @param fuel
	 * @param tank
	 */
	public BenzineEngine(int fuel, int tank) {
		super(fuel = 2, tank);
	}

	/**
	 * returns a textual representation of an benzineengine
	 */
	public String toString() {
		return super.toString() + "Benzine Engine";
	}
}
