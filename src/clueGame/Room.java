import java.util.*;
import java.awt.*;

public class Room {
    // Identification
    private String name;   
    private Character initial;
    
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
    }

    // Graphics
    private Color color;

/*
 * SETTERS
 */         
    public void setLabelCell(BoardCell cell) {
        labelCell = cell;
    }

    public void setCenterCell(BoardCell cell) {
        centerCell = cell;
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


}
