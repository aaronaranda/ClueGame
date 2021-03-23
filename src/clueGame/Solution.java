package clueGame;

import java.util.ArrayList;
import java.util.Random;

public class Solution {
	public Card person;
	public Card room;
	public Card weapon;
	
	public Solution(ArrayList<Card> deck) {
		Random rand = new Random();
		int randNumber = 0;
		ArrayList<Card> personDeck = new ArrayList<Card>();
		ArrayList<Card> weaponDeck = new ArrayList<Card>();
		ArrayList<Card> roomDeck = new ArrayList<Card>();
		for (Card c: deck) {
			if (c.getType().equals(CardType.PERSON) == true) {
				personDeck.add(c);
			} else if (c.getType().equals(CardType.WEAPON) == true) {
				weaponDeck.add(c);		
			} else if (c.getType().equals(CardType.ROOM) == true) {
				roomDeck.add(c);
			}
		}
		randNumber = rand.nextInt(personDeck.size());
		this.person = personDeck.get(randNumber);
		randNumber = rand.nextInt(weaponDeck.size());
		this.room = weaponDeck.get(randNumber);;
		randNumber = rand.nextInt(roomDeck.size());
		this.weapon = roomDeck.get(randNumber);;
	}
}
