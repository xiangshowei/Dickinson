package comp132.examples.exceptions.die;

import java.util.*;

/**
 * A sample program that illustrates Java's try/catch blocks for handling
 * exceptions.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version May 24, 2015
 */
public class RollSimulator {

	/**
	 * Read a number of rolls and a number of sides from the user. Then roll a
	 * die with the specified number of sides the specified number of times.
	 * 
	 * @param args none.
	 */
	public static void main(String[] args) {
		Scanner scr = new Scanner(System.in);

		System.out.print("How many rolls: ");
		String rollsStr = scr.nextLine();
		System.out.print("How many sides on die: ");
		String sidesStr = scr.nextLine();

		try {
			// These lines may generate a NumberFormatException.
			int rolls = Integer.parseInt(rollsStr);
			int sides = Integer.parseInt(sidesStr);

			// This line may generate an IllegalArgument Exception.
			Die d = new Die(sides);

			for (int r = 0; r < rolls; r++) {
				System.out.println(r + ": " + d.roll());
			}
		} catch (NumberFormatException e) {
			/*
			 * The code in this catch block will execute if either of the calls
			 * to parseInt above generate a NumberFormatException.
			 */
			System.out.println("The rolls (" + rollsStr + ") or "
					+ "the die sides (" + sidesStr + ") is not a valid int.");
		} catch (IllegalArgumentException e) {
			/*
			 * The code in this catch block will execute if the call to the Die
			 * constructor above generates an IllegalArgumentException.
			 */
			System.out.println(e.getMessage());
		}

		scr.close();
	}
}
