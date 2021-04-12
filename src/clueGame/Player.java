package clueGame;

import java.awt.*;
import java.util.*;

import javax.swing.JPanel;

public abstract class Player extends JPanel {
	
	protected String name;
	protected boolean isHuman;
	protected Point startLocation;
	protected Color color;
	protected ArrayList<Card> playerDeck;
    protected ArrayList<Card> seenPeopleCards;
    protected ArrayList<Card> seenRoomCards;
    protected ArrayList<Card> seenWeaponCards;

	 
	// Position
	protected int row, col;
		
	//Constructor
	public Player(String name) {
		this.name = name;
		this.playerDeck = new ArrayList<Card>();
        this.seenPeopleCards = new ArrayList<Card>();
        this.seenRoomCards = new ArrayList<Card>();
        this.seenWeaponCards = new ArrayList<Card>();
	}
	
	// Adds card to players deck of cards
	public void updateHand(Card card) {
		if (!this.playerDeck.contains(card)) {
			this.playerDeck.add(card);
		} 
	}

	// Updates the color of each player
	public void updateColor(String colorType) {
		this.color = Color.getColor(colorType);
	}

	
    public void updateSeen(Card seenCard) {
        if (seenCard.getType().equals(CardType.PERSON)) {
			this.seenPeopleCards.add(seenCard);
		}
		else if (seenCard.getType().equals(CardType.ROOM)) {
			this.seenRoomCards.add(seenCard);
		}
		else if (seenCard.getType().equals(CardType.WEAPON)) {
			this.seenWeaponCards.add(seenCard);
		}
    }	
	
    public void setStartPosition(Point point) {
    	this.row = point.x;
    	this.col = point.y;
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.setColor(color);
    	g.fillOval(0, 0, 10, 10);

    	
    	
    }
    
    
	public abstract Card selectTargets();

	// Different createSuggestions for Computer and Human players
	public abstract Solution createSuggestion(Card room);
		 
	public abstract Card disproveSuggestion(Solution suggestion);

	/*
	 * GETTERS
	 */

	public String getName() {
		return this.name;
	}
	
	public boolean getType() {
		return this.isHuman;
	}
	
	public ArrayList<Card> getDeck() {
		return this.playerDeck;
	}

	public ArrayList<Card> getSeenRoomCards() {
		return this.seenRoomCards;
	}

	public ArrayList<Card> getSeenPeopleCards() {
		return this.seenPeopleCards;
	}

	public ArrayList<Card> getSeenWeaponCards() {
		return this.seenWeaponCards;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}


}

