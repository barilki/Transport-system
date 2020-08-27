/**
 * Author : Bar Ilan Kimbarovski 205618457 , Shay Manasherov 311332597
 * Hw 2
 */

package system;

import java.io.ObjectInputStream.GetField;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;
import vehicles.*;

public class MySystem {
	private Point p;
	private static Point minLimit;
	private static Point maxLimit;
	private static Dictionary dict = new Hashtable();

	public static boolean drive(long id, Point p) {
		if (((p.getX() >= minLimit.getX() && p.getX() <= maxLimit.getX())
				&& (p.getY() >= minLimit.getY() && p.getY() <= maxLimit.getY()))) {
			Vehicle vehicle = (Vehicle) dict.get(id);
			if (!vehicle.drive(p)) {
				return false;
			}
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Point p;
		String s, animal;
		long id = 0;
		float km = 0;
		int numWheels;
		int x = 0, y = 0, i, numPassengers = 0, MinAge = 0, fuel = 0, tank = 0;
		boolean Lights = false;
		Location l = null;
		Engine engine = null;
		Color col = null;
		Cordinat cord;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter  Minimum X value:");
		int minX = input.nextInt();
		System.out.println("Enter  Minimum Y value:");
		int minY = input.nextInt();
		minLimit = new Point(minX, minY);
		System.out.println("Enter  Maximum X value:");
		int maxX = input.nextInt();
		System.out.println("Enter  Maximum Y value:");
		int maxY = input.nextInt();
		maxLimit = new Point(maxX, maxY);
		System.out.println("Enter numbers of Vehicles you Want: ");
		int size = input.nextInt();
		Vehicle[] vehicle = new Vehicle[size];
		for (i = 0; i < vehicle.length; i++) {
			System.out.println("your vehicle is with engine? write 1 for yes or 2 for no");
			int bool = input.nextInt();
			switch (bool) {
			case 1:
				System.out.println("Vehicle with engine you have only car.");
				System.out.println("Car");
				System.out.println("Enter id for car: 1000-1000000");
				id = input.nextLong();
				if (id <= 1000 || id >= 1000000) {
					System.out.println("The id is out of range! ");
					break;
				}
				System.out.println("Enter x coordinat: ");
				x = input.nextInt();
				System.out.println("Enter y coordinat: ");
				y = input.nextInt();
				cord = Cordinat.NORTH;
				p = new Point(x, y);
				l = new Location(p, cord);
				System.out.println("Enter the start km: ");
				km = input.nextFloat();
				System.out.println("Enter amout of passengers in car: ");
				numPassengers = input.nextInt();
				System.out.println("Enter the driver minimum age: ");
				MinAge = input.nextInt();
				System.out.println("The car engine is with benzine or solar? for benzine press 1 for solar press 2");
				int eng = input.nextInt();
				switch (eng) {
				case 1:
					System.out.println("Benzine Car");
					engine = new BenzineEngine(fuel, tank);
					vehicle[i] = new Car(id, col, 4, l, km, Lights, engine, 40, MinAge, numPassengers);
					System.out.println("Enter car color: ");
					input.nextLine();
					s = input.nextLine();
					vehicle[i].setColor(s);
					dict.put(id, vehicle[i]);
					break;
				case 2:
					System.out.println("Solar Car");
					engine = new SolarEngine(fuel, tank);
					vehicle[i] = new Car(id, col, 4, l, km, Lights, engine, 40, MinAge, numPassengers);
					break;
				}
				break;
			case 2:
				System.out.println("Enter Your Choice 1 for bike, 2 for carriage :");
				int choose = input.nextInt();
				switch (choose) {
				case 1:
					System.out.println("Bike");
					System.out.println("Enter id for bike: 1000-1000000");
					id = input.nextLong();
					if (id <= 1000 || id >= 1000000) {
						System.out.println("The id is out of range! ");
						break;
					}
					System.out.println("Enter x coordinat: ");
					x = input.nextInt();
					System.out.println("Enter y coordinat: ");
					y = input.nextInt();
					System.out.println("Enter direction: ");
					cord = Cordinat.NORTH;
					p = new Point(x, y);
					l = new Location(p, cord);
					System.out.println("Enter the start km: ");
					km = input.nextFloat();
					System.out.println("Enter number of wheels: ");
					int numW = input.nextInt();
					System.out.println("Choose the Gear for your Bike");
					int gear = input.nextInt();
					vehicle[i] = new Bike(id, col, numW, l, km, Lights, gear);
					break;
				case 2:
					System.out.println("Carriage");
					System.out.println("Enter id for Carriage: 1000-1000000");
					id = input.nextLong();
					if (id <= 1000 || id >= 1000000) {
						System.out.println("The id is out of range! ");
						break;
					}
					System.out.println("Enter x coordinat: ");
					x = input.nextInt();
					System.out.println("Enter y coordinat: ");
					y = input.nextInt();
					cord = Cordinat.NORTH;
					p = new Point(x, y);
					l = new Location(p, cord);
					System.out.println("Enter the start km: ");
					km = input.nextFloat();
					System.out.println("Choose the aniaml that will carry Carriage: ");
					animal = input.next();
					vehicle[i] = new Carriage(id, col, 4, l, km, Lights, animal);
					break;
				}
			}
		}
		for (int z = 0; z < vehicle.length; z++) {
			System.out.println(vehicle[z]);
		}
		int index = 0;
		do {
			System.out.println("Enter index: ");
			index = input.nextInt();
			if (index >= vehicle.length || index < 0)
				break;
			System.out.println(vehicle[index]);
			System.out.println("Enter point X first then Y:");
			int Px = input.nextInt();
			int Py = input.nextInt();
			p = new Point(Px, Py);
			drive(vehicle[index].getNumCar(), p);
		} while (index >= 0 && index < vehicle.length);
		input.close();
	}
}

//		System.out.println("Enter number of vehicles: ");
//		while (true) {
//			id = Long.valueOf(input.nextInt());
//			if (id >= 1000 && id <= 1000000)
//				throw new IllegalArgumentException();
//			System.out.println("Invalid Vehicles number!");
//			dict.put(id, vehicle[i]);
//		}
//		while (true) {
//			System.out.println("Enter the number of the vehicle: ");
//			id = input.nextLong();
//			System.out.println(dict.get(id));
//			System.out.println("Enter point X: ");
//			int x = input.nextInt();
//			System.out.println("Enter point Y: ");
//			int y = input.nextInt();
//			drive(id, new Point(x, y));
//		}
//		input.close();
//	}
//}
