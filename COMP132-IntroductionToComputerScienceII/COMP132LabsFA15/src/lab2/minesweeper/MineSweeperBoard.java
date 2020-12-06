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
	public static final int COVERED_CELL = -1;

	/**
	 * A constant value representing a a cell that has not been uncovered yet
	 * but contains a mine.
	 */
	public static final int MINE = -2;

	/**
	 * A constant value representing a cell which does not contain a mine but
	 * has had a flag placed on it.
	 */
	public static final int FLAG = -3;

	/**
	 * A constant value representing a cell which contains a mine and has had a
	 * flag placed on it.
	 */
	public static final int FLAGGED_MINE = -4;

	/**
	 * A constant value representing a cell containing a mine that has been
	 * uncovered.
	 */
	public static final int UNCOVERED_MINE = -5;

	/**
	 * A constant value representing the contents of an invalid cell. This value
	 * is returned by the getCell method when an invalid cell is specified.
	 */
	public static final int INVALID_CELL = -10;

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

	/**
	 * Construct a new fixed MineSweeperBoard for testing purposes. The board
	 * should have 3 rows and 4 columns. All cells should contain COVERED_CELL,
	 * except that locations (0, 0) and (2, 1) should contain MINE.
	 */
	public MineSweeperBoard() {
		msb = new int[3][4];

		//covering up the cells on the board
		for(int row = 0; row < msb.length; row++){
			for(int col = 0; col < msb[row].length; col++) {
				msb[row][col] = COVERED_CELL; 
			} //end for loop for columns
		}// end for loop for rows

		//setting the mines AKA the trap cards
		msb[0][0] = MINE;
		msb[2][1] = MINE;
	}

	/**
	 * Construct a new MineSweeperBoard for play at the specified level. The
	 * size of the board and the number of mines are determined by the level of
	 * play. Valid levels of play are indicated by the constants BEGINNER_LEVEL,
	 * INTERMEDIATE_LEVEL and EXPERT_LEVEL. If an invalid level of play is
	 * specified the new MineSweeperBoard should be created at the
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
		Random rnd = new Random();

		//number of mines that will be on the board
		int boardMines = 0;

		//random locations of the mines
		int x1 = 0; //for rows
		int y1 = 0; //for columns

		int x2 = 0;
		int y2 = 0;

		//size of the board
		//    	int rows = 0;
		//    	int cols = 0;

		if(level < BEGINNER_LEVEL || level > EXPERT_LEVEL){
			level = BEGINNER_LEVEL;
		}

		if (level == BEGINNER_LEVEL){
			msb = new int [5][10];

			boardMines = 3;

			//covering the board
			for(int row = 0; row < msb.length; row++){
				for(int col = 0; col < msb[row].length; col++) {
					msb[row][col] = COVERED_CELL;
				}
			}

			//setting the mines
			for(int i = 0; i < boardMines; i++) {
				x1 = rnd.nextInt(5);
				y1 = rnd.nextInt(10);

				//if there does NOT exist a mine @ (x1,y1), put one there
				if(msb[x1][y1] != MINE){
					msb[x1][y1] = MINE;
				}//end if statement
				else {//there already exists a mine @ (x1,y1) 
					//get new random values
					x2 = rnd.nextInt(5);
					y2 = rnd.nextInt(10);
					while(msb[x2][y2] != MINE){
						//if x1 =x2, y1=y2; there's a mine at the newly generated locations
						if(getCell(x2,y2) == getCell(x1,y1))
						msb[x2][y2] = MINE;
					}
				}
			}//end for loop

		} // end of set up for BEGINNER_LEVEL

		else if (level == INTERMEDIATE_LEVEL){
			msb = new int [10][15];

			boardMines = 15;

			//covering the board
			for(int row = 0; row < msb.length; row++){
				for(int col = 0; col < msb[row].length; col++) {
					msb[row][col] = COVERED_CELL;
				}
			}

			//setting the mines
			for(int i = 0; i < boardMines; i++) {
				x1 = rnd.nextInt(10);
				y1 = rnd.nextInt(15);

				//if there does NOT exist a mine @ (x,y), put one there
				if(msb[x1][y1] != MINE){
					msb[x1][y1] = MINE;
				}//end if statement
			}//end for loop

		}// end of set up for INTERMEDIATE_LEVEL

		else if (level == EXPERT_LEVEL){
			msb = new int [15][20];

			boardMines = 45;

			//covering the board
			for(int row = 0; row < msb.length; row++){
				for(int col = 0; col < msb[row].length; col++) {
					msb[row][col] = COVERED_CELL;
				}
			}

			//setting the mines
			for(int i = 0; i < boardMines; i++) {
				x1 = rnd.nextInt(15);
				y1 = rnd.nextInt(20);

				//if there does NOT exist a mine @ (x,y), put one there
				if(getCell(x1,y1) != MINE){
					msb[x1][y1] = MINE;
				}//end if statement

				else {//there already exists a mine @ (x,y) 
										
					//get new random numbers
					x2 = rnd.nextInt(15);
					y2 = rnd.nextInt(20);

					//while we're on the board and it's not a mine
//				while(getCell(x2, y2) != INVALID_CELL && getCell(x2, y2) != MINE){
					for(int row = 0; row < msb.length; row++){
						for(int col = 0; col < msb[row].length; col++) {
							//on the board
							if((getCell(row, col) != INVALID_CELL && getCell(row, col) != MINE)) {
								//if the second set of random numbers are not as those in the first set
								//and the new random location does NOT have a mine there, put one there
								while( (x1 != x2 && x1 != y1 && x1 != y2 && y1 != y2 )  &&
										(getCell(x2, y2) != getCell(x1, y1))  && 
										(getCell(x2, y2) != MINE && getCell(x1, y1) == MINE) ) {
									msb[x2][y2] = MINE;
								} //end while loop
							} // end if statement
						} //end for loop for columns
					} // end for loop for rows
					
				

				} // end else to the initial if statement

			} //end for loop before there are anything on the board
			
		}// end of set up for EXPERT_LEVEL
	} //end second constructor


	//		for(int row = 0; row < msb.length; row++){
	//			for(int col = 0; col < msb[row].length; col++) {
	//				msb[row][col] = COVERED_CELL;
	//			}
	//		}
	//
	//		//setting the mines
	//		for(int i = 0; i < boardMines; i++) {
	//			x = rnd.nextInt(rows);
	//			y = rnd.nextInt(cols);
	//			if(msb[x][y] != MINE){
	//				msb[x][y] = MINE;
	//			}
	//		}


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
				}// end if statement
			} //end for loop for columns
		}// end for loop for rows

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
				}//end if statement
			} //end for loop for columns
		} // end for loop for rows

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
