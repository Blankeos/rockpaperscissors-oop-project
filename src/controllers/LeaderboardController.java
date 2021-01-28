package controllers;

import dataModels.PlayerProfileDataModel;
import managers.AppManager;
import managers.ControllerManager;
import managers.DatabaseManager;
import views.LeaderboardForm;
import views.ProfileForm;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LeaderboardController extends Controller {

    LeaderboardController instance;

    ArrayList<PlayerProfileDataModel> leaderboardList;

    public LeaderboardForm leaderboardForm;

    public LeaderboardController() {
        leaderboardForm = new LeaderboardForm();
        view = leaderboardForm;
        instance = this;

        leaderboardList = DatabaseManager.getSortedLeaderboardList();

        updateText();
        createTable();
        hookEvents();
    }

    void updateText() {
        // rank 1
        leaderboardForm.rank1Label.setText(DatabaseManager.getAccount(leaderboardList.get(0).getAccountId()).getUsername());
        leaderboardForm.winsLabel.setText(
                String.format("%d wins and %d losses",
                leaderboardList.get(0).getWins(),
                leaderboardList.get(0).getLosses()
                ));
    }

    void createTable() {
        Object[][] data = new Object[leaderboardList.size()-1][4];

        int dataIndex = 0;
        for (int i = 1; i < leaderboardList.size(); i++) {
            data[dataIndex][0] = i+1;
            data[dataIndex][1] = DatabaseManager.getAccount(leaderboardList.get(i).getAccountId()).getUsername();
            data[dataIndex][2] = leaderboardList.get(i).getWins();
            data[dataIndex][3] = leaderboardList.get(i).getLosses();
            dataIndex++;
        }

        leaderboardForm.leaderboardTable.setModel( new DefaultTableModel(
                data,
                new String[] {"Rank", "Username", "Wins", "Losses"}
        ));

        TableColumnModel columns = leaderboardForm.leaderboardTable.getColumnModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(0).setCellRenderer(centerRenderer);
        columns.getColumn(2).setCellRenderer(centerRenderer);
        columns.getColumn(3).setCellRenderer(centerRenderer);
    }

    void hookEvents() {
        leaderboardForm.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerManager.changeController(new MenuController());
            }
        });
    }

    /*
    public JLabel rank1Label;
    public JList indexList;
    public JList usernameList;
     */

}
