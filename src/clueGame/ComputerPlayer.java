package clueGame;

import java.util.ArrayList;

public class ComputerPlayer extends Player{
	
	ArrayList<Card> seenRoomCards = new ArrayList<Card>();
	ArrayList<Card> seenWeaponCards = new ArrayList<Card>();
	ArrayList<Card> seenPeopleCards = new ArrayList<Card>();
	
	//Constructor
	public ComputerPlayer(String name) {
		super(name);
		this.isHuman = false;
	}
	
	
	//Creates a random suggestion based on non-seen cards
	public Solution createSuggestion(Card room) {
		Solution solution = new Solution();
		solution.setTheRoom(room);
		return solution;
	}
	
	
	/*
	public Solution selectTargets() {
		
	}
	*/
	
	public void addSeenCard(Card card) {
		if (card.getType().equals(CardType.PERSON) == true) {
			seenPeopleCards.add(card);
		}
		else if (card.getType().equals(CardType.WEAPON) == true) {
			seenWeaponCards.add(card);
		}
		else if (card.getType().equals(CardType.ROOM) == true) {
			seenRoomCards.add(card);
		}
	}
	/*
	 * GETTERS
	 */
	
	

	
	
	
	

}
