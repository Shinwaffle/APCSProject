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
	 * The window itself
	 */
	private Window(){

		super("Combination Lock");

		setResizable(false);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 1));

		panel1 = buttons.getUCPanel();
		panel2 = buttons.getFPanel();
		panel3 = new JPanel();

		textbox = new JTextField(33);
		info = new JLabel("Eep!");
		panel3.add(new JPanel().add(info));
		panel3.add(textbox);

		add(panel3);
		add(panel2);
		add(panel1);

		setVisible(true);
	}

	/**
	 * used by ButtonListener to signal the window to
	 * go to the next panel of characters
	 */
	public void cyclePanels() {
		String name = panel1.getName();

		if (name.equals("UCPanel")) {
			replacePanels("LCPanel");
		}
		if (name.equals("LCPanel")) {
			replacePanels("SPanel");
		}
		if (name.equals("SPanel")) {
			replacePanels("NPanel");
		}
		if (name.equals("NPanel")) {
			replacePanels("UCPanel");
		}
	}

	/**
	 * helper function for cyclePanels to reduce verbosity
	 * @param replacement name of the panel
	 */
	private void replacePanels(String replacement) {
		panel1.setVisible(false);
		remove(panel1);
		validate();
		panel1 = buttons.getPanel(replacement);
		add(panel1);
		validate();
		panel1.setVisible(true);
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
