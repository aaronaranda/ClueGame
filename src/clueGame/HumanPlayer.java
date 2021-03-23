package clueGame;

import java.awt.Point;
import java.awt.Color;

public class HumanPlayer extends Player{
	
	public HumanPlayer(String name) {
		super(name);
	}

	private String name;
	private boolean isHuman = true;
	private Point startLocation;
	private Color color;
	
	// Type is '#' in UML...?
	int row, col;
	
	public HumanPlayer(String name) {
		super(name);
	}
	
	
	public void updateColor(String colorType) {
		this.color = color.getColor(colorType);
	}
	
	public boolean getType() {
		return isHuman;
	}
	
	public void updateHand(Card card) {
		
	}
}
