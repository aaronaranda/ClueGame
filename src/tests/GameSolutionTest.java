package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import clueGame.*;
import java.util.*;

public class GameSolutionTest {
    
    private static Board board;
    
    @BeforeAll
    public static void setUp() {
        // Sets up a newly configured board before each test
        board = Board.getInstance();
        board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
        board.initialize();
    }    

    @Test
    public void checkAccusation() {
    	Player playerOne = board.getPlayer('1');
    
    	// Obtains the answer from the board
    	// (This getter will be deleted later)
    	Solution answer = board.getTheAnswer();   	
    
    	ArrayList<Card> playerOneDeck = new ArrayList<Card>(playerOne.getDeck());
    	
    	//First test false scenario
    	assertFalse(board.checkAccusation(
    			playerOneDeck.get(0),
    			playerOneDeck.get(1),
    			playerOneDeck.get(2)));
    	    
    	/*
    	 * Clears players deck, fills with cards equal to 
    	 * the ones in the answer
    	 */   	
    	playerOneDeck.clear();
    	playerOneDeck.add(answer.getThePerson());
    	playerOneDeck.add(answer.getTheRoom());
    	playerOneDeck.add(answer.getTheWeapon());
    	    	
    	assertTrue(board.checkAccusation(
    			playerOneDeck.get(0), 
    			playerOneDeck.get(1), 
    			playerOneDeck.get(2)));    	
    }
    
    @Test
    public void disproveSuggestion() {
    	board.getDeck();
    }
    
    @Test
    public void handleSuggestion() {
    	board.getDeck();
    }
    
    

}