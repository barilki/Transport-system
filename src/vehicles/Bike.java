/**
 * Author : Bar Ilan Kimbarovski 205618457 , Shay Manasherov 311332597
 * Hw 1
 */

package vehicles;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import graphics.CityPanel;

public class Bike extends Vehicle {
	private final static int speed = 2;
	private int gear;
	private final static int seat = 1;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param col
	 * @param numWheels
	 * @param l
	 * @param km
	 * @param Lights
	 * @param speed
	 * @param gear
	 */
	public Bike(Location l, int size, int id, int numWheels, float km, boolean Lights, Color col, int fuelConsumption,
			CityPanel pan, int gear) {
		super(l, size, id, numWheels = 4, km, Lights, col, fuelConsumption = 0, pan);
		this.gear = gear;
		setDurability(1);
	}

	/**
	 * 
	 * @return gear num
	 */
	public int getGear() {
		return gear;
	}

	public int getSpeed() {
		return speed;
	}

	@Override
	public boolean canMove() {
		return true;
	}

	/**
	 * returns a textual representation of bike
	 */
	public String toString() {
		return super.toString() + "The speed is:" + getSpeed() + "\n" + "The gear is:" + getGear() + "\n";
	}
	


	@Override
	public String getVehicleName() {
		// TODO Auto-generated method stub
		return "Bike";
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
			img1 = ImageIO.read(new File(PICTURE_PATH + getColor() + "BikeNorth.png"));
		} catch (IOException e) {
			System.out.println("Cannot load image1" + getVehicleName());
		}
		// Loading South side
		try {
			img2 = ImageIO.read(new File(PICTURE_PATH + getColor() + "BikeSouth.png"));
		} catch (IOException e) {
			System.out.println("Cannot load image2" + getVehicleName());
		}
		// Loading East side
		try {
			img3 = ImageIO.read(new File(PICTURE_PATH + getColor() + "BikeEast.png"));
		} catch (IOException e) {
			System.out.println("Cannot load image3" + getVehicleName());
		}
		// Loading West side
		try {
			img4 = ImageIO.read(new File(PICTURE_PATH + getColor() + "BikeWest.png"));
		} catch (IOException e) {
			System.out.println("Cannot load image4" + getVehicleName());
		}
	}

	@Override
	public boolean refuleVehicles() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCFuel() {
		// TODO Auto-generated method stub
		return 1;
	}

}
