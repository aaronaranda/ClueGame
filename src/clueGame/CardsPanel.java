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

    private Player player;
    private ArrayList<Card> deck;
    private ArrayList<Card> peopleCards;
    private ArrayList<Card> roomCards;
    private ArrayList<Card> weaponCards;


    public CardsPanel(Player player) {
        this.player = player;
        // Known Cards main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 0));
        mainPanel.setBorder(new TitledBorder("Known Cards"));


        // People 
        JPanel peoplePanel = new JPanel();
        peoplePanel.setLayout(new GridLayout(2, 0));
        peoplePanel.setBorder(new TitledBorder("People"));
        JLabel inHandPeople = new JLabel("In Hand:");        
        JLabel seenPeople = new JLabel("Seen:");
            
        // Rooms
        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new GridLayout(2, 0));
        roomPanel.setBorder(new TitledBorder("Rooms"));
        JLabel inHandRooms = new JLabel("In Hand:");
        JLabel seenRooms = new JLabel("Seen:");


        // Weapons
        JPanel weaponPanel = new JPanel();
        weaponPanel.setLayout(new GridLayout(2, 0));
        weaponPanel.setBorder(new TitledBorder("Weapons"));
        JLabel inHandWeapons = new JLabel("In Hand:");
        JLabel seenWeapons = new JLabel("Seen:");




    }

    private ArrayList<Card> getPeopleCards() {
        this.peopleCards = new ArrayList<Card>();
        for (Card c : this.deck) {
            if (c.getType().equals(CardType.PERSON)) {
                peopleCards.add(c);
            }
        }
        return peopleCards;
    }

    private ArrayList<Card> getRoomCards() {
        this.roomCards = new ArrayList<Card>();
        for (Card c : this.deck) {
            if (c.getType().equals(CardType.ROOM)) {
                roomCards.add(c);
            }
        }
        return roomCards;
    }

    private ArrayList<Card> getWeaponCards() {
        this.weaponCards = new ArrayList<Card>();
        for (Card c : this.deck) {
            if (c.getType().equals(CardType.WEAPON)) {
                weaponCards.add(c);
            }
        }
        return weaponCards;
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




