package controllers;

import managers.AppManager;
import managers.ControllerManager;
import managers.DatabaseManager;
import views.GameForm;
import views.MenuForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController extends Controller {

    MenuController instance;

    public MenuForm menuForm;

    public MenuController() {
        menuForm = new MenuForm();
        view = menuForm;
        instance = this;

        menuForm.welcomeLabel.setText("Welcome " + AppManager.getInstance().getCurrentAccount().getUsername() + "!");

        hookEvents();
    }

    void hookEvents() {
        menuForm.playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerManager.changeController(new GameController());
            }
        });

        menuForm.profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerManager.changeController(new ProfileController());
            }
        });

        menuForm.leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerManager.changeController(new LeaderboardController());
            }
        });

        menuForm.logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppManager.getInstance().resetLoginSession();
                ControllerManager.changeController(new LoginController());
            }
        });
    }

}


