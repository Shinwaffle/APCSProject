package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author hubmazur
 *
 * Handles button input and uses the appropriate methods provided by the Window class.
 */
public class ButtonListener extends JFrame implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Window window = Window.getWindow();
        String eventName = e.getActionCommand();

        if (eventName.length() == 1) {
            window.addToTextField(eventName);
        }
        if (eventName.equals("Help")) {
            Help help = Help.getInstance().showHelp();
        }
        if (eventName.equals("Settings")) {
            Settings settings = Settings.getInstance().showSettings();
        }
        if (eventName.equals("Cycle")) {
            window.cyclePanels();
        }
        if (eventName.equals("Clear")) {
            window.clearInfoTextField();
        }
        if (eventName.equals("Set")) {
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
