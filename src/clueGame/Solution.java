package clueGame;

import java.util.ArrayList;
import java.util.Random;

public class Solution {
	//Variables to hold the solution cards
	private Card thePerson;
	private Card theRoom;
	private Card theWeapon;
	
	//Default Constructor
	public Solution() {
		this.thePerson = null;
		this.theRoom = null;
		this.theWeapon = null;
	}
	
	public Solution(ArrayList<Card> deck) {
		Random rand = new Random(deck.size());
		int randNumber = 0;
		ArrayList<Card> personDeck = new ArrayList<Card>();
		ArrayList<Card> roomDeck = new ArrayList<Card>();
		ArrayList<Card> weaponDeck = new ArrayList<Card>();
		//Loops through the deck and adds each specific card type to its respective card ArrayList
		for (Card c: deck) {
			if (c.getType().equals(CardType.PERSON) == true) {
				personDeck.add(c);
			} else if (c.getType().equals(CardType.ROOM) == true) {
				roomDeck.add(c);		
			} else if (c.getType().equals(CardType.WEAPON) == true) {
				weaponDeck.add(c);
			}
		}
		//Creates a random number based on the size of the array list, then sets the solution to
		//a random card from that list for each of the respective card types
		randNumber = rand.nextInt(personDeck.size());
		this.thePerson = personDeck.get(randNumber);
		randNumber = rand.nextInt(roomDeck.size());
		this.theRoom = roomDeck.get(randNumber);;
		randNumber = rand.nextInt(weaponDeck.size());
		this.theWeapon = weaponDeck.get(randNumber);;
	}
	
	/*
	 * GETTERS 
	 */
	
	public Card getThePerson() {
		return this.thePerson;
	}
	
	public Card getTheRoom() {
		return this.theRoom;
	}
	
	public Card getTheWeapon() {
		return this.theWeapon;
	}
	
	/*
	 * SETTERS 
	 */
	
	public void setThePerson(Card card) {
		this.thePerson = card;
	}
	
	public void setTheRoom(Card card) {
		this.theRoom = card;
	}
	
	public void setTheWeapon(Card card) {
		this.theWeapon = card;
	}
}
