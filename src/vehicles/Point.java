/**
 * Author : Bar Ilan Kimbarovski 205618457 , Shay Manasherov 311332597
 * Hw 1
 */

package vehicles;

public class Point implements Cloneable {
	private int x;
	private int y;

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 */
	public Point(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Copy Constructor
	 * 
	 * @param p
	 */
	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
	}

	/**
	 * returns a textual representation of an object
	 */
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

	/**
	 * 
	 * @return x value
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * 
	 * @return y value
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * set Function
	 * 
	 * @param get a x value and change the old value;
	 * @return true
	 */
	public boolean setX(int x) {
		this.x = x;
		return true;
	}

	/**
	 * 
	 * @param get a y value and change the old value
	 * @return true
	 */
	public boolean setY(int y) {
		this.y = y;
		return true;
	}

	/**
	 * Clone function ,deep copy return copy of point
	 */
	public Object clone()  {
		try {
		// call Object.clone()
		return (Point) super.clone();
		}
		catch (CloneNotSupportedException e) {return null;}
		//this won't happen, since we are Cloneable
}
	}

