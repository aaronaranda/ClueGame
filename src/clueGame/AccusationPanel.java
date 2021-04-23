package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AccusationPanel extends JDialog {
	private static Board board = Board.getInstance();
	private Player player = board.getPlayer(0);
	
	private JComboBox<String> personBox;
	private JComboBox<String> roomBox;
	private JComboBox<String> weaponBox;
	
	
	public AccusationPanel() {
		setTitle("Accusation Panel");
		setSize(300, 300);
		setLayout(new GridLayout(4, 2, 1, 1));
		JLabel personLabel = new JLabel("Select Person Card");
		JLabel roomLabel = new JLabel("Select Room Card");
		JLabel weaponLabel = new JLabel("Select Weapon Card");
		JLabel fillerLabel = new JLabel("");
		String[] personCards = new String[player.getPersonCards(false).size()];
		String[] roomCards = new String[player.getRoomCards(false).size()];
		String[] weaponCards = new String[player.getWeaponCards(false).size()];
		int i = 0;
		while (i < player.getPersonCards(false).size()) {
			personCards[i] = player.getPersonCards(false).get(i).getName();
			i++;
		}
		i = 0;
		while(i < player.getRoomCards(false).size()) {
			roomCards[i] = player.getRoomCards(false).get(i).getName();
			i++;
		}
		i = 0;
		while (i < player.getWeaponCards(false).size()) {
			weaponCards[i] = player.getWeaponCards(false).get(i).getName();
			i++;
		}
		personBox = new JComboBox<String>(personCards);
		roomBox = new JComboBox<String>(roomCards);
		weaponBox = new JComboBox<String>(weaponCards);
		add(personLabel, 0, 0);
		add(personBox, 0, 1);
		add(roomLabel, 1, 0);
		add(roomBox, 1, 1);
		add(weaponLabel, 2, 0);
		add(weaponBox, 2, 1);		
		
		JButton button = new JButton("Make Accusation");
		button.addActionListener(new AccusationListener());
		add(button, 3, 0);
		add(fillerLabel, 3, 1);			
	}
	
	private Solution checkAccusation() {		
		Card personCard = player.getCard((String)personBox.getSelectedItem());
		Card roomCard = player.getCard((String)roomBox.getSelectedItem());
		Card weaponCard = player.getCard((String)weaponBox.getSelectedItem());		
		if (personCard == null || roomCard == null || weaponCard == null) {
			JOptionPane.showMessageDialog(null, "Sorry, you don't have enough cards");
			setVisible(false);			
		} else {
			Solution accusation = new Solution(player, personCard, roomCard, weaponCard);
			return accusation;
		}		
		return null;
	}
	
	
	private class AccusationListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Solution accusation = checkAccusation();
			if (accusation != null) {
				boolean win = board.checkAccusation(accusation);
				if (win) {
					JOptionPane.showMessageDialog(null, "You won!");
				} else {
					JOptionPane.showMessageDialog(null, "Sorry, that was wrong. You lose...");
				}				
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
