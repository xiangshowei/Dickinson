package comp132.examples.interfaces;

import java.util.Random;

/**
 * A collection of sample code that illustrates different aspects of the use of
 * interfaces in Java.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 20, 2009
 */
public class InterfaceExamples {

	/**
	 * This method illustrates the use of objects that implement an interface
	 * via a reference of their own class type. Methods in the interface are, by
	 * definition, in the class so they can be invoked using a reference of the
	 * class type.
	 */
	public static void UsingObjectTypes() {
		Car c = new Car("Ford", 5);
		System.out.println("Car:");
		System.out.println(c.getMake());
		c.makeSound();
		System.out.println(c.howLoud());

		Duck d = new Duck("Mallard");
		System.out.println("Duck:");
		System.out.println(d.getSpecies());
		d.makeSound();
		System.out.println(d.howLoud());
	}

	/**
	 * This method illustrates the use of objects that implement an interface
	 * via a reference of their interface type.
	 */
	public static void UsingInterfaceTypes() {
		// We can make an interface type reference directly to a new object.
		MakesSound ms1 = new Dolphin(12);

		Car c = new Car("Ford", 5);
		// We can also set an interface type reference to an existing
		// object that implements the interface.
		MakesSound ms2 = c;

		/*
		 * We can invoke the makeSound and howLoud methods using a reference of
		 * type MakesSound because those methods are defined in the MakesSound
		 * interface. When we do so, each object uses its own unique
		 * implementation to decide what to do.
		 */
		System.out.println("Using ms1 (a Dolphin)");
		ms1.makeSound();
		System.out.println(ms1.howLoud());

		System.out.println("Using ms2 (a Car)");
		ms2.makeSound();
		System.out.println(ms2.howLoud());

		/*
		 * The following line is illegal because while the getMake method is in
		 * the Car class it is not part of the MakesSound interface.
		 */
		// System.out.println(ms2.getMake());
	}

	/**
	 * This method illustrates that with Polymorphic behavior the same line of
	 * code may produce different behaviors at different time, and we may even
	 * be unable to predict what will happen in advance.
	 */
	public static void randomObjectExample() {

		for (int i = 0; i < 10; i++) {
			MakesSound ms;
			if (Math.random() > 0.5) {
				ms = new Duck("Cayuga");
			} else {
				ms = new Car("Honda", 3);
			}

			ms.makeSound(); // What's the output???
		}
	}

	/**
	 * This method accepts two objects that implement the MakesSound method and
	 * returns whichever object makes the louder sound.
	 * 
	 * @param obj1 an object that implements MakesSound
	 * @param obj2 another object that implements MakesSound
	 * @return the object that makes the louder sound.
	 */
	public static MakesSound whoIsLouder(MakesSound obj1, MakesSound obj2) {
		if (obj1.howLoud() >= obj2.howLoud()) {
			return obj1;
		} else {
			return obj2;
		}
	}

	/**
	 * Call the whoIsLouder method with objects of different types and print out
	 * the sound made by whichever object is louder.
	 */
	public static void printLouderSound() {
		/*
		 * Call whoIsLouder with obj1 being a Duck and obj2 being a Car.
		 */
		Duck d1 = new Duck("Mallard");
		Car c1 = new Car("Ford", 5);
		MakesSound ms1 = whoIsLouder(d1, c1);
		ms1.makeSound();

		/*
		 * Call whoIsLouder with obj1 being a Dolphin and obj2 being a Duck.
		 */
		Dolphin do1 = new Dolphin(12);
		Duck d2 = new Duck("Wood");
		MakesSound ms2 = whoIsLouder(do1, d2);
		ms2.makeSound();

		/*
		 * The following is illegal because the class String does not implement
		 * the MakesSound interface.
		 */
		// String p1 = "bob";
		// MakesSound ms3 = whoIsLouder(p1,d1);
	}

	/**
	 * Call the whoIsLouder method with objects of different types and print out
	 * information about whichever object is louder (e.g. the make of a Car, or
	 * the species of a Duck).
	 */
	public static void printLouderInfo() {
		Duck d1 = new Duck("Bufflehead");
		Random rnd = new Random();
		Car c1 = new Car("Porche", rnd.nextInt(10));

		/*
		 * We get back an object that implements MakesSound but we don't know if
		 * it is a Car object or a Duck object.
		 */
		MakesSound ms = whoIsLouder(d1, c1);

		/*
		 * Use instanceOf to determine the type of the object. Once we know the
		 * type of the object we can type cast it, which will allow us to use
		 * the object specific operations not in the interface.
		 */
		if (ms instanceof Car) {
			Car c2 = (Car) ms;
			System.out.println("The " + c2.getMake() + " is louder.");
		} else if (ms instanceof Duck) {
			Duck d2 = (Duck) ms;
			System.out.println("The " + d2.getSpecies() + " is louder.");
		} else {
			System.out.println("Humm, neither a Car nor a Duck!");
		}
	}

	/**
	 * Main method that allows each of the example methods to be run.
	 * 
	 * @param args none
	 */
	public static void main(String[] args) {
		System.out.println("usingObjectTypes:");
		UsingObjectTypes();

		System.out.println();
		System.out.println("usingInterfaceTypes:");
		UsingInterfaceTypes();

		System.out.println();
		System.out.println("randomObjectExample:");
		randomObjectExample();

		System.out.println();
		System.out.println("printLouderSound:");
		printLouderSound();

		System.out.println();
		System.out.println("printLouderInfo:");
		printLouderInfo();
	}
}
