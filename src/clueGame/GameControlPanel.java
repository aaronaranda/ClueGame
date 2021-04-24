package clueGame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

import javax.swing.border.*;

@SuppressWarnings("serial")
public class GameControlPanel extends JPanel {
	private static Board board = Board.getInstance();
	private Player player;
	
	//private String playerName;
	private JTextField playerName;
		
	//private int roll;
	private JTextField roll;
	private int numRoll;
	
	//private String guess;
	private JTextField guess;
	
	//private String guessResult;
	private JTextField guessResult;
	 
    public GameControlPanel() {    	
    	player = board.getPlayer(0);
    	board.setGCP(this);    	
    	// Sizing
    	setSize(new Dimension(900, 100));
    	setMinimumSize(new Dimension(900, 100));    	          
        setLayout(new GridLayout(2, 0));

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(1, 4));

        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayout(0, 2));
        
        // Whose turn is it?
    	JPanel turnPanel = new JPanel();
        turnPanel.setLayout(new GridLayout(2, 0));
    	playerName = new JTextField(15);
    	playerName.setBackground(Color.BLACK);
    	turnPanel.setBackground(Color.BLACK);
    	turnPanel.setBorder(new TitledBorder(new EtchedBorder(Color.CYAN, Color.WHITE),
                "TURN:", 0, 0, null, Color.WHITE));
        turnPanel.add(this.playerName);
               
        // Roll
        JPanel rollPanel = new JPanel();
        rollPanel.setLayout(new GridLayout(2, 0));
        roll = new JTextField(15);
        roll.setBackground(Color.BLACK);
        roll.setForeground(Color.WHITE);
        rollPanel.setBackground(Color.BLACK);
        rollPanel.setBorder(new TitledBorder(new EtchedBorder(Color.CYAN, Color.WHITE),
                "ROLL:", 0, 0, null, Color.WHITE));
        rollPanel.add(this.roll);

    	// Buttons
    	JButton accusationButton = new JButton("ACCUSE!");
    	accusationButton.setForeground(Color.WHITE);
        accusationButton.setOpaque(true);
    	accusationButton.setBackground(Color.BLACK);
    	accusationButton.setBorder(new BevelBorder(BevelBorder.RAISED, Color.CYAN, Color.WHITE));

        JButton nextButton = new JButton("NEXT!");
        nextButton.setForeground(Color.WHITE);
        nextButton.setOpaque(true);
        nextButton.setBackground(Color.BLACK);
        nextButton.setBorder(new BevelBorder(BevelBorder.RAISED, Color.CYAN, Color.WHITE));

        nextButton.addActionListener(new NextListener());
        accusationButton.addActionListener(new AccusationListener());        

        JPanel guessPanel = new JPanel();
    	this.guess = new JTextField(30);
    	guessPanel.add(this.guess);
    	guessPanel.setBorder(new TitledBorder(new EtchedBorder(Color.CYAN, Color.WHITE),
                "GUESS", 0, 0, null, Color.WHITE));
    	guessPanel.setForeground(Color.WHITE);
    	guessPanel.setBackground(Color.BLACK);

        JPanel resultPanel = new JPanel();
        this.guessResult = new JTextField(30);
        resultPanel.add(this.guessResult);
        resultPanel.setBorder(new TitledBorder(
                   new EtchedBorder(Color.CYAN, Color.WHITE), "RESULT", 0, 0, null, Color.WHITE));
        resultPanel.setForeground(Color.WHITE);
        resultPanel.setBackground(Color.BLACK);
        
        upperPanel.add(turnPanel);
        upperPanel.add(rollPanel);
        upperPanel.add(accusationButton);
        upperPanel.add(nextButton);

        lowerPanel.add(guessPanel);
        lowerPanel.add(resultPanel);

        add(upperPanel);
        add(lowerPanel);
    }
    
    public void start() {
    	player = board.getPlayer(0);
    	numRoll = board.diceRoll();    	
    	setTurn();
    	board.play(player, numRoll);
    }
     
    
    private class NextListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {						
			// must check if player moved or not
			if (!player.madeMove()) {
				JOptionPane.showMessageDialog(null, "Please finish your turn.");
			} else {
				player = board.getPlayer(Board.turnNumber % 6);
				numRoll = board.diceRoll();
				setTurn();
				play();
			}									
		}    	
    }
    
    private class AccusationListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		GuessBox accusation = new GuessBox();
    	}
    }
    
    //Sets turn
    public void setTurn() {
        playerName.setText(player.getName());
        playerName.setForeground(player.getColor());
        roll.setText(String.valueOf(numRoll));
    }

    public void play() {
        board.play(player, numRoll);
    }
    
    //Sets the guess
    public void setGuess(String guess, Color color) {
        this.guess.setText(guess);
        this.guess.setBackground(color);
        
    }
    
    
    //Sets the guess results
    public void setGuessResult(String result, Color color) {
        this.guessResult.setText(result);
        this.guessResult.setBackground(color);
        
    }    
}

