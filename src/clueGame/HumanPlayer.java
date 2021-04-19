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
    public void moveLocation(Set<BoardCell> targets) {}
    
    @Override
    public boolean moveLocation(BoardCell cell, Set<BoardCell> targets) {
    	if (targets.contains(cell)) {
    		location.setUnoccupied();
    		for (BoardCell c: targets) {
    			c.setUnoccupied();
    			
    		}
    		location = cell;
    		cell.setOccupied(this);
    		madeMove = true;
    		return true;
    	} else { 
			String message = "Location " + cell.getRow() + " " + cell.getCol();
			JOptionPane.showMessageDialog(null, message);
			madeMove = false;
			return false;
    	}
    	
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
