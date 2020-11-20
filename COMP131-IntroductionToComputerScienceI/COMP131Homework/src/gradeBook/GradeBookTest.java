package gradeBook;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GradeBookTest.
 *
 * @author  Xiang Wei
 * @version (12.11.13)
 */
public class GradeBookTest {
    private GradeBook gradeBook1;
    private GradeBook gradeBook2;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        gradeBook1 = new GradeBook("Q", 3, 2);
        gradeBook1.setHomeworkScore(0, 10);
        gradeBook1.setHomeworkScore(1, 8);
        gradeBook1.setHomeworkScore(2, 9);
        gradeBook1.setQuizScore(0, 8);
        gradeBook1.setQuizScore(1, 9);
        
        gradeBook2 = new GradeBook("l", 5, 0);
        gradeBook2.setHomeworkScore(0, 75);
        gradeBook2.setHomeworkScore(1, 80);
        gradeBook2.setHomeworkScore(2, 97);
        gradeBook2.setHomeworkScore(3, 78);
        gradeBook2.setHomeworkScore(4, 68);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown(){ }
    
    @Test
    public void testGetStudentName() {
        assertEquals("Q", gradeBook1.getStudentName());
    }

    @Test
    public void testGetNumberOfHomeworkAssignments() {
        assertEquals(3, gradeBook1.getNumHomeworks());
    }
    
    @Test
    public void testGetNumberOfQuizzes() {
        assertEquals(2, gradeBook1.getNumQuizzes());
    }
    
    @Test
    public void testSetHomeworkScore() {
        gradeBook1.setHomeworkScore(2, 10);

        assertEquals(10, gradeBook1.getHomeworkScore(2));
    }
    
    @Test
    public void testSetQuizScore() {
        gradeBook1.setQuizScore(0, 6);

        assertEquals(6, gradeBook1.getQuizScore(0));
    }

    @Test
    public void testGetAverageHomeworkScore() {
        assertEquals(9, gradeBook1.getAverageHomeworkScore());
    }

    @Test
    public void testGetMinHomeworkScore() {
        assertEquals(8, gradeBook1.getMinHomeworkScore());
    }

    @Test
    public void testGetIndexOfHighestHomeworkScore() {
        gradeBook1.setHomeworkScore(2, 10);
        assertEquals(2, gradeBook1.getIndexOfHighestHomeworkScore());
    }

    @Test
    public void testIsHomeworkConsistentTrue() {
        assertEquals(true, gradeBook1.isHomeworkScoreConsistent(1));
    }

    @Test
    public void testIsHomeworkConsistentFalse() {
        assertEquals(false, gradeBook1.isHomeworkScoreConsistent(2));
    }

    @Test
    public void testHasDuplicateScoreTrue() {
        assertEquals(true, gradeBook1.hasDuplicateScore());
    }

    @Test
    public void testHasDuplicateScoreFalse() {
        gradeBook1.setHomeworkScore(1, 7);
        gradeBook1.setQuizScore(1, 11);

        assertEquals(false, gradeBook1.hasDuplicateScore());
    }

    @Test
    public void testMaxConsecutiveHWScoreDifference() {
        assertEquals(17, gradeBook2.findMaxConsecutiveHWScoreDifference());
    }


    @Test
    public void testQuizzesShowImprovementTrue() {
        GradeBook gradeBook3 = new GradeBook("l", 0, 7);
        gradeBook3.setQuizScore(0, 90);
        gradeBook3.setQuizScore(1, 80);
        gradeBook3.setQuizScore(2, 82);
        gradeBook3.setQuizScore(3, 68);
        gradeBook3.setQuizScore(4, 70);
        gradeBook3.setQuizScore(5, 75);
        gradeBook3.setQuizScore(6, 83);

        assertEquals(true, gradeBook3.quizzesShowImprovement(3));
    }

    @Test
    public void testQuizzesShowImprovementFalse() {
        GradeBook gradeBook3 = new GradeBook("l", 0, 7);
        gradeBook3.setQuizScore(0, 90);
        gradeBook3.setQuizScore(1, 80);
        gradeBook3.setQuizScore(2, 82);
        gradeBook3.setQuizScore(3, 68);
        gradeBook3.setQuizScore(4, 70);
        gradeBook3.setQuizScore(5, 75);
        gradeBook3.setQuizScore(6, 83);

        assertEquals(false, gradeBook3.quizzesShowImprovement(5));
    }

    @Test
    public void testHasMoreHWScoreIncreaseThanDecreaseTrue() {
        GradeBook gradeBook4 = new GradeBook("L", 8, 0);
        gradeBook4.setHomeworkScore(0, 75);
        gradeBook4.setHomeworkScore(1, 80);
        gradeBook4.setHomeworkScore(2, 97);
        gradeBook4.setHomeworkScore(3, 78);
        gradeBook4.setHomeworkScore(4, 88);
        gradeBook4.setHomeworkScore(5, 93);
        gradeBook4.setHomeworkScore(6, 93);
        gradeBook4.setHomeworkScore(7, 88);

        assertEquals(true, gradeBook4.hasMoreHWScoreIncreaseThanDecrease());
    }

    @Test
    public void testHasMoreHWScoreIncreaseThanDecreaseFalse() {
        GradeBook gradeBook5 = new GradeBook("L", 8, 0);
        gradeBook5.setHomeworkScore(0, 75);
        gradeBook5.setHomeworkScore(1, 70);
        gradeBook5.setHomeworkScore(2, 60);
        gradeBook5.setHomeworkScore(3, 78);
        gradeBook5.setHomeworkScore(4, 88);
        gradeBook5.setHomeworkScore(5, 93);
        gradeBook5.setHomeworkScore(6, 93);
        gradeBook5.setHomeworkScore(7, 88);

        assertEquals(false, gradeBook5.hasMoreHWScoreIncreaseThanDecrease());
    }

}