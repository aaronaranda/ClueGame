package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.border.*;

public class GameControlPanel extends JPanel {
	
	
	//private String playerName;
	private JTextField playerName;
		
	//private int roll;
	private JTextField roll; 
	
	//private String guess;
	private JTextField guess;
	
	//private String guessResult;
	private JTextField guessResult;
 
    public GameControlPanel() {
    	
    	
    	//Sets up the panel size
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 0));

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
        nextButton.addActionListener(new ButtonListener());

        JPanel guessPanel = new JPanel();
    	this.guess = new JTextField(20);
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

        mainPanel.add(upperPanel);
        mainPanel.add(lowerPanel);
        add(mainPanel);

    }
    
    private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (true) {
				String errorMessage = "Please finish your turn.";
				JOptionPane.showMessageDialog(null, errorMessage);
			}
			else {
				
			}
			
			
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
    
    //Main for calling the GUI
    public static void main(String[] args) {
        GameControlPanel controlPanel = new GameControlPanel(); 
        JFrame frame = new JFrame();
        frame.setContentPane(controlPanel);
        frame.setSize(750, 180);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        controlPanel.setTurn(new ComputerPlayer("Dummy"), 5);
        controlPanel.setGuess("I have no guess!");
        controlPanel.setGuessResult("So you have nothing?");
    }


}
