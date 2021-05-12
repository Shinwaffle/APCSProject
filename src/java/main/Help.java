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
        setSize(1000, 1000);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLayout(new GridLayout(1, 1));
        add(new JLabel("test"));
    }

    /**
     * @return the Help instance
     */
    public static Help getInstance() {
        if (help == null) help = new Help();
        return help;
    }

    /**
     * Shows the window, return the Help instance so it can be used alongside <code>getInstance</code> in one line
     *
     * @return the help instance
     */
    public Help showHelp() {
        help.setVisible(true);
        return help;
    }

}
