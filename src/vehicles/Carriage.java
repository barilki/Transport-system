package vehicles;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import graphics.CityPanel;

public class Carriage extends Vehicle {
	private PackAnimal animal;
	private final static int speed = 1;
	private final static int seat=2;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param color
	 * @param numWheels
	 * @param l
	 * @param km
	 * @param Lights
	 * @param speed
	 * @param Color
	 */
	public Carriage(CityPanel pan,Location l,int size,int id,int numWheels,float km,boolean Lights,Color col,int fuelConsumption,Engine engine,PackAnimal animal) {
		super(l,size,id,numWheels=4,km,Lights,col,fuelConsumption,pan);
		this.animal = animal;
		setDurability(2);
	}


	public int getSpeed() {
		return speed;
	}
	
	public boolean canMove() {
		if (animal.getCurrentEnergy() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * returns a textual representation of carriage
	 */
	public String toString() {
		return super.toString() + "The speed is:" + getSpeed() + "\n" + "The animal is: " + this.animal + "\n";
	}

	@Override
	public String getVehicleName() {
		return "Carriage";
	}

	@Override
	public int getFuelConsumption() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	/**
	 * Loading the images
	 */
	public void loadImages() {
			// Loading North side
			try {
				img1 = ImageIO.read(new File(PICTURE_PATH + getColor() +  "CarriageNorth.png"));
			} catch (IOException e) {
				System.out.println("Cannot load image1" + getVehicleName());
			}
		// Loading South side
		try {
			img2 = ImageIO.read(new File(PICTURE_PATH + getColor() +"CarriageSouth.png"));
		} catch (IOException e) {
			System.out.println("Cannot load image2" + getVehicleName());
		}
		// Loading East side
		try {
			img3 = ImageIO.read(new File(PICTURE_PATH + getColor() +  "CarriageEast.png"));
		} catch (IOException e) {
			System.out.println("Cannot load image3" + getVehicleName());
		}
		// Loading West side
		try {
			img4 = ImageIO.read(new File(PICTURE_PATH + getColor() +"CarriageWest.png"));
		} catch (IOException e) {
			System.out.println("Cannot load image4" + getVehicleName());
		}
	}

	@Override
	public boolean refuleVehicles() {
		animal.eat();
		return false;
	}

	@Override
	public int getCFuel() {
		// TODO Auto-generated method stub
		return animal.getCurrentEnergy();
	}



}
