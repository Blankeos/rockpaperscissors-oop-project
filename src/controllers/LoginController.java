package controllers;

import managers.AppManager;
import managers.ControllerManager;
import views.ApplicationFrame;
import views.LoginForm;
import javax.swing.*;
import managers.DatabaseManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;

public class LoginController extends Controller implements KeyListener{

    LoginController instance;

    public LoginForm loginForm;

    public LoginController () {
        loginForm = new LoginForm();
        view = loginForm;
        instance = this;

        loginForm.passwordField.addKeyListener(this);

        hookEvents();

    }

    void hookEvents () {

        loginForm.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        loginForm.registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerManager.changeController(new RegisterController());
            }
        });



    }

    void login() {
        String username = loginForm.usernameField.getText();
        String password = loginForm.passwordField.getText();
        boolean match = DatabaseManager.usernamePasswordMatch(username, password);
        if (match) {
            // login session
            AppManager.getInstance().setLoginSession(DatabaseManager.getAccount(username));
            AppManager.getInstance().printEveryData();
            JOptionPane.showMessageDialog(ApplicationFrame.instance, "Login successful.", "Success", 1);
            ControllerManager.changeController(new MenuController());
        } else {
            JOptionPane.showMessageDialog(ApplicationFrame.instance, "Login failed.", "Error", 0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Non
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
