package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.*;
import java.util.*;

public class gameSetupTests {
	
	private static Board board;
	
	@BeforeAll
	public static void setUp() {
		//Sets up the board amd configures it before each test
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
	}
	
	@Test
	public void testLoadPeople() {			
		
		// Tests weapon name and label associations
		assertEquals("Sword", board.getWeapon('1'));
		assertEquals("Pistol", board.getWeapon('2'));
		assertEquals("Broom", board.getWeapon('3'));
		assertEquals("Pencil", board.getWeapon('4'));
		assertEquals("Rock", board.getWeapon('5'));
		assertEquals("Lighter", board.getWeapon('6'));
		
		// Tests player name and label associatons		
		assertEquals("Johnny", board.getPlayer('1').getName());
		assertEquals("Cooper", board.getPlayer('2').getName());
		assertEquals("Peter", board.getPlayer('3').getName());
		assertEquals("Katelynn", board.getPlayer('4').getName());
		assertEquals("Jessica", board.getPlayer('5').getName());
		assertEquals("Anna", board.getPlayer('6').getName());
	}
	
	@Test
	public void testHumanComputer() {
		int numPlayers = 0;
		int numComputers = 0;
		//Checks all the players to make sure that there is only 1 human player created
		for (int i = 1; i <= 6; i++) {
			boolean type;
			type = board.getPlayer((char)(i + 48)).getType();
		
			if (type == true) {
				numPlayers += 1;
			}
			else {
				numComputers += 1;
			}
		}
		//There should always only be 1 human player
		assertEquals(numPlayers, 1);
		assertEquals(numComputers, 5);
	}
	
	@Test
	public void testDeckCreation() {
		// Testing proper deck sizing (9 rooms, 6 players, 6 weapons)
		assertEquals(21, board.getDeck().size());
		
		ArrayList<Card> deck = board.getDeck();
		int players = 0;
		int weapons = 0; 
		int rooms = 0;
		for (Card c: deck) {
			if (c.getType().equals(CardType.PERSON)) {
				players++;
			} else if (c.getType().equals(CardType.WEAPON)) {
				weapons++;		
			} else if (c.getType().equals(CardType.ROOM)) {
				rooms++;
			}
		}
		
		assertEquals(9, rooms);
		assertEquals(6, players);
		assertEquals(6, weapons);		
	}
	
	@Test
	public void testSolutionDealt() {
		ArrayList<Card> deck = board.getDeck();
		int roomSolution = 0;
		int playerSolution = 0;
		int weaponSolution = 0;
		Solution solution = new Solution(deck);
		//Checks each car din the deck, and adds only if the card in the deck matches that of the solution
		for (Card c: deck) {
			if (c == solution.getThePerson()) {
				playerSolution += 1;
			} else if (c == solution.getTheRoom()) {
				roomSolution += 1;		
			} else if (c == solution.getTheWeapon()) {
				weaponSolution += 1;
			}
		}
		//Makes sure that only 1 card of each type is a solution from the whole deck
		assertEquals(playerSolution, 1);
		assertEquals(roomSolution, 1);
		assertEquals(weaponSolution, 1);
	}
	
	@Test
	public void testCardsDealt() {
		board.deal();
		
		Player playerOne = board.getPlayer('1');
		Player playerTwo = board.getPlayer('2');
		int diff = playerOne.getDeck().size() - playerTwo.getDeck().size();
		// Any difference in the number of cards dealt should only be one
		assertTrue(diff <= 1);
				
		Player playerThree = board.getPlayer('3');
		diff = playerOne.getDeck().size() - playerThree.getDeck().size();
				
		assertTrue(diff <= 1);
		
		Player playerFour = board.getPlayer('4');
		diff = playerOne.getDeck().size() - playerFour.getDeck().size();
				
		assertTrue(diff <= 1);
		
		Player playerFive = board.getPlayer('5');
		diff = playerOne.getDeck().size() - playerFive.getDeck().size();
				
		assertTrue(diff <= 1);

		Player playerSix = board.getPlayer('6');
		diff = playerOne.getDeck().size() - playerSix.getDeck().size();
				
		assertTrue(diff <= 1);
				
		// Compare playerOne's deck with everyone else's, unique cards only
		for (Card c: playerOne.getDeck()) {
			assertFalse(playerTwo.getDeck().contains(c));
			assertFalse(playerThree.getDeck().contains(c));
			assertFalse(playerFour.getDeck().contains(c));
			assertFalse(playerFive.getDeck().contains(c));
			assertFalse(playerSix.getDeck().contains(c));			
		}				
	}	
}
