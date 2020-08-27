/**
 * Author : Bar Ilan Kimbarovski 205618457 , Shay Manasherov 311332597
 * Hw 1
 */

package vehicles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import graphics.CityPanel;

public class Car extends HasEngine {
	private int numPassengers;
	private final static int seat = 5;
	private final static int speed = 4;

	/**
	 * Constructor
	 */
	public Car(CityPanel pan, Location l, int size, int id, int numWheels, float km, boolean Lights, Color col,
			int fuelConsumption, Engine engine, int fuel, int MinAge, int numPassengers) {
		super(pan, l, size, id, numWheels = 4, km, Lights, col, fuelConsumption, engine, fuel = 40);
		this.numPassengers = numPassengers;
		setDurability(3);
	}


	/**
	 * Overrides the drive method return true drive workss
	 */
	@Override
	public boolean drive(Point point) {
		float Km = Math.abs(point.getX() - this.getLocation().getPoint().getX())
				+ Math.abs(point.getY() - this.getLocation().getPoint().getY());

		if (Km == 0)
			return false;
		else {
			float fuelUsed = Km * this.getEngine().getFuel();
			if (fuelUsed < this.getCFuel()) {
				System.out.println("not enough fuel to drive to the destination\n");
				return false;
			}
			this.getLocation().setLocationP(point);
			this.setKm(getKm() + Km);
			this.setCFuel(this.getCFuel() - (this.getEngine().getFuel() * Km));
			
			return true;
		}
	}

	/**
	 * returns a textual representation of car
	 */
	public String toString() {
		return super.toString() + "The numPassengers is:" + this.numPassengers + "\n";
	}

	@Override
	public String getVehicleName() {
		if (this.getEngine() instanceof SolarEngine)
			return "Solar car";
		else if (this.getEngine() instanceof BenzineEngine)
			return "Benzine car";
		return null;
	}

	public boolean canMove() {
		if (this.getCFuel() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int getSpeed() {
		return this.speed;
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
//		String vehicleName = getVehicleName();
//		if (vehicleName.compareTo("Benzine car") == 0 || vehicleName.compareTo("Solar Car") == 0)
			// Loading North side
			try {
				img1 = ImageIO.read(new File(PICTURE_PATH + getColor() +  "CarNorth.png"));
			} catch (IOException e) {
				System.out.println("Cannot load image1" + getVehicleName());
			}
		// Loading South side
		try {
			img2 = ImageIO.read(new File(PICTURE_PATH + getColor() +"CarSouth.png"));
		} catch (IOException e) {
			System.out.println("Cannot load image2" + getVehicleName());
		}
		// Loading East side
		try {
			img3 = ImageIO.read(new File(PICTURE_PATH + getColor() +  "CarEast.png"));
		} catch (IOException e) {
			System.out.println("Cannot load image3" + getVehicleName());
		}
		// Loading West side
		try {
			img4 = ImageIO.read(new File(PICTURE_PATH + getColor() +"CarWest.png"));
		} catch (IOException e) {
			System.out.println("Cannot load image4" + getVehicleName());
		}
	}

	@Override
	public boolean refuleVehicles() {
		this.Refuel();
		return false;
	}

	@Override
	public int getCFuel() {
		// TODO Auto-generated method stub
		return CurrentFuel();
	}



}
