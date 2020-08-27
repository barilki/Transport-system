package graphics;

import vehicles.*;

public class vehicleFactory {

	private static HasEngine e;
	private static Bike b;
	private static PackAnimal pA;
	
	public static Vehicle getVehicle(String vehicleType,CityPanel city) {
		Vehicle x = city.getVehicle();
		if (vehicleType == null) {
			return null;
		}
		if (vehicleType.equals("Car")) {
			return new Car(city, x.getLocation(), x.getSize(), x.getNumCar(), x.getNumWheels(), x.getKm(), x.LightsCheck(), x.getColor(x.getColor()), x.getFuelConsumption(), e.getEngine(), 0, 18, 4);

		} else if (vehicleType.equals("Bike")) {
			return new Bike(x.getLocation(), x.getSize(), x.getNumCar() ,x.getNumWheels(), x.getKm(), x.LightsCheck(), x.getColor(x.getColor()), x.getFuelConsumption(), city, b.getGear());

		} else if (vehicleType.equals("Carriage")) {
			return new Carriage(city, x.getLocation(), x.getSize(), x.getNumCar(), x.getNumWheels(), x.getKm(), x.LightsCheck(), x.getColor(x.getColor()), x.getFuelConsumption(), e.getEngine(), pA.getAnimal());
		}
		return null;
	}

}