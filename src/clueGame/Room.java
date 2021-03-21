// Authors: Aaron Aranda and Alejandro Belli

package clueGame;

public class Room {
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
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
	
    public Room(String name) {
        this.name = name;
    }

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
