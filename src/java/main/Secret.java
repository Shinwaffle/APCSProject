package main;

import javax.swing.*;

/**
 * @author hubmazur
 *
 *
 */
public class Secret {

    private static Secret secret = null;

    private static final Window window = Window.getWindow();
    private final JTextField textbox = window.getTextbox();
    private final JPanel set = window.getSet();
    private final JPanel mechanism = window.getMechanism();
    private final JLabel info = window.getInfo();
    private String code = "";
    private boolean isSet = false;

    private Secret() {}

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

        if (isSet && textbox.getText().equals(code)) {
            info.setText("Replaced code with new one!");
            clearInfoTextField();
            return;
        }

        isSet = true;
        code = textbox.getText();
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
        if (textbox.getText().equals(code)) {
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

    public static Secret getInstance() {
        if (secret == null) secret = new Secret();
        return secret;
    }
}
