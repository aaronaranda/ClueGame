package clueGame;

import java.awt.Color; 
import java.awt.Point;
import java.util.*;

public class Player {
	
	private String name;
	private boolean isHuman;
	private Point startLocation;
	private Color color;
	private Set<Card> playerDeck;
	
	int row, col;
	
	//Constructor
	public Player(String name) {
		this.name = name;
		playerDeck = new HashSet<Card>();
	}
	
	public void updateColor(String colorType) {
		this.color = color.getColor(colorType);
	}
	
	
	public void updateHand(Card card) {
		if (!playerDeck.contains(card)) {
			playerDeck.add(card);
		} 
	}
	
	//Getters for the class
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
