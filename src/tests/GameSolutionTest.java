package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import clueGame.*;
import java.util.*;

public class GameSolutionTest {
    
    private static Board board;
    private static Card testCard;
    private static Solution testSuggestion;
    
    @BeforeEach
    public void setUp() {
        // Sets up a newly configured board before each test
        board = Board.getInstance();
        board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
        board.initialize();
        testCard = new Card("test card", CardType.PERSON);
        testSuggestion = board.getPlayer(2).createSuggestion();
    }    

    @Test
    public void checkAccusation() {
    	Player playerOne = board.getPlayer('1');
    
    	// Obtains the answer from the board
    	// (This getter will be deleted later)
    	Solution answer = board.getTheAnswer();   	
   
    	ArrayList<Card> playerOneDeck = new ArrayList<Card>(playerOne.getDeck());
    	ArrayList<Card> people = new ArrayList<Card>();
    	ArrayList<Card> rooms = new ArrayList<Card>();
    	ArrayList <Card> weapons = new ArrayList<Card>();
    	for (Card c: playerOneDeck) {
    		if (c.getType().equals(CardType.PERSON)) {
    			people.add(c);
    		} else if (c.getType().equals(CardType.ROOM)) {
    			rooms.add(c);
    		} else if (c.getType().equals(CardType.WEAPON)) {
    			weapons.add(c);
    		}
    	}
    	
    	// First false scenario with wrong person
    	assertFalse(board.checkAccusation(new Solution(playerOne,
    			people.get(0), answer.getRoom(), answer.getWeapon())
    			));
    	
    	// Second false scenario with wrong room
    	assertFalse(board.checkAccusation(new Solution(playerOne,
    			answer.getPerson(), rooms.get(0), answer.getWeapon())
    			));
    	
    	// Third false scenario with wrong weapon
    	assertFalse(board.checkAccusation(new Solution(playerOne,
    			answer.getPerson(), answer.getRoom(), weapons.get(0))
    			));
    			    	    
    	/*
    	 * Clears players deck, fills with cards equal to 
    	 * the ones in the answer
    	 */   	
    	playerOneDeck.clear();
    	playerOneDeck.add(answer.getPerson());
    	playerOneDeck.add(answer.getRoom());
    	playerOneDeck.add(answer.getWeapon());
    	    	
    	assertTrue(board.checkAccusation(new Solution(playerOne,
    			playerOneDeck.get(0), 
    			playerOneDeck.get(1), 
    			playerOneDeck.get(2)
    			)));    	
    }
    
    @Test
    public void disproveSuggestion() {
    	// Human player
    	Player playerOne = board.getPlayer('1');

		// Clear playerOne deck
		playerOne.getDeck().clear();
       			
    	assertEquals(playerOne.disproveSuggestion(testSuggestion), null);

    	// Add suggested card to playerOne's deck
		playerOne.getDeck().add(testCard);

		// Ensure that disproveSuggestion returns the suggested card from player's deck
		assertEquals(playerOne.disproveSuggestion(testSuggestion), testCard);
		
		Card matchingTestCard = new Card("test card", CardType.PERSON);
		
		playerOne.getDeck().add(matchingTestCard);
		assertTrue(playerOne.disproveSuggestion(testSuggestion).equals(matchingTestCard));			
    }
    
    @Test
    public void handleSuggestion() {
    
    
    	// Get the map of all the players
    	// Map<Character, Player> players = new HashMap<Character, Player>(board.getPlayers());
    	
    	Card resultCard = new Card("result card", CardType.PERSON);    	
    	
    	for (Player player: board.getPlayers()) {
    		resultCard = player.disproveSuggestion(testSuggestion);
    	}
    	
    	assertEquals(resultCard, null);
    	
    	// Test for null if accusing player can disprove its own suggestion
    	resultCard = new Card("result card", CardType.PERSON);
    	board.getPlayer('2').getDeck().add(resultCard);

    	// Player attempts to disprove its own suggestion
    	assertEquals(board.getPlayer('2').disproveSuggestion(testSuggestion), null);
    }
}
