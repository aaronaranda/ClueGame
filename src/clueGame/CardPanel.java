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
    public CardPanel() {
    	this.board = Board.getInstance();
    	player = board.getPlayer(0);	//Always gets the human player    
    	board.setCardPanel(this);
    	setSize(new Dimension(150, 800));   
    	setMinimumSize(new Dimension(150, 800));
        setLayout(new GridLayout(0, 1));

        setBackground(Color.BLACK);
        setBorder(new TitledBorder(new EtchedBorder(Color.CYAN, Color.WHITE),
                "CARDS", 0, 0, null, Color.WHITE));
        peoplePanel = new CardTypePanel("PEOPLE");
        roomsPanel = new CardTypePanel("ROOMS");
        weaponsPanel = new CardTypePanel("WEAPONS");
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

    

