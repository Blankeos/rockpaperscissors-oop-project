package views;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class GameForm extends View {

    private JPanel mainPanel;
    public JLabel stateLabel;
    public JButton rockButton;
    public JButton paperButton;
    public JButton scissorsButton;
    public JLabel playerChoice;
    public JLabel enemyChoice;
    public JPanel playerChoicePanel;
    public JPanel enemyChoicePanel;
    public ImageIcon rock = new ImageIcon("imgs//rock.png");
    public ImageIcon paper = new ImageIcon("imgs//paper.png");;
    public ImageIcon scissors = new ImageIcon("imgs//scissors.png");;

    public ImageIcon pHand1 = new ImageIcon("imgs//pHand1.png");
    public ImageIcon pHand2 = new ImageIcon("imgs//pHand2.png");
    public ImageIcon pHand3 = new ImageIcon("imgs//pHand3.png");
    public ImageIcon eHand1 = new ImageIcon("imgs//eHand1.png");
    public ImageIcon eHand2 = new ImageIcon("imgs//eHand2.png");
    public ImageIcon eHand3 = new ImageIcon("imgs//eHand3.png");

    public GameForm() {
        panelInstance = mainPanel;
        playerChoice.setText("");
        enemyChoice.setText("");
        playerChoice.setIcon(pHand2);
        enemyChoice.setIcon(eHand2);
    }

    public void setPlayerChoice(int choice) {
        switch (choice) {
            case 0 -> playerChoice.setIcon(rock);
            case 1 -> playerChoice.setIcon(paper);
            case 2 -> playerChoice.setIcon(scissors);
        }
        ApplicationFrame.repack();
    }

    public void setEnemyChoice(int choice) {
        switch (choice) {
            case 0 -> enemyChoice.setIcon(rock);
            case 1 -> enemyChoice.setIcon(paper);
            case 2 -> enemyChoice.setIcon(scissors);
        }
        ApplicationFrame.repack();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


}
