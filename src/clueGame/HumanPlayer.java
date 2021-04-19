package clueGame;
import java.util.*;

import javax.swing.JOptionPane;

import java.awt.*;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
        isHuman = true;
    }

    @Override
    public void moveLocation() {}
    
    @Override
    public boolean moveLocation(BoardCell cell) {
    	if (this.board.getTargets().contains(cell)) {
    		location.setUnoccupied();
    		for (BoardCell targetCells: this.board.getTargets()) {
    			targetCells.setUnoccupied();    			
    		}
    		location = cell;
    		cell.setOccupied(this);
    		madeMove = true;
    		Board.turnNumber++;
    		this.board.getTargets().clear();
    		this.board.repaint();
    		return true;
    	} else { 			
			madeMove = false;
			return false;
    	}
    	
     }
    
    
    public void move() {
    	madeMove = false;
    	this.board.showTargets();
    }

    public boolean hasMoved() {
    	return madeMove;
    }
    
    
    
    
    @Override
    public Card disproveSuggestion(Solution suggestion) {
        // Allow human player to select card to disprove
        Card disproval = null;
        return disproval;
    }

    @Override
    public Solution createSuggestion() {
        Solution suggestion = null;
        // Human player chooses cards
        // Create solution with 3 cards
        return suggestion;        
    }

    @Override
    public Card selectTargets() {
        // choose where to move
    	return null;
    }
}
