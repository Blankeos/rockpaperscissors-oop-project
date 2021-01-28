package managers;

import dataModels.AccountDataModel;
import dataModels.PlayerProfileDataModel;

public class AppManager {
    /* Singleton */
    private static AppManager instance = null;

    private AppManager() { }

    public static AppManager getInstance() {
        if (instance == null) {

            instance = new AppManager();
        }

        return instance;
    }
    /* Singleton */

    private AccountDataModel currentAccount;
    private PlayerProfileDataModel currentPlayerProfile;

    public void setLoginSession(AccountDataModel currentAccount) {
        this.currentAccount = currentAccount;
        this.currentPlayerProfile = DatabaseManager.getPlayerProfileDataModel(currentAccount.getId());
    }

    public AccountDataModel getCurrentAccount() {
        return currentAccount;
    }

    public PlayerProfileDataModel getCurrentPlayerProfile() {
        return currentPlayerProfile;
    }

    public void resetLoginSession() {
        currentAccount = null;
        currentPlayerProfile = null;
    }

    public void printEveryData() { //DEBUGGING
        PlayerProfileDataModel cpp = getCurrentPlayerProfile();
        AccountDataModel ca = getCurrentAccount();
        System.out.println("Profile ID: " + cpp.getAccountId());
        System.out.println("Wins: " + cpp.getWins());
        System.out.println("Losses: " + cpp.getLosses());

    }
}
