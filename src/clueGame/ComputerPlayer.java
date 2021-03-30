package clueGame;

import java.util.ArrayList;
import java.util.Random;

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
	@Override
	public Solution createSuggestion(Card room) {
		Solution solution = new Solution(this);
		solution.setTheRoom(room);
		int seen = 0;
		
		Random rand = new Random(playerDeck.size());
		int randNumber = 0;
		ArrayList<Card> personDeck = new ArrayList<Card>();
		ArrayList<Card> weaponDeck = new ArrayList<Card>();
		//Loops through the deck and adds each specific card type to its respective card ArrayList
		//As long as it hasn't already been seen
		for (Card c: playerDeck) {
			if (c.getType().equals(CardType.PERSON) == true) {
				for (Card x: seenPeopleCards) {
					if (x == c) {
						seen += 1;
					}
				}
				if (seen > 0) {
					seen = 0;
					continue;
				}
				else {
					personDeck.add(c);
				}
			} else if (c.getType().equals(CardType.WEAPON) == true) {
				for (Card y: seenWeaponCards) {
					if (y == c) {
						seen += 1;
					}
				}
				if (seen > 0) {
					seen = 0;
					continue;
				}
				else {
					weaponDeck.add(c);
				}
			}
		}
		//Creates a random number based on the size of the array list, then sets the solution to
		//a random card from that list for each of the respective card types
		randNumber = rand.nextInt(personDeck.size());
		solution.setThePerson(personDeck.get(randNumber));
		randNumber = rand.nextInt(weaponDeck.size());
		solution.setTheWeapon(weaponDeck.get(randNumber));
		return solution;
	}
	
	
	@Override
	public Card selectTargets() {
		Random rand = new Random(playerDeck.size());
		int randNumber = 0;
		int seen = 0;
		Card target;
		ArrayList<Card> roomDeck = new ArrayList<Card>();
		//Loops through the deck and adds each room card type to its respective card ArrayList
		//As long as it hasn't already been seen
		for (Card c: playerDeck) {
			if (c.getType().equals(CardType.ROOM) == true) {
				for (Card x: seenRoomCards) {
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
			randNumber = rand.nextInt(seenRoomCards.size());
			target = seenRoomCards.get(randNumber);
			return target;
		}
		//Will choose a room at random from the list of non-seen rooms
		else {
			randNumber = rand.nextInt(roomDeck.size());
			target = roomDeck.get(randNumber);
			return target;
		}
	}
	
	
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
	
	public ArrayList<Card> getSeenRoomCards() {
		return seenRoomCards;
	}

	
	
	
	

}
