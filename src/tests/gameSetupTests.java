package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class gameSetupTests {
	
	private static Board board;
	
	@BeforeAll
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout306.csv", "ClueSetup306.txt");
		board.initialize();
	}
	
	@Test
	public void testLoadPeople() {
		
	}
	
	@Test
	public void testHumanComputer() {
		
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
