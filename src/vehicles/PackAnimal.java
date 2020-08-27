package vehicles;

import graphics.*;

/**
 * 
 * @author elect
 *
 */
public class PackAnimal implements IAnimal, Cloneable {
	private String AnimalName;
	private final int MaxEnergy = 1000;
	private int CurrentEnergy;

	public PackAnimal(String AnimalName, int CurrentEnergy) {
		this.AnimalName = AnimalName;
		this.CurrentEnergy = CurrentEnergy;
	}

	/**
	 * Function update the Current energy level to maximum when animal eats
	 * 
	 * @return boolean true
	 */
	public boolean eat() {
		this.CurrentEnergy = MaxEnergy;
		return true;
	}

	public int getCurrentEnergy() {
		return CurrentEnergy;
	}

	@Override
	public String getVehicleName() {
		// TODO Auto-generated method stub
		return "Pack animal";
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFuelConsumption() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAnimalName() {
		return null;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	public void setEnergy(int x) {
		this.CurrentEnergy = x;
	}

	@Override
	public int getDurability() {
		// TODO Auto-generated method stub
		return 0;
	}

	public PackAnimal getAnimal() {
		// TODO Auto-generated method stub
		return null;
	}

}
