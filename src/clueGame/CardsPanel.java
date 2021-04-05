package clueGame;

import java.util.*;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.*;


public class CardsPanel extends JPanel {

    private JPanel mainPanel;
    private JPanel peoplePanel;
    private JPanel inHandPeoplePanel;
    private JPanel seenPeoplePanel;

    private JPanel roomPanel;
    private JPanel inHandRoomPanel;
    private JPanel seenRoomPanel;

    private JPanel weaponPanel;
    private JPanel inHandWeaponPanel;
    private JPanel seenWeaponPanel;

    private Player player;
    private ArrayList<Card> deck;
    private ArrayList<Card> ihPeopleCards;
    private ArrayList<Card> seenPeopleCards;
    private ArrayList<Card> ihRoomCards;
    private ArrayList<Card> seenRoomCards;
    private ArrayList<Card> ihWeaponCards;
    private ArrayList<Card> seenWeaponCards;
    
    
    
    public JPanel cardTypePanel(CardType type, String typeName) {    	    	    	
        JPanel mainPanel = new JPanel(new GridLayout(0, 2));                          
        JPanel inHandPanel = new JPanel(new GridLayout(0, 1));
        inHandPanel.add(new JLabel("In Hand:"));       
        JPanel seenPanel = new JPanel(new GridLayout(0, 1));
        seenPanel.add(new JLabel("Seen:"));
        
        if (getCards(type, false).isEmpty()) {
        	inHandPanel.add(new JTextField("None"));
        } else {        	
        	for (Card c: getCards(type, false)) {
        		inHandPanel.add(new JTextField(c.getName()));
        	}
        }
        
        if (getCards(type, true).isEmpty()) {
        	seenPanel.add(new JTextField("None"));
        } else {        
        	for (Card c: getCards(type, true)) {
        		seenPanel.add(new JTextField(c.getName()));
        	}
        }
        
        mainPanel.add(inHandPanel);
        mainPanel.add(seenPanel);                              		
        mainPanel.setBorder(new TitledBorder(new EtchedBorder(), typeName));
        return mainPanel; 
    }

    public CardsPanel() {
    	
    	setLayout(new GridLayout(0, 1));
        setBorder(new TitledBorder(new EtchedBorder(), "Known Cards"));
        peoplePanel = cardTypePanel(CardType.PERSON, "People");
        roomPanel = cardTypePanel(CardType.ROOM, "Rooms");
        weaponPanel = cardTypePanel(CardType.WEAPON, "Weapons");        
        add(peoplePanel);
        add(roomPanel);
        add(weaponPanel);                
        
        ihPeopleCards = getCards(CardType.PERSON, false);
    	seenPeopleCards = getCards(CardType.PERSON, true);
    	ihRoomCards = getCards(CardType.ROOM, false);
    	seenRoomCards = getCards(CardType.ROOM, true);
    	ihWeaponCards = getCards(CardType.WEAPON, false);
    	seenWeaponCards = getCards(CardType.WEAPON, true);
        
    }
    
    public void updateCardsPanel(Player player) {
    	this.player = player;
    
    	peoplePanel = cardTypePanel(CardType.PERSON, "People");
        roomPanel = cardTypePanel(CardType.ROOM, "Rooms");
        weaponPanel = cardTypePanel(CardType.WEAPON, "Weapons");                
    }
    

    
    

    private ArrayList<Card> getCards(CardType type, boolean seen) {
        ArrayList<Card> cards = new ArrayList<Card>();
        if (player != null) {
        	deck = player.getDeck();
        	for (Card c : deck) {
        		if (c.getType().equals(type) && (c.getSeen() == seen)) {
        			cards.add(c);
        		}
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
        frame.setSize(200, 500);
        // frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}




