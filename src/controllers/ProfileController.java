package controllers;
import dataModels.AccountDataModel;
import dataModels.PlayerProfileDataModel;
import managers.AppManager;
import managers.ControllerManager;
import views.ProfileForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileController extends Controller {

    ProfileController instance;

    public ProfileForm profileForm;

    public ProfileController() {
        profileForm = new ProfileForm();
        view = profileForm;
        instance = this;

        updateText();
        hookEvents();
    }

    void updateText() {
        AccountDataModel account = AppManager.getInstance().getCurrentAccount();
        PlayerProfileDataModel playerProfile = AppManager.getInstance().getCurrentPlayerProfile();
        profileForm.usernameLabel.setText(account.getUsername());
        profileForm.passwordLabel.setText(account.getPassword());
        profileForm.winsLabel.setText(Integer.toString(playerProfile.getWins()));
        profileForm.lossesLabel.setText(Integer.toString(playerProfile.getLosses()));
    }

    void hookEvents() {
        profileForm.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerManager.changeController(new MenuController());
            }
        });
    }

}
