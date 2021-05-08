package main;

import javax.swing.*;
import java.awt.*;
import java.util.regex.*;

/**
 * 
 * @author hubmazur
 * 
 * Singleton class for returning panels with buttons alongside logic
 */
public class Buttons{

	private static Buttons buttons = null;
	
	private static final JPanel UCPanel = new JPanel(); //upper-case
	private static final JPanel LCPanel = new JPanel(); //lower-case
	private static final JPanel SPanel = new JPanel(); //special, ?!>< etc..
	private static final JPanel NPanel = new JPanel(); //numbers
	private static final JPanel FPanel = new JPanel(); //functional, "Set", "Unlock"
	
	private static final String[] functions = new String[]{"Cycle", "Help", "Settings", "Lock", "Unlock", "Set"};

	private Buttons() {
		//usable ascii characters range
		for (int i = 33; i < 127; i++) {
			String character = String.valueOf((char)i);
			
			if (Pattern.matches("[a-z]", character)) {
				LCPanel.add(createButton(character));
			} else if (Pattern.matches("[A-Z]", character)) {
				UCPanel.add(createButton(character));
			} else if (Pattern.matches("\\d", character)) {
				NPanel.add(createButton(character));
			} else {
				SPanel.add(createButton(character));
			}
		}
		
		for (int i = 0; i < functions.length; i++) {
			FPanel.add(createButton(functions[i]));
		}

		UCPanel.setName("UCPanel");
		LCPanel.setName("LCPanel");
		SPanel.setName("SPanel");
		NPanel.setName("NPanel");
		FPanel.setName("FPanel");

	}

	public static Buttons getInstance() {
		if (buttons == null) buttons = new Buttons();
		return buttons;
	}

	/**
	 * helper function for constructor to reduce verbosity
	 * @param name name of the button
	 * @return a JButton with the basic functionality
	 */
	private JButton createButton(String name) {
		JButton button = new JButton(name);
		button.setName(name);
		button.addActionListener(new ButtonListener());
		return button;
	}

	public JPanel getPanel(String panelName) {
		if (panelName.equals("UCPanel")) {
			return getUCPanel();
		}
		if (panelName.equals("LCPanel")) {
			return getLCPanel();
		}
		if (panelName.equals("SPanel")) {
			return getSPanel();
		}
		if (panelName.equals("NPanel")) {
			return getNPanel();
		}
		if (panelName.equals("FPanel")) {
			return getFPanel();
		}
		System.out.println("Buttons - We have returned a null panel!");
		return null;
	}
	/**
	 * @return the UCPanel
	 */
	public JPanel getUCPanel() {
		return UCPanel;
	}

	/**
	 * @return the LCPanel
	 */
	public JPanel getLCPanel() {
		return LCPanel;
	}

	/**
	 * @return the SPanel
	 */
	public JPanel getSPanel() {
		return SPanel;
	}

	/**
	 * @return the NPanel
	 */
	public JPanel getNPanel() {
		return NPanel;
	}

	/**
	 * @return the FPanel
	 */
	public JPanel getFPanel() {
		return FPanel;
	}

	
}
