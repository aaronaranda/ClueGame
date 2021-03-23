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
	private Map<Character, String> weapons;
	private Map<Character, Player> players;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;
	private Map<String,BoardCell> centers;	// Keep track of room centers for adjacencies

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
		this.weapons = new HashMap<Character, String>();
		this.players = new HashMap<Character, Player>();
		
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
            } else if (type.equals("Weapon")) {
            	this.weapons.put(label, name);
            } else if (type.equals("Player")) {
            	Player player = new ComputerPlayer(name);
            	this.players.put(temp[2].charAt(1), player);
            	
            }
	    }
        scan.close();
    }
	
	// Reads csv layout file, configures board accordingly
	public void loadLayoutConfig() throws BadConfigFormatException {
		ArrayList<BoardCell> tempBoard = new ArrayList<BoardCell>();
		centers = new HashMap<String, BoardCell>();
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
                		if (cell.isRoomCenter()) {
                			this.centers.put(initial, cell);
                		}
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
        scan.close();
        
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
    public void calcAdjacencies() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
            	// Determine if cell is walkway, then only add adjacent walkways
            	// Determine if cell is walkway with door, connect to roomcenter (*)
            	// Room center (*) ONLY connects to door walkways that enter, secret passage room center
            	boolean isWalkway = grid[i][j].isWalkway();
            	boolean isDoor = grid[i][j].isDoorway();
            	boolean isPassage = grid[i][j].isPassage();
            	
            	// Door ways and Room Centers 
            	if (isDoor) {
            		DoorDirection direction = grid[i][j].getDoorDirection();
            		if (direction == DoorDirection.UP) {
            			String room = grid[i - 1][j].getInitial() + "*";
            			if (this.centers.containsKey(room)) {
            				grid[i][j].addAdj(this.centers.get(room));
            				this.centers.get(room).addAdj(grid[i][j]);
            			}
            		} else if (direction == DoorDirection.DOWN ) {
            			String room = grid[i + 1][j].getInitial() + "*";
            			if (this.centers.containsKey(room)) {
            				grid[i][j].addAdj(this.centers.get(room));
            				this.centers.get(room).addAdj(grid[i][j]);
            			}
            		} else if (direction == DoorDirection.LEFT) {
            			String room = grid[i][j - 1].getInitial() + "*";
            			if (this.centers.containsKey(room)) {
            				grid[i][j].addAdj(this.centers.get(room));
            				this.centers.get(room).addAdj(grid[i][j]);
            			} 	
            		} else if (direction == DoorDirection.RIGHT) {
            			String room = grid[i][j + 1].getInitial() + "*";
            			if (this.centers.containsKey(room) ) {
            				grid[i][j].addAdj(this.centers.get(room));
            				this.centers.get(room).addAdj(grid[i][j]);
            			}
            		}
            	}
            	
            	// Secret Passages
            	if (isPassage) {
            		String roomOne = grid[i][j].getInitial() + "*";
            		String roomTwo = grid[i][j].getSecretPassage() + "*";
            		this.centers.get(roomOne).addAdj(this.centers.get(roomTwo));
            		this.centers.get(roomTwo).addAdj(this.centers.get(roomOne));
            	}        	
            	
            	// Walkways
            	if (isWalkway) {
	                if (isValidCell(i + 1, j) && grid[i+ 1][j].isWalkway()) {	                	
	                	grid[i][j].addAdj(grid[i + 1][j]);
	                }
	                if (isValidCell(i - 1, j) && grid[i - 1][j].isWalkway()) {	                
	                	grid[i][j].addAdj(grid[i - 1][j]);	                	
	                }
	                if (isValidCell(i, j + 1) && grid[i][j + 1].isWalkway()) {	                
	                	grid[i][j].addAdj(grid[i][j + 1]);	                               
	                }
	                if (isValidCell(i, j - 1) && grid[i][j - 1].isWalkway()) {	                	
	                	grid[i][j].addAdj(grid[i][j - 1]);	                	                
	                }
            	}
            }
        }
    }  

    
    // Target location, recursive call 
    public void calcTargets(BoardCell startCell, int pathLength) {
    	boolean[] visited = new boolean[numRows * numColumns];
    	this.targets = new HashSet<BoardCell>();
    	int index = calcIndex(startCell);
    	visited[index] = true;
    	findAllTargets(index, pathLength, visited);
    }
    
    // Recursively find targets
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
    		
    		// End of path to calculated target
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
    
    // Overloaded
    private int calcIndex(BoardCell cell) {
    	return calcIndex(cell.getRow(), cell.getCol());
    }
    
    public Set<BoardCell> getAdjList(int row, int col) {
    	return this.grid[row][col].getAdjList();
    }
    
    // Overloaded getCell
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
	
	public String getWeapon(char label) {
		return this.weapons.get(label);
	}
	
	public Player getPlayer(char label) {
		return this.players.get(label);
	}
	
}
