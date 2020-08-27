/**
 *Author : Bar Ilan Kimbarovski 205618457 , Shay Manasherov 311332597
 * Hw 3
 */

package graphics;

import graphics.CityPanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CityFrame extends JFrame {
	private static CityFrame gui = null;
	JMenuBar menuBar;
	JMenuItem exit, help;
	JFrame frame;
	CityPanel citypanel = null;

	private CityFrame() {
		citypanel.getInstance();
		//CityFrame gui = new CityFrame();
		setPreferredSize(new Dimension(800, 600));
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		exit = new JMenuItem("Exit");
		help = new JMenuItem("Help");
		fileMenu.add(exit);
		helpMenu.add(help);
		help.addActionListener(getAction());
		exit.addActionListener(getAction());
		setLocationRelativeTo(null);
		citypanel = new CityPanel();
		this.setLayout(new GridLayout());
		add(citypanel);

	}

	/**
	 * implement an action listener to define what should be done when an user
	 * performs certain operation
	 *
	 */
	public static class test implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Help":
				JFrame help = new JFrame();
				JOptionPane.showMessageDialog(help, "Home Work 2\nGUI");
				break;
			case "Exit":
				System.exit(0);
				break;
			}
		}
	}

	test A = new test();

	public test getAction() {
		return A;
	}
	

	public static CityFrame getInstance() {
		if (gui == null) {
			gui = new CityFrame();
			System.out.println("FRAME");
		}
		return gui;
	}

	public static void main(String[] args) {
		CityFrame gui = CityFrame.getInstance();
		gui.setVisible(true);
		gui.pack();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setTitle("City");
	}

}