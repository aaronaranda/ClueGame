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
        if (this == suggestion.getWhoSuggested()) {
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
    public Solution createSuggestion() {
    	// Constructor of solution passed a ComputerPlayer creates a randomized suggestion
        Solution suggestion = new Solution(this);
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
    public Card selectTargets() {
        Random rand = new Random(this.deck.size());
		int randNumber = 0;
		int seen = 0;
		Card target;
		ArrayList<Card> roomDeck = new ArrayList<Card>();
		//Loops through the deck and adds each room card type to its respective card ArrayList
		//As long as it hasn't already been seen
		for (Card c: this.deck) {
			if (c.getType().equals(CardType.ROOM)) {
				for (Card x: this.getRoomCards(true)) {
					if (x == c) {
						seen += 1;
					}
				}
				if (seen > 0) {
					seen = 0;
					continue;
				}
				else {
					roomDeck.add(c);
				}
			}
		}
		//If all rooms are seen then one is just selected from random
		if (roomDeck.size() == 0) {
			randNumber = rand.nextInt(this.getRoomCards(true).size());
			target = this.getRoomCards(true).get(randNumber);
			return target;
		}
		//Will choose a room at random from the list of non-seen rooms
		else {
			randNumber = rand.nextInt(this.getRoomCards(false).size());
			target = this.getRoomCards(false).get(randNumber);
			return target;
		}
    }
}
