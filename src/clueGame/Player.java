package clueGame;

import java.awt.*;
import java.util.*;

public abstract class Player {
	
	protected String name;
	protected boolean isHuman;
	protected Point startLocation;
	protected Color color;
	protected ArrayList<Card> playerDeck;
	 
	// Position
	protected int row, col;
		
	//Constructor
	public Player(String name) {
		this.name = name;
		this.playerDeck = new ArrayList<Card>();
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
	
	public abstract void addSeenCard(Card card);
	
	public abstract Solution createSuggestion(Card card);
	
	public abstract Card selectTargets();
	
	public abstract ArrayList<Card> getSeenRoomCards();

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

