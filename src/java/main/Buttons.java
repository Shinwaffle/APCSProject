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
	
	private final JPanel UCPanel = new JPanel(); //upper-case
	private final JPanel LCPanel = new JPanel(); //lower-case
	private final JPanel SPanel = new JPanel(); //special, ?!>< etc..
	private final JPanel NPanel = new JPanel(); //numbers
	private final JPanel FPanel = new JPanel(); //functional, "Set", "Unlock"

	private final String[] functions = new String[]
			{"Help", "Settings", "Cycle", "Lock", "Unlock", "Clear", "Set"};

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

		initFPanel();

		UCPanel.setName("UCPanel");
		LCPanel.setName("LCPanel");
		SPanel.setName("SPanel");
		NPanel.setName("NPanel");
		FPanel.setName("FPanel");

	}

	private void initFPanel() {
		FPanel.setLayout(new GridLayout(1, 3));

		JPanel util = new JPanel();
		JPanel mechanism = new JPanel();
		JPanel set = new JPanel();

		util.setName("util");
		mechanism.setName("mechanism");
		set.setName("set");

		for (int i = 0; i < functions.length; i++) {
			if (i < 2) {
				util.add(createButton(functions[i]));
			} else if (i < 3) {
				util.add(createButton(functions[i]));
			} else if (i < 5) {
				mechanism.add(createButton(functions[i]));
			} else if (i < 7) {
				set.add(createButton(functions[i]));
			}
		}

		FPanel.add(util);
		FPanel.add(mechanism);
		FPanel.add(set);
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
