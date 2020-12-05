package comp132.examples.files.old;

/**
 * A Little demo of using ASCII codes to build a string.
 * 
 * NOTE: Printing control characters (e.g. bell, backspace) works
 * when run from terminal but not when run within eclipse.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version March 16, 2015
 */
public class ASCII {
	public static void main(String[] args) {
		byte[] ascii = new byte[5];
		ascii[0] = 65;
		ascii[1] = 83;
		ascii[2] = 67;
		ascii[3] = 73;
		ascii[4] = 73;
		
		String s = new String(ascii);
		System.out.println(s);
	}
}
