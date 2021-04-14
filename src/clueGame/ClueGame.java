package clueGame;

import java.util.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ClueGame extends JFrame {
	private CardPanel cardPanel;
	private GameControlPanel gameControlPanel;
	
	private static Board board;
	
	public ClueGame() {
		//setSize(900, 900);
		setMinimumSize(new Dimension(1000, 1000));
		setPreferredSize(new Dimension(1000, 1000));
		setTitle("Clue! Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardPanel = new CardPanel();
		gameControlPanel = new GameControlPanel();
		
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout306.csv", "ClueSetup306.txt");
		board.initialize();	
		//setContentPane(board);
		add(board, BorderLayout.CENTER);
		add(cardPanel, BorderLayout.EAST);
		add(gameControlPanel, BorderLayout.SOUTH);
		board.setMinimumSize(new Dimension(900, 900));
		board.setPreferredSize(new Dimension(900, 900));
		
		setLocationRelativeTo(null);
	}	

	
	public static void main(String[] args) {
		
		ClueGame gui = new ClueGame();
	    gui.pack();
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
