package clueGame;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
    
    // Room Centers ocuppied my multiple players
    private ArrayList<Player> roomPlayers;

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
            roomPlayers = new ArrayList<Player>();
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
    
 
    
    public void setUnoccupied(Player player) {
    	isTarget = false;
    	isStart = false;    	
    	if (isCenter && !roomPlayers.isEmpty()) {
    		roomPlayers.remove(player);
    	} else {
    		isOccupied = false;
    	}    	   	    
    }
    
    public void setOccupied(Player player) {
    	isTarget = false;
        isOccupied = true;             
        if (isCenter) {
        	roomPlayers.add(player);
        } else {
        	this.player = player;
        }
    }
        
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
    
    public ArrayList<Player> getRoomPlayers() {
    	return roomPlayers;
    }
    
    public Player getPlayer() {
    	return player;
    }

/*
 * GRAPHICS
 */ 

    public void draw(Graphics2D g, int x, int y, int size) {
    	int h = row * size + y;
    	int w = col * size + x;
    	
    	if (isRoom && !isDoorway) {
    		color = new Color(212, 227, 251);
    	} 
    	if (isSpace) {
    		color = Color.BLACK;
    	} 
    	if (isWalkway) {
    		color = Color.WHITE;    		
    	} 
    	if (!isDoorway && isTarget) {
    		color = Color.RED;
    	}    	
    	g.setColor(color);  
    	g.fillRect(w, h, size, size);
    	
    	if (isWalkway) {
    		g.setColor(Color.BLACK);
    		g.fillRect(w, h, 1, size - 1);
    		g.fillRect(w, h, size - 1, 1);
    	} 
    		
    	if (isDoorway && !isTarget) {
    		drawDoorway(g, x, y, size);
    	}   
    }
    
    public void drawDoorway(Graphics2D g, int x, int y, int size) {
    	// Draw a 2 pixel thick line for doorways on adjRoomCell
    	int xStart = col * size + x;
    	int yStart = row * size + y;    	
    	double width = 0;    	
    	double height = 0;
    	g.setColor(Color.RED);
    	if (doorDirection == DoorDirection.DOWN) {    		
    		yStart = (int)(yStart + (0.8 * size));
    		width = size;
    		height = 0.2 * size;
    	} else if (doorDirection == DoorDirection.UP) {   		    		
    		width = size;
    		height = 0.2 * size;    				
    	} else if (doorDirection == DoorDirection.LEFT) {    		
    		width = 0.2 * size;
    		height = size;
    	} else if (doorDirection == DoorDirection.RIGHT) {
    		xStart = (int)(xStart + (0.8 * size));    		
    		width = (0.2 * size);
    		height = size;
    	}
    	g.fillRect(xStart + 1, yStart + 1,
    			(int)(width), (int)(height));
    }
    
    public JLabel getIcon(int size) {    	
    	ImageIcon icon = room.getIcon();
    	Image im = icon.getImage();
    	Image newImage = im.getScaledInstance(2 * size, 2 * size, Image.SCALE_SMOOTH);
    	icon = new ImageIcon(newImage);
    	JLabel iconLabel = new JLabel();
    	iconLabel.setIcon(icon);
    	iconLabel.setSize(2 * size, 2 * size);
    	return iconLabel;
    	
    }
}
