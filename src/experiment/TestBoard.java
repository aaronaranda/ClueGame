package experiment;
import java.util.*;

public class TestBoard {
	private TestBoardCell[][] grid;
	private Set<TestBoardCell> targets;
	private static final int ROWS = 4;
	private static final int COLUMNS = 4;
	
	public TestBoard() {
        grid = new TestBoardCell[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                TestBoardCell cell = new TestBoardCell(i, j);
                grid[i][j] = cell;
            }
        }
        calcAdjacencies();
	}
    
    private void calcAdjacencies() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
            	TestBoardCell cell = getCell(i, j);
                if (validCell(i + 1, j)) {
                    cell.addAdjacency(getCell(i + 1, j));
                }
                if (validCell(i - 1, j)) {
                    cell.addAdjacency(getCell(i - 1, j));
                }
                if (validCell(i, j + 1)) {
                    cell.addAdjacency(getCell(i, j + 1));
                }
                if (validCell(i, j - 1)) {
                    cell.addAdjacency(getCell(i, j - 1));
                }
            }
        }
    }

    public boolean validCell(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLUMNS;
    }
    
    private int calcIndex(int row, int col) {
        return COLUMNS * row + col;
    }
    private int calcIndex(TestBoardCell cell) {
    	return calcIndex(cell.getRow(), cell.getCol());
    }

    public void calcTargets(TestBoardCell startCell, int pathLength) {
		boolean[] visited =  new boolean[ROWS * COLUMNS];
		this.targets = new HashSet<TestBoardCell>();
		int index = calcIndex(startCell);
		visited[index] = true;
		findAllTargets(index, pathLength, visited);
	}
    
    private void findAllTargets(int startIndex, int pathLength, boolean[] visited) {
    	Set<TestBoardCell> adjacentCells = new HashSet<TestBoardCell>();
    	TestBoardCell startCell = getCell(startIndex);
    	for (TestBoardCell cell: startCell.getAdjList()) {
    		if (visited[calcIndex(cell)] == false) {
    			adjacentCells.add(cell);
    		}
    	}
    	
    	for (TestBoardCell cell: adjacentCells) {
    		visited[calcIndex(cell)] = true;
    		if (pathLength == 1) {
    			targets.add(cell);
    		} else {
    			findAllTargets(calcIndex(cell), pathLength - 1, visited);
    		}
    		visited[calcIndex(cell)] = false;
    	}
    	
    }
	
	public TestBoardCell getCell(int row, int col) {
        return grid[row][col];
	}
	
	public TestBoardCell getCell(int index) {
		int col = index % COLUMNS;
		int row = index / ROWS;
		return grid[row][col];
	}
	
    
	public Set<TestBoardCell> getTargets() {
		return this.targets;
	}
}
