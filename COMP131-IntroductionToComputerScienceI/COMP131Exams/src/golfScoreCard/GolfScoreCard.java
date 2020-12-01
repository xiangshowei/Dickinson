package golfScoreCard;

/**
 * A class representing a golf score card. It will keep track of the scores for
 * a number of holes of golf.
 * 
 * @author Xiang Wei
 * @version 7/23/2016
 */

public class GolfScoreCard {
	private String playerName;
	private int[] scoreCard;
	private int totalScore;

	/**
	 * Construct a new GolfScoreCard. For our purposes, we'll assume that the holes
	 * are numbered starting with 0.
	 * 
	 * @param playerName the player's name.
	 * @param numHoles   the number of holes to keeps score for.
	 */
	public GolfScoreCard(String playerName, int numHoles) {
		this.playerName = playerName;
		scoreCard = new int[numHoles];
		totalScore = 0;
	}

	/**
	 * Get the player's name.
	 * 
	 * @return the player's name.
	 */
	public String getPlayersName() {
		return playerName;
	}

	/**
	 * Get the number of holes on this score card.
	 * 
	 * @return the number of holes for this score card.
	 */
	public int getNumHoles() {
		return scoreCard.length;
	}

	/**
	 * Set the player's score for the specified hole.
	 * 
	 * Only positive scores are valid in golf.
	 * 
	 * @param hole     the hole for which to set the score.
	 * @param newScore the player's score on the hole.
	 */

	public void setHoleScore(int hole, int newScore) {
		if (newScore > 0) {
			int currentScore = getHoleScore(hole);
			totalScore += Math.abs(newScore - currentScore);
			scoreCard[hole] = newScore;
		}
	}

	/**
	 * Get the player's score for the specified hole. If the hole is not valid then
	 * this method returns -1.
	 * 
	 * @param hole the hole for which to get the player's score.
	 * @return the player's score for the hole.
	 */
	public int getHoleScore(int hole) {
		if (hole < 0) {
			return -1;
		}

		else {
			return scoreCard[hole];
		}
	}

	/**
	 * Compute and return the player's total score on all of the holes.
	 * 
	 * @return the player's total score.
	 */
	public int getTotalScore() {
		return totalScore;
	}

	/**
	 * Return true if the player has an ace (i.e. a hole in 1) on this score card.
	 * An ace is indicated by a score of 1 on a hole.
	 * 
	 * @return true if the player has an ace and false otherwise.
	 */
	public boolean hasAce() {
		boolean hasAce = false;

		for (int score : scoreCard) {
			if (score == 1) {
				return true;
			}
		}
		return hasAce;
	}

	/**
	 * Determine if this player is good enough to play with professional golfers. In
	 * order to be good enough to play with Tiger, the player's may have no score
	 * larger than a 5 and can have at most 3 scores of 5.
	 * 
	 * @return true if the player is good enough to play with Tiger and false
	 *         otherwise.
	 */
	public boolean canPlayWithProfessionals() {
		boolean pro = false;

		if (scoreCard.length < 3 && getTotalScore() <= 5) {
			pro = true;
		}

		return pro;
	}

	/**
	 * Get the hole on which the player had his/her highest score. If there is a tie
	 * for the highest score, then the hole with the lower number should be
	 * returned.
	 * 
	 * @return the index of a hole with the highest score.
	 */
	public int getIndexOfMaxScore() {
		int indexOfMaxScore = 0;
		int maxScore = scoreCard[indexOfMaxScore];

		for (int i = indexOfMaxScore + 1; i < scoreCard.length; i++) {
			int curScoreAtHole = scoreCard[i];

			if (curScoreAtHole >= maxScore) {
				indexOfMaxScore = i;
				maxScore = scoreCard[i];
			}
		}

		return indexOfMaxScore;
	}

	/**
	 * One method of leveling the playing field for players of different ability is
	 * to use a handicap system. Each player is assigned a handicap, with higher
	 * handicaps going to weaker players. A player's score card can then be modified
	 * based on their handicap after a round to make weaker players better relative
	 * to stronger players.
	 * 
	 * One technique for applying handicap works as follows. The player's handicap
	 * represents a number of strokes to be removed from a player's score. Each
	 * stroke of the handicap reduces the player's highest score by one stroke. For
	 * example: if a player has the scores 4, 7, 3, 8, 4 and a handicap of 4 then
	 * the scores would become 4, 5, 3, 6, 4. First the 8 is reduced to a 7, then
	 * one of the 7's is reduced to a 6, then the remaining 7 is reduced to a 6 and
	 * finally, one of the 6's is reduced to a 5. When there are multiple instances
	 * of the maximum score, any one of them may be reduced.
	 * 
	 * You may assume that the player's handicap is always positive and that the
	 * player's total score is always larger than their handicap.
	 */
	public void adjustScoresForHandicap(int handicap) {
		int i = 0;

		while (i < handicap) {
			int indexofMaxScore = getIndexOfMaxScore();
			if (!(scoreCard[indexofMaxScore] - 1 < 1)) {
				scoreCard[indexofMaxScore] -= 1;
			}
			i++;
		}
	}

	/**
	 * This method is for those who are not good at golf but have a need to appear
	 * good at golf that exceeds the strength of their moral fiber. It creates and
	 * returns a new GolfScoreCard object with every score reduced by the specified
	 * number of strokes. The one caveat is that if reducing a score by the
	 * specified number of strokes would make it 3 or less, then that score is not
	 * changed. For example, if the player has the scores 5, 6, 9, 2, 7 and they
	 * were going to cheat by 2 strokes, the new GolfScoreCard would contain the
	 * scores: 5, 4, 7, 2, 5
	 * 
	 * Note: The new GolfScoreCard should have the same player's name and the same
	 * number of holes as the current GolfScoreCard. Also, you may assume that the
	 * current GolfScoreCard contains at least 1 hole.
	 * 
	 * @return a new GolfScoreCard with the cheater's scores.
	 */
	public GolfScoreCard cheat(int strokes) {
		GolfScoreCard newCard = new GolfScoreCard(playerName, scoreCard.length);

		for (int i = 0; i < scoreCard.length; i++) {
			int curHoleScore = scoreCard[i];

			if (!((curHoleScore - strokes) <= 3)) {
				newCard.setHoleScore(i, curHoleScore - strokes);
			}

			else {
				newCard.setHoleScore(i, curHoleScore);
			}
		}

		return newCard;
	}
}