package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;


public class gameSetupTests {
	
	private static Board board;
	
	@BeforeAll
	public static void setUp() {
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
		assertEquals(numPlayers, 1);
		assertEquals(numComputers, 5);
	}
	
	@Test
	public void testDeckCreation() {
		
	}
	
	@Test
	public void testSolutionDealt() {
		
	}
	
	@Test
	public void testCardsDealt() {
		
	}
	
}
