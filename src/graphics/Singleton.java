package graphics;

public class Singleton {

	   //create an object of Singleton
	   private static Singleton instance = new Singleton();

	   //make the constructor private so that this class cannot be
	   //instantiated
	   private Singleton(){}

	   //Get the only object available
	   public static Singleton getInstance(){
	      return instance;
	   }

	   public void showMessage(){
	      System.out.println("Hello World!");
	   }
	}