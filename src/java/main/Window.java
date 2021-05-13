package main;

import javax.swing.*;
import java.awt.*;

/**
 * @author hubmazur
 *
 * The main Window to display the ComboLock
 */
public class Window extends JFrame {

    private static Window window;
    private static final long serialVersionUID = 1L;

    private final JTextField textbox;
    private final JLabel info;
    private JPanel inputPadPanel;

    private final JPanel util;
    private final JPanel mechanism;
    private final JPanel set;

    private final transient Buttons buttons = Buttons.getInstance();//this program technically getting serialized right?

    /**
     * Constructor to create the window for the user to interact on.
     *
     * The window is the program, once closed the entire application will close. It also not resizable, so the user
     * does not make the window look ugly by resizing it to awkward proportions. It also allows me to use JLabels in a
     * horrible way.
     *
     * The constructor creates everything besides the buttons. This includes the JTextField and JLabels.
     */
    private Window() {

        super("Combination Lock");

        setResizable(false);
        setSize(520, 430);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        inputPadPanel = buttons.getUCPanel();
        JPanel functionPadPanel = buttons.getFPanel();
        JPanel inputPanel = new JPanel();
        JPanel information = new JPanel();

        /*
         * Component does not have setEnabled(boolean), JPanel does
         * Also, we are guaranteed for these to be JPanels
         * unless the creation of FPanel in Buttons is changed
         */
        util = (JPanel) functionPadPanel.getComponent(0);
        mechanism = (JPanel) functionPadPanel.getComponent(1);
        set = (JPanel) functionPadPanel.getComponent(2);

        util.getComponent(0).setEnabled(true);
        util.getComponent(1).setEnabled(true);

        mechanism.getComponent(0).setEnabled(false);
        mechanism.getComponent(1).setEnabled(false);

        textbox = new JTextField(40);
        info = new JLabel("Eep! Set a code!");
        inputPanel.add(new JPanel().add(info));
        inputPanel.add(textbox);

		/*
		this looks horrible but I don't feel like messing with layout managers
		 */
        JLabel title = new JLabel(
                "                                Combination Lock by Hubert Mazur                             "
        );
        JLabel version = new JLabel(
                "                                            Version 1.0                                          "
        );
        information.add(title);
        information.add(version);

        add(information);
        add(inputPanel);
        add(functionPadPanel);
        add(inputPadPanel);

        setVisible(true);
    }



    /**
     * Function to change the inputPadPanel to the "next" inputPadPanel in a set sequence.
     * The sequence is as follows: Upper Case, Lower Case, "Special" (?!.,'"[] etc.), Numbers.
     * This function calls replacePanels to properly remove the current panel and place the next one in sequence.
     */
    public void cyclePanels() {
        String name = inputPadPanel.getName();

        if (name.equals("UCPanel")) {
            replacePanels("LCPanel");
        }
        if (name.equals("LCPanel")) {
            replacePanels("SPanel");
        }
        if (name.equals("SPanel")) {
            replacePanels("NPanel");
        }
        if (name.equals("NPanel")) {
            replacePanels("UCPanel");
        }
    }

    /**
     * Helper function used by cyclePanels to replace panels.
     *
     * @param replacement name of the panel
     */
    private void replacePanels(String replacement) {
        inputPadPanel.setVisible(false);
        remove(inputPadPanel);
        validate();
        inputPadPanel = buttons.getPanel(replacement);
        add(inputPadPanel);
        validate();
        inputPadPanel.setVisible(true);
    }

    /**
     * @return the {@code JTextField} textbox
     */
    public JTextField getTextbox() {
        return textbox;
    }

    /**
     * @return the {@code JLabel} info
     */
    public JLabel getInfo() {
        return info;
    }

    /**
     * @return the {@code JPanel} set
     */
    public JPanel getSet() {
        return set;
    }

    /**
     * @return the {@code JPanel} mechanism
     */
    public JPanel getMechanism() {
        return mechanism;
    }

    /**
     * Convenience function for other classes to use to access the window instance.
     *
     * This method is mostly used by the ButtonListener class to relay what buttons are being input and handling
     * them accordingly.
     *
     * @return the {@code Window} instance
     */
    public static Window getWindow() {
        return window;
    }

    /**
     * Main method to create a new Window instance, in turn initializing the main window.
     *
     * Although making this class a Singleton class would be ideal, setting the window field as null would cause some
     * complications. I guess this class would still be considered a Singleton but without the whole null part.
     *
     * @param args arguments which won't be used whatsoever in this app. Might be used in the future tho
     */

    public static void main(String[] args) {
        window = new Window();
    }
}
