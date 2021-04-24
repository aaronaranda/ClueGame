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
    	if (this.board.getTargets().contains(cell) && (!cell.isOccupied() || cell.isCenter())) {
    		location.setUnoccupied(this);
    		for (BoardCell targetCells: this.board.getTargets()) {
    			targetCells.setUnoccupied(this);    			
    		}
    		if (cell.isRoom()) {
    		    location = cell.getRoom().getCenterCell();
            } else {
                location = cell;
            }
    		cell.setOccupied(this);
    		madeMove = true;    		
    		this.board.getTargets().clear();
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
    
    public void setMove(boolean move) {
    	madeMove = move;
    }
        
    
    
    @Override
    public Card disproveSuggestion(Solution suggestion) {    
    	if (this.equals(suggestion.getWhoSuggested())) {
    		return null;
    	}
        for (Card card: deck) {
        	if (card.equals(suggestion.getPerson())) {
        		return card;
        	} else if (card.equals(suggestion.getRoom())) {
        		return card;
        	} else if (card.equals(suggestion.getWeapon())) {
        		return card;
        	}
        }
        return null;
    }

    @Override
    public Solution createSuggestion() {
      
        // Human player chooses cards
        // Create solution with 3 cards
        return null;      
    }

   
	@Override
	public Solution createSuggestion(Room room) {
		// TODO Auto-generated method stub
		return null;
	}
}
