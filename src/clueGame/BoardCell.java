package clueGame;
import java.util.*;
import java.awt.*;

public class BoardCell {
    // Positioning
    int row, col;
    
    // Color
    Color color;

    // Type checks
    // Rooms
    private boolean isRoom;
    private boolean isCenter;
    private boolean isLabel;    
    private Room room;
    // SecretPassage
    private boolean isPassage;
    private Room passageRoom;

    // Spaces/Walkways
    private boolean isSpace;
    private boolean isWalkway;
    private boolean isDoorway;
    private DoorDirection doorDirection;

    // Starting position, player
    private boolean isStart;
    private boolean isOccupied;

    // Adjacent Cells
    ArrayList<BoardCell> adjList;


    // Constructor
    public BoardCell(int row, int col) {
        this.row = row;
        this.col = col;
        adjList = new ArrayList<BoardCell>();       
    }
    

/*
 * SETTERS
 */ 
        
    public void setRoom(Room room) {
        isRoom = true;
        this.room = room;
        room.addCell(this);
    }

    public void hasMarker(Character marker) {
        if (isRoom) {
            setCenter(marker);
            setLabel(marker);                                   
        } else if (isSpace) {
            setDoorway(marker);
        }
    }
    
    public void setCenter(Character marker)  {
        if (marker.equals('*')) {
            isCenter = true;
            room.setCenterCell(this);
        } else {
            isCenter = false;
        }
    }

    public void setLabel(Character marker) {
        if (marker.equals('#')) {
            isLabel = true;
            room.setLabelCell(this);
        } else {
            isLabel = false;
        }
    }

    public void setDoorway(Character marker) {
        switch(marker) {
            case '>':
                doorDirection = DoorDirection.RIGHT;
                break;
            case '<':
                doorDirection = DoorDirection.LEFT;
                break;
            case '^':
                doorDirection = DoorDirection.UP;
                break;
            case 'v':
                doorDirection = DoorDirection.DOWN;
                break;
            default:
                isDoorway = false;
                return;                
        }
        isDoorway = true;
    }

    public void setSpace(Character initial) {
        if (initial.equals('W')) {
            isWalkway = true;
        } else if (initial.equals('X')) {
            isSpace = true;
        } 
    }

    public void setSecretPassage(Room passage) {
        isPassage = true;
        passageRoom = passage;
    }

    public void setOccupied() {
        isOccupied = true;
    }

    public void addAdj(BoardCell cell) {
        adjList.add(cell);
    }

    public void updateColor() {
    	if (isRoom) {
    		color = Color.CYAN;
    	} else if (isSpace) {
    		color = Color.BLACK;
    	} else if (isWalkway) {
    		color = Color.WHITE;
    	}
    }

 /*
  * GETTERS
  */  
//Positioning
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;    
    }
// Types
// Room
    public boolean isRoom() {
        return isRoom;
    }

    public boolean isCenter() {
        return isCenter;
    }

    public boolean isLabel() {
        return isLabel;
    }       

    public boolean isPassage() {
        return isPassage;
    }
// Spaces
    public boolean isSpace() {
        return isSpace;
    }

    public boolean isWalkway() {
        return isWalkway;
    }

    public boolean isDoorway() {
        return isDoorway;
    }
// Player
    public boolean isStart() {
        return isStart;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
//Objects
    public Room getRoom() {
        return room;
    }

    public Room getPassage() {
        return passageRoom;
    }

    public DoorDirection getDoorDirection() {
        return doorDirection;
    }

    public ArrayList<BoardCell> getAdjList() {
        return adjList;
    }
    
    public Color getColor() {
    	return color;
    }

/*
 * GRAPHICS
 */ 

    public void draw(Graphics2D g, int x, int y, int size) {

    	if (isWalkway) {
    		color = Color.WHITE;
    	} else if (isRoom) {
    		color = Color.cyan;
    	} else if (isSpace) {
    		color = Color.black;
    	}
    	g.setColor(color);
    	g.fillRect(x, y, size, size);
    	
    }

}
