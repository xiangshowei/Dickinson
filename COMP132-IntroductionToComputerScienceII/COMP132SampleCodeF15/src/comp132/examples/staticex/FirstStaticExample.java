package comp132.examples.staticex;

/**
 * A first look at (non-final) static fields and static methods. This class does
 * not show any practical use, but illustrates the mechanics of how such fields
 * and methods behave.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version May 20, 2015
 */
public class FirstStaticExample {

	private static int x = 1; // a static field.
	private int y; // an instance field.

	public FirstStaticExample() {
		y = 5;
	}

	public void doStuff(int dx, int dy) {
		x = x + dx;
		y = y + dy;   // or this.y = this.y + dy;
	}

	public static void messWithX(int dx) {
		x = x - dx;
		// static means we don't have "this" (i.e. no object)!
		// so we can't access y.
	}

	public String toString() {
		return "x = " + x + " : y = " + y;
	}

	/**
	 * Some statements to illustrate the difference between static and instance
	 * fields.
	 */
	public static void main(String[] args) {
		FirstStaticExample fse1 = new FirstStaticExample();
		FirstStaticExample fse2 = new FirstStaticExample();

		fse1.doStuff(3, 4);
		System.out.println(fse1.toString());
		System.out.println(fse2);  // same as fse2.toString();

		fse2.doStuff(4, 5);
		System.out.println(fse1);
		System.out.println(fse2);

		FirstStaticExample.messWithX(2);  // Use class name for static method
		System.out.println(fse1);
		System.out.println(fse2);
	}
}
