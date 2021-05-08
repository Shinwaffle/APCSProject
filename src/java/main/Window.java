package main;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

	public static Window window;
	private static final long serialVersionUID = 1L;
	private final int WINDOW_WIDTH = 400;  // Width
	private final int WINDOW_HEIGHT = 420; // Height

	private JTextField textbox;
	private JPanel inputPadPanel, functionPadPanel, inputPanel;
	private JLabel info;
	
	private Buttons buttons = Buttons.getInstance();
	private String Secret = "";
	private boolean isSet = false;
	private boolean isLocked = false;


	/**
	 * The window itself
	 */
	private Window(){

		super("Combination Lock");

		setBackground(Color.black);
		setResizable(false);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(4, 1));

		inputPadPanel = buttons.getUCPanel();
		functionPadPanel = buttons.getFPanel();
		inputPanel = new JPanel();

		textbox = new JTextField(33);
		info = new JLabel("Eep! S-Set a code!");
		inputPanel.add(new JPanel().add(info));
		inputPanel.add(textbox);

		add(inputPanel);
		add(functionPadPanel);
		add(inputPadPanel);

		setVisible(true);
	}

	public void clearTextField() {
		textbox.setText("");
	}

	public void setSecret() {
		if (textbox.getText().length() != 3) {
			info.setText("Code must be 3 characters long!");
			return;
		}

		if (isLocked) {
			info.setText("Cannot set a code when locked!");
			return;
		}
		if (isSet) {
		    info.setText("Replaced code with new one!");
        }
		isSet = true;
		Secret = textbox.getText();
        info.setText("New code set!");
		functionPadPanel.getComponent(4).setEnabled(true);
	}

	public void setLocked() {
	    info.setText("Enter code");
	    textbox.setText("");
        functionPadPanel.getComponent(4).setEnabled(false);
        functionPadPanel.getComponent(5).setEnabled(true);
        functionPadPanel.getComponent(6).setEnabled(false);
	}

	public void setUnlocked() {
	    if (textbox.getText().equals(Secret)) {
            functionPadPanel.getComponent(4).setEnabled(true);
            functionPadPanel.getComponent(5).setEnabled(false);
            functionPadPanel.getComponent(6).setEnabled(true);
            info.setText("Welcome!");
        } else {
	        info.setText("Incorrect code!");
        }
    }
	/**
	 * used by ButtonListener to signal the window to
	 * go to the next panel of characters
	 */
	public void cyclePanels() {
		String name = inputPadPanel.getName();

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
		inputPadPanel.setVisible(false);
		remove(inputPadPanel);
		validate();
		inputPadPanel = buttons.getPanel(replacement);
		add(inputPadPanel);
		validate();
		inputPadPanel.setVisible(true);
	}

	public void addToTextField(String character) {
		if (textbox.getText().length() < 3) {
			textbox.setText(textbox.getText() + character);
		}
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
