package clueGame;
import java.util.*;
import java.awt.*;

public class ComputerPlayer extends Player {
    public ComputerPlayer(String name) {
        super(name);
        this.isHuman = false;
        this.madeMove = false;
    }

    @Override
    public boolean moveLocation(BoardCell cell) {
    	location.setUnoccupied(this);
    	location = cell;
    	location.setOccupied(this);
    	return true;
    }
    
    @Override
    public void moveLocation() {
    	if (!this.board.getTargets().isEmpty()) {
			BoardCell[] arrTargets = new BoardCell[this.board.getTargets().size()];
			arrTargets = this.board.getTargets().toArray(arrTargets);
			Random rand = new Random();
			location.setUnoccupied(this);			
			int index = rand.nextInt(arrTargets.length);
			location = arrTargets[index];
			if (location.isRoom()) {
				location = location.getRoom().getCenterCell();
				
			}
			location.setOccupied(this);
			madeMove = true;
		}
    }
    
    @Override
    public Card disproveSuggestion(Solution suggestion) {
        // Ensure player doesn't try to disprove their own suggestion
        if (this.equals(suggestion.getWhoSuggested())) {
            return null;
        }

        for (Card c: this.deck) {
            if (c.equals(suggestion.getPerson())) {
                return c;
            } else if (c.equals(suggestion.getRoom())) {
                return c;
            } else if (c.equals(suggestion.getWeapon())) {
                return c;
            }
        }
        return null;
    }
         
    @Override
    public Solution createSuggestion(Room room) {
    	// Constructor of solution passed a ComputerPlayer creates a randomized suggestion
        Solution suggestion = new Solution(this, this.board); 
        if (suggestion.getPerson() == null || suggestion.getWeapon() == null) {
        	return null;
        }
        return suggestion;
    }
    
    public void updateSeen(Card card) {
    	for (Card c: deck) {
    		if (c.equals(card)) {
    			c.seeCard();
    		}
    	}
    }
    
	@Override
	public Solution createSuggestion() {	
		return null;
	}
}
