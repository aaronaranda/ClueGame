package clueGame;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class BoardGUI extends JFrame {
	private CardsPanel cardsPanel;
	private GameControlPanel gameControlPanel;
	private JPanel board;
	
	public BoardGUI() {
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardsPanel = new CardsPanel();
		gameControlPanel = new GameControlPanel();
		board = new JPanel();
		
		add(board, BorderLayout.CENTER);
		add(cardsPanel, BorderLayout.EAST);
		add(gameControlPanel, BorderLayout.SOUTH);
	}	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		BoardGUI gui = new BoardGUI();
		gui.pack();
		gui.setVisible(true);		
	}
	
	
}
