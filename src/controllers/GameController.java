package controllers;

import managers.AppManager;
import managers.ControllerManager;
import managers.DatabaseManager;
import views.ApplicationFrame;
import views.GameForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameController extends Controller{
    GameController instance;

    public GameForm gameForm;

    int rpsChoice;

    public GameController() {
        gameForm = new GameForm();
        view = gameForm;
        instance = this;

        hookEvents();
    }

    void hookEvents() {
        gameForm.rockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setButtonsEnable(false);
                rpsChoice = 0;
                startRPS();
            }
        });

        gameForm.paperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rpsChoice = 1;
                setButtonsEnable(false);
                startRPS();
            }
        });

        gameForm.scissorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rpsChoice = 2;
                setButtonsEnable(false);
                startRPS();
            }
        });
    }

    void setButtonsEnable(boolean bool) {
        gameForm.rockButton.setEnabled(bool);
        gameForm.paperButton.setEnabled(bool);
        gameForm.scissorsButton.setEnabled(bool);
    }

    int k = 0;
    void startRPS() {
        int enemyChoice = generateEnemyChoice();
        int playerChoice = rpsChoice;
        char result = calculateRPSResults(playerChoice, enemyChoice);

        RPSAnim playerHandAnim = new RPSAnim(
                gameForm.playerChoice,
                gameForm.pHand1,
                gameForm.pHand2,
                gameForm.pHand3,
                70
        );
        RPSAnim enemyHandAnim = new RPSAnim(
                gameForm.enemyChoice,
                gameForm.eHand1,
                gameForm.eHand2,
                gameForm.eHand3,
                70
        );

        Timer timer = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(k) {
                    case 0:
                        gameForm.stateLabel.setText("ROCK!");
                        break;
                    case 1:
                        gameForm.stateLabel.setText("PAPER!");
                        break;
                    case 2:
                        gameForm.stateLabel.setText("SCISSORS!");
                        break;
                    case 3:
                        playerHandAnim.timer.stop();
                        enemyHandAnim.timer.stop();
                        //gameForm.playerChoice.setIcon(gameForm.pHand3);
                        //gameForm.enemyChoice.setIcon(gameForm.eHand3);
                        gameForm.stateLabel.setText("SHOOT!");
                        break;
                    case 4:
                        showRPSResults(playerChoice, enemyChoice, result);
                        break;
                    case 7:
                        showNextState(result);
                        break;
                    case 10: // Go back to menu or try again
                        if (result == 'd') {
                            ControllerManager.changeController(new GameController());
                        } else {
                            ControllerManager.changeController(new MenuController());
                        }
                        break;
                    case 11:
                        ((Timer)e.getSource()).stop();
                        break;
                }
                k++;
            }
        });
        timer.start();
    }

    void showRPSResults(int p1, int p2, char result) {
        gameForm.setPlayerChoice(p1);
        gameForm.setEnemyChoice(p2);
        switch (result) {
            case 'w':
                gameForm.stateLabel.setForeground(Color.green);
                gameForm.stateLabel.setText("You won!");
                break;
            case 'l':
                gameForm.stateLabel.setForeground(Color.red);
                gameForm.stateLabel.setText("You lost!");
                break;
            case 'd':
                gameForm.stateLabel.setForeground(Color.orange);
                gameForm.stateLabel.setText("It's a draw!");
                break;
        }
        //AppManager.getInstance().printEveryData();
    }

    void showNextState(char result) {
        switch(result) {
            case 'w':
                gameForm.stateLabel.setText(
                        String.format("You now have %d wins.",
                                AppManager.getInstance().getCurrentPlayerProfile().getWins()));
                break;
            case 'l':
                gameForm.stateLabel.setText(
                        String.format("You now have %d losses.",
                                AppManager.getInstance().getCurrentPlayerProfile().getLosses()));
                break;
            case 'd':
                gameForm.stateLabel.setText("Try again...");
                break;
        }
    }

    char calculateRPSResults(int p1, int p2) {
        char result;
        if ((p1 + 1) % 3 == p2) {
            result = 'l';
            DatabaseManager.addLoss();
        }
        else if (p1 == p2) {
            result = 'd';
        }
        else {
            result = 'w';
            DatabaseManager.addWin();
        }
        return result;
    }

    int generateEnemyChoice() {
        return ThreadLocalRandom.current().nextInt(0, 2 + 1);
    }

    class RPSAnim implements ActionListener {
        ImageIcon frame1;
        ImageIcon frame2;
        ImageIcon frame3;
        public Timer timer;
        JLabel jLabelReference;
        int count = 0;

        public RPSAnim(JLabel jLabelReference, ImageIcon frame1,ImageIcon frame2, ImageIcon frame3, int delay) {
            this.frame1 = frame1;
            this.frame2 = frame2;
            this.frame3 = frame3;
            this.jLabelReference = jLabelReference;
            timer = new Timer(delay,this);
            timer.start();
        }

        int crement = 1;
        @Override
        public void actionPerformed(ActionEvent e) {
            if (count > 2 || count < 0) {
                crement = crement * -1;
            }
            if (count == 0) jLabelReference.setIcon(frame1);
            if (count == 1) jLabelReference.setIcon(frame2);
            if (count == 2) jLabelReference.setIcon(frame3);

            count += crement;

        }
    }

}

