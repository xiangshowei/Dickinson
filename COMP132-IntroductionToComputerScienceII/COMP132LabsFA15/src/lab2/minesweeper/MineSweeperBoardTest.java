package lab2.minesweeper;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class MineSweeperBoardTest {
	private MineSweeperBoard msb;

	@Before
	public void setUp() throws Exception {
		msb = new MineSweeperBoard();
	}

	@After
	public void tearDown() {

	}

	@Test
	public void testContructorDefaultLevelBoard() {
		assertEquals(MineSweeperBoard.DEFAULT_LEVEL, msb.getDifficultyLevel());
		assertEquals(MineSweeperBoard.DEFAULT_BOARD_ROWS, msb.getNumRows());
		assertEquals(MineSweeperBoard.DEFAULT_BOARD_COLUMNS, msb.getNumColumns());
		assertEquals(MineSweeperBoard.DEFAULT_BOARD_NUM_MINES, msb.getNumMines());

		testMineLocations(MineSweeperBoard.DEFAULT_LEVEL, msb);
	}

	@Test
	public void testConstructorInvalidLevel() {
		//level higher than allowed max level
		MineSweeperBoard msb1 = new MineSweeperBoard(5);
		assertEquals(MineSweeperBoard.BEGINNER_LEVEL, msb1.getDifficultyLevel());
		assertEquals(MineSweeperBoard.BEGINNER_BOARD_ROWS, msb1.getNumRows());
		assertEquals(MineSweeperBoard.BEGINNER_BOARD_COLUMNS, msb1.getNumColumns());
		assertEquals(MineSweeperBoard.BEGINNER_BOARD_NUM_MINES, msb1.getNumMines());

		testMineLocations(MineSweeperBoard.BEGINNER_LEVEL, msb1) ;

		//level lower than allowed min level
		MineSweeperBoard msb2 = new MineSweeperBoard(0);
		assertEquals(MineSweeperBoard.BEGINNER_LEVEL, msb2.getDifficultyLevel());
		assertEquals(MineSweeperBoard.BEGINNER_BOARD_ROWS, msb2.getNumRows());
		assertEquals(MineSweeperBoard.BEGINNER_BOARD_COLUMNS, msb2.getNumColumns());
		assertEquals(MineSweeperBoard.BEGINNER_BOARD_NUM_MINES, msb2.getNumMines());

		testMineLocations(MineSweeperBoard.BEGINNER_LEVEL, msb2);
	}

	@Test
	public void testContructorBeginnerLevel() { 
		MineSweeperBoard msb3 = new MineSweeperBoard(MineSweeperBoard.BEGINNER_LEVEL);
		assertEquals(MineSweeperBoard.BEGINNER_LEVEL, msb3.getDifficultyLevel());
		assertEquals(MineSweeperBoard.BEGINNER_BOARD_ROWS, msb3.getNumRows());
		assertEquals(MineSweeperBoard.BEGINNER_BOARD_COLUMNS, msb3.getNumColumns());
		assertEquals(MineSweeperBoard.BEGINNER_BOARD_NUM_MINES, msb3.getNumMines());

		testMineLocations(MineSweeperBoard.BEGINNER_LEVEL, msb3);
	}

	@Test
	public void testContructorIntermediateLevel() {
		MineSweeperBoard msb4 = new MineSweeperBoard(MineSweeperBoard.INTERMEDIATE_LEVEL);
		assertEquals(MineSweeperBoard.INTERMEDIATE_LEVEL, msb4.getDifficultyLevel());
		assertEquals(MineSweeperBoard.INTERMEDIATE_BOARD_ROWS, msb4.getNumRows());
		assertEquals(MineSweeperBoard.INTERMEDIATE_BOARD_COLUMNS, msb4.getNumColumns());
		assertEquals(MineSweeperBoard.INTERMEDIATE_BOARD_NUM_MINES, msb4.getNumMines());

		testMineLocations(MineSweeperBoard.INTERMEDIATE_LEVEL, msb4);
	}

	@Test
	public void testContructorExpertLevel() {
		MineSweeperBoard msb5 = new MineSweeperBoard(MineSweeperBoard.EXPERT_LEVEL);
		assertEquals(MineSweeperBoard.EXPERT_LEVEL, msb5.getDifficultyLevel());
		assertEquals(MineSweeperBoard.EXPERT_BOARD_ROWS, msb5.getNumRows());
		assertEquals(MineSweeperBoard.EXPERT_BOARD_COLUMNS, msb5.getNumColumns());
		assertEquals(MineSweeperBoard.EXPERT_BOARD_NUM_MINES, msb5.getNumMines());

		testMineLocations(MineSweeperBoard.EXPERT_LEVEL, msb5);
	}

	@Test
	public void testGetCellInvalidCell(){
		//locations with negative integers
		assertEquals(MineSweeperBoard.INVALID_CELL, msb.getCell(-1, 0));
		assertEquals(MineSweeperBoard.INVALID_CELL, msb.getCell(0, -1));

		//row or column that exceed board size
		assertEquals(MineSweeperBoard.INVALID_CELL, msb.getCell(2, 5));
		assertEquals(MineSweeperBoard.INVALID_CELL, msb.getCell(5, 2));
	}

	@Test
	public void testGetCellValidCell(){
		assertEquals(MineSweeperBoard.MINE, msb.getCell(0, 0));
		assertEquals(MineSweeperBoard.COVERED_CELL, msb.getCell(2, 3));
	}

	@Test
	public void testNumAdjacentMines() {
		assertEquals(2 , msb.getNumAdjacentMines(1,1));
		assertEquals(1 , msb.getNumAdjacentMines(1,2));
		assertEquals(0 , msb.getNumAdjacentMines(1,3));
	}

	@Test
	public void testNumAdjacentMinesFlaggedMines() {
		msb.flagCell(0, 0);
		msb.flagCell(2, 1);

		assertEquals(2 , msb.getNumAdjacentMines(1,1));
		assertEquals(1 , msb.getNumAdjacentMines(1,2));
		assertEquals(0 , msb.getNumAdjacentMines(1,3));
	}

	@Test
	public void testNumAdjacentMinesUncoveredMines() {
		msb.uncoverCell(0, 0);
		msb.uncoverCell(2, 1);

		assertEquals(2 , msb.getNumAdjacentMines(1,1));
		assertEquals(1 , msb.getNumAdjacentMines(1,2));
		assertEquals(0 , msb.getNumAdjacentMines(1,3));
	}

	@Test
	public void testUncoverCellRegularCells() {  
		msb.uncoverCell(0, 1);
		assertEquals(msb.getNumAdjacentMines(0, 1), msb.getCell(0, 1));
		msb.uncoverCell(2, 0);
		assertEquals(msb.getNumAdjacentMines(2, 0), msb.getCell(2, 0));
		msb.uncoverCell(1, 1);
		assertEquals(msb.getNumAdjacentMines(1, 1), msb.getCell(1, 1));
	}
	
	@Test
	public void testUncoverCellMines() {
		assertEquals(MineSweeperBoard.MINE, msb.getCell(0, 0));
		msb.uncoverCell(0, 0);
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, msb.getCell(0, 0));
		assertEquals(0, msb.getNumAdjacentMines(0, 0));

		//uncovering an uncovered mine should not change the board
		msb.uncoverCell(0, 0);
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, msb.getCell(0, 0));
		assertEquals(0, msb.getNumAdjacentMines(0, 0));
	}

	@Test
	public void testFlagCell() {
		//flagging a non-mine cell 
		assertEquals(MineSweeperBoard.COVERED_CELL, msb.getCell(0, 3));
		msb.flagCell(0, 3);
		assertEquals(MineSweeperBoard.FLAGGED_CELL, msb.getCell(0, 3));

		//unflagging a flagged cell
		msb.flagCell(0, 3);
		assertEquals(MineSweeperBoard.COVERED_CELL, msb.getCell(0, 3));
		
		//flagging a mine
		assertEquals(MineSweeperBoard.MINE, msb.getCell(2, 1));
		msb.flagCell(2, 1);
		assertEquals(MineSweeperBoard.FLAGGED_MINE, msb.getCell(2, 1));
		
		//return a flagged mine to a mine
		msb.flagCell(2, 1);
		assertEquals(MineSweeperBoard.MINE, msb.getCell(2, 1));
	}

	@Test
	public void testFlagCellUncoveredCells() {
		//mine
		assertEquals(MineSweeperBoard.MINE, msb.getCell(0, 0));
		msb.uncoverCell(0, 0);
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, msb.getCell(0, 0));

		//uncovering an uncovered mine should not change the board
		msb.flagCell(0, 0);
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, msb.getCell(0, 0));

		//regular cell
		assertEquals(MineSweeperBoard.COVERED_CELL, msb.getCell(0, 3));
		msb.uncoverCell(0, 3);
		assertEquals(msb.getNumAdjacentMines(0, 3), msb.getCell(0, 3));

		//uncovering an uncovered cell should not change the board
		msb.flagCell(0, 3);
		assertEquals(msb.getNumAdjacentMines(0, 3), msb.getCell(0, 3));
	}	

	@Test
	public void testRevealBoard() {
		msb.revealBoard();

		//testing the mines
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, msb.getCell(0, 0));
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, msb.getCell(2, 1));

		//testing the other cells
		assertEquals(msb.getNumAdjacentMines(0, 3), msb.getCell(0, 3));
		assertEquals(msb.getNumAdjacentMines(1, 1), msb.getCell(1, 1));
		assertEquals(msb.getNumAdjacentMines(2, 2), msb.getCell(2, 2));
		assertEquals(msb.getNumAdjacentMines(0, 1), msb.getCell(0, 1));
		assertEquals(msb.getNumAdjacentMines(2, 0), msb.getCell(2, 0));
	}

	@Test
	public void testHasUncoveredMine() {
		assertEquals(false, msb.hasUncoveredMine());

		msb.uncoverCell(0, 0);
		assertEquals(true, msb.hasUncoveredMine());
	}

	@Test
	public void testGameWon(){
		// assertEquals(false, msb.gameWon());

		// msb.flagCell(0, 0);
		// msb.flagCell(2, 1);

		// assertEquals(true, msb.gameWon());
	}

	private void testMineLocations(int level, MineSweeperBoard msb) {
		String[] mineLocations = msb.getMineLocations();
		int mineXCoordinate = 0;
		int mineYCoordinate = 0;

		if(level == MineSweeperBoard.DEFAULT_LEVEL){
			assertEquals("0,0", mineLocations[0]);
			assertEquals("2,1", mineLocations[1]);
		}

		else {
			for(String coordinate : mineLocations) {
				String[] mineCoordinates = coordinate.split(",");
				
				mineXCoordinate = Integer.valueOf(mineCoordinates[0]);
				mineYCoordinate = Integer.valueOf(mineCoordinates[1]);
			
				if(level == MineSweeperBoard.BEGINNER_LEVEL) {
					assertEquals(true, mineXCoordinate >= 0 && mineXCoordinate < MineSweeperBoard.BEGINNER_BOARD_ROWS);
					assertEquals(true, mineYCoordinate >= 0 && mineYCoordinate < MineSweeperBoard.BEGINNER_BOARD_COLUMNS);
				}

				else if(level == MineSweeperBoard.INTERMEDIATE_LEVEL) {
					assertEquals(true, mineXCoordinate >= 0 && mineXCoordinate < MineSweeperBoard.INTERMEDIATE_BOARD_ROWS);
					assertEquals(true, mineYCoordinate >= 0 && mineYCoordinate < MineSweeperBoard.INTERMEDIATE_BOARD_COLUMNS);
				}

				else if(level == MineSweeperBoard.EXPERT_LEVEL) {
					assertEquals(true, mineXCoordinate >= 0 && mineXCoordinate < MineSweeperBoard.EXPERT_BOARD_ROWS);
					assertEquals(true, mineYCoordinate >= 0 && mineYCoordinate < MineSweeperBoard.EXPERT_BOARD_COLUMNS);
				}
			}
		}
	}
}