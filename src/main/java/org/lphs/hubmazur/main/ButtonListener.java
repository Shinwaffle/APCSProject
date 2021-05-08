package main;

import javax.swing.*;
import java.awt.event.*;

public class ButtonListener extends JFrame implements ActionListener{

	private static Window window = null;
	public void actionPerformed(ActionEvent e)
	{
		window = Window.window;
		String eventName = e.getActionCommand();
		System.out.println("I see a: "+eventName);
		if (eventName.length() == 1) {
			window.setTextField(eventName);
		}
		if (eventName.equals("Cycle")) {
			window.cyclePanels();
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
