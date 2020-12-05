package lab05.whackamole;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WhackAMoleModelTest {

	private WhackAMoleModel wamm;
	private boolean[][] board;

	@Before
	public void setUp() throws Exception {
		//the mole is initially at (2,3)
		wamm = new WhackAMoleModel(2,3);
		board = new boolean[wamm.getRows()][wamm.getCols()];
	}

	@Test
	public void testConstructor() {
		assertEquals("the model should have a score of zero", 0, wamm.getScore());
		assertEquals("the model should have 5 rows", 5, wamm.getRows());
		assertEquals("the model should have 5 columns", 5, wamm.getCols());
		assertEquals("the mole should be at row 2", 2, wamm.getMoleRow());
		assertEquals("the mole should be at column 3", 3, wamm.getMoleCol());
	}
	
	@Test
	public void testConstructor_MoleAppearsAnywhereOnBoard() {
		for(int i = 0; i < 25; i++) {
			for(int j = 0; j < 25; j++) {
				WhackAMoleModel newWAMM = new WhackAMoleModel();
				board[newWAMM.getMoleRow()][newWAMM.getMoleCol()] = true;
			}
		}
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				assertTrue("the mole never appeared at (" + i + "," + j + ")", board[i][j]);
			}
		}
	}

	@Test
	public void testWhack_ScoreIncreasesWhenMoleIsWhacked() {
		assertEquals("the score should be zero because the board has not been whacked yet", 0, wamm.getScore());
		wamm.whack(2, 3);
		assertEquals("the score should have increased by 10 because the mole was whacked", 10, wamm.getScore());
	}
	
	@Test 
	public void testWhack_ScoreDecreasesWhenHoleIsWhacked() {
		assertEquals("the score should be zero because the board has not been whacked yet", 0, wamm.getScore());
		wamm.whack(0, 0);
		assertEquals("the score should have decreased by 5 because a hole was whacked", -5, wamm.getScore());
	}
	
	@Test
	public void testWhack_MoleMovesWhenWhacked() {
		assertEquals("x coordinate of mole should be at 2", 2, wamm.getMoleRow());
		assertEquals("y coordinate of mole should be at 3", 3, wamm.getMoleCol());
		wamm.whack(2, 3);
		assertTrue("x and y coordinates of the mole should have changed", 
				wamm.getMoleRow() != 2 || wamm.getMoleCol() != 3);
	}
	
	@Test
	public void testWhack_MoleMovesWhenHoleIsWhacked() {
		assertEquals("x coordinate of mole should be at 2", 2, wamm.getMoleRow());
		assertEquals("y coordinate of mole should be at 3", 3, wamm.getMoleCol());
		wamm.whack(0, 0);
		assertFalse("x and y coordinates of the mole should have changed",
				wamm.getMoleRow() == 2 && wamm.getMoleCol() == 3);
	}
	
	@Test public void testWhack_MoleMovesToAnyLocationWhenWhacked(){
		for(int i = 0; i < 1000; i++) {
			int moleXPosition = wamm.getMoleRow();
			int moleYPosition = wamm.getMoleCol();
			wamm.whack(moleXPosition, moleYPosition);
			board[moleXPosition][moleYPosition] = true;
		}

		for (int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				assertTrue("the mole never appeared at (" + i + "," + j + ")", board[i][j]);
			}
		}
	}	
}