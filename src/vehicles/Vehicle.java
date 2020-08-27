/**
 * Hw 1
 */

package vehicles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import graphics.CityPanel;
import graphics.IDrawable;
import graphics.IMoveable;

public abstract class Vehicle implements IMoveable, IDrawable, Cloneable ,Runnable {
	protected int id = 999;
	protected Location location;
	protected int size = 65;
	protected int numWheels;
	protected float km;
	protected boolean Lights;
	protected Color col;
	protected int fuelConsumption = 0;
	protected CityPanel pan;
	protected BufferedImage img1, img2, img3, img4;
	protected final static int minAge = 18;
	private String threadName;
	private Rectangle rec = new Rectangle();
	private int Durability;
	private int Collisioninfo =0;

	// protected long Carid;
	/**
	 * 
	 * Constructor
	 * 
	 * @param id
	 * @param numWheels
	 * @param l
	 * @param km
	 * @param Lights
	 * @param Color
	 */
	public Vehicle(Location l, int size, int id, int numWheels, float km, boolean Lights, Color col,
			int fuelConsumption, CityPanel pan) {
		this.id = id;
		this.size = size;
		this.pan = pan;
		this.fuelConsumption = fuelConsumption;
		this.setNumWheels(numWheels);
		this.location = l;
		this.setKm(0);
		this.Lights = LightsCheck();
		this.col = col;

	}
	
	public void run() {
		while (!Thread.interrupted()) {				
	      try {
					if(this != null)
					{
					Thread.sleep(100/this.getSpeed());
				if(this.getCFuel()==0)
					synchronized (this) {
						wait();
					}
						
					}
					this.move();
	         // Let the thread sleep for a while.
	      } catch (InterruptedException e) {
	 
	    	  break;
	      }

	   }
}
	
	
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @param point
	 * @return
	 */
	public boolean drive(Point point) {
		float Km = Math.abs(point.getX() - this.getLocation().getPoint().getX())
				+ Math.abs(point.getY() - this.getLocation().getPoint().getY());

		if (Km == 0)
			return false;
		else {
			this.getLocation().setLocationP(point);
			this.setKm(getKm() + Km);
			this.fuelConsumption += getCFuel();
			return true;
		}
	}

	/**
	 * get number of wheels
	 * 
	 * @return number of wheels
	 */
	public int getNumWheels() {
		return numWheels;
	}

	/**
	 * set number wheels
	 * 
	 * @param numWheels
	 * @return true
	 */
	public boolean setNumWheels(int numWheels) {
		this.numWheels = numWheels;
		return true;
	}

	/**
	 * getter to location
	 * 
	 * @return location object
	 */
	public Location getLocation() {
		return this.location;
	}

	/**
	 * 
	 * @return km
	 */
	public float getKm() {
		return km;
	}

	/**
	 * setter to km
	 * 
	 * @param km return true
	 */
	public boolean setKm(float km) {
		this.km = km;
		return true;
	}

	/**
	 * 
	 * @param string of color
	 * @return the color
	 */
	public Color getColor(String s) {
		switch (s) {
		case "red":
			return Color.Red;
		case "green":
			return Color.Green;
		case "white":
			return Color.White;
		case "silver":
			return Color.Silver;
		default:
			return Color.White;
		}
	}

	/**
	 * setter for color
	 * 
	 * @param s
	 * @return true
	 */
	public boolean setColor(String s) {
		this.col = getColor(s);
		return true;
	}

	public String getColor() {
		return col.name();
	}

	public abstract String getVehicleName();

	public int getNumCar() {
		return this.id;
	}

//	public boolean LightsCheck() {
//		if (this.Lights == true) {
//			this.Lights = false;
//			return false;
//		} else
//			this.Lights = true;
//		return true;
//	}

	public boolean LightsCheck() {
		if (this.Lights) {
			this.Lights = false;
			return true;
		} 
		else {
			this.Lights = true;
			return false;
		}
	}

	public int getFuelConsumption() {
		return 0;
	}

	/**
	 * returns a textual representation of Vehicle
	 */
	public String toString() {
		return "The car id is: " + this.id + "\n" + "The color is: " + getColor() + "\n" + "The km is: " + this.getKm()
				+ "\n" + "The locatoin is: " + this.location + "\n" + "The lights are: " + this.Lights + "\n"
				+ "The numwheels is: " + this.numWheels + "\n";
	}

	/**
	 * Clone function ,deep copy return copy of vehicle
	 */
	public Object clone() {
		try {
			// call Object.clone()

			Vehicle cloned = (Vehicle) super.clone();

			// clone mutable fields
			cloned.location = (Location) super.clone();
			cloned.col = (Color) super.clone();
			cloned.pan = (CityPanel) super.clone();
			cloned.img1 = (BufferedImage) super.clone();
			cloned.img2 = (BufferedImage) super.clone();
			cloned.img3 = (BufferedImage) super.clone();
			cloned.img4 = (BufferedImage) super.clone();
			return cloned;
		} catch (CloneNotSupportedException e) {
			return null;
		}
		// this won't happen, since we are Cloneable
	}

	public void drawObject(Graphics g) {
		if (location.getCord() == Cordinat.NORTH) // drives to right side
			g.drawImage(img1, location.getPoint().getX(), location.getPoint().getY(), size, size * 2, pan);
		rec.setBounds(location.getPoint().getX(), location.getPoint().getY(), size, size * 2);
		if (location.getCord() == Cordinat.SOUTH) {// drives to the south side
			g.drawImage(img2, location.getPoint().getX(), location.getPoint().getY(), size, size * 2, pan);
			rec.setBounds(location.getPoint().getX(), location.getPoint().getY(), size, size * 2);
		} else if (location.getCord() == Cordinat.EAST) { // drives to the east side
			g.drawImage(img3, location.getPoint().getX(), location.getPoint().getY(), size * 2, size, pan);
			rec.setBounds(location.getPoint().getX(), location.getPoint().getY(), size * 2, size);
		} else if (location.getCord() == Cordinat.WEST) // drives to the west side
			g.drawImage(img4, location.getPoint().getX(), location.getPoint().getY(), size * 2, size, pan);
		rec.setBounds(location.getPoint().getX(), location.getPoint().getY(), size * 2, size);
	}

	public abstract boolean canMove();

	public void move() {
		if (canMove()) {
			this.drive(nextLocation());
			pan.repaint();
		}
	}

//	public Point nextLocation() {
//		Point p = new Point(location.getPoint());
//		p.setX(p.getX() + this.getSpeed());
//		if (location.getCord() == Cordinat.NORTH) {
//			p.setY(location.getPoint().getY() + this.getSpeed());
//
//			if (this.location.getPoint().getX() < 800) {
//				p.setX(location.getPoint().getX() + this.getSpeed());
//			}
//
//		}
//
//		return p;
//
//	}

	public Point nextLocation() {
		Random rand = new Random();
		int x = this.location.getPoint().getX(), y = this.location.getPoint().getY();
		switch (this.getVehicleName()) {
		case "Carriage":
			if (this.location.getPoint().getX() < 670 && this.location.getPoint().getY() == 0) // in the panel on the
																								// side of the road
			{
				if (this.location.getPoint().getY() == -10)
					this.location.getPoint().setY(0);
				if (this.location.getPoint().getX() == 659)
					this.location.getPoint().setX(660);
				location.setCordination(Cordinat.EAST);
				x = this.location.getPoint().getX() + getSpeed() * 10; // update the locationation ten meters per pixel
				y = this.location.getPoint().getY();
			}
			if (this.location.getPoint().getX() == 670 && this.location.getPoint().getY() < 180) // in the panel on the
																									// side of the road
			{
				location.setCordination(Cordinat.SOUTH);
				x = this.location.getPoint().getX();
				y = this.location.getPoint().getY() + getSpeed() * 10; // update the locationation ten meters per pixel
			}
			if (this.location.getPoint().getX() == 670 && this.location.getPoint().getY() == 180
					&& this.location.getCord() == Cordinat.SOUTH) // in the cross section, chose random way
			{
				boolean bool = rand.nextBoolean();
				if (bool == true) {
					location.setCordination(Cordinat.SOUTH);
					x = this.location.getPoint().getX();
					y = this.location.getPoint().getY() + getSpeed() * 10;
				}
				if (bool == false) {
					location.setCordination(Cordinat.WEST);
					x = this.location.getPoint().getX() - getSpeed() * 10;
					y = this.location.getPoint().getY();
				}
			}
			if (this.location.getPoint().getX() >= 0 && this.location.getPoint().getY() == 180
					&& this.location.getCord() == Cordinat.WEST) // in the turn
			{
				location.setCordination(Cordinat.WEST);
				x = this.location.getPoint().getX() - getSpeed() * 10;
				y = this.location.getPoint().getY();
			}
			if (this.location.getPoint().getY() >= 190 && this.location.getPoint().getY() < 450
					&& this.location.getPoint().getX() == 670 && this.location.getCord() == Cordinat.SOUTH) // in the
																											// turn
			{
				location.setCordination(Cordinat.SOUTH);
				x = this.location.getPoint().getX();
				y = this.location.getPoint().getY() + getSpeed() * 10;
			}
			if ((this.location.getPoint().getX() == 0 || this.location.getPoint().getX() == -1)
					&& this.location.getPoint().getY() > 0
					&& (this.location.getCord() == Cordinat.NORTH || this.location.getCord() == Cordinat.WEST)) {
				location.setCordination(Cordinat.NORTH);
				x = this.location.getPoint().getX();
				y = this.location.getPoint().getY() - getSpeed() * 10;
			}
			if (this.location.getPoint().getX() > 0 && this.location.getPoint().getY() == 450) {
				location.setCordination(Cordinat.WEST);
				if (this.location.getPoint().getX() == 40)
					this.location.getPoint().setX(39);
				x = this.location.getPoint().getX() - getSpeed() * 10;
				y = this.location.getPoint().getY();
			}
			if (this.location.getPoint().getX() == -1 && this.location.getPoint().getY() == 170
					&& this.location.getCord() == Cordinat.NORTH) {
				boolean bool = false;
				if (bool == true) {
					location.setCordination(Cordinat.NORTH);
					x = this.location.getPoint().getX();
					y = this.location.getPoint().getY() - getSpeed() * 10;
				}
				if (bool == false) {
					location.setCordination(Cordinat.EAST);
					x = this.location.getPoint().getX() + getSpeed() * 10;
					y = this.location.getPoint().getY();
				}
			}
			if (this.location.getPoint().getX() <= 680 && this.location.getPoint().getY() == 170
					&& location.getCord() == Cordinat.EAST) {
				location.setCordination(Cordinat.EAST);
				if (this.location.getPoint().getX() == 629)
					this.location.getPoint().setX(630);
				x = this.location.getPoint().getX() + getSpeed() * 10;
				y = this.location.getPoint().getY();
			}
			break;
		case "Benzine car":
		case "Solar car":
			if (this.location.getPoint().getX() <= 680
					&& (this.location.getPoint().getY() == 0 || this.location.getPoint().getY() == -10)) {
				if (this.location.getPoint().getY() == -10)
					this.location.getPoint().setY(0);
				if (this.location.getPoint().getX() == 679)
					this.location.getPoint().setX(680);
				location.setCordination(Cordinat.EAST);
				x = this.location.getPoint().getX() + getSpeed() * 10;
				y = this.location.getPoint().getY();
			}
			if (this.location.getPoint().getX() == 720 && this.location.getPoint().getY() < 180) {
				location.setCordination(Cordinat.SOUTH);
				x = this.location.getPoint().getX();
				y = this.location.getPoint().getY() + getSpeed() * 10;
			}
			if (this.location.getPoint().getX() == 720 && this.location.getPoint().getY() == 200
					&& this.location.getCord() == Cordinat.SOUTH) {
				if (this.location.getPoint().getY() == 200)
					this.location.getPoint().setY(230);
				boolean bool = rand.nextBoolean();
				if (bool == true) {
					location.setCordination(Cordinat.SOUTH);
					x = this.location.getPoint().getX();
					y = this.location.getPoint().getY() + getSpeed() * 10;
				}
				if (bool == false) {
					location.setCordination(Cordinat.WEST);
					x = this.location.getPoint().getX() - getSpeed() * 10;
					y = this.location.getPoint().getY();
				}
			}
			if (this.location.getPoint().getX() > 0 && this.location.getPoint().getY() == 230
					&& this.location.getCord() == Cordinat.WEST) {
				location.setCordination(Cordinat.WEST);
				x = this.location.getPoint().getX() - getSpeed() * 10;
				y = this.location.getPoint().getY();
			}
			if (this.location.getPoint().getY() >= 190 && this.location.getPoint().getY() < 450
					&& this.location.getPoint().getX() == 720 && this.location.getCord() == Cordinat.SOUTH) {
				location.setCordination(Cordinat.SOUTH);
				x = this.location.getPoint().getX();
				y = this.location.getPoint().getY() + getSpeed() * 10;
			}
			if ((this.location.getPoint().getX() == 0 || this.location.getPoint().getX() == -1)
					&& this.location.getPoint().getY() > 0
					&& (this.location.getCord() == Cordinat.NORTH || this.location.getCord() == Cordinat.WEST)) {
				location.setCordination(Cordinat.NORTH);
				x = this.location.getPoint().getX();
				y = this.location.getPoint().getY() - getSpeed() * 10;
			}
			if (this.location.getPoint().getX() > 0
					&& (this.location.getPoint().getY() == 470 || this.location.getPoint().getY() == 450)) {
				location.setCordination(Cordinat.WEST);
				if (this.location.getPoint().getX() == 40)
					this.location.getPoint().setX(39);
				x = this.location.getPoint().getX() - getSpeed() * 10;
				y = this.location.getPoint().getY();
			}
			if (this.location.getPoint().getX() == -1 && this.location.getPoint().getY() == 230
					&& this.location.getCord() == Cordinat.NORTH) {
				boolean bool = rand.nextBoolean();
				if (bool == true) {
					location.setCordination(Cordinat.NORTH);
					x = this.location.getPoint().getX();
					y = this.location.getPoint().getY() - getSpeed() * 10;
				}
				if (bool == false) {
					if (this.location.getPoint().getY() == 150)
						this.location.getPoint().setY(230);
					location.setCordination(Cordinat.EAST);
					x = this.location.getPoint().getX() + getSpeed() * 10;
					y = this.location.getPoint().getY();
				}
			}
			if (this.location.getPoint().getX() <= 680 && this.location.getPoint().getY() == 230
					&& location.getCord() == Cordinat.EAST) {
				if (this.location.getPoint().getX() == 679)
					this.location.getPoint().setX(680);
				location.setCordination(Cordinat.EAST);
				x = this.location.getPoint().getX() + getSpeed() * 10;
				y = this.location.getPoint().getY();
			}
			if (this.location.getPoint().getX() == 720 && this.location.getPoint().getY() < 450
					&& this.location.getCord() == Cordinat.EAST) {
				location.setCordination(Cordinat.SOUTH);
				x = this.location.getPoint().getX();
				y = this.location.getPoint().getY() + getSpeed() * 10;
			}
			break;
		case "Bike":
			if (this.location.getPoint().getX() <= 680
					&& (this.location.getPoint().getY() == 0 || this.location.getPoint().getY() == -10)) {

				if (this.location.getPoint().getY() == -10)
					this.location.getPoint().setY(0);
				if (this.location.getPoint().getX() == 679)
					this.location.getPoint().setX(680);
				location.setCordination(Cordinat.EAST);
				x = this.location.getPoint().getX() + getSpeed() * 10;
				y = this.location.getPoint().getY();
			}
			if (this.location.getPoint().getX() == 700 && this.location.getPoint().getY() < 180) {
				location.setCordination(Cordinat.SOUTH);
				x = this.location.getPoint().getX();
				y = this.location.getPoint().getY() + getSpeed() * 10;
			}
			if (this.location.getPoint().getX() == 700 && this.location.getPoint().getY() == 180
					&& this.location.getCord() == Cordinat.SOUTH) {
				if (this.location.getPoint().getY() == 200)
					this.location.getPoint().setY(230);
				boolean bool = rand.nextBoolean();
				if (bool == true) {
					location.setCordination(Cordinat.SOUTH);
					x = this.location.getPoint().getX();
					y = this.location.getPoint().getY() + getSpeed() * 10;
				}
				if (bool == false) {
					location.setCordination(Cordinat.WEST);
					x = this.location.getPoint().getX() - getSpeed() * 10;
					y = this.location.getPoint().getY();
				}
			}
			if (this.location.getPoint().getX() > 0 && this.location.getPoint().getY() == 180
					&& this.location.getCord() == Cordinat.WEST) {
				location.setCordination(Cordinat.WEST);
				x = this.location.getPoint().getX() - getSpeed() * 10;
				y = this.location.getPoint().getY();
			}
			if (this.location.getPoint().getY() >= 190 && this.location.getPoint().getY() < 450
					&& this.location.getPoint().getX() == 700 && this.location.getCord() == Cordinat.SOUTH) {
				location.setCordination(Cordinat.SOUTH);
				x = this.location.getPoint().getX();
				y = this.location.getPoint().getY() + getSpeed() * 10;
			}
			if ((this.location.getPoint().getX() == 0 || this.location.getPoint().getX() == -1)
					&& this.location.getPoint().getY() > 0
					&& (this.location.getCord() == Cordinat.NORTH || this.location.getCord() == Cordinat.WEST)) {
				location.setCordination(Cordinat.NORTH);
				x = this.location.getPoint().getX();
				y = this.location.getPoint().getY() - getSpeed() * 10;
			}
			if (this.location.getPoint().getX() > 0
					&& (this.location.getPoint().getY() == 460 || this.location.getPoint().getY() == 450)) {
				location.setCordination(Cordinat.WEST);
				if (this.location.getPoint().getX() == 40)
					this.location.getPoint().setX(39);
				x = this.location.getPoint().getX() - getSpeed() * 10;
				y = this.location.getPoint().getY();
			}
			if (this.location.getPoint().getX() == -1 && this.location.getPoint().getY() == 200
					&& this.location.getCord() == Cordinat.NORTH) {
				boolean bool = rand.nextBoolean();
				if (bool == true) {
					location.setCordination(Cordinat.NORTH);
					x = this.location.getPoint().getX();
					y = this.location.getPoint().getY() - getSpeed() * 10;
				}
				if (bool == false) {
					location.setCordination(Cordinat.EAST);
					x = this.location.getPoint().getX() + getSpeed() * 10;
					y = this.location.getPoint().getY();
				}
			}
			if (this.location.getPoint().getX() <= 680 && this.location.getPoint().getY() == 200
					&& location.getCord() == Cordinat.EAST) {
				if (this.location.getPoint().getX() == 679)
					this.location.getPoint().setX(680);
				location.setCordination(Cordinat.EAST);
				x = this.location.getPoint().getX() + getSpeed() * 10;
				y = this.location.getPoint().getY();
			}
			if (this.location.getPoint().getX() == 700 && this.location.getPoint().getY() < 450
					&& this.location.getCord() == Cordinat.EAST) {
				location.setCordination(Cordinat.SOUTH);
				x = this.location.getPoint().getX();
				y = this.location.getPoint().getY() + getSpeed() * 10;
			}
			break;
		}
		Point p = new Point(x, y);
		return p;
	}

	public boolean setPan(CityPanel p) {
		pan = p;
		return true;
	}

	abstract public boolean refuleVehicles();

	abstract public int getCFuel();

	public int getDurability() {
		return Durability;
	}
	
	public int setDurability(int durabilty) {
		return Durability=durabilty;
	}
	

	public Rectangle getRet() {
		return rec;
	}

	public Rectangle getBounds(int width, int height) {
		return new Rectangle(location.getPoint().getX(), location.getPoint().getY(), width, height);
	}
	
	public int changeCollisioninfo(int coli)
	{
		return Collisioninfo=coli;
	}

	public int getCollisioninfo()
	{
		return Collisioninfo;
	}

}
