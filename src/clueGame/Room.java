// Authors: Aaron Aranda and Alejandro Belli

package clueGame;

public class Room {
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	private boolean notRoom;
	
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
	
	public void isNotRoom() {
		notRoom = true;
	}
	
    // Getters
	public String getName() {
		return this.name;
	}
	
	public boolean getNotRoom() {
		return notRoom;
	}
	
	public BoardCell getLabelCell() {
		return labelCell;
	}
	
	public BoardCell getCenterCell() {
		return centerCell;
	}
	
}
