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

	/**
	 * A constant value representing a covered cell. A covered cell is any cell
	 * which does not contains a mine, has not been flagged and has not yet been
	 * uncovered.
	 */
	protected static final int COVERED_CELL = -1;

	/**
	 * A constant value representing a cell that has not been uncovered yet
	 * but contains a mine.
	 */
	protected static final int MINE = -2;

	/**
	 * A constant value representing a cell which does not contain a mine but
	 * has had a flag placed on it.
	 */
	protected static final int FLAG = -3;

	/**
	 * A constant value representing a cell which contains a mine and has had a
	 * flag placed on it.
	 */
	protected static final int FLAGGED_MINE = -4;

	/**
	 * A constant value representing a cell containing a mine that has been
	 * uncovered.
	 */
	protected static final int UNCOVERED_MINE = -5;

	/**
	 * A constant value representing the contents of an invalid cell. This value
	 * is returned by the getCell method when an invalid cell is specified.
	 */
	protected static final int INVALID_CELL = -10;

	private static final int DEFAULT_LEVEL = 0;

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

	private static final int DEFAULT_BOARD_ROWS = 3;
	private static final int DEFAULT_BOARD_COLUMNS = 4;

	private static final int BEGINNER_BOARD_ROWS = 5;
	private static final int BEGINNER_BOARD_COLUMNS = 10;
	
	private static final int INTERMEDIATE_BOARD_ROWS = 10;
	private static final int INTERMEDIATE_BOARD_COLUMNS = 15;

	private static final int EXPERT_BOARD_ROWS = 15;
	private static final int EXPERT_BOARD_COLUMNS = 20;

	private static final int DEFAULT_BOARD_NUM_MINES = 2;
	private static final int BEGINNER_BOARD_NUM_MINES = 3;
	private static final int INTERMEDIATE_BOARD_NUM_MINES = 15;
	private static final int EXPERT_BOARD_NUM_MINES = 45;

	/**
	 * Construct a new fixed MineSweeperBoard for testing purposes. The board
	 * should have 3 rows and 4 columns. All cells should contain COVERED_CELL,
	 * except 2 locations contain a MINE.
	 */
	public MineSweeperBoard() {
		int[][] defaultBoard = createBoard(DEFAULT_LEVEL);
		coverBoard(defaultBoard);
		placeMines(defaultBoard, DEFAULT_LEVEL);
	}

	/**
	 * Construct a new MineSweeperBoard for play at the specified level. 
	 * The size of the board and the number of mines are determined by the level of play. 
	 * Valid levels of play are indicated by the constants
	 * BEGINNER_LEVEL, INTERMEDIATE_LEVEL and EXPERT_LEVEL. 
	 * 
	 * If an invalid level of play is specified, the new MineSweeperBoard should be created at the
	 * BEGINNER_LEVEL. The size of the board and the number of cells which
	 * contain mines is as follows:
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
		if(level < BEGINNER_LEVEL || level > EXPERT_LEVEL){
			level = BEGINNER_LEVEL;
		}

		if (level == BEGINNER_LEVEL){
			int[][] beginnerBoard = createBoard(BEGINNER_LEVEL);
			coverBoard(beginnerBoard);
			placeMines(beginnerBoard, BEGINNER_LEVEL);
		}

		else if (level == INTERMEDIATE_LEVEL){
			int[][] intermedidateBoard = createBoard(INTERMEDIATE_LEVEL);
			coverBoard(intermedidateBoard);
			placeMines(intermedidateBoard, INTERMEDIATE_LEVEL);
		}

		else if (level == EXPERT_LEVEL){
			int[][] expertBoard = createBoard(EXPERT_LEVEL);
			coverBoard(expertBoard);
			placeMines(expertBoard, EXPERT_LEVEL);
		}
	}

	private int[][] createBoard(int level) {
		if(level == DEFAULT_LEVEL) {
			msb = new int[DEFAULT_BOARD_ROWS][DEFAULT_BOARD_COLUMNS];
		}

		else if (level == BEGINNER_LEVEL){
			msb = new int[BEGINNER_BOARD_ROWS][BEGINNER_BOARD_COLUMNS];
		}

		else if (level == INTERMEDIATE_LEVEL){
			msb = new int[INTERMEDIATE_BOARD_ROWS][INTERMEDIATE_BOARD_COLUMNS];
		}
		
		else if (level == EXPERT_LEVEL){
			msb = new int[EXPERT_BOARD_ROWS][EXPERT_BOARD_COLUMNS];
		}

		return msb;
	}
	

	private void coverBoard(int[][] board) {
		for(int row = 0; row < board.length; row++){
			for(int col = 0; col < board[row].length; col++) {
				board[row][col] = COVERED_CELL;
			}
		}
	}

	private int placeMineIfNotExist(int[][] board, int x, int y, int numExistingMines) {
		int numMinesPlaced = numExistingMines;

		if(board[x][y] != MINE) {
			board[x][y] = MINE;
			numMinesPlaced++;
		}

		return numMinesPlaced;
	}

	private void placeMines(int[][] board, int level) {
		Random rnd = new Random();
		int numMinesPlaced = 0;
		int mineXCoordinate = 0;
		int mineYCoordinate = 0;

		if(level == DEFAULT_LEVEL) {
			while(numMinesPlaced < DEFAULT_BOARD_NUM_MINES) {
				mineXCoordinate = rnd.nextInt(DEFAULT_BOARD_ROWS);
				mineYCoordinate = rnd.nextInt(DEFAULT_BOARD_COLUMNS);
				numMinesPlaced = placeMineIfNotExist(board, mineXCoordinate, mineYCoordinate, numMinesPlaced);
			}
		}

		else if(level == BEGINNER_LEVEL) {
			while(numMinesPlaced < BEGINNER_BOARD_NUM_MINES) {
				mineXCoordinate = rnd.nextInt(BEGINNER_BOARD_ROWS);
				mineYCoordinate = rnd.nextInt(BEGINNER_BOARD_COLUMNS);
				numMinesPlaced = placeMineIfNotExist(board, mineXCoordinate, mineYCoordinate, numMinesPlaced);
			}
		}

		else if(level == INTERMEDIATE_LEVEL) {
			while(numMinesPlaced < INTERMEDIATE_BOARD_NUM_MINES) {
				mineXCoordinate = rnd.nextInt(INTERMEDIATE_BOARD_ROWS);
				mineYCoordinate = rnd.nextInt(INTERMEDIATE_BOARD_COLUMNS);
				numMinesPlaced = placeMineIfNotExist(board, mineXCoordinate, mineYCoordinate, numMinesPlaced);		
			}
		}

		else if(level == EXPERT_LEVEL) {
			while(numMinesPlaced < EXPERT_BOARD_NUM_MINES) {
				mineXCoordinate = rnd.nextInt(EXPERT_BOARD_ROWS);
				mineYCoordinate = rnd.nextInt(EXPERT_BOARD_COLUMNS);
				numMinesPlaced = placeMineIfNotExist(board, mineXCoordinate, mineYCoordinate, numMinesPlaced);			
			}
		}
	}

	/**
	 * Get the number of rows in this MineSweeperBoard.
	 * 
	 * @return the number of rows in this MineSweeperBoard.
	 */
	public int getRows() {
		int numRows = 0;
		for(int row = 0; row < msb.length; row++) {
			numRows++;
		}
		return numRows;
	}

	/**
	 * Get the number of columns in this MineSweeperBoard.
	 * 
	 * @return the number of columns in this MineSweeperBoard.
	 */
	public int getColumns() {
		return msb[0].length;
	}

	/**
	 * Get the number of mines in this MineSweeperBoard.
	 * 
	 * @return the number of mines in this MineSweeperBoard.
	 */
	public int getNumMines() {
		int numMines = 0;
		for(int row = 0; row < msb.length; row++){
			for(int col = 0; col < msb[row].length; col++) {
				if(msb[row][col] == MineSweeperBoard.MINE) {
					numMines++;
				}
			}
		}

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
		//checking negative indices and indices that exceed the board's row and/or column size
		if(row < 0 || col < 0 || row > (getRows() - 1) || col > (getColumns() - 1)){
			return INVALID_CELL;
		}

		return msb[row][col];
	}

	/**
	 * Count the number of mines that appear in cells that are adjacent to the
	 * specified cell.  This method returns the number of adjacent mines but
	 * does not change the contents of the board. It is a helper method for
	 * the uncoverCell method below.
	 * 
	 * @param row the row of the cell.
	 * @param col the column of the cell.
	 * @return the number of mines adjacent to the specified cell.
	 *         If the specified cell is not on the board, return 0.
	 */
	public int numAdjMines(int row, int col) {
		int numMines = 0;

		//checking all of the surrounding cell locations that matter
		for(int rows = -1; rows <= 1; rows ++) {
			for(int cols = -1; cols <= 1; cols++) {
				if(getCell(row + rows, col + cols) == MINE || getCell(row + rows, col + cols) == FLAGGED_MINE 
						|| getCell(row + rows, col + cols) == UNCOVERED_MINE) {
					numMines++;
				}
			}
		}

		return numMines;
	}

	/**
	 * Uncover the specified cell. If the cell already contains a flag it should
	 * not be uncovered. If there is not a mine under the specified cell then
	 * the value in that cell is changed to the number of mines that appear in
	 * adjacent cells. If there is a mine under the specified cell then the cell 
	 * is changed to the value UNCOVERED_MINE. If the specified cell is already 
	 * uncovered or is not on the board, no change is made to the board.
	 * 
	 * @param row the row of the cell to be uncovered.
	 * @param col the column of the cell to be uncovered.
	 */
	public void uncoverCell(int row, int col) {
		//if the location on the board is a mine, 
		//does not check for a flagged mine because it's flagged even though it is a mine
		//does not check for an uncovered mine because it's already uncovered
		if(getCell(row, col) == MINE) { 
			msb[row][col] = UNCOVERED_MINE;
		}
		else {//anything else that's not a mine
			msb[row][col] = numAdjMines(row, col);
		}
	}

	/**
	 * Place or remove a flag from the specified cell. If the cell is currently
	 * covered then place a flag on the cell. If the cell currently contains a
	 * flag, remove that flag but do not uncover the cell. If the cell has
	 * already been uncovered or is not on the board, no change is made to the board.
	 * 
	 * @param row the row of the cell to be flagged/unflagged.
	 * @param col the column of the cell to be flagged/unflagged.
	 */
	public void flagCell(int row, int col) {
		//flag non-mine covered cell
		if(getCell(row,col) == COVERED_CELL){
			msb[row][col] = FLAG;
		}

		//flags a mine
		else if(getCell(row,col) == MINE) {
			msb[row][col] = FLAGGED_MINE;
		}

		//if the cell is already flagged, return it to a covered cell
		else if(getCell(row,col) == FLAG ) {
			msb[row][col] = COVERED_CELL;
		}

		//return the flagged mine to a mine
		else if(getCell(row,col) == FLAGGED_MINE) {
			msb[row][col] = MINE;
		}
	}

	/**
	 * Uncover all of the cells on the board.
	 */
	public void revealBoard() {
		for(int row = 0; row < msb.length; row++) {
			for(int col = 0; col < msb[row].length; col ++) {
				uncoverCell(row,col);
			}
		}
	}

	/**
	 * Determine if the player has lost the current game. The game is lost if
	 * the player has uncovered a mine.
	 * 
	 * @return true if the current game has been lost and false otherwise.
	 */
	public boolean gameLost() {
		boolean lost = false;
		for(int row = 0; row < msb.length; row++) {
			for(int col = 0; col < msb[row].length; col ++) {
				if(msb[row][col] == MINE) {
					lost = true;
				}// end while loop
			}
		}
		return lost;
	}

	/**
	 * Determine if the player has won the current game. The game is won when
	 * three conditions are met:
	 * <OL>
	 * <LI>Flags have been placed on all of the mines.
	 * <LI>No flags have been placed incorrectly.
	 * <LI>All non-flagged cells have been uncovered.
	 * </OL>
	 * 
	 * @return true if the current game has been won and false otherwise.
	 */
	public boolean gameWon() {
		boolean gameWon = false;
		boolean minesFlagged = false;
		boolean correctlyFlagged = false; 
		boolean uncoveredNonFlags = false;

		//checking if all of the mines are flagged
		for(int row = 0; row < msb.length; row++) {
			for(int col = 0; col < msb[row].length; col++) {
				if(getCell(row, col) == FLAGGED_MINE) {
					minesFlagged = true;
				}
			}
		}

		//check if the flags are correctly placed
		for(int row = 0; row < msb.length; row++) {
			for(int col = 0; col < msb[row].length; col++) {
				if(getCell(row, col) != FLAG) {
					correctlyFlagged = true;
				}
			}
		}

		//check if non-flag cells have been uncovered
		for(int row = 0; row < msb.length; row++) {
			for(int col = 0; col < msb[row].length; col++) {
				if(getCell(row, col) != UNCOVERED_MINE) {
					uncoveredNonFlags = true;
				}
			}
		}

		//if all three conditions are met, the player wins
		if (minesFlagged && correctlyFlagged && uncoveredNonFlags) {
			gameWon = true;
		}

		return gameWon;
	}
}