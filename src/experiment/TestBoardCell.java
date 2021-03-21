package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoardCell {
	private int row;
    private int column;
	private boolean isOccupied;
	private boolean isRoom;
	private Set<TestBoardCell> adjList;	
    
	public TestBoardCell(int row, int column) {
		super();
		this.row = row;
		this.column = column;
		this.adjList = new HashSet();
	}
	
	public void addAdjacency(TestBoardCell cell) {        
		adjList.add(cell);
	}
	
	public Set<TestBoardCell> getAdjList() {
		return adjList;
	}
	
	public int getRow() {
		return this.row;
	}
	public int getCol() {
		return this.column;
	}
	
	public void setRoom(boolean x) {
		isRoom = x;
	}
	
	public boolean isRoom() {
		return isRoom;
	}
	
	public void setOccupied(boolean y) {
		isOccupied = y;
	}
	
	public boolean getOccupied() {
		if (isOccupied == true) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
