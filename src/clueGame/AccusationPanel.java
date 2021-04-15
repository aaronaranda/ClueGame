package clueGame;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class AccusationPanel extends JDialog {
	public AccusationPanel(Player player) {
		setTitle("Accusation Panel");
		setSize(200, 200);
	
		
		setLayout(new GridLayout(6, 0));
		JLabel personLabel = new JLabel("Select Person Card");
		JLabel roomLabel = new JLabel("Select Room Card");
		JLabel weaponLabel = new JLabel("Select Weapon Card");
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
		JComboBox<String> personBox = new JComboBox<String>(personCards);
		JComboBox<String> roomBox = new JComboBox<String>(roomCards);
		JComboBox<String> weaponBox = new JComboBox<String>(weaponCards);
		add(personLabel);
		add(personBox);
		add(roomLabel);
		add(roomBox);
		add(weaponLabel);
		add(weaponBox);		
		
		JButton button = new JButton("Make Accusation");
		button.addActionListener(e -> setVisible(false));
		
	}
}
