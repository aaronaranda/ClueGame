package clueGame;

import java.awt.*;
import java.util.*;

public abstract class Player {
	
	private String name;
	private boolean isHuman;
	private Point startLocation;
	private Color color;
	private Set<Card> playerDeck;
	
	private int row, col;
	
	public Player(String name) {
		this.name = name;
		playerDeck = new HashSet<Card>();
	}
	
	// Adds card to players deck of cards
	public void updateHand(Card card) {
		if (!playerDeck.contains(card)) {
			playerDeck.add(card);
		} 
	}
	
	// Updates the color of each player
	public void updateColor(String colorType) {
		this.color = Color.getColor(colorType);
	}

	/*
	 * GETTERS
	 */
	
	public String getName() {
		return this.name;
	}
	
	public boolean getType() {
		return isHuman;
	}
	
	public Set<Card> getDeck() {
		return this.playerDeck;
	}	
}
