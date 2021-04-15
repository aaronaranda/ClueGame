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
	private String name;
	
	private static Board board;
	
	public ClueGame() {
		setSize(new Dimension(900, 900));
		setMinimumSize(new Dimension(900, 900));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue! Game");		
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout306.csv", "ClueSetup306.txt");
		board.initialize();	
		cardPanel = new CardPanel(board);
		gameControlPanel = new GameControlPanel(board);	
		board.setGCP(gameControlPanel);
		add(board, BorderLayout.CENTER);
		add(cardPanel, BorderLayout.EAST);
		add(gameControlPanel, BorderLayout.SOUTH);				
		name = board.getPlayer(0).getName();		
	}	
	

	public String yourName() {
		return name;
	}
	
	public Player getHuman() {
		return board.getPlayer(0);
	}
	
	public void setTurn() {
		gameControlPanel.setTurn(board.getPlayer(board.turnNumber % 6), board.diceRoll());
		
	}

	

	
	public static void main(String[] args) {		
		ClueGame gui = new ClueGame();	  
		gui.setVisible(true);						
	    // show a joptionpane dialog using showMessageDialog
	    JOptionPane.showMessageDialog(gui,
	        "You are " + gui.yourName() + "\n" + "Can you find the solution" + "\n" + "before the computer players?",
	        "Welcome to Clue",
	        JOptionPane.INFORMATION_MESSAGE);
	    
	    gui.setTurn();
	   
	    
	 
	    
	    
	    
	}	
}
