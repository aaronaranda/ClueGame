package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

import javax.swing.border.*;

public class GameControlPanel extends JPanel {

    public GameControlPanel() {
       
                
    }
    
    private void createLayout() {

    }

    public void setTurn(Player player) {
        // Do something
    }


    public static void main(String[] args) {
    GameControlPanel controlPanel = new GameControlPanel(); 
    JFrame frame = new JFrame();
    frame.setContentPane(controlPanel);
    frame.setSize(750, 180);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    controlPanel.setTurn(new ComputerPlayer("Dummy"));



    }


}
