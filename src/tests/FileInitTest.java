// Authors: Aaron Aranda and Alejandro Belli

package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;
import clueGame.Room;

public class FileInitTest {
	
	public static final int NUM_ROWS = 32;
	public static final int NUM_COLUMNS = 25;

	// NOTE: I made Board static because I only want to set it up one
	// time (using @BeforeAll), no need to do setup before each test.
	private static Board board;
	
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		// Initialize will load BOTH config files
		board.initialize();
	}
	
	@Test
	public void testLayout() {
		// Testing some room names
		BoardCell cell = board.getCell(0, 3);
		// assert("Unused".equals(cell.getRoom().getName()));
		cell = board.getCell(1,22);
		assert("Bedroom".equals(cell.getRoom().getName()));
		
		//Testing sizing
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
		
		
	}
	
	@Test
	public void testRowsAndColumns() {
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}
	
	@Test
	public void testDoorDirection() {
		BoardCell cell = board.getCell(7, 6);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.UP, cell.getDoorDirection());
		cell = board.getCell(18, 6);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.RIGHT, cell.getDoorDirection());
		cell = board.getCell(23, 8);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.DOWN, cell.getDoorDirection());
		cell = board.getCell(18, 16);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.LEFT, cell.getDoorDirection());
		cell = board.getCell(0, 0);
		assertFalse(cell.isDoorway());
	}
	
	
	@Test
	public void numberOfDoors() {
		int numDoors = 0;
		for (int row = 0; row < board.getNumRows(); row++)
			for (int col = 0; col < board.getNumColumns(); col++) {
				BoardCell cell = board.getCell(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		Assert.assertEquals(24, numDoors);
	}
	@Test
	public void testRoomCenters() {
		BoardCell cell = board.getCell(18, 1);
		assertTrue(cell.isCenter());
		cell = board.getCell(11, 5);
		assertTrue(cell.isCenter());
		cell = board.getCell(18, 8);
		assertTrue(cell.isCenter());
	}
	
	
	@Test
	public void testInitials() {
		BoardCell cell = board.getCell(10, 12);
		assertTrue(cell.getInitial().equals('X'));
		cell = board.getCell(22,13);
		assertTrue(cell.getInitial().equals('D'));
		cell = board.getCell(14, 21);
		assertTrue(cell.getInitial().equals('T'));		
	}
	
}
