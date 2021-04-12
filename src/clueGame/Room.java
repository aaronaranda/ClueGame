// Authors: Aaron Aranda and Alejandro Belli

package clueGame;

import java.awt.*;

public class Room {
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	private boolean notRoom;
	private Color color = Color.CYAN;
	
    public Room(String name) {
        this.name = name;
        notRoom = false;
    }
	
	// Setters
	public void setLabelCell(BoardCell cell) {
		if (cell.isLabel()) {
			this.labelCell = cell;
		}
	}
	
	public void setCenterCell(BoardCell cell) {
		if (cell.isRoomCenter()) {
			this.centerCell = cell;
		}
	}	

    // Getters
	public String getName() {
		return this.name;
	}
	
	public BoardCell getLabelCell() {
		return labelCell;
	}
	
	public BoardCell getCenterCell() {
		return centerCell;
	}			
}
