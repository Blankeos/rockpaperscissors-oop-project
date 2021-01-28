package views;

import javax.swing.*;

public class RegisterForm extends View {
    private JPanel mainPanel;
    public JTextField usernameField;
    public JPasswordField passwordField;
    public JButton registerButton;
    public JButton backButton;

    public RegisterForm () {
        panelInstance = mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
