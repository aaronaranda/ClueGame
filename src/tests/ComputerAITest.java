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
		Card card6 = new Card("test6", CardType.ROOM);
		Card card7 = new Card("test7", CardType.ROOM);
		Card card8 = new Card("test8", CardType.ROOM);
		Card card9 = new Card("test9", CardType.ROOM);
		
		//Adds card to hand
		aIPlayer.updateHand(card1);
		aIPlayer.updateHand(card2);
		aIPlayer.updateHand(card3);
		aIPlayer.updateHand(card4);
		aIPlayer.updateHand(card5);
		aIPlayer.updateHand(card6);
		aIPlayer.updateHand(card7);
		aIPlayer.updateHand(card8);
		aIPlayer.updateHand(card9);
				
		
		//Creates test rooms
		Card room = aIPlayer.selectTargets();
		
		//Tests that a room was selected from the list
		boolean bool = false;
		int iterator = 0;
		for (Card c: aIPlayer.getDeck()) {
			if (c == room) {
				iterator += 1;
			}
		}
		if (iterator > 0) {
			bool = true;
		}
		assertEquals(bool, true);
		
		//Adds seen room test
		aIPlayer.addSeenCard(card1);
		aIPlayer.addSeenCard(card2);
		aIPlayer.addSeenCard(card3);
		aIPlayer.addSeenCard(card4);
		aIPlayer.addSeenCard(card5);
		aIPlayer.addSeenCard(card6);
		aIPlayer.addSeenCard(card7);
		aIPlayer.addSeenCard(card8);
		aIPlayer.addSeenCard(card9);
		
		//Creates test cells
		room = aIPlayer.selectTargets();
		
		//Tests that the room was still selected even with seen rooms
		bool = false;
		iterator = 0;
		for (Card c: aIPlayer.getSeenRoomCards()) {
			if (c == room) {
				iterator += 1;
			}
		}
		if (iterator > 0) {
			bool = true;
		}
		assertEquals(bool, true);
		
		
	}
}