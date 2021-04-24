package clueGame;
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
    	setSize(new Dimension(150, 200));
    	setMinimumSize(new Dimension(150, 200));
    	setBackground(Color.BLACK);
        setLayout(new GridLayout(0, 1));
        setBorder(new TitledBorder(new EtchedBorder(Color.CYAN, Color.WHITE),
                type, 0, 0, null, Color.WHITE));
        inHandPanel = new JPanel(new GridLayout(0, 2));
        seenPanel = new JPanel(new GridLayout(0, 2));
        seenPanel.setBackground(Color.BLACK);
        seenPanel.setBorder(new TitledBorder(new EtchedBorder(Color.CYAN, Color.WHITE),
                "Seen:", 0, 0, null, Color.WHITE));
        inHandPanel.setBackground(Color.BLACK);
        inHandPanel.setBorder(new TitledBorder(new EtchedBorder(Color.CYAN, Color.WHITE),
                "In Hand:", 0, 0, null, Color.WHITE));

        inHandCards = new ArrayList<Card>();
        seenCards = new ArrayList<Card>();
        inHandCards.add(new Card("None", CardType.PERSON, Color.WHITE));
        seenCards.add(new Card("None", CardType.PERSON, Color.WHITE));
        displayCards();
        add(inHandPanel);
        add(seenPanel);
    }

    private void displayCards() {
    	
        for (Card card: inHandCards) {        
        	inHandPanel.add(card.getLabel());
        	inHandPanel.add(card);        		       		                	
        }
        for (Card card: seenCards) {
        	seenPanel.add(card.getLabel());
        	seenPanel.add(card);               		        
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
        seenPanel.removeAll();      
        displayCards();
    }
}


    


