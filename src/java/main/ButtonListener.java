package main;

import javax.swing.*;
import java.awt.event.*;

public class ButtonListener extends JFrame implements ActionListener {

	public void actionPerformed(ActionEvent e)
	{
		Window window = Window.window;
		String eventName = e.getActionCommand();

		if (eventName.length() == 1) {
			window.addToTextField(eventName);
		}
		if (eventName.equals("Cycle")) {
			window.cyclePanels();
		}
		if (eventName.equals("Clear")) {
			window.clearInfoTextField();
		}
		if(eventName.equals("Set")){
			window.setSecret();
		}
		if (eventName.equals("Lock")) {
			window.setLocked();
		}
		if (eventName.equals("Unlock")) {
			window.setUnlocked();
		}
	}
}
