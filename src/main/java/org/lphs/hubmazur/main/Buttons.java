package main;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.*;

/**
 * 
 * @author hubmazur
 * 
 * Singleton class for returning panels with buttons on them alongside logic.
 * Also a factory of panels? I guess?
 */
public class Buttons{

	private static Buttons buttons = new Buttons();
	
	private JPanel UCPanel = new JPanel(); //upper-case
	private JPanel LCPanel = new JPanel(); //lower-case
	private JPanel SPanel = new JPanel(); //special, ?!>< etc..
	private JPanel NPanel = new JPanel(); //numbers
	private JPanel FPanel = new JPanel(); //functional, "Set", "Unlock"
	
	private String[] functions = new String[]{"Help", "Settings", "Lock", "Unlock", "Set"};

	private Buttons() {
		//usable ascii characters range
		for (int i = 33; i < 127; i++) {
			String character = String.valueOf((char)i);
			
			if (Pattern.matches("[a-z]", character)) {
				JButton temp = new JButton(character);
				temp.addActionListener(new ButtonListener());
				LCPanel.add(temp);
				continue;
			}
			
			if (Pattern.matches("[A-Z]", character)) {
				JButton temp = new JButton(character);
				temp.addActionListener(new ButtonListener());
				UCPanel.add(temp);
				continue;
			}
			
			if (Pattern.matches("\\d", character)) {
				JButton temp = new JButton(character);
				temp.addActionListener(new ButtonListener());
				NPanel.add(temp);
				continue;
			}
			
			JButton temp = new JButton(character);
			temp.addActionListener(new ButtonListener());
			SPanel.add(temp);
		}
		
		for (int i = 0; i < functions.length; i++) {
			JButton temp = new JButton(functions[i]);
			temp.addActionListener(new ButtonListener());
			FPanel.add(temp);
		}
	}
	
	public static Buttons getInstance() {
		return buttons;
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
