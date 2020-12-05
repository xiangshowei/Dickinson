package comp132.examples.files.samples;

import java.util.Random;

/**
 * Simple model of a Die that can be rolled.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version May 24, 2015
 */
public class Die {
	private int sides;
	private Random rnd;

	/**
	 * Construct a Die with a specified number of sides.
	 * 
	 * @param sides the number of sides for the Die.
	 * @throws IllegalArgumentException if the Die has less than 4 sides.
	 */
	public Die(int sides) {
		if (sides < 4) {
			throw new IllegalArgumentException(
					"Cannot have a die with < 4 sides.");
		}

		this.sides = sides;
		rnd = new Random();
	}

	/**
	 * Roll the Die.
	 * 
	 * @return a random number between 1 and the number of sides.
	 */
	public int roll() {
		return rnd.nextInt(sides) + 1;
	}
}
