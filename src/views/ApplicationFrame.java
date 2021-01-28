package views;

import managers.DatabaseManager;

import javax.swing.*;

public class ApplicationFrame extends JFrame{

    public static ApplicationFrame instance = null;
    public ApplicationFrame(String title, JPanel panel) {
        super(title);
        instance = this;
        instance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        changePanel(panel);
    }

    public static void changePanel(JPanel panel) {
        instance.setContentPane(panel);
        instance.pack();
        instance.setVisible(true);
    }

    public static void repack() {
        instance.pack();
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
