package graphics;
import java.awt.Graphics;

import system.*;
import vehicles.*;


	public interface IDrawable {
		 public final static String PICTURE_PATH = "src\\\\graphics\\\\";
		 public void loadImages();
		 public void drawObject (Graphics g);
		 public String getColor();	 
}
