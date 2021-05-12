package main;

import javax.swing.*;
import java.awt.*;

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
     * @return the settings instance
     */
    public static Settings getInstance() {
        if (settings == null) settings = new Settings();
        return settings;
    }

    /**
     * Shows the window, return the settings instance so it can be used alongside <code>getInstance</code> in one line
     *
     * @return the settings instance
     */
    public Settings showSettings() {
        settings.setVisible(true);
        return settings;
    }
}
