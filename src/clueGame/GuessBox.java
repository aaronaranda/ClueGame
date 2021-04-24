package clueGame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
@SuppressWarnings("serial")
public class GuessBox extends JDialog {
	private JComboBox<String> personBox, roomBox, weaponBox;
	private Board board = Board.getInstance();
	private String roomName;
	
	// Constructor for making a suggestion
	public GuessBox(Board board, Room room) {		
		this.roomName = room.getName();
		this.setSize(300, 300);
		this.setLayout(new GridLayout(4, 2));
		personBox = new JComboBox<String>();
		roomBox = new JComboBox<String>();
		weaponBox = new JComboBox<String>();
		setTitle("Make a Suggestion");
		JLabel roomLabel = new JLabel("Current room");
		add(roomLabel);
		JTextField roomText = new JTextField(roomName);
		roomText.setEditable(false);
		add(roomText);
		roomBox.addItem(roomName);		
		makeCombo(personBox, CardType.PERSON, "Person");
		makeCombo(weaponBox, CardType.WEAPON, "Weapon");
		JButton submitButton = new JButton("Submit");
		JButton cancelButton = new JButton("Cancel");
		submitButton.addActionListener(new SubmitListener());		
		cancelButton.addActionListener(new CancelListener());
		add(submitButton);
		add(cancelButton);
		setLocationRelativeTo(board);
		setModal(true);
		pack();
		setVisible(true);	
	}
	
	// Constructor for making accusation
	public GuessBox() {		 
		setSize(300, 300);
		setTitle("Make Accusation");
		setLayout(new GridLayout(4, 2));
		personBox = new JComboBox<String>();
		roomBox = new JComboBox<String>();
		weaponBox = new JComboBox<String>();
		makeCombo(personBox, CardType.PERSON, "Person");
		makeCombo(roomBox, CardType.ROOM, "Room");
		makeCombo(weaponBox, CardType.WEAPON, "Weapon");
		JButton accuseButton = new JButton("Accuse!");
		JButton cancelButton = new JButton("Cancel");
		accuseButton.addActionListener(new AccuseListener());
		cancelButton.addActionListener(new CancelListener());
		add(accuseButton);
		add(cancelButton);
		setLocationRelativeTo(board);
		setModal(true);
		pack();
		setVisible(true);		
	}
	
	
	
	private void makeCombo(JComboBox<String> cb, CardType type, String name) {
		for (Card c: board.getDeck()) {
			if (c.getType().equals(type)) {
				cb.addItem(c.getName());
			}
		}
		JLabel typeLabel = new JLabel(name);
		add(typeLabel);
		add(cb);		
	}
	
	
	// Canceling just closes the window
	private class CancelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
	
	// For submitting a suggestion
	private class SubmitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Card personCard = board.getCard((String)personBox.getSelectedItem());
			Card roomCard = board.getCard(roomName);
			Card weaponCard = board.getCard((String)weaponBox.getSelectedItem());
			Solution suggestion = new Solution(board.getPlayer(0), 
					personCard, roomCard, weaponCard);
			board.makeSuggestion(suggestion, board.getPlayer(0));
			setVisible(false);
		}
	}
	
	// For submitting the accusation
	private class AccuseListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Card personCard = board.getCard((String)personBox.getSelectedItem());
			Card roomCard = board.getCard((String)roomBox.getSelectedItem());
			Card weaponCard = board.getCard((String)weaponBox.getSelectedItem());
			Solution accusation = new Solution(board.getPlayer(0),
					personCard, roomCard, weaponCard);
			boolean win = board.checkAccusation(accusation);
			if (win) {
				// Player wins the game
				JOptionPane.showMessageDialog(board,	"You Won!");
				System.exit(0);
			} else {
				// Player loses the game, shows correct answer
				JOptionPane.showMessageDialog(board, "Sorry, that was wrong. You lose...");
				JOptionPane.showMessageDialog(board, board.getTheAnswer());						
				System.exit(0);
			}			 					
		}
	}		
}
