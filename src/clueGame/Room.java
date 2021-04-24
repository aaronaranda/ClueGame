package clueGame;
import java.util.*;
import java.awt.*;
import javax.swing.*;
public class Room extends JLabel {
    // Identification
    private String name;   
    private Character initial;
    private ImageIcon icon;
    
    // Pointers to center and label cells
    private BoardCell labelCell;
    private BoardCell centerCell;

    // Data structure of cells w/in room
    private ArrayList<BoardCell> roomCells;    

    // Constructor
    public Room(String name, Character initial) {
        this.name = name;
        this.initial = initial;
        roomCells = new ArrayList<BoardCell>();
        //Icon
        if (this.name.contains(" ")) {
        	String[] temp = name.split(" ");
        	icon = new ImageIcon("./icons/" + temp[0] + ".png"); 
        } else {
        	icon = new ImageIcon("./icons/" + name + ".png");
        }                        
    }

    // Graphics
    private Color color;

/*
 * SETTERS
 */         
    public void setLabelCell(BoardCell cell) {
        labelCell = cell;
        addCell(cell);
    }

    public void setCenterCell(BoardCell cell) {
        centerCell = cell;
        addCell(cell);
    }

    public void addCell(BoardCell cell) {
        roomCells.add(cell);
    }


/*
 * GETTERS
 */  

    public String getName() {
        return name;
    }

    public Character getInitial() {
        return initial;
    }

    public BoardCell getLabelCell() {
        return labelCell;
    }

    public BoardCell getCenterCell() {
        return centerCell;
    }
    
    public ArrayList<BoardCell> getCells() {
    	return roomCells;
    }
    
    public ImageIcon getIcon() {
    	return icon;
    }


}
