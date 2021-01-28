package controllers;

import managers.ControllerManager;
import javax.swing.*;
import managers.DatabaseManager;
import views.ApplicationFrame;
import views.RegisterForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.*;

public class RegisterController extends Controller implements KeyListener{

    RegisterController instance;

    RegisterForm registerForm;

    public RegisterController () {
        registerForm = new RegisterForm();
        view = registerForm;
        instance = this;

        registerForm.passwordField.addKeyListener(this);

        hookEvents();
    }

    public void hookEvents () {
        registerForm.registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });

        registerForm.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerManager.changeController(new LoginController());
            }
        });
    }

    void register() {
        String username = registerForm.usernameField.getText();
        String password = registerForm.passwordField.getText();

        // check if username is not in database yet.
        if (!DatabaseManager.usernameExists(username)) {
            // check if username is empty
            if (username.length() != 0) {
                // check if password is already 3 characters long.
                if (password.length() >= 3) {
                    DatabaseManager.createNewAccount(username, password);
                    JOptionPane.showMessageDialog(ApplicationFrame.instance, username + " is successfully registered!");
                    DatabaseManager.saveData("accounts.txt");
                } else {
                    JOptionPane.showMessageDialog(ApplicationFrame.instance, "Password must be at least 3 characters long.", "Error", 0);
                }
            } else {
                JOptionPane.showMessageDialog(ApplicationFrame.instance, "Username can't be empty.", "Error", 0);
            }

        } else {
            JOptionPane.showMessageDialog(ApplicationFrame.instance, "Username is already taken.", "Error", 0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Non
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            register();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Non
    }
}
