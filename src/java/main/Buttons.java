package main;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

/**
 * @author hubmazur
 *
 * Singleton class for returning panels with buttons alongside logic
 */
public class Buttons {

    private static Buttons buttons = null;

    private final JPanel UCPanel = new JPanel(); //upper-case
    private final JPanel LCPanel = new JPanel(); //lower-case
    private final JPanel SPanel = new JPanel(); //special, ?!>< etc..
    private final JPanel NPanel = new JPanel(); //numbers
    private final JPanel FPanel = new JPanel(); //functional, "Set", "Unlock"

    private final String[] functions = new String[]
            {"Help", "Settings", "Cycle", "Lock", "Unlock", "Clear", "Set"};

    /**
     * Constructs the various private panels with appropriate symbols.
     *
     * The constructor converts the index of the for loop into a char which will correspond
     * to those seen on the ASCII table. Anything before 33 and 126 aren't necessarily a "character" such as
     * an "A" or a "/".
     */
    private Buttons() {
        //usable ascii characters range
        for (int i = 33; i < 127; i++) {
            String character = String.valueOf((char) i);

            if (Pattern.matches("[a-z]", character)) {
                LCPanel.add(createButton(character));
            } else if (Pattern.matches("[A-Z]", character)) {
                UCPanel.add(createButton(character));
            } else if (Pattern.matches("\\d", character)) {
                NPanel.add(createButton(character));
            } else {
                SPanel.add(createButton(character));
            }
        }

        initFPanel();

        UCPanel.setName("UCPanel");
        LCPanel.setName("LCPanel");
        SPanel.setName("SPanel");
        NPanel.setName("NPanel");
        FPanel.setName("FPanel");

    }

    /**
     * <p>Initializes the "FPanel" which contains buttons that allow the user to view different input panels,
     * "lock" and "unlock", set new codes, and so on.
     * </p><p>
     * The FPanel has it's own layout, being one row and 3 columns. These columns are named for easier use. The left is
     * named "util", middle "mechanism", and right "set". The names that are set for what panel is hard coded but in
     * the next section it's assumed the functions String[] array hasn't been changed.
     * </p><p>
     * util is used for utility actions, such as cycling character panels and viewing the "Help" and "Settings" windows.
     * mechanism is used for the "Lock" and "Unlock" buttons. set is used for the "Set" and "Clear" buttons.
     * </p>
     */
    private void initFPanel() {
        FPanel.setLayout(new GridLayout(1, 3));

        JPanel util = new JPanel();
        JPanel mechanism = new JPanel();
        JPanel set = new JPanel();

        util.setName("util");
        mechanism.setName("mechanism");
        set.setName("set");

        for (int i = 0; i < functions.length; i++) {
            if (i < 2) {
                JButton button = createButton(functions[i]);
                button.setToolTipText("Work in progress");
                util.add(button);
            } else if (i < 3) {
                util.add(createButton(functions[i]));
            } else if (i < 5) {
                mechanism.add(createButton(functions[i]));
            } else if (i < 7) {
                set.add(createButton(functions[i]));
            }
        }

        FPanel.add(util);
        FPanel.add(mechanism);
        FPanel.add(set);
    }

    /**
     * Helper function to create buttons basic functionality
     *
     * @param name name to give the button; internally and externally
     * @return a JButton with a listener, and using name an internal name and display name
     */
    private JButton createButton(String name) {
        JButton button = new JButton(name);
        button.setName(name);
        button.addActionListener(new ButtonListener());
        return button;
    }

    /**
     * Function to get a panel by using a parameter
     *
     * @param panelName Name of the panel to return, case sensitive
     * @return a JPanel corresponding to panelName.
     *         Returns an empty JPanel if panelName can't match an existing panel.
     */
    public JPanel getPanel(String panelName) {
        if (panelName.equals("UCPanel")) {
            return getUCPanel();
        }
        if (panelName.equals("LCPanel")) {
            return getLCPanel();
        }
        if (panelName.equals("SPanel")) {
            return getSPanel();
        }
        if (panelName.equals("NPanel")) {
            return getNPanel();
        }
        if (panelName.equals("FPanel")) {
            return getFPanel();
        }

        return new JPanel();
    }

    /**
     * @return the UCPanel
     */
    public JPanel getUCPanel() {
        return UCPanel;
    }

    /**
     * @return the LCPanel
     */
    public JPanel getLCPanel() {
        return LCPanel;
    }

    /**
     * @return the SPanel
     */
    public JPanel getSPanel() {
        return SPanel;
    }

    /**
     * @return the NPanel
     */
    public JPanel getNPanel() {
        return NPanel;
    }

    /**
     * @return the FPanel
     */
    public JPanel getFPanel() {
        return FPanel;
    }

    /**
     * @return an instance of Buttons
     */
    public static Buttons getInstance() {
        if (buttons == null) buttons = new Buttons();
        return buttons;
    }

}