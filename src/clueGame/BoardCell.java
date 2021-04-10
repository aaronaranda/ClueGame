// Authors: Aaron Aranda and Alejandro Belli

package clueGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class BoardCell extends JPanel {

    // Location
	private int row;
	private int col;

    // Type Identifiers
	private char initial;
	private char secretPassage;
	private Room room;
	Color color;


    // Specifics 
    private DoorDirection doorDirection;
	private boolean roomLabel;
	private boolean roomCenter;
	private boolean doorway;
	private boolean occupied;
	private boolean walkway;
	private boolean passage;
	private JLabel label;

    // Default attributes
	private Set<BoardCell> adjList;

    // Constructors    
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}	
	
	public void addAdj(BoardCell cell) {
		if (this.adjList == null) {
			this.adjList = new HashSet<BoardCell>();
		}
        this.adjList.add(cell);
	}
	
	/*
	 * GRAPHICS
	 */
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(color);
	}


    /*
     * SETTERS
     */
	
    public void setInitial(char initial) {
        this.initial = initial;
        if (initial == 'W') {
        	this.walkway = true;
        	this.color = Color.WHITE;
        	setBorder(BorderFactory.createMatteBorder(
        			1, 1, 1, 1, Color.BLACK));
        } else if (initial == 'X') {
        	this.color = Color.BLACK;
		}
    }
    
    public void setCenter(char center) {
    	if (center == '*') {
    		this.roomCenter = true;
    		this.room.setCenterCell(this);
    	} else {
    		this.roomCenter = false;
    	}
    }
    
    public void setDirection(char direction) {
    	this.doorway = true;

		switch (direction) {
			case '>': 
				this.doorDirection = DoorDirection.RIGHT;
				setBorder(new MatteBorder(
								0, 0, 0, 2, Color.RED));
				break;			
			case '<': 
				this.doorDirection = DoorDirection.LEFT;
				setBorder(new MatteBorder(
						0, 2, 0, 0, Color.RED));
				break;
			case '^':
				this.doorDirection = DoorDirection.UP;
				setBorder(new MatteBorder(
							2, 0, 0, 0, Color.RED));
				break;			
			case 'v':
				this.doorDirection = DoorDirection.DOWN;
				setBorder(new MatteBorder(
							0, 0, 2, 0, Color.RED));
				break;
			default : {
				this.doorway = false;
				break;
			}
		}
    }
    
    // If indicator is '#', indication of where to draw room label
    public void setLabel(char isLabel) {
    	if (isLabel == '#') {
    		this.roomLabel = true;
    		this.room.setLabelCell(this);
    		// Then set the label, which should be the name
			this.label = new JLabel(room.getName());
			add(this.label);
    	} else {
    		this.roomLabel = false;
    	}

    	
    }
    
    public void setRoom(Room room) {
    	this.room = room;
    	this.color = Color.CYAN;
    }

    public void setSecretPassage(char passage) {
    	this.passage = true;
        this.secretPassage = passage;
    }
    
    public void setOccupied(boolean occupied) {
    	this.occupied = occupied;
    }

    /*
     * GETTERS
     */
    
    public int getRow() {
    	return this.row;
    }
    
    public int getCol() {
    	return this.col;
    }
    
    public Set<BoardCell> getAdjList() {
    	return this.adjList;
    }
    
	public Room getRoom() {
		return this.room;
	}
	
	public char getInitial() {
		return this.initial;
	}
	
	public DoorDirection getDoorDirection() {
		return this.doorDirection;
	}
	
	public boolean isLabel() {
		return this.roomLabel;
	}
	
	public boolean isDoorway() {
		return this.doorway;
	}
	
	public boolean isRoomCenter() {
		return this.roomCenter;
	}
	
	public boolean isOccupied() {
		return this.occupied;
	}
	
	public boolean isWalkway() {
		return this.walkway;
	}
	
	public boolean isPassage() {
		return this.passage;
	}
	
	public char getSecretPassage() {
		return secretPassage;
	}
	
	public String getLabel() {
		return this.room.getName();
	}
}
