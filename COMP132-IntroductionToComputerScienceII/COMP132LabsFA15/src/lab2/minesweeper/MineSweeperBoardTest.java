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
		assertEquals(3, msb.getRows());
		assertEquals(4, msb.getColumns());
		assertEquals(2, msb.getNumMines());

		testMineLocations(MineSweeperBoard.DEFAULT_LEVEL);
	}

	@Test
	public void testConstructorInvalidLevel() {
		//level higher than allowed max level
		MineSweeperBoard msb1 = new MineSweeperBoard(5);
		assertEquals(MineSweeperBoard.BEGINNER_LEVEL, msb1.getDifficultyLevel());
		assertEquals(5, msb1.getRows());
		assertEquals(10, msb1.getColumns());
		assertEquals(3, msb1.getNumMines());

		testMineLocations(MineSweeperBoard.BEGINNER_LEVEL);

		//level lower than allowed min level
		MineSweeperBoard msb2 = new MineSweeperBoard(0);
		assertEquals(MineSweeperBoard.BEGINNER_LEVEL, msb2.getDifficultyLevel());
		assertEquals(5, msb2.getRows());
		assertEquals(10, msb2.getColumns());
		assertEquals(3, msb2.getNumMines());	

		testMineLocations(MineSweeperBoard.BEGINNER_LEVEL);
	}

	@Test
	public void testContructorBeginnerLevel() { 
		MineSweeperBoard msb3 = new MineSweeperBoard(MineSweeperBoard.BEGINNER_LEVEL);
		assertEquals(MineSweeperBoard.BEGINNER_LEVEL, msb3.getDifficultyLevel());
		assertEquals(5, msb3.getRows());
		assertEquals(10, msb3.getColumns());
		assertEquals(3, msb3.getNumMines());

		testMineLocations(MineSweeperBoard.BEGINNER_LEVEL);
	}

	@Test
	public void testContructorIntermediateLevel() {
		MineSweeperBoard msb4 = new MineSweeperBoard(MineSweeperBoard.INTERMEDIATE_LEVEL);
		assertEquals(MineSweeperBoard.INTERMEDIATE_LEVEL, msb4.getDifficultyLevel());
		assertEquals(10, msb4.getRows());
		assertEquals(15, msb4.getColumns());
		assertEquals(15, msb4.getNumMines());

		testMineLocations(MineSweeperBoard.INTERMEDIATE_LEVEL);
	}

	@Test
	public void testContructorExpertLevel() {
		MineSweeperBoard msb5 = new MineSweeperBoard(MineSweeperBoard.EXPERT_LEVEL);
		assertEquals(MineSweeperBoard.EXPERT_LEVEL, msb5.getDifficultyLevel());
		assertEquals(15, msb5.getRows());
		assertEquals(20, msb5.getColumns());
		assertEquals(45, msb5.getNumMines());

		testMineLocations(MineSweeperBoard.EXPERT_LEVEL);
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
	public void testUncoverCell() {  
		//testing regular cells
		msb.uncoverCell(0, 1);
		assertEquals(1, msb.getCell(0, 1));
		msb.uncoverCell(2, 0);
		assertEquals(1, msb.getCell(2, 0));
		msb.uncoverCell(1, 1);
		assertEquals(2, msb.getCell(1, 1));

		//testing the mines
		assertEquals(MineSweeperBoard.MINE, msb.getCell(0, 0));
		msb.uncoverCell(0, 0);
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, msb.getCell(0, 0));
		assertEquals(0, msb.getNumAdjacentMines(0, 0));

		assertEquals(MineSweeperBoard.MINE, msb.getCell(2, 1));
		msb.uncoverCell(2, 1);
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, msb.getCell(2, 1));
		assertEquals(0, msb.getNumAdjacentMines(2, 1));
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
	public void testRevealBoard() {
		msb.revealBoard();

		//testing the mines
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, msb.getCell(0, 0));
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, msb.getCell(2, 1));

		//testing the other cells
		assertEquals(0, msb.getCell(0, 3));
		assertEquals(2, msb.getCell(1, 1));
		assertEquals(1, msb.getCell(2, 2));
		assertEquals(1, msb.getCell(0, 1));
		assertEquals(1, msb.getCell(2, 0));
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

	private void testMineLocations(int level) {
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
