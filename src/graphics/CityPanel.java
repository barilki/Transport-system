
//* Author : Bar Ilan Kimbarovski 205618457 , Shay Manasherov 311332597
		
package graphics;

import vehicles.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.concurrent.RejectedExecutionHandler;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class CityPanel extends JPanel implements ActionListener {
	private BufferedImage img = null;
	private Vehicle v = null;
	private Vehicle[] arrV = null;
	private AddVehicleDialog dialog;
	private CarBoarderDecorator cBD;
	Vehicle t;
	private int flag=1;
	private int ThreadCounter=0;
	private static CityPanel cP = null;
//	private LinkedList<Vehicle> VehiclesThread = new LinkedList();
	private static int maxVehcile = 5;
	private LinkedList<Vehicle> linklistMax = new LinkedList();
	private List<Vehicle> VehiclesThread = new ArrayList<>(5);
//	Queue<Runnable> tasks = new ArrayDeque<Runnable>();
	BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(5);
//	ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.MILLISECONDS , Queue );
//	Runnable task;
	
	
	private CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor(5,
             5, 0, TimeUnit.MILLISECONDS, blockingQueue);

	protected CityPanel() {
		setLayout(new BorderLayout());
		createButtons();
		/**
		 * Open backgruond photo
		 */
		try {
			img = ImageIO.read(new File("src\\graphics\\cityBackground.png"));
		} catch (IOException e) {
			System.out.println("Cannot load image");
		}

	}
	
	
	
	
	public static CityPanel getInstance() {
		if (cP == null) {
			cP = new CityPanel();
			System.out.println("PANEL");
		}
		return cP;
	}

	public void createButtons() {
		JPanel bottomPanel = new JPanel(new GridLayout());
		JButton addvehicle = new JButton("Add Vehicle");
		JButton clear = new JButton("Clear");
		JButton fuelfood = new JButton("Fuel/Food");
		JButton lights = new JButton("Lights");
		JButton info = new JButton("Info");
		JButton boarder = new JButton("Boarder");
		JButton save = new JButton("Save");
		JButton restore = new JButton("Restore");
		JButton exitpanel = new JButton("Exit");
		bottomPanel.add(addvehicle);
		bottomPanel.add(clear);
		bottomPanel.add(fuelfood);
		bottomPanel.add(lights);
		bottomPanel.add(info);
		bottomPanel.add(boarder);
		bottomPanel.add(save);
		bottomPanel.add(restore);
		bottomPanel.add(exitpanel);
		addvehicle.addActionListener(this);
		exitpanel.addActionListener(this);
		clear.addActionListener(this);
		info.addActionListener(this);
		boarder.addActionListener(this);
		save.addActionListener(this);
		restore.addActionListener(this);
		fuelfood.addActionListener(this);
		lights.addActionListener(this);
		add(bottomPanel, BorderLayout.PAGE_END);
	}

	public Vehicle getVehicle() {
		return v;
	}

	public boolean setVehicle(Vehicle v) {
		this.v = v;
		VehiclesThread.add(v);
		executor.submit(v);
//		new Thread(VehiclesThread.getLast()).start();
		repaint();
		return true;
	}
	
//	public void maxVehicle() {
//		if(linklistMax.size()>0) {
//		Vehicle Vmax= linklistMax.get(0);
//		linklistMax.remove(0);
//		VehiclesThread.add(Vmax);
//		new Thread(VehiclesThread.get(0)).start();
//		}
//				
//	}

	public void addVehicleToSystem(Vehicle v) {
		this.v = v;
		pushArr(v);
	}

	public void pushArr(Vehicle vehicle) {
		if (arrV != null) {
			Vehicle[] newArr = new Vehicle[arrV.length + 1];
			for (int i = 0; i < arrV.length; i++)
				newArr[i] = arrV[i];
			newArr[newArr.length - 1] = vehicle;
			this.arrV = newArr;
		} else {
			arrV = new Vehicle[1];
			arrV[0] = vehicle;
		}
	}
	
	public List<Vehicle> getVehiclesThread(){
		return VehiclesThread;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), 530, this);
		if (v != null) { // if the vehicle object exists
			System.out.println(VehiclesThread.size());
			for (int i = 0; i < VehiclesThread.size(); i++) {
				VehiclesThread.get(i).drawObject(g);
				System.out.println("The index is" + VehiclesThread.get(i));

			}
			checkCollision();
		}
	}

	public void checkCollision() {
		if (VehiclesThread != null) {
			for (int i = 0; i < VehiclesThread.size(); i++) {
				for (int j = 0; j < VehiclesThread.size(); j++) {
					if (i != j) {
						if (VehiclesThread.get(i).getRet()
								.intersects(VehiclesThread.get(j).getRet())) {
							if (VehiclesThread.get(i).getDurability() < VehiclesThread.get(j).getDurability())
							{
								VehiclesThread.get(i).changeCollisioninfo(VehiclesThread.get(j).getNumCar());
								VehiclesThread.get(j).changeCollisioninfo(VehiclesThread.get(i).getNumCar());
								VehiclesThread.remove(i);
							}
							else if (VehiclesThread.get(i).getDurability() > VehiclesThread.get(j).getDurability())
							{
								VehiclesThread.get(i).changeCollisioninfo(VehiclesThread.get(j).getNumCar());
								VehiclesThread.get(j).changeCollisioninfo(VehiclesThread.get(i).getNumCar());
								VehiclesThread.remove(j);
							}
							
							else if (VehiclesThread.get(i).getDurability() == VehiclesThread.get(j).getDurability()) {
								VehiclesThread.get(j).changeCollisioninfo(VehiclesThread.get(i).getNumCar());
								VehiclesThread.get(i).changeCollisioninfo(VehiclesThread.get(j).getNumCar());
								VehiclesThread.remove(j);
								VehiclesThread.remove(i);
							}

						}

					}
				}
			}
		}

	}
	

	public void printer() {
		JFrame f = new JFrame("Print Vehicle");
		Object rows[][] = new Object[arrV.length][10];
		Object columns[] = { "Vehicles", "ID", "Color", "Wheels", "Speed", "Fuel Amount", "Dictance",
				"Fuel Consumption", "Lights" ,"Collision with" };
		for (int i = 0; i < arrV.length; i++) {
			rows[i][0] = arrV[i].getVehicleName();
			rows[i][1] = arrV[i].getNumCar();
			rows[i][2] = arrV[i].getColor();
			rows[i][3] = arrV[i].getNumWheels();
			rows[i][4] = arrV[i].getSpeed();
			rows[i][5] = arrV[i].getCFuel();
			rows[i][6] = arrV[i].getKm();
			rows[i][7] = arrV[i].getFuelConsumption();
			rows[i][8] = arrV[i].LightsCheck();
			rows[i][9] = arrV[i].getCollisioninfo();
		}
		JTable table = new JTable(rows, columns);
		JScrollPane scrollPane = new JScrollPane(table);
		f.add(scrollPane, BorderLayout.CENTER);
		f.setSize(1200, 600);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		  Originator originator = new Originator();  
          Caretaker careTaker = new Caretaker();  
		switch (e.getActionCommand()) {
		case "Add Vehicle":
//			if (v != null) {
//				JOptionPane.showMessageDialog(null, "Error, you can create only 1 vehicle");
//			} else {
			AddVehicleDialog add_dialog = new AddVehicleDialog(this);
			add_dialog.setVisible(true);

//			}
			break;
		case "Clear":
//			if (this.getVehicle() == null)
//				JOptionPane.showMessageDialog(null, "Error, the vehicle not created");
//			else {
	//			Vehicle v = null;
			this.setVehicle(v);
//				System.out.println("Test" + VehiclesThread.size());
//				repaint();
					for (int i = 0; i < VehiclesThread.size(); i++) {
						new Thread(VehiclesThread.get(i)).interrupt();
					}

	
				//JOptionPane.showMessageDialog(null, "The vehicle cleared from the panel");
			//}
			break;
		case "Fuel/Food":
			if (getVehicle() == null) {
				JOptionPane.showMessageDialog(null, "Error, the vehicle not created");
			} else {
//				JFrame f = new JFrame(); // Open Frame to Fuel/Food
//				String arr[] = { "Benzine", "Solar", "Food" };
//				int output = JOptionPane.showOptionDialog(f, "Please choose food", "Fuel for cars/Food for animals",
//						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, arr, arr[0]); // Make
//																											// dialog
//																											// with text
//																											// and
//																											// buttons
//				if (output == JOptionPane.YES_OPTION) { // First option Benzine
//					if (v.getVehicleName() == "Benzine car") {
//						v.refuleVehicles();
//						repaint();
//					} else if (v.getVehicleName() != "Benzine car") {
//						JOptionPane.showMessageDialog(null, "Error, Doesnt Match to type on vehicle");
//					}
//				} else if (output == JOptionPane.NO_OPTION) { // Second option Solar
//					if (v.getVehicleName() == "Solar car") {
//						v.refuleVehicles();
//						repaint();
//					} else if (v.getVehicleName() != "Solar car") {
//						JOptionPane.showMessageDialog(null, "Error, Doesnt Match to type on vehicle");
//					}
//				} else if (output == JOptionPane.CANCEL_OPTION) { // Third option Food
//					if (v.getVehicleName() == "Carriage") {
//						v.refuleVehicles();
//						repaint();
//					} else if (v.getVehicleName() != "Carriage") {
//						JOptionPane.showMessageDialog(null, "Error, Doesnt Match to type on vehicle");
//					}
//				}
				for(int i=0;i<VehiclesThread.size();i++)
				{
					synchronized (VehiclesThread.get(i)) {
						VehiclesThread.get(i).refuleVehicles();
						VehiclesThread.get(i).notify();
					}
					
					
					
				}
			}
			break;
		case "Lights":
			if (this.getVehicle() == null)
				JOptionPane.showMessageDialog(null, "Error, the vehicle not created");
			else {
				if (this.getVehicle().LightsCheck())
					JOptionPane.showMessageDialog(null, "Lights on");
				else
					JOptionPane.showMessageDialog(null, "Lights off");
			}
			break;
		case "Info":
			if (arrV == null)
				JOptionPane.showMessageDialog(null, "Error, the vehicle not created");
			else
				printer();
			break;
		case "Boarder":
			if (arrV == null)
				JOptionPane.showMessageDialog(null, "Error, the vehicle not created");
			else {
				CarBoarderDecorator abc = new CarBoarderDecorator(this);
				JFrame f = new JFrame("Decorator Boarder");
			    final JLabel label = new JLabel();          
			    label.setHorizontalAlignment(JLabel.CENTER);  
			    label.setSize(300,200);
			    //JButton b=new JButton("OK");  
			    //b.setBounds(200,100,75,20);
				Object rows[] = new Object[arrV.length];
				for (int i = 0; i < arrV.length; i++) {
					rows[i] = "CarID:" + arrV[i].getNumCar();
				}     
			    final JComboBox cb = new JComboBox(rows);
				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout());
				JButton b = new JButton("Ok");// create button b
				b.setBounds(30, 220, 160, 40);// x axis, y axis, width, height
				panel2.add(b);
				b.addActionListener(new java.awt.event.ActionListener() {
				      public void actionPerformed(ActionEvent e) {
				          System.out.println(cb.getSelectedIndex());
				          System.out.println(cb.getSelectedItem());
				        }
				      });
				add(panel2, BorderLayout.PAGE_END);
			    cb.setBounds(50, 100,150,20);    
			    f.add(cb); f.add(label); f.add(b);    
			    f.setLayout(null);    
			    f.setSize(350,350);    
			    f.setVisible(true);
			}
			break;

		case "Save":
			if (arrV == null)
				JOptionPane.showMessageDialog(null, "Error, the vehicle not created");
			else {
				originator.setState("Vehicle");
				careTaker.add(originator.saveStateToMemento());
				flag = 0;
				JOptionPane.showMessageDialog(null, "Saved!");
			}

			break;
		case "Restore":
			if (arrV == null)
			{
				JOptionPane.showMessageDialog(null, "Error, the vehicle not created");
			}
			 originator.getStateFromMemento(careTaker.get(0));  
			 JOptionPane.showMessageDialog(null, "Restored!");

			break;

		case "Exit":
			System.exit(0);
		}
		checkCollision();
	}
}