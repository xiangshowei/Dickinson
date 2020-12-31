package lab2.minesweeper;

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

	private int[][] msb;
	private int difficultyLevel;
	private int numMines;
	private String[] mineLocations;

	/**
	 * A constant value representing a covered cell. A covered cell is any cell
	 * which does not contains a mine, has not been flagged and has not yet been
	 * uncovered.
	 */
	protected static final int COVERED_CELL = -1;

	/**
	 * A constant value representing a cell that has not been uncovered yet but
	 * contains a mine.
	 */
	protected static final int MINE = -2;

	/**
	 * A constant value representing a cell which does not contain a mine but has
	 * had a flag placed on it.
	 */
	protected static final int FLAGGED_CELL = -3;

	/**
	 * A constant value representing a cell which contains a mine and has had a flag
	 * placed on it.
	 */
	protected static final int FLAGGED_MINE = -4;

	/**
	 * A constant value representing a cell containing a mine that has been
	 * uncovered.
	 */
	protected static final int UNCOVERED_MINE = -5;

	/**
	 * A constant value representing the contents of an invalid cell. This value is
	 * returned by the getCell method when an invalid cell is specified.
	 */
	protected static final int INVALID_CELL = -10;

	public static final int DEFAULT_LEVEL = 0;

	/**
	 * A constant value representing the easiest level of play.
	 */
	public static final int BEGINNER_LEVEL = 1;

	/**
	 * A constant value representing an intermediate level of play.
	 */
	public static final int INTERMEDIATE_LEVEL = 2;

	/**
	 * A constant value representing the hardest level of play.
	 */
	public static final int EXPERT_LEVEL = 3;

	protected static final int DEFAULT_BOARD_ROWS = 3;
	protected static final int DEFAULT_BOARD_COLUMNS = 4;

	protected static final int BEGINNER_BOARD_ROWS = 5;
	protected static final int BEGINNER_BOARD_COLUMNS = 10;

	protected static final int INTERMEDIATE_BOARD_ROWS = 10;
	protected static final int INTERMEDIATE_BOARD_COLUMNS = 15;

	protected static final int EXPERT_BOARD_ROWS = 15;
	protected static final int EXPERT_BOARD_COLUMNS = 20;

	protected static final int DEFAULT_BOARD_NUM_MINES = 2;
	protected static final int BEGINNER_BOARD_NUM_MINES = 3;
	protected static final int INTERMEDIATE_BOARD_NUM_MINES = 15;
	protected static final int EXPERT_BOARD_NUM_MINES = 45;

	/**
	 * Construct a new fixed MineSweeperBoard for testing purposes. The board should
	 * have 3 rows and 4 columns. All cells should contain a COVERED_CELL, except
	 * that cells (0, 0) and (2, 1) should contain a MINE.
	 */
	public MineSweeperBoard() {
		int[][] defaultBoard = createBoard(DEFAULT_LEVEL);
		coverBoard(defaultBoard);
		placeMines(defaultBoard, DEFAULT_LEVEL, DEFAULT_BOARD_NUM_MINES);
	}

	/**
	 * Construct a new MineSweeperBoard for play at the specified level. The size of
	 * the board and the number of mines are determined by the level of play. Valid
	 * levels of play are indicated by the constants BEGINNER_LEVEL,
	 * INTERMEDIATE_LEVEL and EXPERT_LEVEL.
	 * 
	 * If an invalid level of play is specified, the new MineSweeperBoard should be
	 * created at the BEGINNER_LEVEL. The size of the board and the number of cells
	 * which contain mines is as follows:
	 * 
	 * <pre>
	 * &lt;U&gt;
	 * Level:              Board Size (RxC):   Mines:&lt;/U&gt;        
	 * BEGINNER_LEVEL      5x10                3
	 * INTERMEDIATE_LEVEL  10x15               15
	 * EXPERT_LEVEL        15x20               45
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
			placeMines(beginnerBoard, BEGINNER_LEVEL, BEGINNER_BOARD_NUM_MINES);
		}

		else if (level == INTERMEDIATE_LEVEL) {
			int[][] intermedidateBoard = createBoard(INTERMEDIATE_LEVEL);
			coverBoard(intermedidateBoard);
			placeMines(intermedidateBoard, INTERMEDIATE_LEVEL, INTERMEDIATE_BOARD_NUM_MINES);
		}

		else if (level == EXPERT_LEVEL) {
			int[][] expertBoard = createBoard(EXPERT_LEVEL);
			coverBoard(expertBoard);
			placeMines(expertBoard, EXPERT_LEVEL, EXPERT_BOARD_NUM_MINES);
		}
	}

	private int[][] createBoard(int level) {
		if (level == DEFAULT_LEVEL) {
			msb = new int[DEFAULT_BOARD_ROWS][DEFAULT_BOARD_COLUMNS];
			mineLocations = new String[DEFAULT_BOARD_NUM_MINES];
			numMines = DEFAULT_BOARD_NUM_MINES;
		}

		else if (level == BEGINNER_LEVEL) {
			msb = new int[BEGINNER_BOARD_ROWS][BEGINNER_BOARD_COLUMNS];
			mineLocations = new String[BEGINNER_BOARD_NUM_MINES];
			numMines = BEGINNER_BOARD_NUM_MINES;
		}

		else if (level == INTERMEDIATE_LEVEL) {
			msb = new int[INTERMEDIATE_BOARD_ROWS][INTERMEDIATE_BOARD_COLUMNS];
			mineLocations = new String[INTERMEDIATE_BOARD_NUM_MINES];
			numMines = INTERMEDIATE_BOARD_NUM_MINES;
		}

		else if (level == EXPERT_LEVEL) {
			msb = new int[EXPERT_BOARD_ROWS][EXPERT_BOARD_COLUMNS];
			mineLocations = new String[EXPERT_BOARD_NUM_MINES];
			numMines = EXPERT_BOARD_NUM_MINES;
		}

		difficultyLevel = level;

		return msb;
	}

	private void coverBoard(int[][] board) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = COVERED_CELL;
			}
		}
	}

	private void placeMines(int[][] board, int level, int numMinesToPlace) {
		if (level == DEFAULT_LEVEL) {
			board[0][0] = MINE;
			board[2][1] = MINE;
			mineLocations[0] = "0,0";
			mineLocations[1] = "2,1";
		}

		else {
			Random rnd = new Random();
			int numMinesPlaced = 0;
			int mineXCoordinate = 0;
			int mineYCoordinate = 0;

			while (numMinesPlaced < numMinesToPlace) {
				if (level == BEGINNER_LEVEL) {
					mineXCoordinate = rnd.nextInt(BEGINNER_BOARD_ROWS);
					mineYCoordinate = rnd.nextInt(BEGINNER_BOARD_COLUMNS);
				}

				else if (level == INTERMEDIATE_LEVEL) {
					mineXCoordinate = rnd.nextInt(INTERMEDIATE_BOARD_ROWS);
					mineYCoordinate = rnd.nextInt(INTERMEDIATE_BOARD_COLUMNS);
				}

				else if (level == EXPERT_LEVEL) {
					mineXCoordinate = rnd.nextInt(EXPERT_BOARD_ROWS);
					mineYCoordinate = rnd.nextInt(EXPERT_BOARD_COLUMNS);
				}

				mineLocations[numMinesPlaced] = new String(
						String.valueOf(mineXCoordinate) + "," + String.valueOf(mineYCoordinate));

				// place mine if one didn't exist
				if (board[mineXCoordinate][mineYCoordinate] == COVERED_CELL) {
					board[mineXCoordinate][mineYCoordinate] = MINE;
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
		if (row < 0 || col < 0 || row > (getNumRows() - 1) || col > (getNumColumns() - 1)) {
			return INVALID_CELL;
		}

		return msb[row][col];
	}

	public String[] getMineLocations() {
		return mineLocations;
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
					if (examinedCell == MINE || examinedCell == FLAGGED_MINE || examinedCell == UNCOVERED_MINE) {
						// don't increment mine count if the cell being examined is a mine
						// only consider adjacent mines
						// mine @ (row, col) does not contribute to the count
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

		// only check cells that have not been uncovered
		if (cell >= 0 || cell != UNCOVERED_MINE) {
			if (cell != INVALID_CELL || cell != FLAGGED_CELL || cell != FLAGGED_MINE) {
				if (cell == MINE) {

					msb[row][col] = UNCOVERED_MINE;
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
				// flag non-mine covered cell
				if (cell == COVERED_CELL) {
					msb[row][col] = FLAGGED_CELL;
				}

				// flag a mine
				else if (cell == MINE) {
					msb[row][col] = FLAGGED_MINE;
				}

				// if the cell is already flagged, return it to a covered cell
				else if (cell == FLAGGED_CELL) {
					msb[row][col] = COVERED_CELL;
				}

				// return the flagged mine to a mine
				else if (cell == FLAGGED_MINE) {
					msb[row][col] = MINE;
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
		boolean uncoveredMine = false;

		for (int row = 0; row < msb.length; row++) {
			for (int col = 0; col < msb[row].length; col++) {
				if (msb[row][col] == UNCOVERED_MINE) {
					return true;
				}
			}
		}

		return uncoveredMine;
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
	public boolean gameWon() {
		boolean gameWon = false;

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

	private boolean allMinesFlagged() {
		return false;
	}
}