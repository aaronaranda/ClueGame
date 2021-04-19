package clueGame;
import java.util.*;
import java.awt.*;

public class BoardCell {
	private Character initial;
	
    // Positioning
    private int row, col;
    
    // Graphics
    private Color color;
    
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
    private Player player;
    private boolean isTarget;

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
        } else {
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
    	isDoorway = false;
        switch(marker) {
            case '>':
                doorDirection = DoorDirection.RIGHT;
                isDoorway = true;
                break;
            case '<':
                doorDirection = DoorDirection.LEFT;
                isDoorway = true;
                break;
            case '^':
                doorDirection = DoorDirection.UP;
                isDoorway = true;
                break;
            case 'v':
                doorDirection = DoorDirection.DOWN;
                isDoorway = true;
                break;
            default:
                break;
        }        
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

    public void addAdj(BoardCell cell) {
        adjList.add(cell);
        cell.getAdjList().add(this);
    }

   
     
    public void setStart(Player player) {
    	isStart = true;
    	isOccupied = true;
    	this.player = player;    	    	
    }
        
    
    public void setTarget(boolean isTarget) {
    	this.isTarget = isTarget;      	
    }
    
    public void setUnoccupied() {
    	isTarget = false;
    	isStart = false;
    	isOccupied = false;   
    }
    
    public void setOccupied(Player player) {
    	isTarget = false;
        isOccupied = true;
        this.player = player;        
    }
    
//    public void setOccupied(boolean occupied) {
//    	isOccupied = occupied;
//    }
    
    public void setInitial(Character initial) {
    	this.initial = initial;
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
    
    public Character getSecretPassage() {
    	return room.getInitial();
    }
    
    public Character getInitial() {
    	return initial;
    }
    
    public Point getRoomFromDoorway() {
    	if (isDoorway) {
    		int row = 0;
    		int col = 0;
    		if (doorDirection.equals(DoorDirection.UP)) {
    			row = -1;    			
    		} else if (doorDirection.equals(DoorDirection.DOWN)) {
    			row = 1;
    		} else if (doorDirection.equals(DoorDirection.RIGHT)) {
    			col = 1;
    		} else if (doorDirection.equals(DoorDirection.LEFT)) {
    			col = -1;
    		}
    		return new Point(col, row);   		
    	}
    	return null;
    }

/*
 * GRAPHICS
 */ 

    public void draw(Graphics2D g, int x, int y, int size) {    	
    	if (isRoom && !isDoorway) {
    		color = new Color(212, 227, 251);
    	} 
    	if (isSpace) {
    		color = Color.BLACK;
    	} 
    	if (isWalkway) {
    		color = Color.WHITE;    		
    	} 
    	if (isTarget) {
    		color = Color.red;
    	}
    	int h = row * size + y;
    	int w = col * size + x;    	
    	g.setColor(color);
    	g.fillRect(w, h, size, size);
    	g.setColor(Color.BLACK);
    	if (isWalkway) {
    		g.drawRect(w + 1, h + 1, size + 2, size + 2);
    	}
    	if (isOccupied || isStart) {
    		g.setColor(player.color);
    		g.fillOval(w, h, size , size);
    	}
    }
//    
//    public void draw(Graphics2D g, int x, int y, int size, Set<BoardCell> targets ) {
//    	
//
//
//    	if (!targets.isEmpty()) {
//    		if (targets.contains(this)) {
//    			color = Color.red;
//    		}
//    	}
//    	else if (isRoom && !isDoorway) {
//    		color = new Color(212, 227, 251);
//    	} else if (isSpace) {
//    		color = Color.BLACK;
//    	} else if (isWalkway) {
//    		color = Color.WHITE;    		
//    	} 	  
//    	int h = row * size + y;
//    	int w = col * size + x;    	
//    	g.setColor(color);
//    	g.fillRect(w, h, size, size);
//    	g.setColor(Color.BLACK);
//    	if (isWalkway) {
//    		g.drawRect(w + 1, h + 1, size + 2, size + 2);
//    	}
//    	if (isOccupied || isStart) {
//    		g.setColor(player.color);
//    		g.fillOval(w, h, size , size);
//    	}
//    }
}
