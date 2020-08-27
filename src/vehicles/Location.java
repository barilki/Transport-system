package vehicles;

public class Location {
	private Point point;
	private Cordinat cord;

	/**
	 * Constructor
	 * 
	 * @param point
	 * @param cordinat
	 */
	public Location(Point point, Cordinat cord) {
		this.point = new Point(point);
		this.cord = cord;
	}

	/**
	 * Copy Constructor
	 * 
	 * @param location
	 */
	public void Location(Location location) {
		this.point = location.getPoint();
	}

	/**
	 * set the currect cordinat
	 */
	public boolean setCordination(Cordinat cordinat) {
		if (cordinat == Cordinat.WEST)
			cord = Cordinat.WEST;
		if (cordinat == Cordinat.SOUTH)
			cord = Cordinat.SOUTH;
		if (cordinat == Cordinat.NORTH)
			cord = Cordinat.NORTH;
		if (cordinat == Cordinat.EAST)
			cord = Cordinat.EAST;
		return true;
	}

	public Cordinat getCord() {
		return cord;
	}

	public boolean setLocationP(Point point) {
		this.point = point;
		return true;
	}

	/**
	 * get the Point
	 * 
	 * @return point
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * returns a textual representation of Location
	 */
	public String toString() {
		return "The point is:" + getPoint() + "\n" + "the cordinat is:" + getCord() + "\n";
	}

}