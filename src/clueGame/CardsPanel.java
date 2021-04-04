package clueGame;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.*;

public class CardsPanel extends JPanel {

    /* Known Cards {
     *  People {
     *      In hand:
     *      Seen:
     *  }
     *  Rooms {
     *      In hand:
     *      Seen:
     *  }
     *  Weapons {
     *      In hand:
     *      Seen:
     *  }
     * }
     */

    private JPanel mainPanel;

    private Player player;
    private ArrayList<Card> deck;
    private ArrayList<Card> peopleCards;
    private ArrayList<Card> roomCards;
    private ArrayList<Card> weaponCards;

    private JTextField inHandPeople;
    private JTextField seenPeople;

    private JTextField inHandRooms;
    private JTextField seenRooms;

    private JTextField inHandWeapons;
    private JTextField seenWeapons;

    public CardsPanel() {
        // Known Cards main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 0));
        mainPanel.setBorder(new TitledBorder(new EtchedBorder(), "Known Cards"));

        // People 
        JPanel peoplePanel = new JPanel();
        peoplePanel.setLayout(new GridLayout(4, 0));
        peoplePanel.setBorder(new TitledBorder(new EtchedBorder(), "People"));
        this.inHandPeople = new JTextField("None");
        this.seenPeople = new JTextField("None");
        JLabel inHandPeople = new JLabel("In Hand:");
        JLabel seenPeople = new JLabel("Seen:");
        
        peoplePanel.add(inHandPeople);
        peoplePanel.add(this.inHandPeople);
        peoplePanel.add(seenPeople);
        peoplePanel.add(this.seenPeople);
            
        // Rooms
        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new GridLayout(4, 0));
        roomPanel.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
        this.inHandRooms = new JTextField("None");
        this.seenRooms = new JTextField("None");
        JLabel inHandRooms = new JLabel("In Hand:");
        JLabel seenRooms = new JLabel("Seen:");

        roomPanel.add(inHandRooms);
        roomPanel.add(this.inHandRooms);
        roomPanel.add(seenRooms);
        roomPanel.add(this.seenRooms);

        // Weapons
        JPanel weaponPanel = new JPanel();
        weaponPanel.setLayout(new GridLayout(4, 0));
        weaponPanel.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
        this.inHandWeapons = new JTextField("None");
        this.seenWeapons = new JTextField("None");
        JLabel inHandWeapons = new JLabel("In Hand:");
        JLabel seenWeapons = new JLabel("Seen:");

        weaponPanel.add(inHandWeapons);
        weaponPanel.add(this.inHandWeapons);
        weaponPanel.add(seenWeapons);
        weaponPanel.add(this.seenWeapons);

        mainPanel.add(peoplePanel);
        mainPanel.add(roomPanel);
        mainPanel.add(weaponPanel);
        add(mainPanel);
    }

    public void updateCardsPanel(Player player) {
        this.player = player;

        //People
        for(Card c: this.peopleCards) {
            JTextField
        }
    }

    private ArrayList<Card> getPeopleCards() {
        this.peopleCards = new ArrayList<Card>();
        for (Card c : this.deck) {
            if (c.getType().equals(CardType.PERSON)) {
                this.peopleCards.add(c);
            }
        }
        return this.peopleCards;
    }

    private ArrayList<Card> getRoomCards() {
        this.roomCards = new ArrayList<Card>();
        for (Card c : this.deck) {
            if (c.getType().equals(CardType.ROOM)) {
                this.roomCards.add(c);
            }
        }
        return this.roomCards;
    }

    private ArrayList<Card> getWeaponCards() {
        this.weaponCards = new ArrayList<Card>();
        for (Card c : this.deck) {
            if (c.getType().equals(CardType.WEAPON)) {
                this.weaponCards.add(c);
            }
        }
        return this.weaponCards;
    }

    

    public static void main(String[] args) {
        Board board = Board.getInstance();
        board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
        board.initialize();    

        CardsPanel cardsPanel = new CardsPanel(board.getPlayer('1'));
        JFrame frame = new JFrame();
        frame.setContentPane(cardsPanel);
        frame.setSize(100, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);           
    }
}




