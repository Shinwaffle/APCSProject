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
        Secret secret = Secret.getInstance();

        if (eventName.length() == 1) {
            secret.addToTextField(eventName);
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
            secret.clearInfoTextField();
        }
        if (eventName.equals("Set")) {
            secret.setSecret();
        }
        if (eventName.equals("Lock")) {
            secret.setLocked();
        }
        if (eventName.equals("Unlock")) {
            secret.setUnlocked();
        }
    }
}
