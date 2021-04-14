import java.util.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

public class Board extends JPanel {
    // Sizing
    private int numRows;
    private int numCols;

    //Config files
    private String layoutConfigFile;
    private String setupConfigFile;

    // Data structures
    private BoardCell[][] grid;
    private Map<Character, String> boardObjects;
    private Map<Character, Room> roomMap;
    private Map<Integer, Point> startPositions;
    private ArrayList<Player> players;
    private Set<BoardCell> targets;
    private ArrayList<Card> cards;

    // Singletop Pattern, once instance of the board
    private static Board theInstance = new Board();

    public static Board getInstance() {
        return theInstance;    
    }

    /*
     * INITIALIZATION
     */  
    
    public void initialize() {
        try {
            loadSetupConfig();
        } catch(BadConfigFormatException e) {
            System.out.println("Error occurred during setup file loading.");
        }  
        try {
            loadLayoutConfig();            
        } catch(BadConfigFormatException e) {
            System.out.println("Error occurred during layout configuration.");
        }
        //setLayout(new GridLayout(numRows, numCols));
        calcAdjacencies();
        deal();
    }
    
    
    public void setConfigFiles(String csv, String txt) {
        layoutConfigFile = csv;
        setupConfigFile = txt;
    }

    public void loadSetupConfig() throws BadConfigFormatException {
        roomMap = new HashMap<Character, Room>();
        players = new ArrayList<Player>();
        cards = new ArrayList<Card>();
        boardObjects = new HashMap<Character, String>();
        setStartPositions();

        // Reading
        FileReader setupReader = null;
        try {
            setupReader = new FileReader(setupConfigFile);
        } catch(FileNotFoundException e) {
            throw new BadConfigFormatException(setupConfigFile);
        }
        Scanner scan = new Scanner(setupReader);

        while (scan.hasNextLine()) {
            String[] line = scan.nextLine().split(", ");
            if (line.length < 2) {
                continue;
            }
            String type = line[0];
            String name = line[1];
            char initial = line[2].charAt(0);
            boardObjects.putIfAbsent(initial, type);

            if (type.equals("Room")) {
                Room room = new Room(name, initial);
                roomMap.putIfAbsent(initial, room);
                cards.add(new Card(room.getName(), CardType.ROOM));
            } else if (type.equals("Weapon")) {
                cards.add(new Card(name, CardType.WEAPON));
            } else if (type.equals("Player")) {
                cards.add(new Card(name, CardType.PERSON));
                String color = line[3];
                Player player = null;
                if (initial == '1') {
                    player = new HumanPlayer(name);
                } else {
                    player = new ComputerPlayer(name);
                }
                players.add(player);
                player.updateColor(color);
                player.setStartPosition(startPositions.get(
                            Character.getNumericValue(initial)));
            }
        }
        scan.close();
    }

    public void loadLayoutConfig() throws BadConfigFormatException {
        ArrayList<BoardCell> tempBoard = new ArrayList<BoardCell>();


        // Reading
        FileReader layoutReader = null;
        try {
            layoutReader = new FileReader(layoutConfigFile);
        } catch (FileNotFoundException e) {
            throw new BadConfigFormatException(layoutConfigFile);
        }
        Scanner scan = new Scanner(layoutReader);
        int row = 0;
        int maxCol = 0;

        while (scan.hasNext()) {
            int col = 0;
            String[] line = scan.next().split(",");
            for (String initial: line) {
                char cellInitial = initial.charAt(0);
                Character indicator = null;
                boolean hasIndicator = false;
                if (initial.length() > 1) {
                    hasIndicator = true;
                    indicator = initial.charAt(1);
                }
                if (boardObjects.containsKey(cellInitial)) {
                    String type = boardObjects.get(cellInitial);
                    BoardCell cell = new BoardCell(row, col);
                    if (type.equals("Room")) {
                        cell.setRoom(roomMap.get(cellInitial));
                        if (hasIndicator) {
                            if (Character.isLetter(indicator)) {
                                cell.setSecretPassage(
                                        roomMap.get(indicator));
                            } else {
                                cell.hasMarker(indicator);
                            }
                        }
                    } else if (type.equals("Space")) {
                        cell.setSpace(cellInitial);
                        if (hasIndicator) {
                            cell.hasMarker(indicator);
                        }
                    }
                    tempBoard.add(cell);
                    col++;
                } else {
                    throw new BadConfigFormatException(cellInitial);
                }
            }
            if (row == 0) {
                maxCol = col;
            }
            row++;
            if (maxCol != col) {
                throw new BadConfigFormatException(maxCol, col);
            }
        }
        scan.close();
        numRows = row;
        numCols = maxCol;

        //Convert the board to a 2D array, now that there are set dimensions
        convertBoard(tempBoard);
    }
    
    // Converts temporary board to 2D array since dimensions known at this point
    private void convertBoard(ArrayList<BoardCell> temp) {
        grid = new BoardCell[numRows][numCols];
        System.out.println(numRows * numCols);
        System.out.println(temp.size());
        int k = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                grid[i][j] = temp.get(k);
                k++;
            }
        }
    }


    public void setStartPositions() {
        startPositions = new HashMap<Integer, Point>();
        startPositions.put(1, new Point(21, 5));
        startPositions.put(2, new Point(16, 5));
        startPositions.put(3, new Point(23, 17));
        startPositions.put(4, new Point(14, 17));
        startPositions.put(5, new Point(10, 19));
        startPositions.put(6, new Point(0, 19));        
    }



    public void calcAdjacencies() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                boolean isWalkway = grid[i][j].isWalkway();
                boolean isDoor = grid[i][j].isDoorway();
                boolean isPassage = grid[i][j].isPassage();

                if (isDoor) {
                    grid[i][j].addAdj(grid[i][j].getRoom().getCenterCell());
                }

                if (isPassage) {
                    grid[j][j].addAdj(grid[i][j].getPassage().getCenterCell());                    
                }

                if (isWalkway) {
                    if (isValidCell(i + 1, j) && grid[i + 1][j].isWalkway()) {
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

    public boolean isValidCell(int row, int col) {
        return (row >= 0 && row < numRows) && (col >=0 && col < numCols);
    }
    
    private int calcIndex(int row, int col) {
        return numCols * row + col;
    }

    private int calcIndex(BoardCell cell) {
        return calcIndex(cell.getRow(), cell.getCol());
    }


    public void calcTargets(BoardCell startCell, int pathLength) {
        boolean[] visited = new boolean[numRows * numCols];
        targets = new HashSet<BoardCell>();
        int index = calcIndex(startCell);
        visited[index] = true;
        findAllTargets(index, pathLength, visited, startCell);
    }

    private void findAllTargets(
            int index, int pathLength,
            boolean[] visited, BoardCell startCell) {
        Set<BoardCell> adjacentCells = new HashSet<BoardCell>();
        for (BoardCell cell: startCell.getAdjList()) {
            if (!visited[calcIndex(cell)]) {
                adjacentCells.add(cell);
            }
        }

        for (BoardCell cell: adjacentCells) {
            visited[calcIndex(cell)] = true;

            // Blocked doorway
            if (cell.isOccupied() && !cell.isCenter()) {
                continue;
            }

            // Room cells always adjacent
            if (cell.isCenter()) {
                targets.add(cell);
                continue;
            }

            // End of path to calculated target
            if (pathLength == 1) {
                targets.add(cell);
            } else {
                findAllTargets(
                        calcIndex(cell), pathLength -1, visited, cell);
            }
            visited[calcIndex(cell)] = false;
        }
    }


    private void deal() {
        ArrayList<Card> tempDeck = new ArrayList<Card>(cards);
        int i = 1;
        while (!tempDeck.isEmpty()) {
            if (i == 6) {
                i = 0;
            }
            int index = tempDeck.size() - 1;
            players.get(i).updateHand(cards.get(index));
            tempDeck.remove(index);
            i++;                
        }
    }




/*
 * GETTERS
 */ 

    public Player getPlayer(int index) {
        return players.get(index - 1);
    }




/*
 * GRAPHICS
 */

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	setBackground(Color.BLACK);
    	int width = (int)(0.8 * getWidth());
    	int height = (int)(0.8 * getHeight());
    	        	
    	int xNumCells = (int) (width / numCols); // or size
    	int yNumCells = (int) (height / numRows);
    	
    	int dX = Math.min(xNumCells, yNumCells);
    	int size = dX - 2;
    	
    	int xStart = (getWidth() - width) / 2;
    	int yStart = (getHeight() - height) / 2;    	
    	
        
        Graphics2D g2 = (Graphics2D) g;
        
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
            	grid[i][j].draw(g2, j * dX + xStart, i * dX + yStart, size);
            
            }
        }       
    }


}
