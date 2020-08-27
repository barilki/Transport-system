/**
 * Author : Bar Ilan Kimbarovski 205618457 , Shay Manasherov 311332597
 * Hw 2
 */

package graphics;

import vehicles.*;
import vehicles.Color;
import vehicles.Point;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.awt.event.ActionEvent;



public class AddVehicleDialog extends JDialog {

	// Vehicle vehicle = null;
	private CityPanel cityp = null;
	protected static int id = 999;


	// Declaration of object of JRadioButton class.
	JRadioButton jRadioButton1, jRadioButton2, jRadioButton3, jRadioButton4, jRadioButton5, jRadioButton6,
			jRadioButton7, jRadioButton8;

	// Declaration of object of JButton class.
	JButton jButton;

	// Declaration of object of ButtonGroup class.
	ButtonGroup G1;

//	private static AddVehicleDialog instance;// Singleton

	/**
	 * Constructor
	 */
	public AddVehicleDialog(CityPanel cityp) {
		this.cityp = cityp;
		// Options to the window//
		setTitle("Add Vehicle");
		setSize(700, 500);
		setLayout(new BorderLayout());
		setResizable(false);// Get bigger or smaller size of the window
		setModal(true);
		setLocationRelativeTo(null);

		// left panel

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(2, 3));
		leftPanel.setPreferredSize(new Dimension(300, 20));
		TitledBorder border = new TitledBorder(BorderFactory.createTitledBorder("Choose a vehicle"));
		leftPanel.setBorder(border);
		/**
		 * Radio Button
		 */
		// Setting layout as null of JFrame.
//		this.setLayout(null);

		jRadioButton1 = new JRadioButton();
		jRadioButton2 = new JRadioButton();
		jRadioButton3 = new JRadioButton();
		jRadioButton4 = new JRadioButton();

		// Initialization of object of "ButtonGroup" class.
		G1 = new ButtonGroup();

		// setText(...) function is used to set text of radio button.
		// Setting text of "jRadioButton2".
		jRadioButton1.setText("Benzine Car");
		jRadioButton2.setText("Solar Car");
		jRadioButton3.setText("Bike");
		jRadioButton4.setText("Carriage");

		// Setting Bounds of "jRadioButton"
		jRadioButton1.setBounds(0, 30, 100, 50); // x , y , width ,height
		jRadioButton2.setBounds(100, 30, 80, 50);
		jRadioButton3.setBounds(0, 90, 80, 50);
		jRadioButton4.setBounds(100, 90, 80, 50);

		// "this" keyword in java refers to current object.
		// Adding "jRadioButton" on JFrame.
		leftPanel.add(jRadioButton1);
		leftPanel.add(jRadioButton2);
		leftPanel.add(jRadioButton3);
		leftPanel.add(jRadioButton4);
		add(leftPanel, BorderLayout.WEST);

		// Adding "jRadioButton" and "jRadioButton3" in a Button Group "G2".
		G1.add(jRadioButton1);
		G1.add(jRadioButton2);
		G1.add(jRadioButton3);
		G1.add(jRadioButton4);

		// End left panel

		// Right panel

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(1, 1));
		rightPanel.setPreferredSize(new Dimension(250, 100));
		TitledBorder border2 = new TitledBorder(BorderFactory.createTitledBorder("Choose a color"));
		rightPanel.setBorder(border2);
		/**
		 * Radio Button
		 */
		// Setting layout as null of JFrame.
//		this.setLayout(null);

		// Initialization of object of "JRadioButton" class.
		jRadioButton5 = new JRadioButton();
		jRadioButton6 = new JRadioButton();
		jRadioButton7 = new JRadioButton();
		jRadioButton8 = new JRadioButton();

		// Initialization of object of "ButtonGroup" class.
		G1 = new ButtonGroup();

		// setText(...) function is used to set text of radio button.
		// Setting text of "jRadioButton2".
		jRadioButton5.setText("Red");
		jRadioButton6.setText("Green");
		jRadioButton7.setText("Silver");
		jRadioButton8.setText("White");

		// Setting Bounds of "jRadioButton"
		jRadioButton5.setBounds(200, 30, 100, 50); // x , y , width ,height
		jRadioButton6.setBounds(300, 30, 80, 50);
		jRadioButton7.setBounds(200, 90, 80, 50);
		jRadioButton8.setBounds(300, 90, 80, 50);

		// "this" keyword in java refers to current object.
		// Adding "jRadioButton" on JFrame.
		rightPanel.add(jRadioButton5);
		rightPanel.add(jRadioButton6);
		rightPanel.add(jRadioButton7);
		rightPanel.add(jRadioButton8);
		add(rightPanel, BorderLayout.EAST);

		// Adding "jRadioButton" and "jRadioButton3" in a Button Group "G2".
		G1.add(jRadioButton5);
		G1.add(jRadioButton6);
		G1.add(jRadioButton7);
		G1.add(jRadioButton8);

		// End Right panel

		// North panel (include Left panel + Right panel)

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout(2, 3));
		northPanel.add(rightPanel, BorderLayout.EAST);
		northPanel.add(leftPanel, BorderLayout.WEST);
		add(northPanel, BorderLayout.NORTH);

		// Slider bar
		JLabel textgears = new JLabel("Choose bike gears:", JLabel.CENTER);
		JSlider gearSlider = new JSlider(0, 10);
		JPanel centerPanel = new JPanel();
		centerPanel.setVisible(false);

		centerPanel.setLayout(new BorderLayout());

		textgears.setHorizontalAlignment(JLabel.CENTER);
		textgears.setVerticalAlignment(JLabel.CENTER);
		centerPanel.add(textgears, BorderLayout.CENTER);

		// Add Text
		Font font = new Font("", Font.BOLD, 15);
		gearSlider.setFont(font);

		gearSlider.setMajorTickSpacing(2);
		gearSlider.setMinorTickSpacing(1);
		gearSlider.setExtent(1);
		gearSlider.setPaintTicks(true);
		gearSlider.setPaintLabels(true);
//		gearSlider.addChangeListener(this);
		centerPanel.add(gearSlider, BorderLayout.SOUTH);
		this.add(centerPanel, BorderLayout.CENTER);

		// if bike button is chosen then the slider will be displayed
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jRadioButton3.isSelected()) {
					centerPanel.setVisible(true);
				} else
					centerPanel.setVisible(false);

			}

		};
		jRadioButton1.addActionListener(al);
		jRadioButton2.addActionListener(al);
		jRadioButton3.addActionListener(al);
		jRadioButton4.addActionListener(al);
		jRadioButton5.addActionListener(al);
		jRadioButton6.addActionListener(al);
		jRadioButton7.addActionListener(al);
		jRadioButton8.addActionListener(al);

		// end slider bar

		/**
		 * Regular Button
		 */
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout());

		JButton b = new JButton("Ok");// create button b
		b.setBounds(30, 220, 160, 40);// x axis, y axis, width, height

		JButton c = new JButton("Cancel");// create button c
		c.setBounds(220, 220, 160, 40);// x axis, y axis, width, height

		panel2.add(b);
		panel2.add(c);

		add(panel2, BorderLayout.PAGE_END);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Location l = new Location(new Point(0, 0), Cordinat.EAST);
				int size = 65;
				int numWheels = 0;
				float km = 0;
				boolean Lights = false;
				Color col = null;
				int fuelConsumption = 0;
				int benzFuel = 0, solarFuel = 0;
				int tank = 0;
				Engine engine = new BenzineEngine(benzFuel, tank);
				Engine engine2 = new SolarEngine(solarFuel, tank);
				int fuel;
				int numPassengers = 4;
				CityPanel pan = null;
				int MinAge = 18;
				Vehicle v = cityp.getVehicle();

				if (jRadioButton1.isSelected()) {
					if (jRadioButton5.isSelected()) { // red
						id++;
						col = Color.Red; 
						Car carBr = new Car(pan, l, size, id, numWheels, km, Lights, col, fuelConsumption,
								engine, benzFuel, MinAge, numPassengers);
						carBr.loadImages();
						cityp.setVehicle(carBr);
						carBr.setPan(cityp);
						cityp.pushArr(carBr);
						cityp.repaint();
					}
					if (jRadioButton6.isSelected()) { // green
						id++;
						col = Color.Green;
						Car carBg = new Car(pan, l, size, id, numWheels, km, Lights, col, fuelConsumption,
								engine, benzFuel, MinAge, numPassengers);
						carBg.loadImages();
						cityp.setVehicle(carBg);
						carBg.setPan(cityp);
						cityp.pushArr(carBg);
						cityp.repaint();
					}
					if (jRadioButton7.isSelected()) {// silver
						id++;
						col = Color.Silver;
						Car carBs = new Car(pan, l, size, id, numWheels, km, Lights, col, fuelConsumption, engine,
								benzFuel, MinAge, numPassengers);
						carBs.loadImages();
						cityp.setVehicle(carBs);
						carBs.setPan(cityp);
						cityp.pushArr(carBs);
						cityp.repaint();
					}
					if (jRadioButton8.isSelected()) {// white
						id++;
						col = Color.White;
						Car carBw = new Car(pan, l, size, id, numWheels, km, Lights, col, fuelConsumption, engine,
								benzFuel, MinAge, numPassengers);
						carBw.loadImages();
						cityp.setVehicle(carBw);
						carBw.setPan(cityp);
						cityp.pushArr(carBw);
						cityp.repaint();
					}

				}
				if (jRadioButton2.isSelected()) { // Solar Car
					if (jRadioButton5.isSelected()) { // red
						id++;
						col = Color.Red; // carSr = car Solar red
						Car carSr = new Car(pan, l, size, id, numWheels, km, Lights, col, fuelConsumption, engine2,
								solarFuel, MinAge, numPassengers);
						carSr.loadImages();
						cityp.setVehicle(carSr);
						carSr.setPan(cityp);
						cityp.pushArr(carSr);
						cityp.repaint();
					}
					if (jRadioButton6.isSelected()) { // green
						id++;
						col = Color.Green;
						Car carSg = new Car(pan, l, size, id, numWheels, km, Lights, col, fuelConsumption, engine2,
								solarFuel, MinAge, numPassengers);

						carSg.loadImages();
						cityp.setVehicle(carSg);
						carSg.setPan(cityp);
						cityp.pushArr(carSg);
						cityp.repaint();
					}
					if (jRadioButton7.isSelected()) {// silver
						id++;
						col = Color.Silver;
						Car carSs = new Car(pan, l, size, id, numWheels, km, Lights, col, fuelConsumption, engine2,
								solarFuel, MinAge, numPassengers);

						carSs.loadImages();
						cityp.setVehicle(carSs);
						carSs.setPan(cityp);
						cityp.pushArr(carSs);
						cityp.repaint();
					}
					if (jRadioButton8.isSelected()) {// white
						id++;
						col = Color.White;
						Car carSw = new Car(pan, l, size, id, numWheels, km, Lights, col, fuelConsumption, engine2,
								solarFuel, MinAge, numPassengers);

						carSw.loadImages();
						cityp.setVehicle(carSw);
						carSw.setPan(cityp);
						cityp.pushArr(carSw);
						cityp.repaint();
					}
				}
				if (jRadioButton3.isSelected()) { // Bike
					int gear = gearSlider.getValue();
					if (jRadioButton5.isSelected()) {// red
						id++;
						col = Color.Red;
						Bike br = new Bike(l, size, id, numWheels, km, Lights, col, fuelConsumption, pan, gear);
						br.loadImages();
						cityp.setVehicle(br);
						System.out.println(cityp.getVehicle());
						br.setPan(cityp);
						cityp.pushArr(br);
						cityp.repaint();
					}
					if (jRadioButton6.isSelected()) {// green
						id++;
						col = Color.Green;
						Bike bg = new Bike(l, size, id, numWheels, km, Lights, col, fuelConsumption, pan, gear);
						bg.loadImages();
						cityp.setVehicle(bg);
						bg.setPan(cityp);
						cityp.pushArr(bg);
						cityp.repaint();
					}
					if (jRadioButton7.isSelected()) {// silver
						id++;
						col = Color.Silver;
						Bike bs = new Bike(l, size, id, numWheels, km, Lights, col, fuelConsumption, pan, gear);
						bs.loadImages();
						cityp.setVehicle(bs);
						bs.setPan(cityp);
						cityp.pushArr(bs);
						cityp.repaint();
					}
					if (jRadioButton8.isSelected()) {// white
						id++;
						col = Color.White;
						Bike bw = new Bike(l, size, id, numWheels, km, Lights, col, fuelConsumption, pan, gear);
						bw.loadImages();
						cityp.setVehicle(bw);
						bw.setPan(cityp);
						cityp.pushArr(bw);
						cityp.repaint();
					}
				}
				if (jRadioButton4.isSelected()) { // Carrige
					PackAnimal animal = new PackAnimal("Horse", 1000);
					if (jRadioButton5.isSelected()) {// red
						id++;
						col = Color.Red;
						Carriage carrigeR = new Carriage(pan, l, size, id, numWheels, km, Lights, col, fuelConsumption,
								engine, animal);
						v=carrigeR;
						carrigeR.loadImages();
//						cityp.setVehicle(carrigeR);
//						carrigeR.setPan(cityp);
						cityp.pushArr(carrigeR);
//						cityp.repaint();
					}
					if (jRadioButton6.isSelected()) {// green
						id++;
						col = Color.Green;
						Carriage carrigeG = new Carriage(pan, l, size, id, numWheels = 4, km, Lights, col,
								fuelConsumption, engine, animal);
						v=carrigeG;
						carrigeG.loadImages();
						//cityp.setVehicle(carrigeG);
						//carrigeG.setPan(cityp);
						cityp.pushArr(carrigeG);
						//cityp.repaint();
					}
					if (jRadioButton7.isSelected()) {// silver
						id++;
						col = Color.Silver;
						Carriage carrigeS = new Carriage(pan, l, size, id, numWheels = 4, km, Lights, col,
								fuelConsumption, engine, animal);
						carrigeS.loadImages();
						//cityp.setVehicle(carrigeS);
						v=carrigeS;
						//carrigeS.setPan(cityp);
						cityp.pushArr(carrigeS);
					//	cityp.repaint();
					}
					if (jRadioButton8.isSelected()) {// white
						id++;
						col = Color.White;
						Carriage carrigeW = new Carriage(pan, l, size, id, numWheels = 4, km, Lights, col,
								fuelConsumption, engine, animal);
						carrigeW.loadImages();
						v=carrigeW;
					//	cityp.setVehicle(carrigeW);
						//carrigeW.setPan(cityp);
						cityp.pushArr(carrigeW);
					}
					cityp.setVehicle(v);
					v.setPan(cityp);
					System.out.println(v);
					cityp.repaint();

				}
				
			

				dispose();
			}

		});

		add(panel2, BorderLayout.PAGE_END);
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Or whatever else
			}
		});

	}

}