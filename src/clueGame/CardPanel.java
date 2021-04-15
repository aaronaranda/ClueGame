package clueGame;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class CardPanel extends JPanel {
    private Board board;
	private Player player;
    
   // Separate class made for panel of specific card type
    private CardTypePanel peoplePanel;
    private CardTypePanel roomsPanel;
    private CardTypePanel weaponsPanel;
    
    // Constructor sets up overall panel
    public CardPanel(Board board) {
    	this.board = board;
    	player = board.getPlayer(0);	//Always gets the human player    
    	setSize(new Dimension(100, 800));    	
        setLayout(new GridLayout(0, 1));
        setBorder(new TitledBorder(new EtchedBorder(), "Known Cards"));
        peoplePanel = new CardTypePanel("People");
        roomsPanel = new CardTypePanel("Rooms");
        weaponsPanel = new CardTypePanel("Weapons");
        add(peoplePanel);
        add(roomsPanel);
        add(weaponsPanel);
        updateCardsPanel();
    }

    // Calls update of each panel associated with specific CardType
    public void updateCardsPanel() {
    	peoplePanel.updateCardsPanel(player.getPersonCards(false), player.getPersonCards(true));
    	roomsPanel.updateCardsPanel(player.getRoomCards(false), player.getRoomCards(true)); 
    	weaponsPanel.updateCardsPanel(player.getWeaponCards(false), player.getWeaponCards(true));
    }
}

    

