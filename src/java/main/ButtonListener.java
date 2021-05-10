package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    /**
     * Gets the button that was pressed and determines what to do with it
     */
    public void actionPerformed(ActionEvent e) {
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
