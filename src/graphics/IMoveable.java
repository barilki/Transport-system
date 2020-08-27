package graphics;
import system.*;
import vehicles.*;

public interface IMoveable {	
    public String getVehicleName();
	 public int getSpeed();
	 public int getFuelConsumption();
	 public void move();
	 public int getDurability();
}

