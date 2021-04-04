package clueGame;

import java.awt.*;
import java.util.*;

public abstract class Player {
	
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
	
	public Card disproveSuggestion(Solution suggestion) {
		Card disproval = null;
		
		// If player tries to dipsrove their own suggestion return null
		if (this == suggestion.getWhoSuggested()) {
			return disproval;
		}
		
		for (Card c: this.playerDeck) {
			if (c.equals(suggestion.getThePerson())) {
				disproval = suggestion.getThePerson();
			} else if (c.equals(suggestion.getTheRoom())) {
				disproval = suggestion.getTheRoom();
			} else if (c.equals(suggestion.getTheWeapon())) {
				disproval = suggestion.getTheWeapon();
			}
		}
		return disproval;
	}
	
    public void addSeenCard(Card card) {
        if (card.getType().equals(CardType.PERSON) == true) {
			this.seenPeopleCards.add(card);
		}
		else if (card.getType().equals(CardType.ROOM) == true) {
			this.seenRoomCards.add(card);
		}
		else if (card.getType().equals(CardType.WEAPON) == true) {
			this.seenWeaponCards.add(card);
		}
    }	
	 	
	public abstract Card selectTargets();
	
    public ArrayList<Card> getSeenRoomCards() {
        return this.seenRoomCards;
    }

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

	

	

	
}

