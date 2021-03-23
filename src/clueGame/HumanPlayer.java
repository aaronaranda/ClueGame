package clueGame;

import java.awt.Point;
import java.awt.Color;

public class HumanPlayer extends Player{

	private String name;
	private boolean isHuman;
	private Point startLocation;
	private Color color;
	
	public HumanPlayer(String name) {
		super(name);
		isHuman = true;
	}
	
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
