package main;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import main.Buttons;

public class Main extends JFrame {

	private static Main main;
	private static final long serialVersionUID = 1L;
	private final int WINDOW_WIDTH = 400;  // Width
	private final int WINDOW_HEIGHT = 420; // Height

	private JButton button_lock, button_unlock, button_set, test;

	private JTextField textbox;
	private JPanel panel1, panel2, panel3;
	
	private JLabel info;
	
	private Buttons buttons = Buttons.getInstance();

	/**
	 *  Constructor
	 */

	public Main(){
	
		/**
		 * TODO:
		 * update jlabel dynamically
		 * insert the button input into the textfield
		 * 
		 */

		// Set the title bar text.
		super("Combination Lock");
		//user won't be able to make window look weird
		setResizable(false);
		// set initial values

		// Set the size of the window.
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

		// Specify an action for the close button.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add a GridLayout manager to the content pane.
		setLayout(new GridLayout(3, 1));

		button_lock = new JButton("Lock");
		button_unlock = new JButton("Unlock");
		button_set = new JButton("Set");
		textbox = new JTextField(33);
		info = new JLabel("Eep!");
		// Add an action listener to the button.
		button_set.addActionListener(new ButtonListener());
		// Create 3 panels.
		panel1 = buttons.getUCPanel();
		panel2 = buttons.getFPanel();
		panel3 = new JPanel();

		// Add the buttons to the panels.

		panel2.add(button_lock);
		panel2.add(button_unlock);
		panel2.add(button_set);
		button_lock.setEnabled(false);
		button_unlock.setEnabled(false);
		panel3.add(new JPanel().add(info));
		panel3.add(textbox);
		// Add the panels to the content pane.
		add(panel1); // Goes into row 1, column 1
		add(panel2); // Goes into row 2, column 1
		add(panel3);

		// Display the window.
		setVisible(true);
	}
	
	public static Main getInstance() {
		return main;
	}
	
	public void setTextField(String character) {
		if (textbox.getText().length() >= 3) return; 
		textbox.setText(new StringBuilder(
				textbox.getText())
				.append(character)
				.toString()
				);
	}
	
	/**
	 *  The main method creates an instance of the
	 *  GridPanelwindow class, causing it to display
	 *  its window. 
	 */

	public static void main(String[] args)
	{
		main = new Main();
	}
}
