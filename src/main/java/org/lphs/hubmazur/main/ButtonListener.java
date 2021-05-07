package main;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import main.Main;

public class ButtonListener extends JFrame implements ActionListener{
	public void actionPerformed(ActionEvent e)
	{

		String eventName = e.getActionCommand();
		System.out.println("I see a: "+eventName);
		if (eventName.length() == 1) {
			Main.getInstance().setTextField(eventName);
			
		}
		if(eventName == "Set"){
			/**
			panel2.remove(button_lock);
			validate();
			panel2.add(test);
			validate();
			*/

		}
	}
}
