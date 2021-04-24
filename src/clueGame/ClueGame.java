package clueGame;

import java.awt.*;
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
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();	
		cardPanel = new CardPanel();
		gameControlPanel = new GameControlPanel();	
		board.setGCP(gameControlPanel);
		add(board, BorderLayout.CENTER);
		add(cardPanel, BorderLayout.EAST);
		add(gameControlPanel, BorderLayout.SOUTH);				
		name = board.getPlayer(0).getName();		
	}	
	
	public String yourName() {
		return name;
	}
	
	public void startGame() {
		gameControlPanel.start();
	}

	public static void main(String[] args) {		
		ClueGame gui = new ClueGame();	  
		gui.setVisible(true);						
	    // show a joptionpane dialog using showMessageDialog
	    JOptionPane.showMessageDialog(gui,
	        "You are " + gui.yourName() + "\n" + "Can you find the solution" + "\n" + "before the computer players?",
	        "Welcome to Clue",
	        JOptionPane.INFORMATION_MESSAGE);	    
	    gui.startGame();	    
	}	
}
