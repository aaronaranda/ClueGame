package clueGame;

import java.awt.Point;
import java.awt.Color;

public class ComputerPlayer extends Player{
	private String name;
	private boolean isHuman;
	private Point startLocation;
	private Color color;
	
	int row, col;
	
	//Constructor
	public ComputerPlayer(String name) {
		super(name);
		isHuman = false;
	}
	public void updateColor(String colorType) {
		this.color = color.getColor(colorType);
	}
	
	//Getter for boolean type
	public boolean getType() {
		return isHuman;
	}
	
	public void updateHand(Card card) {
		
	}
}
