package clueGame;

import java.awt.Point;
import java.util.ArrayList;
import java.awt.Color;

public class HumanPlayer extends Player{

	//Constructor
	public HumanPlayer(String name) {
		super(name);
		this.isHuman = true;
	}

	@Override
	public Card disproveSuggestion(Solution suggestion) {
        Card disproval = null;
        // Allow player to select a card to disprove        
		return disproval;
    }

	public Solution createSuggestion(
            Card person, Card room, Card weapon) {
        Solution suggestion = new Solution(this);
        suggestion.setThePerson(person);
        suggestion.setTheRoom(room);
        suggestion.setTheWeapon(weapon);
        return suggestion;
	}

	@Override
	public Card selectTargets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Card> getSeenRoomCards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Solution createSuggestion(Card room) { 
		return null;
	}
}
