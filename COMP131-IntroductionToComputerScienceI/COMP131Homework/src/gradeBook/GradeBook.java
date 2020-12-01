package gradeBook;

/**
 * The GradeBook class models the way an instructor might keep grades for a student. 
 * A GradeBook will contain 2 arrays, one to hold the student's
 * homework scores and one to hold the student's quiz scores. 
 * 
 * Homework scores and quiz scores are represented using integer values.
 * 
 * @author Xiang Wei
 * @version (12.11.13)
 */

public class GradeBook {

    private String studentName;
    private int[] homeworkScores;
    private int[] quizScores;

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
        homeworkScores = new int[numHomeworks];
        quizScores = new int[numQuizzes];
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
        return homeworkScores.length;
    }

    /**
     * Returns the number of quiz scores a student has.
     * 
     * @return the number of homework assignments
     */

    public int getNumQuizzes() {
        return quizScores.length;
    }

    /**
     * Sets the score for a particular homework assignment.
     * 
     * @param nthHomework the nth homework assignment
     * @param score       the homework score to be set
     */

    public void setHomeworkScore(int nthHomework, int score) {
        homeworkScores[nthHomework] = score;
    }

    /**
     * Sets the score for a particular quiz.
     * 
     * @param nthQuiz the nth quiz
     * @param score   the quiz score to be set
     */

    public void setQuizScore(int nthQuiz, int score) {
        quizScores[nthQuiz] = score;
    }

    /**
     * Returns the score for a particular homework assignment.
     * 
     * @param nthHomework the nth homework assignment
     * @return the score of the nth homework
     */

    public int getHomeworkScore(int nthHomework) {
        return homeworkScores[nthHomework];
    }

    /**
     * Retrieves the score for a particular quiz.
     * 
     * @param quizNumber the position of the quiz
     * @return the score of the nth quiz
     */

    public int getQuizScore(int nthQuiz) {
        return quizScores[nthQuiz];
    }

    /**
     * Returns an int value indicating the average of all of the homework scores.
     * 
     * Decimals are truncated.
     * 
     * @return the average score of all homework assignments
     */

    public int getAverageHomeworkScore() {
        int total = 0;
        for (int homeworkAssignmentScore : homeworkScores) {
            total += homeworkAssignmentScore;
        }

        return total / homeworkScores.length;
    }

    /**
     * Returns an int value indicating the lowest homework score.
     * 
     * @return the lowest homework score
     */

    public int getMinHomeworkScore() {
        int min = homeworkScores[0];

        for (int hwScore : homeworkScores) {
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
     * @return the index of the homework with the highest homework score
     */

    public int getIndexOfHighestHomeworkScore() {
        int index = 0;
        int maxHomeworkScore = homeworkScores[0];

        for (int i = index + 1; i < homeworkScores.length; i++) {
            if (homeworkScores[i] >= maxHomeworkScore) {
                index = i;
                maxHomeworkScore = homeworkScores[i];
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
     * @param allowableScoreDifference the allowable difference compare with average score
     * @return whether the average homework score is within the specified score difference 
     */

    public boolean isHomeworkScoreConsistent(int allowableScoreDifference) {
        boolean consistentHWScores = true;
        int hwAverage = getAverageHomeworkScore();

        for (int hwScore : homeworkScores) {
            if(hwScore > hwAverage) {
                if (!((hwScore - hwAverage) <= allowableScoreDifference)) {
                    consistentHWScores = false;
                }
            }

            else if (hwScore < hwAverage ) {
                if (!((hwAverage - hwScore) >= allowableScoreDifference)) {
                    consistentHWScores = false;
                }
            }

            else {// current homework score is the same as the average homework score
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
     * @return whether there exists the same score for a homework assingment and a quiz
     */

    public boolean hasDuplicateScore() {
        for (int hwScore : homeworkScores) {
            for (int quizScore : quizScores) {
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
     * @return the value indicating the biggest positive difference between any two succesive scores
     */

    public int findMaxConsecutiveHWScoreDifference() {
        int maxConsecutiveHWScoreDifference = 0;

        for (int i = 0; i < homeworkScores.length - 1; i++) {
            int scoreDifference = homeworkScores[i + 1] - homeworkScores[i];
            if (scoreDifference > maxConsecutiveHWScoreDifference) {
                maxConsecutiveHWScoreDifference = scoreDifference;
            }
        }

        return maxConsecutiveHWScoreDifference;
    }

    /**
     * This method checks to see if the student's quiz scores are improving towards the end.
     * 
     * @param targetNumQuizScoreImprovements the number of improvements in quiz score
     * @return whether the student has higher scores in later quizzes
     */

    public boolean hasShownQuizScoreImprovement(int targetNumQuizScoreImprovements) {
        boolean hasShownEnoughImprovement = false;
        int actualNumQuizScoreImprovements = 0;

        for (int i = quizScores.length - 1; i >= 1; i--) {
            if (quizScores[i] > quizScores[i - 1]) {
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
     * @return whether there are more increased homework scores between consecutive homework assignments
     * than that of decreased homework scores
     */

    public boolean hasMoreHWScoreIncreaseThanDecrease() {
        int numIncreases = 0;
        int numDecreases = 0;

        for (int i = 0; i < homeworkScores.length - 1; i++) {
            if (homeworkScores[i + 1] > homeworkScores[i]) {
                numIncreases += 1;
            }
            if (homeworkScores[i + 1] < homeworkScores[i]) {
                numDecreases += 1;
            }
        }

        return numIncreases > numDecreases;
    }
}
