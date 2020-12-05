package comp132.examples.inheritance;

import java.util.ArrayList;

/**
 * This class demonstrates some of the implications of and effects caused by
 * having all classes in Java implicitly extend Object.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version May 22, 2015
 */

/**
 * The One class inherits the default implementations of toString and equals
 * from the Object class.
 */
class One {
	private String s;

	public One(String s) {
		this.s = s;
	}
}

/**
 * The Two class overrides the toString and the equals methods that it inherited
 * from Object.
 */
class Two {
	private String s;

	public Two(String s) {
		this.s = s;
	}

	public String toString() {
		return s;
	}

	public boolean equals(Object other) {
		String otherS = ((Two)other).s;
		return s.equals(otherS);
	}
}

public class ExtendingObject {

	/**
	 * Demonstrates that println automatically invokes the toString method on an
	 * object. This can be done because every class inherits that method from
	 * Object. The default implementation simply returns the object reference.
	 * Class Two overrides toString to return a more meaningful representation
	 * of the object.
	 */
	public static void toStringExample() {
		One one = new One("I'm a One.");

		// The toString inherited from Object simply returns the object
		// reference.
		System.out.println("one: " + one);
		System.out.println("one: " + one.toString());

		Two two = new Two("I'm a Two.");
		
		// Class Two overrides the inherited toString method.
		System.out.println("two: " + two);
		System.out.println("two: " + two.toString());
	}

	/**
	 * Demonstrates inherited and overridden equals methods. The equals method
	 * inherited from Object by class One compares object references, checking
	 * if the two references refer to the exact same object. The overridden
	 * equals method defined in class Two compares the values in the object's
	 * fields to see if they are the same. Thus, two different objects with the
	 * same values can be considered equal.
	 */
	public static void equalsExample() {
		One one = new One("I'm a One.");
		One uno = new One("I'm a One");
		One eins = one;

		// The equals inherited from Object compares object references.
		System.out.println("one.equals(uno):  " + one.equals(uno));
		System.out.println("one.equals(eins): " + one.equals(eins));
		
		Two two = new Two("I'm a Two.");
		Two dos = new Two("I'm a Two.");
		Two zwei = two;
		Two duex = new Two("I'm a duex.");

		// The overridden equals in Two compares the strings.
		System.out.println("two.equals(dos):  " + two.equals(dos));
		System.out.println("two.equals(zwei): " + two.equals(zwei));
		System.out.println("two.equals(duex): " + two.equals(duex));
	}

	/**
	 * Because Java knows all objects will have an equals method it can make use
	 * of that to define reusable algorithms. For example the ArrayList class
	 * has methods contains, indexOf, lastIndexOf and remove that invoke equals
	 * in their operation. This allows these methods to operate on any type of
	 * object.
	 */
	public static void equalsUseByJavaExample() {
		ArrayList<Two> twoList = new ArrayList<Two>();
		twoList.add(new Two("first"));
		twoList.add(new Two("second"));
		twoList.add(new Two("third"));
		
		// contains method uses equals to check the list for an "equal" object.
		System.out.println("contains first: " + twoList.contains(new Two("first")));
		System.out.println("contains second: " + twoList.contains(new Two("second")));
		System.out.println("contains fourth: " + twoList.contains(new Two("fourth")));
		
		// indexOf also use equals to find the index of the first "equal" object.
		System.out.println("indexOf third: 	" + twoList.indexOf(new Two("third")));
		System.out.println("indexOf fourth: " + twoList.indexOf(new Two("fourth")));
		
		// remove also uses equals to find the "equal" object to be removed.
		System.out.println("contains third: " + twoList.contains(new Two("third")));
		twoList.remove(new Two("third"));
		System.out.println("contains third: " + twoList.contains(new Two("third")));	
	}

	/**
	 * Invokes the methods in this class to demonstrate the toString and
	 * equals methods.
	 */
	public static void main(String[] args) {

		System.out.println("toStringExample():");
		toStringExample();

		System.out.println();
		System.out.println("equalsExample():");
		equalsExample();
		
		System.out.println();
		System.out.println("equalsUseByJavaExample():");
		equalsUseByJavaExample();
	}
}
