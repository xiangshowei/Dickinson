package gradeBook;

/**
 * The GradeBook class models the way an instructor might keep grades for a
 * student. A GradeBook will contain 2 arrays, one to hold the student's
 * homework scores and one to hold the student's quiz scores. Homework scores
 * and quiz scores are represented using integer values.
 * 
 * @author Xiang Wei
 * @version (12.11.13) 
 */

public class GradeBook {

    private String studentName;
    private int[] homeworks;
    private int[] quizzes;

    /**
     * Construct a new GradeBook for the specified student. You may assume that the
     * values provided for numHomeworks and numQuizzes are positive.
     * 
     * @param studentName  the name of the student
     * @param numHomeworks the number of homework assignments
     * @param numQuizzes   the number of quizzes
     */
    public GradeBook(String studentName, int numHomeworks, int numQuizzes) {
        this.studentName = studentName;
        homeworks = new int[numHomeworks];
        quizzes = new int[numQuizzes];
    }

    /**
     * Returns the student's name.
     * 
     * @return the student's name
     */

    public String getStudentName() {
        return studentName;
    }

    /**
     * Returns the number of homework scores a student has.
     * 
     * @return the number of homework assignments
     */

    public int getNumHomeworks() {
        return homeworks.length;
    }

    /**
     * Returns the number of quiz scores a student has.
     * 
     * @return the number of homework assignments
     */

    public int getNumQuizzes() {
        return quizzes.length;
    }

    /**
     * Sets the score for a particular homework assignment.
     * 
     * @param nthHomework the nth homework assignment
     * @param score       the score to be set
     */

    public void setHomeworkScore(int nthHomework, int score) {
        homeworks[nthHomework] = score;
    }

    /**
     * Sets the score for a particular quiz.
     * 
     * @param numQuizzes the position of the quiz
     * @param score      the score to be set
     */

    public void setQuizScore(int numQuizzes, int score) {
        quizzes[numQuizzes] = score;
    }

    /**
     * Returns the score for a particular homework assignment.
     * 
     * @param nthHomework the nth homework assignment
     * @return the score of the homework
     */

    public int getHomeworkScore(int nthHomework) {
        return homeworks[nthHomework];
    }

    /**
     * Retrieves the score for a particular quiz.
     * 
     * @param quizNumber the position of the quiz
     * @return the score of the quiz
     */

    public int getQuizScore(int nthQuiz) {
        return quizzes[nthQuiz];
    }

    /**
     * Returns an int value indicating the average of all of the homework scores.
     * 
     * Any fractional part of the average is truncated.
     * 
     * @return average the average score of homework
     */

    public int getAverageHomeworkScore() {
        int total = 0;
        for (int homeworkAssignmentScore : homeworks) {
            total += homeworkAssignmentScore;
        }

        return total / homeworks.length;
    }

    /**
     * Returns an int value indicating the lowest homework score.
     * 
     * @return min the lowest homework score
     */

    public int getMinHomeworkScore() {
        int min = homeworks[0];

        for (int hwScore : homeworks) {
            if (hwScore < min) {
                min = hwScore;
            }
        }

        return min;
    }

    /**
     * Returns an int value indicating the index of the highest homework score. If
     * there are ties for the highest homework score, this method returns the index
     * of the most recent homework with that score.
     * 
     * @return the index of the homework with the highest score
     */

    public int getIndexOfHighestHomeworkScore() {
        int index = 0;
        int maxHomeworkScore = homeworks[0];

        for (int i = index + 1; i < homeworks.length; i++) {
            if (homeworks[i] >= maxHomeworkScore) {
                index = i;
                maxHomeworkScore = homeworks[i];
            }
        }
        return index;
    }

    /**
     * This method takes one parameter that is an int value specifying the maximum
     * allowable difference of the homework scores from the average homework score.
     * 
     * This method then returns true if all of the homework scores differ from the
     * average by no more than the specified maximum allowable difference, and false
     * otherwise.
     * 
     * @param allowableScoreDifference the allowable difference compare with average
     *                                 score
     * @return boolean
     */

    public boolean isHomeworkScoreConsistent(int allowableScoreDifference) {
        boolean consistentHWScores = true;
        int hwAverage = getAverageHomeworkScore();

        for (int hwScore : homeworks) {
            if (!(Math.abs(hwScore - hwAverage) == allowableScoreDifference)) {
                consistentHWScores = false;
            }

            else {
                if (allowableScoreDifference == 0) {
                    consistentHWScores = true;
                }
            }

            if (consistentHWScores == false) {
                return consistentHWScores;
            }
        }

        return consistentHWScores;
    }

    /**
     * This method checks to see if the student received the same score on a
     * homework and a quiz. If any homework score is equal to any quiz score this
     * method returns true, otherwise it returns false.
     * 
     * @return boolean whether if there exists the same score for a homework
     *         assingment and a quiz
     */

    public boolean hasDuplicateScore() {
        for (int hwScore : homeworks) {
            for (int quizScore : quizzes) {
                if (hwScore == quizScore) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns an int value indicating the biggest positive difference in scores
     * between any two successive homework grades.
     * 
     * @return jump the biggest jump
     */

    public int findMaxConsecutiveHWScoreDifference() {
        int maxConsecutiveHWScoreDifference = 0;

        for (int i = 0; i < homeworks.length - 1; i++) {
            int scoreDifference = homeworks[i + 1] - homeworks[i];
            if (scoreDifference > maxConsecutiveHWScoreDifference) {
                maxConsecutiveHWScoreDifference = scoreDifference;
            }
        }

        return maxConsecutiveHWScoreDifference;
    }

    /**
     * This method checks to see if the student's quiz scores are improving towards
     * the end.
     * 
     * @param targetNumQuizScoreImprovements the number of improvements in quiz
     *                                       score
     * @return boolean
     */

    public boolean quizzesShowImprovement(int targetNumQuizScoreImprovements) {
        boolean hasShownEnoughImprovement = false;
        int actualNumQuizScoreImprovements = 0;

        for (int i = quizzes.length - 1; i >= 1; i--) {
            if (quizzes[i] > quizzes[i - 1]) {
                actualNumQuizScoreImprovements++;
            }

            if (actualNumQuizScoreImprovements == targetNumQuizScoreImprovements) {
                return true;
            }
        }

        return hasShownEnoughImprovement;
    }

    /**
     * Returns true if there are more increases in the homework scores than there
     * are decreases and false otherwise.
     * 
     * @return boolean
     */

    public boolean hasMoreHWScoreIncreaseThanDecrease() {
        int numIncreases = 0;
        int numDecreases = 0;

        for (int i = 0; i < homeworks.length - 1; i++) {
            if (homeworks[i + 1] > homeworks[i]) {
                numIncreases += 1;
            }
            if (homeworks[i + 1] < homeworks[i]) {
                numDecreases += 1;
            }
        } 

        return numIncreases > numDecreases;
    }
}
