package comp132.examples.exceptions;

/**
 * An example that illustrates that a Checked Exception in Java can be either
 * propagated with declaration or enclosed in a try/catch.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version May 24, 2015
 */
public class CheckedExceptionExample {

	/**
	 * The main method calls methodA which can throw a Checked Exception. Thus,
	 * the main method must either enclose that code in a try/catch or declare
	 * that it also might also throw the exception. In this case, the code is
	 * enclosed in a try/catch. The code in methodA illustrates how a method can
	 * allow a Checked Exception to propagate.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			methodA();
		}
		catch(InterruptedException e) {
			System.out.println("Darn... I need my beauty rest.");
		}
	}

	/**
	 * Because methodA contains code that throws a Checked Exception. This
	 * method does not handle the exception and instead lets it propagate.
	 * However, because the exception being thrown is a Checked Exception the
	 * method must declare that the method throws the exception in the method
	 * signature.
	 */
	public static void methodA() throws InterruptedException {

		System.out.println("Yawn... I'm a bit tired.");
		/*
		 * The Thread.sleep method causes the program to pause for the specified
		 * number of ms. This method throws an InterruptedException which is a
		 * checked exception.
		 */
		Thread.sleep(5000);

		System.out.println("Ahhh... that's better.");
	}
}
