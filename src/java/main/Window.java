package main;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public static Window window;
    private static final long serialVersionUID = 1L;
    private final int WINDOW_WIDTH = 520;  // Width
    private final int WINDOW_HEIGHT = 430; // Height

    private final JTextField textbox;
    private JPanel inputPadPanel;
    private final JLabel info;

    private final JPanel util;
    private final JPanel mechanism;
    private final JPanel set;

    private final Buttons buttons = Buttons.getInstance();
    private String Secret = "";
    private boolean isSet = false;


    /**
     * The window itself
     */
    private Window() {

        super("Combination Lock");

        setBackground(Color.black);
        setResizable(false);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
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

    public void setSecret() {
        if (textbox.getText().isEmpty()) {
            info.setText("Enter a code you dummy!");
            return;
        }

        if (textbox.getText().length() != 3) {
            info.setText("Code must be 3 characters long!");
            return;
        }

        if (isSet) {
            info.setText("Replaced code with new one!");
            return;
        }

        isSet = true;
        Secret = textbox.getText();
        info.setText("New code set!");
        clearInfoTextField();
        mechanism.getComponent(0).setEnabled(true);
    }

    public void setLocked() {
        info.setText("Enter code");
        textbox.setText("");
        mechanism.getComponent(0).setEnabled(false);
        mechanism.getComponent(1).setEnabled(true);
        set.getComponent(1).setEnabled(false);
    }

    public void setUnlocked() {
        if (textbox.getText().equals(Secret)) {
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
     * used by ButtonListener to signal the window to
     * go to the next panel of characters
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
     * helper function for cyclePanels to reduce verbosity
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

    public void addToTextField(String character) {
        if (textbox.getText().length() < 3) {
            textbox.setText(textbox.getText() + character);
        }
    }

    public void clearInfoTextField() {
        textbox.setText("");
    }

    /**
     * The window method creates an instance of the
     * GridPanelwindow class, causing it to display
     * its window.
     */

    public static void main(String[] args) {
        window = new Window();
    }
}
