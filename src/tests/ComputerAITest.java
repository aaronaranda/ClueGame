package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import clueGame.*;
import org.junit.jupiter.api.*;

public class ComputerAITest {
	private static Board board;
	
	@BeforeEach
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
	}
	
	@Test
	public void selectTargets() {
		
	}
	
	@Test 
	public void createSuggestion() {
		
	}
}