package main;

import javax.swing.*;
import java.awt.*;

/**
 * @author hubmazur
 *
 * The main Window to display the ComboLock
 */
public class Window extends JFrame {

    private static Window window; //keep as uninitialized to avoid any double window problems
    private static final long serialVersionUID = 1L;

    private final JTextField textbox;
    private JPanel inputPadPanel;
    private final JLabel info;

    private final JPanel util;
    private final JPanel mechanism;
    private final JPanel set;

    private final transient Buttons buttons = Buttons.getInstance();//this program technically getting serialized right?
    private String secret = "";
    private boolean isSet = false;


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

        util.getComponent(0).setEnabled(false);
        util.getComponent(1).setEnabled(false);

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
     * Sets the "Secret" of the lock after passing a bunch of conditionals.
     *
     * Although the user is provided buttons to input text into the info text-field, the user can avoid them entirely
     * and enter text themselves via direct input (keyboard). This is checked alongside other states in the text-field
     * to view a message to the user to inform them.
     */
    public void setSecret() {
        if (textbox.getText().isEmpty()) {
            info.setText("Enter a code you dummy!");
            return;
        }

        if (textbox.getText().length() != 3) {
            info.setText("Code must be 3 characters long!");
            return;
        }

        if (isSet && textbox.getText().equals(secret)) {
            info.setText("Replaced code with new one!");
            clearInfoTextField();
            return;
        }

        isSet = true;
        secret = textbox.getText();
        info.setText("New code set!");
        clearInfoTextField();
        mechanism.getComponent(0).setEnabled(true);
    }

    /**
     * Sets the lock as "Locked", restricting the user from setting a new code or locking it again.
     *
     * Once the lock is locked, it restricts other actions relating to the lock by disabling them.
     */
    public void setLocked() {
        info.setText("Enter code");
        clearInfoTextField();
        mechanism.getComponent(0).setEnabled(false);
        mechanism.getComponent(1).setEnabled(true);
        set.getComponent(1).setEnabled(false);
    }

    /**
     * If the input equals the Secret, it "unlocks" the lock and allowing the user to lock it again or set a new code.
     *
     * If the user doesn't input the correct Secret, the user will simply be informed. If it equals the Secret,
     * Lock and Set are enabled once again and Unlock is disabled.
     */
    public void setUnlocked() {
        if (textbox.getText().equals(secret)) {
            clearInfoTextField();
            mechanism.getComponent(0).setEnabled(true);
            mechanism.getComponent(1).setEnabled(false);
            set.getComponent(1).setEnabled(true);
            info.setText("Welcome!");
        } else {
            info.setText("Incorrect code!");
        }
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
     * Function used to add a character to the JTextField info
     *
     * @param character character to input into the JTextField info
     */
    public void addToTextField(String character) {
        if (textbox.getText().length() < 3) {
            textbox.setText(textbox.getText() + character);
        }
    }

    /**
     * Simply clears the info textfield.
     */
    public void clearInfoTextField() {
        textbox.setText("");
    }

    /**
     * Convenience function for other classes to use to access the window instance.
     *
     * This method is mostly used by the ButtonListener class to relay what buttons are being input and handling
     * them accordingly.
     *
     * @return the window instance
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
     * @param args arguments which won't be used whatsoever.
     */

    public static void main(String[] args) {
        window = new Window();
    }
}
