package clueGame;

import java.awt.Color;
import java.awt.Point;

public class Player {
	
	private String name;
	private boolean isHuman;
	private Point startLocation;
	private Color color;
	
	// Type is '#' in UML...?
	int row, col;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean getType() {
		return isHuman;
	}
	
	public void updateColor(String colorType) {
		this.color = color.getColor(colorType);
	}
	
	
	
	public void updateHand(Card card) {
		
	}
}
