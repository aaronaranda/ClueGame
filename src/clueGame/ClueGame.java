package clueGame;

import java.util.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ClueGame extends JFrame {
	private CardsPanel cardsPanel;
	private GameControlPanel gameControlPanel;
	
	private static Board board;
	
	public ClueGame() {
		setSize(800, 800);
		setTitle("Clue! Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardsPanel = new CardsPanel();
		gameControlPanel = new GameControlPanel();
		
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();	
		add(board, BorderLayout.CENTER);
		add(cardsPanel, BorderLayout.EAST);
		add(gameControlPanel, BorderLayout.SOUTH);
	}	

	
	public static void main(String[] args) {
		
		ClueGame gui = new ClueGame();
	    gui.pack();
		gui.setVisible(true);		
		
		String name = board.getPlayer('1').getName();
	    
	    // create a jframe
	    JFrame frame = new JFrame("Intro");

	    // show a joptionpane dialog using showMessageDialog
	    JOptionPane.showMessageDialog(frame,
	        "You are " + name + "\n" + "Can you find the solution" + "\n" + "before the computer players?",
	        "Welcome to Clue",
	        JOptionPane.INFORMATION_MESSAGE);
	    
	}	
}
