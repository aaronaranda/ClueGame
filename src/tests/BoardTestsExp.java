package tests;
import java.util.Set;
import experiment.*;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;

public class BoardTestsExp {
	private TestBoard board;
	
	@BeforeEach
	public void setUp() {
		this.board = new TestBoard();
	}
	
	@Test
	public void testAdjacency () {
		TestBoardCell cell = board.getCell(0,0);
        Set<TestBoardCell> testList = cell.getAdjList();
        Assert.assertTrue(testList.contains(board.getCell(0, 1)));
        Assert.assertTrue(testList.contains(board.getCell(1, 0)));
        Assert.assertEquals(testList.size(), 2);
	}
    
    @Test
    public void testTargetsNormal() {
    	TestBoardCell cell = board.getCell(0, 0);
    	board.calcTargets(cell, 3);
    	Set<TestBoardCell> targets = board.getTargets();
    	Assert.assertEquals(6,  targets.size());
    	Assert.assertTrue(targets.contains(board.getCell(3, 0)));
    	Assert.assertTrue(targets.contains(board.getCell(2, 1)));
    	Assert.assertTrue(targets.contains(board.getCell(0, 1)));
    	Assert.assertTrue(targets.contains(board.getCell(1, 2)));
    	Assert.assertTrue(targets.contains(board.getCell(0, 3)));
    	Assert.assertTrue(targets.contains(board.getCell(1, 0)));
    }

    @Test
    public void testTargetsMixed() {
    	board.getCell(0, 2).setOccupied(true);
    	board.getCell(1, 2).setRoom(true);
    	TestBoardCell cell = board.getCell(0, 3);
    	board.calcTargets(cell, 3);
    	Set<TestBoardCell> targets = board.getTargets();
    	Assert.assertEquals(6,  targets.size());
    	Assert.assertTrue(targets.contains(board.getCell(0, 0)));
    	Assert.assertTrue(targets.contains(board.getCell(1, 1)));
    	Assert.assertTrue(targets.contains(board.getCell(2, 2)));
    	Assert.assertTrue(targets.contains(board.getCell(3, 3)));
    }
    
    @Test
    public void testOccupied() {
    	TestBoardCell cell = board.getCell(0, 0);
    	board.calcTargets(cell, 3);
    	Set<TestBoardCell> targets = board.getTargets();
    	board.getCell(3, 3).setOccupied(true);
    	board.getCell(1, 1).setOccupied(true);
    	board.getCell(2, 2).setOccupied(false);
    	board.getCell(1, 2).setOccupied(false);
    	Assert.assertTrue(board.getCell(3, 3).getOccupied());
    	Assert.assertTrue(board.getCell(1, 1).getOccupied());
    	Assert.assertFalse(board.getCell(2, 2).getOccupied());
    	Assert.assertFalse(board.getCell(1, 2).getOccupied());   	
    }
    
    @Test
    public void testRooms() {
    	TestBoardCell cell = board.getCell(0, 0);
    	board.calcTargets(cell, 3);
    	Set<TestBoardCell> targets = board.getTargets();
    	board.getCell(1, 1).setRoom(true);
    	board.getCell(1, 2).setRoom(true);
    	board.getCell(2, 2).setRoom(false);
    	board.getCell(2, 1).setRoom(false);
    	Assert.assertTrue(board.getCell(1, 1).isRoom());
    	Assert.assertTrue(board.getCell(1, 2).isRoom());
    	Assert.assertFalse(board.getCell(2, 2).isRoom());
    	Assert.assertFalse(board.getCell(2, 1).isRoom());    	
    	
    }
    

}
