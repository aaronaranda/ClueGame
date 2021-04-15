package clueGame;

import java.util.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class ClueGame extends JFrame {
	private CardPanel cardPanel;
	private GameControlPanel gameControlPanel;
	
	private static Board board;
	
	public ClueGame() {
		setSize(new Dimension(700, 700));
		setMinimumSize(new Dimension(700, 700));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue! Game");		
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();	
		cardPanel = new CardPanel();
		gameControlPanel = new GameControlPanel();
		
		add(board, BorderLayout.CENTER);
		add(cardPanel, BorderLayout.EAST);
		add(gameControlPanel, BorderLayout.SOUTH);

	}	
	

	

	
	public static void main(String[] args) {
		
		ClueGame gui = new ClueGame();
	  
		gui.setVisible(true);		
		
		String name = board.getPlayer(1).getName();
	    
	    // create a jframe
	    JFrame frame = new JFrame("Intro");

	    // show a joptionpane dialog using showMessageDialog
	    JOptionPane.showMessageDialog(frame,
	        "You are " + name + "\n" + "Can you find the solution" + "\n" + "before the computer players?",
	        "Welcome to Clue",
	        JOptionPane.INFORMATION_MESSAGE);
	    
	}	
}
