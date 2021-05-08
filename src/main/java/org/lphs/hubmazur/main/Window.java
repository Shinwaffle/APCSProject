package main;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

	public static Window window;
	private static final long serialVersionUID = 1L;
	private final int WINDOW_WIDTH = 400;  // Width
	private final int WINDOW_HEIGHT = 420; // Height

	private JTextField textbox;
	private JPanel panel1, panel2, panel3;
	
	private JLabel info;
	
	private Buttons buttons = Buttons.getInstance();

	/**
	 *  Constructor
	 */

	private Window(){
	
		/*
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

		textbox = new JTextField(33);
		info = new JLabel("Eep!");

		// Create 3 panels.
		panel1 = buttons.getUCPanel();
		panel2 = buttons.getFPanel();
		panel3 = new JPanel();

		// Add the buttons to the panels.

		panel3.add(new JPanel().add(info));
		panel3.add(textbox);
		// Add the panels to the content pane.
		add(panel1); // Goes into row 1, column 1
		add(panel2); // Goes into row 2, column 1
		add(panel3);

		// Display the window.
		setVisible(true);
	}

	public void cyclePanels() {
		String name = panel1.getName();

		if (name.equals("UCPanel")) {
			remove(panel1);
			validate();
			panel1 = buttons.getLCPanel();
			validate();
		}
	}
	
	public void setTextField(String character) {
		if (textbox.getText().length() >= 3) return; 
		textbox.setText(textbox.getText() + character);
	}

	/**
	 *  The window method creates an instance of the
	 *  GridPanelwindow class, causing it to display
	 *  its window.
	 */

	public static void main(String[] args)
	{
		window = new Window();
	}
}
