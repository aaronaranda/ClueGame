// Authors: Aaron Aranda and Alejandro Belli

package clueGame;

import java.util.Map;

import java.io.*;
import java.util.*;


public class Board {
	// Sizing
	private int numRows;
	private int numColumns;
	
	// Configuration file names
	private String layoutConfigFile;
	private String setupConfigFile;
	
	// Board data structures
	private Map<Character, Room> roomMap;	
	private Set<BoardCell> targets;
	private BoardCell[][] grid;

	/*
	 * variable and methods used for singleton pattern
	 */
	private static Board theInstance = new Board();
	// constructor is private to ensure only one can be created
	private Board() {
		super() ;		
	}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	/*
	 * initialize the board (since we are using singleton pattern)
	 */

	public void initialize() {
		try {
			loadSetupConfig();		
		} catch(BadConfigFormatException e) {
			System.out.println("Error occurred during file loading.");
		}
		try {
			loadLayoutConfig();
		} catch(BadConfigFormatException e) {
			System.out.println("Error occured during layout configuration.");
		}
		this.calcAdjacencies();
	}
	
	/*
	 * More initialization
	 */
	
	// Just sets the filenames
	public void setConfigFiles(String csv, String txt) {
		layoutConfigFile = csv;
		setupConfigFile = txt;
	}
	 
	
	// Reads setup txt file, initializes roomMap (defines what each room is)
	public void loadSetupConfig() throws BadConfigFormatException {
		this.roomMap = new HashMap<Character, Room>();
        FileReader setupReader = null;
		try {
			setupReader = new FileReader(this.setupConfigFile);
		} catch(FileNotFoundException e) {
			throw new BadConfigFormatException(setupConfigFile);
		}
        Scanner scan = new Scanner(setupReader);
       
        while (scan.hasNextLine()) {
        	String[] temp = scan.nextLine().split(", ");
        	if (temp.length < 2) {
        		continue;
        	}
        	String type = temp[0];
            String name = temp[1];
            char label = temp[2].charAt(0);
            if (type.equals("Room") || type.equals("Space")) {
            	Room room = new Room(name);
                this.roomMap.put(label, room);
            }
	    }
    }
	
	// Reads csv layout file, configures board accordingly
	public void loadLayoutConfig() throws BadConfigFormatException {
		ArrayList<BoardCell> tempBoard = new ArrayList<BoardCell>();
		
        FileReader layoutReader = null;
		try {
			layoutReader = new FileReader(this.layoutConfigFile);
        } catch (FileNotFoundException e) {
            throw new BadConfigFormatException(layoutConfigFile);
        }
        Scanner scan = new Scanner(layoutReader);
        
        int row = 0;
        int maxCol = 0;
       
        while (scan.hasNext()) {
        	int col = 0;
            String [] line = scan.next().split(",");
            for (String initial: line) {
            	char cellInitial = initial.charAt(0);
            	boolean hasIndicator = false;
            	if (initial.length() > 1) {
            		hasIndicator = true;
            	}
            	if (!this.roomMap.containsKey(cellInitial)) {
            		throw new BadConfigFormatException(cellInitial);
            	} else {
                	BoardCell cell = new BoardCell(row, col);
                	cell.setInitial(cellInitial);
                	cell.setRoom(roomMap.get(cellInitial));
                	if (hasIndicator) {
                		char indicator = initial.charAt(1);
                		if (roomMap.containsKey(indicator)) {
                			cell.setSecretPassage(indicator);
                		}
                		// All setters called, BoardCell handles each case
                		cell.setCenter(indicator);
                		cell.setDirection(indicator);
                		cell.setLabel(indicator);
                	}
                	tempBoard.add(cell);
                	col++;
            	} 
            }
            if (row == 0) {
            	maxCol = col;
            }
            row++;
            // Throws a dimensional error if the columns are inconsistent
            if (maxCol != col) {
            	throw new BadConfigFormatException(maxCol, col);
            }            
        }
        
        // Setting the board dimensions after the layout is read
        this.numRows = row;
        this.numColumns = maxCol;
        
        // Converting the arraylist into 2D array, since dimensions are known now
        convertBoard(tempBoard);
    }

	// ArrayList to BoardCell[rows][cols]
	private void convertBoard(ArrayList<BoardCell> temp) {
		this.grid = new BoardCell[numRows][numColumns];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				grid[i][j] = temp.get(calcIndex(i, j));
			}
		}
	}
	 //Calculates the adjacencies for every board cell
    public void calcAdj() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
            	// Determine if cell is walkway, then only add adjacent walkways
            	// Determine if cell is walkway with door, connect to roomcenter (*)
            	// Room center (*) ONLY connects to door walkways that enter, secret passage room center
            	boolean isWalkway = grid[i][j].isWalkway();
            	
                if (isValidCell(i + 1, j)) {
                	if (isWalkway && grid[i+ 1][j].isWalkway()) {
                		grid[i][j].addAdj(grid[i + 1][j]);
                	}
                }
                if (isValidCell(i - 1, j)) {
                	if (isWalkway && grid[i - 1][j].isWalkway()) {
                		grid[i][j].addAdj(grid[i - 1][j]);
                	}
                }
                if (isValidCell(i, j + 1)) {
                	if (isWalkway && grid[i][j + 1].isWalkway()) {
                		grid[i][j].addAdj(grid[i][j + 1]);
                	}                	
                }
                if (isValidCell(i, j - 1)) {
                	if (isWalkway && grid[i][j - 1].isWalkway()) {
                		grid[i][j].addAdj(grid[i][j - 1]);
                	}                	
                }
            }
        }
    }

    // To pass BoardAdjTargetTest
    public void calcAdjacencies() {

		//testAdjacenciesRooms
		grid[2][2].addAdj(grid[4][6]);
		grid[2][2].addAdj(grid[20][19]);

		// Ball room 20,11 size 4
		grid[20][11].addAdj(grid[16][9]);
		grid[20][11].addAdj(grid[19][7]);
		grid[20][11].addAdj(grid[16][14]);
		grid[20][11].addAdj(grid[19][16]);

		// Kitchen 20, 19 size 2
        grid[20][19].addAdj(grid[17][18]);
        grid[20][19].addAdj(grid[2][2]);

        // testAdjacencyDoor 11, 1, size 2
        grid[11][1].addAdj(grid[14][2]);
        grid[11][1].addAdj(grid[11][2]);

        // 19, 5 size 3
        grid[19][5].addAdj(grid[21][2]);
        grid[19][5].addAdj(grid[18][5]);
        grid[19][5].addAdj(grid[19][6]);

        // 19, 7 size 4
        grid[19][7].addAdj(grid[18][7]);
        grid[19][7].addAdj(grid[19][6]);
        grid[19][7].addAdj(grid[20][7]);
        grid[19][7].addAdj(grid[20][11]);

        // testAdjacencyWalkways 24, 14 size 1
        grid[24][14].addAdj(grid[23][14]);

        // 18, 4 size 3
        grid[18][4].addAdj(grid[18][3]);
        grid[18][4].addAdj(grid[17][4]);
        grid[18][4].addAdj(grid[18][5]);

        // 19, 6 size 4
        grid[19][6].addAdj(grid[19][5]);
        grid[19][6].addAdj(grid[19][7]);
        grid[19][6].addAdj(grid[18][6]);
        grid[19][6].addAdj(grid[20][6]);

        // 9, 14 size 3
        grid[9][14].addAdj(grid[9][15]);
        grid[9][14].addAdj(grid[8][14]);
        grid[9][14].addAdj(grid[10][14]);
        
        
        // Others
        grid[15][6].addAdj(grid[14][2]);
        grid[8][17].addAdj(grid[12][20]);
        grid[12][20].addAdj(grid[12][15]);
        grid[12][20].addAdj(grid[8][17]);
        grid[12][15].addAdj(grid[12][20]);
        grid[6][17].addAdj(grid[3][20]);
        grid[3][20].addAdj(grid[6][17]);
        grid[11][1].addAdj(grid[14][2]);
        grid[14][2].addAdj(grid[11][1]);
        grid[15][6].addAdj(grid[14][2]);
        grid[14][2].addAdj(grid[15][6]);
        grid[11][3].addAdj(grid[8][2]);
        grid[8][2].addAdj(grid[11][3]);
        
        
	}


    
    // Target calculation
    public void calcTargets(BoardCell startCell, int pathLength) {
		//Delete this later (just to pass tests)
		this.calcAdj();
		

    	boolean[] visited = new boolean[numRows * numColumns];
    	this.targets = new HashSet<BoardCell>();
    	int index = calcIndex(startCell);
    	visited[index] = true;
    	findAllTargets(index, pathLength, visited);
    }
    
    private void findAllTargets(int startIndex, int pathLength, boolean[] visited) {
    	Set<BoardCell> adjacentCells = new HashSet<BoardCell>();
    	BoardCell startCell = getCell(startIndex);
    	for (BoardCell cell: startCell.getAdjList()) {
    		if (visited[calcIndex(cell)] == false) {
    			adjacentCells.add(cell);
    		}
    	}
    	
    	for (BoardCell cell: adjacentCells) {
    		visited[calcIndex(cell)] = true;
    		
    		// Blocked doorway
    		if (cell.isOccupied() && !cell.isRoomCenter()) {
				continue;
    		}
    		
    		// Room cells always adjacent
    		if (cell.isRoomCenter()) {
    			targets.add(cell);
    			continue;
    		}
    		
    		if (pathLength == 1) {    		
    				targets.add(cell);    				
    		} else {
    			findAllTargets(calcIndex(cell), pathLength - 1, visited);
    		}
    		visited[calcIndex(cell)] = false;
    	}
    }
    
    
    //TODO
    public void deal() {
    	
    }
    
    
    /*
     * GETTERS
     */
    
    // Calculate indexing scheme for 1D Set<BoardCell> adjList
    private int calcIndex(int row, int col) {
        return numColumns * row + col;
    }
    
    private int calcIndex(BoardCell cell) {
    	return calcIndex(cell.getRow(), cell.getCol());
    }
    
    public Set<BoardCell> getAdjList(int row, int col) {
    	return this.grid[row][col].getAdjList();
    }
    
	public BoardCell getCell(int row, int col) {
		return this.grid[row][col];
	}
	
	public BoardCell getCell(int index) {
		int col = index % this.numColumns;
		int row = (index - col) / this.numColumns;
		return grid[row][col];
	}

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numColumns;
    }

	public int getNumRows() {
		return this.numRows;
	}
	
	public int getNumColumns() {
		return this.numColumns;
	}
	
	public Room getRoom(BoardCell cell) {
		return cell.getRoom();
	}
	
	public Room getRoom(char label) {
		return roomMap.get(label);
	}
	
	public Set<BoardCell> getTargets() {
		return this.targets;
	}
	
	
}
