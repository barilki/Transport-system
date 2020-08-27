package graphics;

import system.*;
import vehicles.*;

public interface IAnimal extends IMoveable {
	public String getAnimalName();

	public boolean eat();
}
