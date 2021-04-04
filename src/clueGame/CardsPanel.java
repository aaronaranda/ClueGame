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

    private JLabel inHandPeopleLabel;
    private JLabel seenPeopleLabel;
    private JLabel inHandRoomsLabel;
    private JLabel seenRoomsLabel;
    private JLabel inHandWeaponsLabel;
    private JLabel seenWeaponsLabel;
    


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
        this.inHandPeopleLabel = new JLabel("In Hand:");
        this.seenPeopleLabel = new JLabel("Seen:");
        this.inHandPeople = new JTextField("None");
        this.seenPeople = new JTextField("None");
       
        this.inHandPeopleLabel.add(this.inHandPeople);
        this.seenPeopleLabel.add(this.seenPeople);

        peoplePanel.add(this.inHandPeopleLabel);
        peoplePanel.add(this.seenPeopleLabel);
            
        // Rooms
        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new GridLayout(4, 0));
        roomPanel.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
        this.inHandRooms = new JTextField("None");
        this.seenRooms = new JTextField("None");
        this.inHandRoomsLabel = new JLabel("In Hand:");
        this.seenRoomsLabel = new JLabel("Seen:");

        this.inHandRoomsLabel.add(this.inHandRooms);
        this.seenRoomsLabel.add(this.inHandRooms);

        roomPanel.add(this.inHandRoomsLabel);
        roomPanel.add(this.seenRoomsLabel);

        // Weapons
        JPanel weaponPanel = new JPanel();
        weaponPanel.setLayout(new GridLayout(4, 0));
        weaponPanel.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
        this.inHandWeaponsLabel = new JLabel("In Hand:");
        this.seenWeaponsLabel = new JLabel("Seen:");
        this.inHandWeapons = new JTextField("None");
        this.seenWeapons = new JTextField("None");

        this.inHandWeaponsLabel.add(this.inHandWeapons);
        this.seenWeaponsLabel.add(this.seenWeapons);

        weaponPanel.add(this.inHandWeaponsLabel);
        weaponPanel.add(this.seenWeaponsLabel);

        mainPanel.add(peoplePanel);
        mainPanel.add(roomPanel);
        mainPanel.add(weaponPanel);
        add(mainPanel);
    }

    public void updateCardsPanel(Player player) {
        this.player = player;

        //People
        for(Card c: this.peopleCards) {
            JTextField person = new JTextField(c.getName());
            
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




