package managers;

import dataModels.AccountDataModel;
import dataModels.PlayerProfileDataModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
public class DatabaseManager {
    /* Singleton */
    private static DatabaseManager instance = null;

    private DatabaseManager() { }

    public static DatabaseManager getInstance() {
        if (instance == null) {

            instance = new DatabaseManager();
        }

        return instance;
    }
    /* Singleton */

    private static Scanner sc;
    public static ArrayList<AccountDataModel> accounts = new ArrayList<>();
    public static ArrayList<PlayerProfileDataModel> playerProfiles = new ArrayList<>();

    public void createFile(String filePath){
        try {
            File myFile = new File(filePath);
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /*
    public void load() {
        try {
            File file = new File(filepath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] lineArray = line.split("|");
            }
        }
    }


     */
    public static void loadData() {
        try {
            File myFile = new File("accounts.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] lineArray = line.split(",");

                accounts.add(new AccountDataModel(Integer.parseInt(lineArray[0]), lineArray[1], lineArray[2]));
            }
            myReader.close();
            myFile = new File("playerprofiles.txt");
            myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] lineArray = line.split(",");

                playerProfiles.add(new PlayerProfileDataModel(Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[1]), Integer.parseInt(lineArray[2])));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(playerProfiles.size());
    }

    public static void saveData(String filePath) {
        try {
            FileWriter myWriter = new FileWriter("accounts.txt");
            for (AccountDataModel account : accounts) {
                myWriter.write(account.getId() +","+ account.getUsername() + "," + account.getPassword());
                if (accounts.indexOf(account) != accounts.size()-1) {
                    myWriter.write("\n");
                }
            }
            myWriter.close();

            myWriter = new FileWriter("playerprofiles.txt");
            for (PlayerProfileDataModel pp : playerProfiles) {
                myWriter.write(pp.getAccountId() +","+ pp.getWins() + "," + pp.getLosses());
                if (playerProfiles.indexOf(pp) != playerProfiles.size()-1) {
                    myWriter.write("\n");
                }
            }

            myWriter.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void createNewAccount(String username, String password) {
        // accounts
        int id = accounts.get(accounts.size()-1).getId() + 1;
        AccountDataModel newAccount = new AccountDataModel(id, username, password);
        accounts.add(newAccount);

        // player profiles
        PlayerProfileDataModel newPlayerProfile = new PlayerProfileDataModel(id, 0, 0);
        playerProfiles.add(newPlayerProfile);
    }

    public static AccountDataModel getAccount(String username) {
        for (AccountDataModel account : accounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    public static AccountDataModel getAccount(int accountId) {
        if (accountId < accounts.size()) {
            return accounts.get(accountId);
        }
        return null;
    }

    public static PlayerProfileDataModel getPlayerProfileDataModel(int accountId) {
        return playerProfiles.get(accountId);
    }

    public static boolean usernameExists(String username) {
        boolean bool = false;
        for (AccountDataModel account : accounts) {
            if (account.getUsername().equals(username)) {
                bool = true;
            }
        }
        return bool;
    }

    public static boolean passwordExists(String password) {
        return true;
    }

    public static boolean usernamePasswordMatch(String username, String password) {
        boolean bool = false;
        for (AccountDataModel account : accounts) {
            if (account.getUsername().equals(username)) {
                System.out.println("DatabaseManager: username Match.");
                if (account.getPassword().equals(password)) {
                    System.out.println("DatabaseManager: password Match.");
                    bool = true;
                }
            }
        }
        return bool;
    }

    static class CompareByWins implements Comparator<PlayerProfileDataModel> {

        @Override
        public int compare(PlayerProfileDataModel o1, PlayerProfileDataModel o2) {
            return o2.getWins()-o1.getWins();
        }
    }
    public static ArrayList<PlayerProfileDataModel> getSortedLeaderboardList() {
        ArrayList<PlayerProfileDataModel> sortedLeaderboardList = new ArrayList<>();

        for (PlayerProfileDataModel pp : playerProfiles) {
            sortedLeaderboardList.add(pp);
        }

        Collections.sort(sortedLeaderboardList, new CompareByWins());

        return sortedLeaderboardList;
    }

    public static void addWin() {
        int currentWins = AppManager.getInstance().getCurrentPlayerProfile().getWins();
        AppManager.getInstance().getCurrentPlayerProfile().setWins(currentWins + 1);
        saveData("playerprofiles.txt");
    }

    public static void addLoss() {
        int currentLosses = AppManager.getInstance().getCurrentPlayerProfile().getLosses();
        AppManager.getInstance().getCurrentPlayerProfile().setLosses(currentLosses + 1);
        saveData("playerprofiles.txt");
    }
}
