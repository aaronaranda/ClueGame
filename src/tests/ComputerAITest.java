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
		aIPlayer.getDeck().clear();
		
		//Created suggestion
		Solution suggestion;
		
		//Test cards
		Card card1 = new Card("test1", CardType.PERSON);
		Card card2 = new Card("test2", CardType.PERSON);
		Card card3 = new Card("test3", CardType.WEAPON);
		Card card4 = new Card("test4", CardType.WEAPON);
		Card card5 = new Card("test5", CardType.ROOM);
		
		//Adds card to hand
		aIPlayer.updateHand(card1);
		aIPlayer.updateHand(card2);
		aIPlayer.updateHand(card3);
		aIPlayer.updateHand(card4);
		aIPlayer.updateHand(card5);
		
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
		//AI player
		Player aIPlayer = board.getPlayer('2');
		aIPlayer.getDeck().clear();
		
		//Test rooms
		Card card1 = new Card("test1", CardType.ROOM);
		Card card2 = new Card("test2", CardType.ROOM);
		Card card3 = new Card("test3", CardType.ROOM);
		Card card4 = new Card("test4", CardType.ROOM);
		Card card5 = new Card("test5", CardType.ROOM);
		
		//Adds card to hand
		aIPlayer.updateHand(card1);
		aIPlayer.updateHand(card2);
		aIPlayer.updateHand(card3);
		aIPlayer.updateHand(card4);
		aIPlayer.updateHand(card5);
				
		
		//Creates test cells
		BoardCell cell1 = aIPlayer.selectTargets();
		BoardCell cell2 = aIPlayer.selectTargets();
		
		//Tests that the 2 cells are not equal due to random selection, but will occasionally fail due to randomness
		assertNotEquals(cell1, cell2);
		
		//Adds seen room test
		aIPlayer.addSeenCard(card1);
		aIPlayer.addSeenCard(card2);
		aIPlayer.addSeenCard(card3);
		aIPlayer.addSeenCard(card4);
		aIPlayer.addSeenCard(card5);
		
		//Creates test cells
		cell1 = aIPlayer.selectTargets();
		cell2 = aIPlayer.selectTargets();
		
		//Tests that the 2 cells are not equal due to random selection, but will occasionally fail due to randomness
		assertNotEquals(cell1, cell2);
		
		
	}
}