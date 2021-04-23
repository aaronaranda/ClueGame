package clueGame;
import java.util.*;

import javax.swing.JOptionPane;

import java.awt.*;

public abstract class Player {
    protected String name;
    protected int roll;
    protected boolean isHuman;
    protected Point startPosition;
    protected BoardCell startCell;
    protected Color color;
    protected ArrayList<Card> deck;
    protected ArrayList<Card> seenCards;
    protected BoardCell location;
    protected boolean madeMove;
    protected Board board;
    

    public Player(String name) {
        this.name = name;
        deck = new ArrayList<Card>();
        seenCards = new ArrayList<Card>();
        board = Board.getInstance();
        
    }

    public abstract Card disproveSuggestion(Solution suggestion);
    public abstract Solution createSuggestion();
    public abstract Card selectTargets();

   
/*
 * SETTERS
 */     

    public void updateColor(String color) {
        this.color = Color.getColor(color);
    }

    public void updateColor(int r, int g, int b) {
        this.color = new Color(r, g, b);
    }

    public void updateHand(Card card) {   	
        if (deck.contains(card)) {
            deck.get(deck.indexOf(card)).seeCard();
            seenCards.add(card);           
        } else {
            deck.add(card);
            card.setHolder(this);
        }
    }
    
    public void setStartPosition(Point point) {   
    	startPosition = point;
    }
    
    public void setStartPosition(BoardCell cell) {
    	location = cell;
    	startCell = cell;
    	cell.setStart(this);
    }
    
   
    public void setPosition(BoardCell cell) {
    	if (!cell.equals(location)) {
    		location.setUnoccupied(this);
    		cell.setOccupied(this);
    		location = cell;    		
    	}
    }
   
    public abstract boolean moveLocation(BoardCell cell);
    public abstract void moveLocation();

/*
 * GETTERS
 */ 

    public BoardCell getLocation() {
    	return location;
    }
    
    public int getRoll() {
    	return roll;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isHuman() {
    	return isHuman;
    }
    
    public boolean madeMove() {
    	return madeMove;
    }
    
    public Point getStart() {
    	return startPosition;
    }
    
    public BoardCell getStartCell() {
    	return startCell;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card> getSeen() {
        return seenCards;
    }
    public Color getColor() {
    	return color;
    }
    
    public Card getCard(String cardName) {
    	for (Card card: this.deck) {
    		if (card.getName().equals(cardName)) {
    			return card;
    		}
    	}
    	return null;
    }

    public ArrayList<Card> getPersonCards(boolean seen) {
        ArrayList<Card> personCards = new ArrayList<Card>();
        for (Card c: deck) {
            if (c.getType().equals(CardType.PERSON)) {
                if (seen) {
                    if (c.getSeen()) {
                        personCards.add(c);
                    }
                } else {
                    personCards.add(c);
                }
            }
        }
        return personCards;
    }

    public ArrayList<Card> getRoomCards(boolean seen) {
        ArrayList<Card> roomCards = new ArrayList<Card>();
        for (Card c: deck) {
            if (c.getType().equals(CardType.ROOM)) {
                if (seen) {
                    if (c.getSeen()) {
                        roomCards.add(c);
                    }
                } else {
                    roomCards.add(c);
                }
            }
        }
        return roomCards;
    }

    public ArrayList<Card> getWeaponCards(boolean seen) {
        ArrayList<Card> weaponCards = new ArrayList<Card>();
        for (Card c: deck) {
            if (c.getType().equals(CardType.WEAPON)) {
                if (seen) {
                    if (c.getSeen()) {
                        weaponCards.add(c);                        
                    } 
                } else {
                    weaponCards.add(c);
                }
            }
        }
        return weaponCards; 
    }

    


}
