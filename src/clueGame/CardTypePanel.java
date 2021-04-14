import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class CardTypePanel extends JPanel {
    private JPanel inHandPanel;
    private JPanel seenPanel;
    private ArrayList<Card> inHandCards; 
    private ArrayList<Card> seenCards;

    public CardTypePanel(String type) {
        setLayout(new GridLayout(0, 1));
        setBorder(new TitledBorder(new EtchedBorder(), type));
        inHandPanel = new JPanel(new GridLayout(0, 1));
        seenPanel = new JPanel(new GridLayout(0, 1));
        seenPanel.add(new JLabel("Seen:"));
        inHandCards = new ArrayList<Card>();
        seenCards = new ArrayList<Card>();
        inHandCards.add(new Card("None", CardType.PERSON));
        seenCards.add(new Card("None", CardType.PERSON));
        displayCards();
        add(inHandPanel);
        add(seenPanel);
    }

    private void displayCards() {
        for (Card c: inHandCards) {
            inHandPanel.add(new JTextField(c.getName()));
        }
        for (Card c: seenCards) {
            seenPanel.add(new JTextField(c.getName()));
        }
    }

    // Updates the visible cards given a list of a type of cards
    public void updateCardsPanel(ArrayList<Card> inHand, ArrayList<Card> seen) {
    	inHandCards.addAll(inHand);
    	seenCards.addAll(seen);
        if (inHandCards.size() > 1) {
            inHandCards.remove(0);
        }
        if (seenCards.size() > 1) {
            seenCards.remove(0);
        }
        inHandPanel.removeAll();
        inHandPanel.add(new JLabel("In Hand:"));
        seenPanel.removeAll();
        seenPanel.add(new JLabel("Seen:"));
        displayCards();
    }
}


    


