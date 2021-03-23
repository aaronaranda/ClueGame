package clueGame;
import java.awt.*;
public class Player {
	private String name;
	private Color color;	// TODO: What is a Color?
	private boolean isHuman;
	private Point startLocation;
	
	// Type is '#' in UML...?
	int row, col;
	
	
	public Player(String name) {
		this.name = name;
		// color = new Color(color);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setType(boolean type) {
		this.isHuman = true;
	}
	

	
	
	public void updateHand(Card card) {
		
	}
	
		
}
