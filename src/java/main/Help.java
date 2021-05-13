package main;

import javax.swing.*;
import java.awt.*;

/**
 * @author hubmazur
 *
 * Singleton Help which is a window to inform the user about the program
 */
public class Help extends JFrame {

    private static Help help = null;

    public Help() {

        super("Help");

        setAlwaysOnTop(true);
        requestFocus();
        setResizable(false);
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLayout(new GridLayout(1, 1));
        add(new JLabel("test"));
    }

    /**
     * @return the {@code Help} instance
     */
    public static Help getInstance() {
        if (help == null) help = new Help();
        return help;
    }

    /**
     * Shows the window, return the Help instance so it can be used alongside
     * {@code getInstance} in one line
     *
     * @return the {@code Help} instance
     */
    public Help showHelp() {
        help.setVisible(true);
        return this;
    }

}
