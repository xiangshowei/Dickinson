package lab2.minesweeper;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * A MineSweeperBoard holds a representation of the contents of the playing
 * field for a Mine Sweeper game. The playing field is represented using a 2
 * dimensional array of integer values. The integer value stored in each cell of
 * the array indicates the icon which will appear in the corresponding cell of
 * the graphical user interface for the game.
 * 
 * @author Grant Braught
 * @author Tim Wahls
 * @author Dickinson College
 * @version Aug 13, 2009
 *
 * @author Xiang Wei
 * @version 09/08/2015
 */
public class MineSweeperBoard {

	/*
	 * A covered cell is any cell that does not contains a mine and 
	 * has not been flagged and has not yet been uncovered
	 */
	protected static final int COVERED_CELL = -1;

	/*
	 * A covered cell that that contains a mine
	 */
	protected static final int COVERED_MINE = -2;

	/*
	 * An uncovered cell containing a mine
	 */
	protected static final int UNCOVERED_MINE = -3;
	
	/*
	 * A non-mine cell with a flag placed on it
	 */
	protected static final int FLAGGED_CELL = -4;

	/*
	 * A mine cell with a flag placed on it.
	 */
	protected static final int FLAGGED_MINE = -5;

	/*
	 * Represents a cell that is not a valid position on the board
	 */
	protected static final int INVALID_CELL = -10;

	/* Difficulty levels */
	public static final int DEFAULT_LEVEL = 0;
	public static final int BEGINNER_LEVEL = 1;
	public static final int INTERMEDIATE_LEVEL = 2;
	public static final int EXPERT_LEVEL = 3;
	
	/* Board sizes for each difficulty level */
	protected static final int DEFAULT_BOARD_ROWS = 3;
	protected static final int DEFAULT_BOARD_COLUMNS = 4;

	protected static final int BEGINNER_BOARD_ROWS = 5;
	protected static final int BEGINNER_BOARD_COLUMNS = 10;

	protected static final int INTERMEDIATE_BOARD_ROWS = 10;
	protected static final int INTERMEDIATE_BOARD_COLUMNS = 15;

	protected static final int EXPERT_BOARD_ROWS = 15;
	protected static final int EXPERT_BOARD_COLUMNS = 20;
	
	/* Mine count for each difficulty level */
	protected static final int DEFAULT_BOARD_NUM_MINES = 2;
	protected static final int BEGINNER_BOARD_NUM_MINES = 3;
	protected static final int INTERMEDIATE_BOARD_NUM_MINES = 15;
	protected static final int EXPERT_BOARD_NUM_MINES = 45;
	
	private int[][] msb;
	private int difficultyLevel;
	private int numMines;
	private HashMap<Integer, HashSet<Point>> boardState;

	/**
	 * Construct a new fixed MineSweeperBoard for testing purposes. The board should
	 * have 3 rows and 4 columns. Each cell is a COVERED_CELL, with the exception of 
	 * cells (0, 0) and (2, 1) where they are a COVERED_MINE.
	 */
	public MineSweeperBoard() {
		int[][] defaultBoard = createBoard(DEFAULT_LEVEL);
		coverBoard(defaultBoard);
		placeMines(defaultBoard, DEFAULT_BOARD_NUM_MINES);
	}

	/**
	 * Construct a new MineSweeperBoard for play at the specified level. The size of
	 * the board and the number of mines are determined by the level of play. Valid
	 * levels of play, and their corresponding board size and number of mines are as follows:
	 * 
	 * <pre>
	 * Level              	Board Size   	Mines       
	 * BEGINNER_LEVEL      	5x10            3
	 * INTERMEDIATE_LEVEL  	10x15           15
	 * EXPERT_LEVEL        	15x20           45
	 * </pre>
	 * 
	 * @param level the level of play.
	 */
	public MineSweeperBoard(int level) {
		if (level < BEGINNER_LEVEL || level > EXPERT_LEVEL) {
			level = BEGINNER_LEVEL;
		}

		if (level == BEGINNER_LEVEL) {
			int[][] beginnerBoard = createBoard(BEGINNER_LEVEL);
			coverBoard(beginnerBoard);
			placeMines(beginnerBoard, BEGINNER_BOARD_NUM_MINES);
		}

		else if (level == INTERMEDIATE_LEVEL) {
			int[][] intermedidateBoard = createBoard(INTERMEDIATE_LEVEL);
			coverBoard(intermedidateBoard);
			placeMines(intermedidateBoard, INTERMEDIATE_BOARD_NUM_MINES);
		}

		else if (level == EXPERT_LEVEL) {
			int[][] expertBoard = createBoard(EXPERT_LEVEL);
			coverBoard(expertBoard);
			placeMines(expertBoard, EXPERT_BOARD_NUM_MINES);
		}
	}

	private int[][] createBoard(int level) {
		if (level == DEFAULT_LEVEL) {
			msb = new int[DEFAULT_BOARD_ROWS][DEFAULT_BOARD_COLUMNS];
			numMines = DEFAULT_BOARD_NUM_MINES;
		}
		
		else if (level == BEGINNER_LEVEL) {
			msb = new int[BEGINNER_BOARD_ROWS][BEGINNER_BOARD_COLUMNS];
			numMines = BEGINNER_BOARD_NUM_MINES;
		}
		
		else if (level == INTERMEDIATE_LEVEL) {
			msb = new int[INTERMEDIATE_BOARD_ROWS][INTERMEDIATE_BOARD_COLUMNS];
			numMines = INTERMEDIATE_BOARD_NUM_MINES;
		}
		
		else if (level == EXPERT_LEVEL) {
			msb = new int[EXPERT_BOARD_ROWS][EXPERT_BOARD_COLUMNS];
			numMines = EXPERT_BOARD_NUM_MINES;
		}
		
		difficultyLevel = level;
		
		boardState = new HashMap<Integer, HashSet<Point>>();
		boardState.put(COVERED_CELL, new HashSet<Point>());
		boardState.put(COVERED_MINE, new HashSet<Point>());
		boardState.put(UNCOVERED_MINE, new HashSet<Point>());
		boardState.put(FLAGGED_CELL, new HashSet<Point>());
		boardState.put(FLAGGED_MINE, new HashSet<Point>());

		return msb;
	}

	private void coverBoard(int[][] board) {
		HashSet<Point> coveredCellLocations = boardState.get(COVERED_CELL);

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = COVERED_CELL;
				coveredCellLocations.add(new Point(row, col));
			}
		}
	}

	private void placeMines(int[][] board, int numMinesToPlace) {
		HashSet<Point> mineLocations = boardState.get(COVERED_MINE);

		if (difficultyLevel == DEFAULT_LEVEL) {
			board[0][0] = COVERED_MINE;
			board[2][1] = COVERED_MINE;
			mineLocations.add(new Point(0, 0));
			mineLocations.add(new Point(2, 1));	
		}

		else {
			Random rnd = new Random();
			int numMinesPlaced = 0;
			int mineXCoordinate = 0;
			int mineYCoordinate = 0;

			while (numMinesPlaced < numMinesToPlace) {
				if (difficultyLevel == BEGINNER_LEVEL) {
					mineXCoordinate = rnd.nextInt(BEGINNER_BOARD_ROWS);
					mineYCoordinate = rnd.nextInt(BEGINNER_BOARD_COLUMNS);
				}

				else if (difficultyLevel == INTERMEDIATE_LEVEL) {
					mineXCoordinate = rnd.nextInt(INTERMEDIATE_BOARD_ROWS);
					mineYCoordinate = rnd.nextInt(INTERMEDIATE_BOARD_COLUMNS);
				}

				else if (difficultyLevel == EXPERT_LEVEL) {
					mineXCoordinate = rnd.nextInt(EXPERT_BOARD_ROWS);
					mineYCoordinate = rnd.nextInt(EXPERT_BOARD_COLUMNS);
				}

				// place mine if one didn't exist
				if (board[mineXCoordinate][mineYCoordinate] == COVERED_CELL) {
					board[mineXCoordinate][mineYCoordinate] = COVERED_MINE;
					
					//store mine coordinates
					Point mine = new Point(mineXCoordinate, mineYCoordinate);
					mineLocations.add(mine);

					numMinesPlaced++;
				}
			}
		}
	}

	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	/**
	 * Get the number of rows in this MineSweeperBoard.
	 * 
	 * @return the number of rows in this MineSweeperBoard.
	 */
	public int getNumRows() {
		return msb.length;
	}

	/**
	 * Get the number of columns in this MineSweeperBoard.
	 * 
	 * @return the number of columns in this MineSweeperBoard.
	 */
	public int getNumColumns() {
		return msb[0].length;
	}

	/**
	 * Get the number of mines in this MineSweeperBoard.
	 * 
	 * @return the number of mines in this MineSweeperBoard.
	 */
	public int getNumMines() {
		return numMines;
	}

	/**
	 * Get the current contents of the specified cell on this MineSweeperBoard.
	 * 
	 * @param row the row containing the cell.
	 * @param col the column containing the cell.
	 * @return the value contained in the cell specified by row and col, or
	 *         INVALID_CELL if the specified cell is not on the board.
	 */
	public int getCell(int row, int col) {
		// checking indices that exceed the board's dimensions
		if (row < 0 || col < 0 || row > getNumRows() - 1 || col > (getNumColumns() - 1)) {
			return INVALID_CELL;
		}

		return msb[row][col];
	}

	public HashSet<Point> getLocations(int cellType) {
		switch (cellType) {
			case COVERED_CELL:
				return boardState.get(COVERED_CELL);
			case COVERED_MINE:
				return boardState.get(COVERED_MINE);
			case UNCOVERED_MINE:
				return boardState.get(UNCOVERED_MINE);
			case FLAGGED_CELL:
				return boardState.get(FLAGGED_CELL);
			case FLAGGED_MINE:
				return boardState.get(FLAGGED_MINE);
			default:
				return null;
		}
	}

	/**
	 * Count the number of mines that appear in cells that are adjacent to the
	 * specified cell. This method returns the number of adjacent mines but does not
	 * change the contents of the board.
	 * 
	 * @param row the row of the cell.
	 * @param col the column of the cell.
	 * @return the number of mines adjacent to the specified cell. If the specified
	 *         cell is not on the board, return 0.
	 */
	public int getNumAdjacentMines(int row, int col) {
		int numAdjacentMines = 0;

		// checking the surrounding locations centered at cell (row, col)
		for (int adjacentRow = -1; adjacentRow <= 1; adjacentRow++) {
			for (int adjacentCol = -1; adjacentCol <= 1; adjacentCol++) {

				int examinedXCoordinate = row + adjacentRow;
				int examinedYCoordinate = col + adjacentCol;
				int examinedCell = getCell(examinedXCoordinate, examinedYCoordinate);

				// only examine valid cells
				if (examinedCell != INVALID_CELL) {
					if (examinedCell == COVERED_MINE || examinedCell == FLAGGED_MINE || examinedCell == UNCOVERED_MINE) {
						/*
						 * Only consider adjacent mines such that mines @ do not contribute to the count;
						 * using OR instead of AND since both X & Y coordinates need to match the mine @ (row, col) 
						 * so it's not included in mine count
						 */
						if (examinedXCoordinate != row || examinedYCoordinate != col) {
							numAdjacentMines++;
						}
					}
				}
			}
		}

		return numAdjacentMines;
	}

	/**
	 * Uncover the specified cell. If the cell already contains a flag it should not
	 * be uncovered. If there is not a mine under the specified cell then the value
	 * in that cell is changed to the number of mines that appear in adjacent cells.
	 * If there is a mine under the specified cell then the cell is changed to the
	 * value UNCOVERED_MINE. If the specified cell is already uncovered or is not on
	 * the board, no change is made to the board.
	 * 
	 * @param row the row of the cell to be uncovered.
	 * @param col the column of the cell to be uncovered.
	 */
	public void uncoverCell(int row, int col) {
		int cell = getCell(row, col);
		HashSet<Point> uncoveredMineLocations = boardState.get(UNCOVERED_MINE);

		// only check cells that have not been uncovered
		if (cell >= 0 || cell != UNCOVERED_MINE) {
			if (cell != INVALID_CELL || cell != FLAGGED_CELL || cell != FLAGGED_MINE) {
				if (cell == COVERED_MINE) {
					msb[row][col] = UNCOVERED_MINE;
					uncoveredMineLocations.add(new Point(row, col));
				}

				// mark the cell with number of adjacent mines
				else {
					msb[row][col] = getNumAdjacentMines(row, col);
				}
			}
		}
	}

	/**
	 * Place or remove a flag from the specified cell. If the cell is currently
	 * covered then place a flag on the cell. If the cell currently contains a flag,
	 * remove that flag but do not uncover the cell. If the cell has already been
	 * uncovered or is not on the board, no change is made to the board.
	 * 
	 * @param row the row of the cell to be flagged/unflagged.
	 * @param col the column of the cell to be flagged/unflagged.
	 */
	public void flagCell(int row, int col) {
		int cell = getCell(row, col);

		// only check cells that have not been uncovered
		if (cell >= 0 || cell != UNCOVERED_MINE) {
			if (cell != INVALID_CELL) {
				HashSet<Point> flaggedCellLocations = boardState.get(FLAGGED_CELL);
				HashSet<Point> flaggedMineLocations = boardState.get(FLAGGED_MINE);
				Point point = new Point(row, col);

				// flag non-mine covered cell
				if (cell == COVERED_CELL) {
					msb[row][col] = FLAGGED_CELL;
					flaggedCellLocations.add(point);
				}

				// unflag a non-mine covered cell
				else if (cell == FLAGGED_CELL) {
					msb[row][col] = COVERED_CELL;
					flaggedCellLocations.remove(point);
				}

				// flag a mine
				else if (cell == COVERED_MINE) {
					msb[row][col] = FLAGGED_MINE;
					flaggedMineLocations.add(point);
				}

				// unflag a mine
				else if (cell == FLAGGED_MINE) {
					msb[row][col] = COVERED_MINE;
					flaggedMineLocations.remove(point);
				}
			}
		}
	}

	/**
	 * Uncover all of the cells on the board.
	 */
	public void revealBoard() {
		for (int row = 0; row < msb.length; row++) {
			for (int col = 0; col < msb[row].length; col++) {
				uncoverCell(row, col);
			}
		}
	}

	/**
	 * Determine if the player has uncovered a mine. The game is lost if the player
	 * has uncovered a mine.
	 * 
	 * @return true if the current game has been lost and false otherwise.
	 */
	public boolean hasUncoveredMine() {
		return boardState.get(UNCOVERED_MINE).size() > 0;
	}

	/**
	 * Determine if the player has won the current game. The game is won when three
	 * conditions are met:
	 * <OL>
	 * <LI>Flags have been placed on all of the mines.
	 * <LI>No flags have been placed incorrectly.
	 * <LI>All non-flagged cells that are not mines have been uncovered.
	 * </OL>
	 * 
	 * @return true if the current game has been won and false otherwise.
	 */
	public boolean hasWonGame() {
		return areAllMinesFlagged(boardState.get(UNCOVERED_MINE)) && areAllNonMineCellsUncovered();
	}

	// TODO: finish implementation
	private boolean areFlagsPlacedCorrectly() {
		//each time a cell is flagged, record in collection(hashmap)

		//for each flagged cell
			//check if the flagged cell's coordinate matches that of the mine location
		return false;
	}
	
	private boolean areAllNonMineCellsUncovered() {
		for (int row = 0; row < msb.length; row++) {
			for (int col = 0; col < msb[row].length; col++) {
				int cell = getCell(row, col);
	
				if (cell == COVERED_CELL || cell == FLAGGED_CELL) {
					return false;
				}
			}
		}

		return true;
	} 

	private int getNumMinesFlagged(HashSet<Point> mineLocations) {
		int numMinesFlagged = 0;
		
		for (Point mine : mineLocations) {
				
			int mineXCoordinate = (int) mine.getX();
			int mineYCoordinate = (int) mine.getY();
			
			if(getCell(mineXCoordinate, mineYCoordinate) == FLAGGED_MINE) {
				numMinesFlagged++;
			}
		}

		return numMinesFlagged;
	}

	private boolean areAllMinesFlagged(HashSet<Point> mineLocations) {
		int numMinesFlagged = getNumMinesFlagged(mineLocations);
		boolean allMinesFlagged = false;
		
		if(difficultyLevel == DEFAULT_LEVEL) {
			if(numMinesFlagged == DEFAULT_BOARD_NUM_MINES) {
				allMinesFlagged = true;
			}
		}

		else if(difficultyLevel == BEGINNER_LEVEL) {
			if(numMinesFlagged == BEGINNER_BOARD_NUM_MINES) {
				allMinesFlagged = true;
			}
		}

		else if(difficultyLevel == INTERMEDIATE_LEVEL) {
			if(numMinesFlagged == INTERMEDIATE_BOARD_NUM_MINES) {
				allMinesFlagged = true;
			}
		}

		else if(difficultyLevel == EXPERT_LEVEL) {
			if(numMinesFlagged == EXPERT_BOARD_NUM_MINES) {
				allMinesFlagged = true;
			}
		}

		return allMinesFlagged;
	}

}