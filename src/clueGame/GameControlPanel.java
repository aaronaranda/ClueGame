package clueGame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

import javax.swing.border.*;

@SuppressWarnings("serial")
public class GameControlPanel extends JPanel {
	
	private static Board board;
	private Player player;
	//private String playerName;
	private JTextField playerName;
		
	//private int roll;
	private JTextField roll; 
	
	//private String guess;
	private JTextField guess;
	
	//private String guessResult;
	private JTextField guessResult;
 
    public GameControlPanel(Board board) {
    	this.board = board;
    	player = board.getPlayer(0);
    	
    	// Sizing
    	setSize(new Dimension(700, 100));
    	setMinimumSize(new Dimension(700, 100));    	          
        setLayout(new GridLayout(2, 0));

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(1, 4));

        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayout(0, 2));
        
        // Whose turn is it?
    	JPanel turnPanel = new JPanel();
        turnPanel.setLayout(new GridLayout(2, 0));
     	JLabel turnLabel = new JLabel("Whose turn?");
    	this.playerName = new JTextField(20);
    	turnPanel.add(turnLabel);
        turnPanel.add(this.playerName);
               
        // Roll
        JPanel rollPanel = new JPanel();
        JLabel rollLabel = new JLabel("Roll: ");
        this.roll = new JTextField(20);
        rollPanel.add(rollLabel);
        rollPanel.add(this.roll);

    	// Buttons
    	JButton accusationButton = new JButton("Make Accusation");
        JButton nextButton = new JButton("NEXT!");
        nextButton.setSize(50, 50);
        accusationButton.setSize(50, 50);
        nextButton.addActionListener(new NextListener());
        accusationButton.addActionListener(new AccusationListener());        

        JPanel guessPanel = new JPanel();
    	this.guess = new JTextField(10);
    	guessPanel.add(this.guess);
    	guessPanel.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));

        JPanel resultPanel = new JPanel();
        this.guessResult = new JTextField(20);
        resultPanel.add(this.guessResult);
        resultPanel.setBorder(new TitledBorder(
                    new EtchedBorder(), "Guess Result"));
        
        upperPanel.add(turnPanel);
        upperPanel.add(rollPanel);
        upperPanel.add(accusationButton);
        upperPanel.add(nextButton);

        lowerPanel.add(guessPanel);
        lowerPanel.add(resultPanel);

        add(upperPanel);
        add(lowerPanel);
    }
    
    private class NextListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// must check if player moved or not
			String errorMessage = "Please finish your turn.";
			JOptionPane.showMessageDialog(null, errorMessage);				
			setTurn(board.nextPlayer(), board.diceRoll());						
		}    	
    }
    
    private class AccusationListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		 AccusationPanel accusationPanel = new AccusationPanel(player);
    		 accusationPanel.setVisible(true);
    	}
    }
    
    //Sets turn
    public void setTurn(Player player, int roll) {
        this.playerName.setText(player.getName());
        this.roll.setText(String.valueOf(roll));
    }
    
    //Sets the guess
    public void setGuess(String guess) {
        this.guess.setText(guess);
    }
    
    
    //Sets the guess results
    public void setGuessResult(String result) {
        this.guessResult.setText(result);
    }    
}

