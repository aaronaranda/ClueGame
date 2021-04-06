package clueGame;

import java.util.*;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.*;


public class CardsPanel extends JPanel {

    private Player player;
    private ArrayList<Card> deck;

    // Separate class made for panel of specific card type
    private CardTypePanel peoplePanel;
    private CardTypePanel roomsPanel;
    private CardTypePanel weaponsPanel;
    
    // Constructor sets up overall panel
    public CardsPanel() {
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
    	peoplePanel.updateCardsPanel(getCards(CardType.PERSON));
    	roomsPanel.updateCardsPanel(getCards(CardType.ROOM));
    	weaponsPanel.updateCardsPanel(getCards(CardType.WEAPON));
    }

    // Returns list of players cards of a certain type
    private ArrayList<Card> getCards(CardType type) {
        ArrayList<Card> cards = new ArrayList<Card>();
        deck = player.getDeck();
        	for (Card c : deck) {
                if (c.getType().equals(type)) {
                    cards.add(c);
                }
            }
        return cards;
    }
   
    public static void main(String[] args) {
        Board board = Board.getInstance();
        board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
        board.initialize();    

        CardsPanel cardsPanel = new CardsPanel();
        cardsPanel.updateCardsPanel(board.getPlayer('1'));
        JFrame frame = new JFrame();
        frame.setContentPane(cardsPanel);
        frame.setSize(200, 700);
        // frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}




