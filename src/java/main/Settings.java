package main;

import javax.swing.*;
import java.awt.*;

/**
 * @author hubmazur
 *
 * Singleton Settings which is a window to allow the user to change the behavior of the window
 */
public class Settings extends JFrame {
    
    private static Settings settings = null;
    
    public Settings() {
        super("Settings");

        setAlwaysOnTop(true);
        requestFocus();
        setResizable(false);
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLayout(new GridLayout(1, 1));
        add(new JLabel("setting test"));
    }

    /**
     * @return the {@code Settings} instance
     */
    public static Settings getInstance() {
        if (settings == null) settings = new Settings();
        return settings;
    }

    /**
     * Shows the window, return the {@code Settings} instance so it can be used alongside
     * {@code getInstance} in one line
     *
     * @return the {@code Settings} instance
     */
    public Settings showSettings() {
        settings.setVisible(true);
        return this;
    }
}
