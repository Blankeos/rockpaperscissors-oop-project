package views;

import javax.swing.*;

public class LoginForm extends View {
    public JPanel mainPanel;
    public JTextField usernameField;
    public JPasswordField passwordField;
    public JButton loginButton;
    public JButton registerButton;

    public LoginForm() {
        panelInstance = mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
