package clueGame;

import java.lang.reflect.Array;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.*;

public class Board extends JPanel {
    // Sizing
    private int numRows;
    private int numCols;
    private int cellSize;
    private int xOffset;
    private int yOffset;

    // Config files
    private String layoutConfigFile;
    private String setupConfigFile;

    // Data structures
    private BoardCell[][] grid;
    private Map<Character, String> boardObjects;
    private Map<Character, Room> roomMap;    
    private ArrayList<Player> players;
    private ArrayList<String> weapons;
    private Set<BoardCell> targets;
    private ArrayList<Card> cards;
    private GameControlPanel gcp;
   
    // Current turn
    public static int turnNumber;   
    private Player currentPlayer;
    
    private HumanPlayer humanPlayer;
    
    // Answer to the game
    private static Solution theSolution;
    
    // Singletop Pattern, once instance of the board
    private static Board theInstance = new Board();

    public static Board getInstance() {    	
        return theInstance;    
    }

    /*
     * INITIALIZATION
     */  
    
    public Board() {    
    	setSize(new Dimension(800, 800));
    	setBackground(Color.BLACK);
    	addMouseListener(new gameListener());
    	turnNumber = 0;
    }
    
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
        calcAdjacencies();
        deal();
    }
    
    public void setGCP(GameControlPanel gcp) {
    	this.gcp = gcp;
    }
    
    public void setConfigFiles(String csv, String txt) {
        layoutConfigFile = csv;
        setupConfigFile = txt;
    }

    public void loadSetupConfig() throws BadConfigFormatException {
        roomMap = new HashMap<Character, Room>();
        players = new ArrayList<Player>();
        weapons = new ArrayList<String>();
        cards = new ArrayList<Card>();
        boardObjects = new HashMap<Character, String>();        

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
                cards.add(new Card(room.getName(), CardType.ROOM, Color.WHITE));
            } else if (type.equals("Weapon")) {
            	weapons.add(name);
                cards.add(new Card(name, CardType.WEAPON, Color.WHITE));
            } else if (type.equals("Player")) {              
                int r = Integer.parseInt(line[3]);
                int g = Integer.parseInt(line[4]);
                int b = Integer.parseInt(line[5]);                
                Player player = null;
                if (initial == '0') {
                    player = new HumanPlayer(name);
                    humanPlayer = (HumanPlayer) player;
                } else {
                    player = new ComputerPlayer(name);
                }
                players.add(player);
                player.updateColor(r, g, b);
                cards.add(new Card(name, CardType.PERSON, new Color(r, g, b)));
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
                    cell.setInitial(cellInitial);
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
                        	if (Character.isDigit(indicator)) {
                        		players.get((int)(indicator - 48)).setStartPosition(cell);
                        	} else {
                        	cell.hasMarker(indicator);                      
                        	}
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
        int k = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                grid[i][j] = temp.get(k);                
                k++;
            }
        }
        for (int i = 0; i < numRows; i++) {
        	for (int j = 0; j < numCols; j++) {
        		if (grid[i][j].isDoorway()) {
            		int row = grid[i][j].getRoomFromDoorway().y;
            		int col = grid[i][j].getRoomFromDoorway().x;
            		grid[i][j].setRoom(grid[i + row][j + col].getRoom());            		            		
            	}
        	}
        }
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

    public void deal() {
        ArrayList<Card> tempDeck = new ArrayList<Card>(cards);
        int i = 1;
        Random rand = new Random();
        while (!tempDeck.isEmpty()) {
            if (i == 6) {
                i = 0;
            }             
            int index = rand.nextInt(tempDeck.size());
            players.get(i).updateHand(cards.get(index));
            tempDeck.remove(index);
            i++;                
        }
    }
    
    
    public boolean checkAccusation(Solution accusation) {
    	return theSolution.equals(accusation);
    }
    
    public int diceRoll() {
    	Random rand = new Random();    	
    	return rand.nextInt(6) + 1;
    }
    
    // Keeps track of how humanPlayer making a move or not   
    public boolean play(Player currentPlayer, int roll) {
    	this.currentPlayer = currentPlayer;    	
    	calcTargets(currentPlayer.getLocation(), roll);
    	if (!currentPlayer.equals(humanPlayer)) {
    		currentPlayer.moveLocation();
    		targets.clear();
    		repaint();
			turnNumber++;
			return true;
    	} else if (currentPlayer.equals(humanPlayer)) {
    		humanPlayer.move();
    		repaint();
    		turnNumber++;
    		return humanPlayer.madeMove();
   		}
    	return false;
   	}

/*
 * GRAPHICS
 */
    
    public final void paintComponent(Graphics g) {
    	super.paintComponent(g);    	    
        Graphics2D g2 = (Graphics2D) g;
        
        removeAll();
        int width = getWidth();
        int height= getHeight();
        
        //Paint the background Color
        setBackground(Color.BLACK);
        
        // Get cell sizing based on board width and height
        int cellWidth = width / (numCols + 1);
        int cellHeight = height / (numRows + 1);
         
        // Equal cell width and height to maintain square cells
        cellSize = Math.min(cellWidth, cellHeight);
        
        // Offsets to keep board in board JPanel, regardless of screen resizing
        xOffset = (width - cellSize * numCols) / 2;
        yOffset = (height - cellSize * numRows) / 2;
        
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {              
            	grid[i][j].draw(g2, xOffset, yOffset, cellSize);            	
            	if (grid[i][j].isLabel()) {      
            		String roomName = grid[i][j].getRoom().getName(); 
            		JLabel label = new JLabel(roomName);
            		Dimension size = label.getPreferredSize();
            		label.setBounds(
            				j * cellSize + xOffset, i * cellSize + yOffset, size.width, size.height);
            		add(label);
            	}
            }
        }       
        drawPlayers(g2);
    }   
    
    // Draws player
    // Players are drawn after cells so that rooms with multiple players aren't covered by cells   
    private void drawPlayers(Graphics2D g) {
    	ArrayList<BoardCell> playerLocations = new ArrayList<BoardCell>();    	
    	for (Player player: players) {
    		playerLocations.add(player.getLocation());    		
    	}
    	 
    	for (BoardCell cell: playerLocations) {
    		if (cell.isOccupied() || cell.isStart()) {
    			int y = cell.getRow() * cellSize + yOffset;
				int x = cell.getCol() * cellSize + xOffset;
    			if (cell.isCenter() && !cell.getRoomPlayers().isEmpty()) {
    				for (int i = 0; i < cell.getRoomPlayers().size(); i++) {    					    					
    					g.setColor(cell.getRoomPlayers().get(i).getColor());
    					g.fillOval(x + (10 * i), y, cellSize, cellSize);
    					g.setColor(Color.BLACK);
    					g.drawOval(x + (10 * i), y, cellSize, cellSize);
    				}
    			} else if (!cell.isCenter()) {
    				g.setColor(cell.getPlayer().getColor());
    				g.fillOval(x, y, cellSize, cellSize);
    				g.setColor(Color.BLACK);
    				g.drawOval(x, y, cellSize, cellSize);
    			}
    		}
    	}	
    }
    
    // Shows targets to humanPlayer as red, including rooms
    public void showTargets() {
    	if (targets.isEmpty()) {
    		return;
    	}
    	ArrayList<BoardCell> tempTargets = new ArrayList<BoardCell>(targets);
    	for (BoardCell cell: tempTargets) {
    		if (cell.isRoom()) {
    			for (BoardCell roomCell: cell.getRoom().getCells()) {
    				roomCell.setTarget(true);
    				targets.add(roomCell);
    			}
    		} else {
    			cell.setTarget(true);
    		}
    	}
    	this.repaint();
    }
    
    // Gets the cell associated with the location on JPanel that is clicked
    public BoardCell getClickedCell(int x , int y) {
    	int row = (y - yOffset) / cellSize;
    	int col = (x - xOffset) / cellSize;
    	BoardCell clickedCell = grid[row][col];
    	if (clickedCell.isRoom()) {
    		clickedCell = clickedCell.getRoom().getCenterCell();
    	}
    	if (targets.contains(clickedCell)) {
    		return clickedCell;
    	} 
    	return null;    	
    }
    
    private class gameListener implements MouseListener {
    	public void mousePressed(MouseEvent event) {}
    	public void mouseReleased(MouseEvent event) {
            if (!humanPlayer.hasMoved()) {
                BoardCell clickedCell = getClickedCell(event.getX(), event.getY());
                if  (clickedCell == null) {
                    JOptionPane.showMessageDialog(null, "Invalid selection");
                } else {
                    humanPlayer.moveLocation(clickedCell);
                    repaint();
                }
            }
        }
    	public void mouseEntered(MouseEvent event) {}
    	public void mouseExited(MouseEvent event) {}
    	public void mouseClicked(MouseEvent event) {}
    }
    
    
    /*
     * GETTERS
     */ 

        public Player getPlayer(int index) {
            return players.get(index);
        }
        
        public Player nextPlayer() {
        	return players.get(turnNumber % 6); 
        }
        
       public BoardCell getCell(int row, int col) {
    	   return grid[row][col];
       }
       
       public Room getRoom(BoardCell cell) {
    	   return cell.getRoom();
       }
       
       public Room getRoom(Character initial) {
    	   return roomMap.get(initial);
       }
       
       public int getNumRows() {
    	   return numRows;
       }
       
       public int getNumColumns() {
    	   return numCols;
       }  

       public String getWeapon(int index) {
    	   return weapons.get(index);
       }
       
       public ArrayList<Card> getDeck() {
    	   return cards;
       }
       
        public Set<BoardCell> getTargets() {
        	return this.targets;
        }

        public ArrayList<BoardCell> getAdjList(int row, int col) {
        	return grid[row][col].getAdjList();
        }
        
        public Solution getTheAnswer() {
        	return theSolution;
        }
        
        public ArrayList<Player> getPlayers() {
        	return players;
        }
}
