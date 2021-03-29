package tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import clueGame.*;


public class ComputerAITest {
	
	private static Board board;
    
    @BeforeEach
    public void setUp() {
        // Sets up a newly configured board before each test
        board = Board.getInstance();
        board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
        board.initialize();
    }    
	
	@Test 
	public void createSuggestion() {
		//AI player
		Player aIPlayer = board.getPlayer('2');
		
		//Created suggestion
		Solution suggestion;
		
		//Test cards
		Card card1 = new Card("test1", CardType.PERSON);
		Card card2 = new Card("test2", CardType.PERSON);
		Card card3 = new Card("test3", CardType.WEAPON);
		Card card4 = new Card("test4", CardType.WEAPON);
		Card card5 = new Card("test5", CardType.ROOM);
		
		//Adds seen cards
		aIPlayer.addSeenCard(card1);
		aIPlayer.addSeenCard(card3);
		
		//Creates test suggestion
		suggestion = aIPlayer.createSuggestion(card5);
		
		//Tests that the created suggestion does not contain already seen cards
		assertEquals(suggestion.getThePerson(), card2);
		assertEquals(suggestion.getTheWeapon(), card4);
		
		
		
	}
	
	@Test
	public void selectTargets() {
		
	}
}