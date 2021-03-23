package clueGame;

import java.awt.Point;
import java.awt.Color;

public class ComputerPlayer extends Player{
	private String name;
	private boolean isHuman;
	private Point startLocation;
	private Color color;
	
	// Type is '#' in UML...?
	int row, col;
	
	public void updateColor(String colorType) {
		this.color = color.getColor(colorType);
	}
	
	public boolean getType() {
		return isHuman;
	}
	
	public void updateHand(Card card) {
		
	}
}
