package main;

import javax.swing.*;
import java.awt.event.*;

public class ButtonListener extends JFrame implements ActionListener {

	public void actionPerformed(ActionEvent e)
	{
		Window window = Window.window;
		String eventName = e.getActionCommand();

		if (eventName.length() == 1) {
			window.setTextField(eventName);
		}
		if (eventName.equals("Cycle")) {
			window.cyclePanels();
		}
		if (eventName.equals("Clear")) {
			window.clearTextField();
		}
		if(eventName.equals("Set")){
			/*
			panel2.remove(button_lock);
			validate();
			panel2.add(test);
			validate();
			*/

		}
	}
}
