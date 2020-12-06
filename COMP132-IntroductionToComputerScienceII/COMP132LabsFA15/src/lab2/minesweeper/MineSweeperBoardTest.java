package lab2.minesweeper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MineSweeperBoardTest {
	private MineSweeperBoard msb;

	@Before
	public void setUp() throws Exception {
		msb = new MineSweeperBoard();
	}

	@Test
	public void testFirstContructor() {
		assertEquals("The MineSweeperBoard should have 3 rows", 3, msb.getRows());
		assertEquals("The MineSweeperBoard should have 4 columns", 4, msb.getColumns());
		assertEquals("The MineSweeperBoard should have 2 mines", 2, msb.getNumMines());
	}

	@Test
	public void testSecondConstructorInvalidLevel() {
		//level greater than the max level
		MineSweeperBoard msb1 = new MineSweeperBoard(5);
		assertEquals("the board should have 5 rows", 5, msb1.getRows());
		assertEquals("the board should have 10 columns", 10, msb1.getColumns());
		assertEquals("the beginner board should have 3 mines", 3, msb1.getNumMines());

		//level greater than the min level
		MineSweeperBoard msb2 = new MineSweeperBoard(0);
		assertEquals("the board should have 5 rows", 5, msb2.getRows());
		assertEquals("the board should have 10 columns", 10, msb2.getColumns());
		assertEquals("the beginner board should have 3 mines", 3, msb2.getNumMines());	
	}

	@Test
	public void testSecondContructorBeginnerLevel() { 
		MineSweeperBoard msb3 = new MineSweeperBoard(MineSweeperBoard.BEGINNER_LEVEL);

		assertEquals("the board should have 5 rows", 5, msb3.getRows());
		assertEquals("the board should have 10 columns", 10, msb3.getColumns());
		assertEquals("the beginner board should have 3 mines", 3, msb3.getNumMines());

	}

	@Test
	public void testSecondContructorIntermediateLevel() {
		MineSweeperBoard msb4 = new MineSweeperBoard(MineSweeperBoard.INTERMEDIATE_LEVEL);

		assertEquals("the board should have 10 rows", 10, msb4.getRows());
		assertEquals("the board should have 15 columns", 15, msb4.getColumns());
		assertEquals("the intermediate level board should have 15 mines", 15, msb4.getNumMines());
	}

	@Test
	public void testSecondContructorExpertLevel() {
		MineSweeperBoard msb5 = new MineSweeperBoard(MineSweeperBoard.EXPERT_LEVEL);

		assertEquals("the board should have 15 rows", 15, msb5.getRows());
		assertEquals("the board should have 20 columns", 20, msb5.getColumns());
		assertEquals("the expert board level should have 45 mines", 45, msb5.getNumMines());
	}


	@Test
	public void testGetCell(){
		//locations with negative integers
		assertEquals("Location (-1,0) should be an invalid cell on the MineSweeperBoard", MineSweeperBoard.INVALID_CELL, msb.getCell(-1, 0));
		assertEquals("Location (0,-1) should be an invalid cell on the MineSweeperBoard", MineSweeperBoard.INVALID_CELL, msb.getCell(0, -1));

		//row or column that exceed row or column size
		assertEquals("Location (2,5) should be an invalid cell on the MineSweeperBoard", MineSweeperBoard.INVALID_CELL, msb.getCell(2, 5));
		assertEquals("Location (5,2) should be an invalid cell on the MineSweeperBoard", MineSweeperBoard.INVALID_CELL, msb.getCell(5, 2));

		//locations both exceed row and column size 
		assertEquals("Location (5,5) should be an invalid cell on the MineSweeperBoard", MineSweeperBoard.INVALID_CELL, msb.getCell(5, 5));
	}
	@Test
	public void testMineLocations() {
		assertEquals("Location (0,0) on the MineSweeperBoard should be a mine", MineSweeperBoard.MINE, msb.getCell(0, 0));
		assertEquals("Location (2,1) on the MineSweeperBoard should be a mine", MineSweeperBoard.MINE, msb.getCell(2, 1));
	}

	@Test
	public void testCoveredCellLocations(){
		assertEquals("Location (0,1) on the MineSweeperBoard should be a covered cell", MineSweeperBoard.COVERED_CELL, msb.getCell(0, 1));
		assertEquals("Location (1,1) on the MineSweeperBoard should be a covered cell", MineSweeperBoard.COVERED_CELL, msb.getCell(1, 1));
		assertEquals("Location (2,0) on the MineSweeperBoard should be a covered cell", MineSweeperBoard.COVERED_CELL, msb.getCell(2, 0));
	}

	@Test
	public void testNumAdjMinesForMines() {
		//testing mines
		assertEquals("Location (1,1) should have 2 adjacent mines", 2 , msb.numAdjMines(1,1));
		assertEquals("Location (1,0) should have 2 adjacent mines", 2 , msb.numAdjMines(1,0));
		assertEquals("Location (2,2) should have 1 adjacent mines", 1 , msb.numAdjMines(2,2));
		assertEquals("Location (1,3) should have 0 adjacent mines", 0 , msb.numAdjMines(1,3));
	}

	@Test
	public void testNumAdjMinesForFlaggedMines() {
		//testing flagged mines
		msb.flagCell(0, 0);
		msb.flagCell(2, 1);

		assertEquals("Location (1,1) should have 2 adjacent mines", 2 , msb.numAdjMines(1,1));
		assertEquals("Location (1,0) should have 2 adjacent mines", 2 , msb.numAdjMines(1,0));
		assertEquals("Location (2,2) should have 1 adjacent mines", 1 , msb.numAdjMines(2,2));
		assertEquals("Location (1,3) should have 0 adjacent mines", 0 , msb.numAdjMines(1,3));

	}

	@Test
	public void testNumAdjMinesForUncoveredMines() {
		//should still return correct number of mines even though it's uncovered
		msb.uncoverCell(0, 0);
		msb.uncoverCell(2, 1);

		assertEquals("Location (1,1) should have 2 adjacent mines", 2 , msb.numAdjMines(1,1));
		assertEquals("Location (1,0) should have 2 adjacent mines", 2 , msb.numAdjMines(1,0));
		assertEquals("Location (2,2) should have 1 adjacent mines", 1 , msb.numAdjMines(2,2));
		assertEquals("Location (1,3) should have 0 adjacent mines", 0 , msb.numAdjMines(1,3));

	}

	@Test
	public void testUncoverCell() {  
		//testing regular cells
		msb.uncoverCell(0, 1);
		assertEquals("Location (0,1) should have an adjacent mine", 1, msb.getCell(0, 1));
		msb.uncoverCell(2, 0);
		assertEquals("Location (2,0) should have 2 adjacent mines", 1, msb.getCell(2, 0));
		msb.uncoverCell(1, 1);
		assertEquals("Location (1,1) should have 2 adjacent mines", 2, msb.getCell(1, 1));

		//testing the mines
		assertEquals("Location (0,0) should be a mine", MineSweeperBoard.MINE, msb.getCell(0, 0));
		msb.uncoverCell(0, 0);
		assertEquals("Location (0,0) should be a mine", MineSweeperBoard.UNCOVERED_MINE, msb.getCell(0, 0));

		assertEquals("Location (2,1) should be a mine", MineSweeperBoard.MINE, msb.getCell(2, 1));
		msb.uncoverCell(2, 1);
		assertEquals("Location (2,1) should be a mine", MineSweeperBoard.UNCOVERED_MINE, msb.getCell(2, 1));
	}

	@Test
	public void testFlagCell() {
		//flagging a non-mine cell 
		assertEquals("Location (0, 3) on the board should be a covered cell",MineSweeperBoard.COVERED_CELL, msb.getCell(0, 3));
		msb.flagCell(0, 3);
		assertEquals("Location (0, 3) on the board should be a flagged cell",MineSweeperBoard.FLAG, msb.getCell(0, 3));

		assertEquals("Location (1,2) on the board should be a covered cell",MineSweeperBoard.COVERED_CELL, msb.getCell(1, 2));
		msb.flagCell(1, 2);
		assertEquals("Location (1, 2) on the board should be a flagged cell",MineSweeperBoard.FLAG, msb.getCell(1, 2));

		//flagging a mine
		assertEquals("Location (0,0) on the MineSweeperBoard should be a mine", MineSweeperBoard.MINE, msb.getCell(0, 0));
		msb.flagCell(0, 0);
		assertEquals("Location (0,0) on the MineSweeperBoard should be a flagged mine", MineSweeperBoard.FLAGGED_MINE, msb.getCell(0, 0));
		msb.flagCell(2, 1);
		assertEquals("Location (2,1) on the MineSweeperBoard should be a flagged mine", MineSweeperBoard.FLAGGED_MINE, msb.getCell(2, 1));

		//unflagging a flagged cell
		msb.flagCell(0, 3);
		assertEquals("Location (0,3) on the MineSweeperBoard should be a covered cell", MineSweeperBoard.COVERED_CELL, msb.getCell(0, 3));

		//return a flagged mine to a mine
		msb.flagCell(0, 0);
		assertEquals("Location (0,0) on the MineSweeperBoard should be a mine", MineSweeperBoard.MINE, msb.getCell(0, 0));
		msb.flagCell(2, 1);
		assertEquals("Location (2,1) on the MineSweeperBoard should be a mine", MineSweeperBoard.MINE, msb.getCell(2, 1));
	}

	@Test
	public void testRevealBoard() {
		msb.revealBoard();

		//testing the mines
		assertEquals("Location (0,0) should be an uncovered mine", MineSweeperBoard.UNCOVERED_MINE, msb.getCell(0, 0));
		assertEquals("Location (2,1) should be an uncovered mine", MineSweeperBoard.UNCOVERED_MINE, msb.getCell(2, 1));

		//testing the cells surrounding cell
		assertEquals("Location (0,3) should have 0 adjacent mines", 0, msb.getCell(0, 3));
		assertEquals("Location (1,1) should have 2 adjacent mines", 2, msb.getCell(1, 1));
		assertEquals("Location (2,2) should have 1 adjacent mine", 1, msb.getCell(2, 2));
		assertEquals("Location (0,1) should have 1 adjacent mine", 1, msb.getCell(0, 1));
		assertEquals("Location (2,0) should have 1 adjacent mine", 1, msb.getCell(2, 0));
	}

	@Test
	public void testGameLost() {
		assertTrue("the default board should have lost", msb.gameLost());
	}

	@Test
	public void testGameWon(){
		assertFalse("the default board should NOT have won", msb.gameWon());
	}
}
