package clueGame;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class CardPanel extends JPanel {
    private Player player;
    
   // Separate class made for panel of specific card type
    private CardTypePanel peoplePanel;
    private CardTypePanel roomsPanel;
    private CardTypePanel weaponsPanel;
    
    // Constructor sets up overall panel
    public CardPanel() {
    	setPreferredSize(new Dimension(300, 700));
    	setMinimumSize(new Dimension(300, 700));
        setLayout(new GridLayout(0, 1));
        setBorder(new TitledBorder(new EtchedBorder(), "Known Cards"));
        peoplePanel = new CardTypePanel("People");
        roomsPanel = new CardTypePanel("Rooms");
        weaponsPanel = new CardTypePanel("Weapons");
        add(peoplePanel);
        add(roomsPanel);
        add(weaponsPanel);
    }

    // Calls update of each panel associated with specific CardType
    public void updateCardsPanel(Player player) {
    	this.player = player;
    	peoplePanel.updateCardsPanel(player.getPersonCards(false), player.getPersonCards(true));
    	roomsPanel.updateCardsPanel(player.getRoomCards(false), player.getRoomCards(true)); 
    	weaponsPanel.updateCardsPanel(player.getWeaponCards(false), player.getWeaponCards(true));
    }

    // Returns list of players cards of a certain type
  
   
    public static void main(String[] args) {
        Board board = Board.getInstance();
        board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
        board.initialize();    

        CardPanel cardsPanel = new CardPanel();
        cardsPanel.updateCardsPanel(board.getPlayer(1));
        JFrame frame = new JFrame();
        frame.setContentPane(cardsPanel);
        frame.setSize(200, 700);
        //frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

    

