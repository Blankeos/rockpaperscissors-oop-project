package views;

import javax.swing.*;

public class ProfileForm extends View {
    private JPanel mainPanel;
    public JLabel usernameLabel;
    public JLabel passwordLabel;
    public JLabel winsLabel;
    public JLabel lossesLabel;
    public JButton backButton;

    public ProfileForm() {
        panelInstance = mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
