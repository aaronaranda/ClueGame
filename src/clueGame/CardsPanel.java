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
    private ArrayList<Card> peopleCards;
    private ArrayList<Card> roomCards;
    private ArrayList<Card> weaponCards;

    public CardsPanel() {
        // Known Cards main panel
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridLayout(9, 0));
        this.mainPanel.setBorder(new TitledBorder(new EtchedBorder(), "Known Cards"));

        // People
        this.peoplePanel = new JPanel();
        this.inHandPeoplePanel = new JPanel();
        this.seenPeoplePanel = new JPanel();
        this.peoplePanel.setLayout(new BoxLayout(this.peoplePanel, BoxLayout.Y_AXIS));
        this.inHandPeoplePanel.setLayout(new BoxLayout(this.inHandPeoplePanel, BoxLayout.Y_AXIS));
        this.seenPeoplePanel.setLayout(new BoxLayout(this.seenPeoplePanel, BoxLayout.Y_AXIS));
        this.peoplePanel.setBorder(new TitledBorder(new EtchedBorder(), "People"));
        this.inHandPeoplePanel.add(new JLabel("In Hand:"));
        this.seenPeoplePanel.add(new JLabel("Seen:"));

        this.inHandPeoplePanel.add(new JTextField("None", 20));
        this.seenPeoplePanel.add(new JTextField("None", 20));

        this.peoplePanel.add(this.inHandPeoplePanel);
        this.peoplePanel.add(this.seenPeoplePanel);

        // Rooms
        this.roomPanel = new JPanel();
        this.inHandRoomPanel = new JPanel();
        this.seenRoomPanel = new JPanel();
        this.roomPanel.setLayout(new BoxLayout(this.roomPanel, BoxLayout.Y_AXIS));
        this.inHandRoomPanel.setLayout(new BoxLayout(this.inHandRoomPanel, BoxLayout.Y_AXIS));
        this.seenRoomPanel.setLayout(new BoxLayout(this.seenRoomPanel, BoxLayout.Y_AXIS));
        this.roomPanel.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
        this.inHandRoomPanel.add(new JLabel("In Hand:"));
        this.seenRoomPanel.add(new JLabel("Seen:"));
        this.inHandRoomPanel.add(new JTextField("None", 20));
        this.seenRoomPanel.add(new JTextField("None", 20));

        this.roomPanel.add(this.inHandRoomPanel);
        this.roomPanel.add(this.seenRoomPanel);

        // Weapons
        this.weaponPanel = new JPanel();
        this.inHandWeaponPanel = new JPanel();
        this.seenWeaponPanel = new JPanel();
        this.weaponPanel.setLayout(new BoxLayout(this.weaponPanel, BoxLayout.Y_AXIS));
        this.inHandWeaponPanel.setLayout(new BoxLayout(this.inHandWeaponPanel, BoxLayout.Y_AXIS));
        this.seenWeaponPanel.setLayout(new BoxLayout(this.seenWeaponPanel, BoxLayout.Y_AXIS));
        this.weaponPanel.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
        this.inHandWeaponPanel.add(new JLabel("In Hand"));
        this.seenWeaponPanel.add(new JLabel("Seen:"));
        this.inHandWeaponPanel.add(new JTextField("None", 20));
        this.seenWeaponPanel.add(new JTextField("None", 20));

        this.weaponPanel.add(inHandWeaponPanel);
        this.weaponPanel.add(seenWeaponPanel);

        this.mainPanel.add(this.peoplePanel);
        this.mainPanel.add(this.roomPanel);
        this.mainPanel.add(this.weaponPanel);
        add(mainPanel);
    }

    public void updateCardsPanel(Player player) {
        this.player = player;
        this.inHandPeoplePanel.remove(1);
        this.seenPeoplePanel.remove(1);
        this.inHandRoomPanel.remove(1);
        this.seenRoomPanel.remove(1);
        this.inHandWeaponPanel.remove(1);
        this.seenWeaponPanel.remove(1);


        for (Card c: this.player.getDeck()) {
            if (c.getType().equals(CardType.PERSON)) {
                if (!c.getSeen()) {
                    this.inHandPeoplePanel.add(new JTextField(c.getName()));
                } else if (c.getSeen()) {
                    this.seenPeoplePanel.add(new JTextField(c.getName()));
                }
            } else if (c.getType().equals(CardType.ROOM)) {
                if (!c.getSeen()) {
                    this.inHandRoomPanel.add(new JTextField(c.getName()));
                } else if (c.getSeen()) {
                    this.seenRoomPanel.add(new JTextField(c.getName()));
                }
            } else if (c.getType().equals(CardType.WEAPON)) {
                if (!c.getSeen()) {
                    this.inHandWeaponPanel.add(new JTextField(c.getName()), this.getY()+1);
                } else if (c.getSeen()) {
                    this.seenWeaponPanel.add(new JTextField(c.getName()), this.getY()+1);
                }
            }
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

        CardsPanel cardsPanel = new CardsPanel();
        cardsPanel.updateCardsPanel(board.getPlayer('1'));
        JFrame frame = new JFrame();
        frame.setContentPane(cardsPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}




