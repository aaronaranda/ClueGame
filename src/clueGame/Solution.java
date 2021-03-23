package clueGame;

import java.util.ArrayList;
import java.util.Random;

public class Solution {
	//Variables to hold the solution cards
	public Card person;
	public Card room;
	public Card weapon;
	
	public Solution(ArrayList<Card> deck) {
		Random rand = new Random();
		int randNumber = 0;
		ArrayList<Card> personDeck = new ArrayList<Card>();
		ArrayList<Card> weaponDeck = new ArrayList<Card>();
		ArrayList<Card> roomDeck = new ArrayList<Card>();
		//Loops through the deck and adds each specific card type to its respective card ArrayList
		for (Card c: deck) {
			if (c.getType().equals(CardType.PERSON) == true) {
				personDeck.add(c);
			} else if (c.getType().equals(CardType.WEAPON) == true) {
				weaponDeck.add(c);		
			} else if (c.getType().equals(CardType.ROOM) == true) {
				roomDeck.add(c);
			}
		}
		//Creates a random number based on the size of the array list, then sets the solution to
		//a random card from that list for each of the respective card types
		randNumber = rand.nextInt(personDeck.size());
		this.person = personDeck.get(randNumber);
		randNumber = rand.nextInt(weaponDeck.size());
		this.room = weaponDeck.get(randNumber);;
		randNumber = rand.nextInt(roomDeck.size());
		this.weapon = roomDeck.get(randNumber);;
	}
}
