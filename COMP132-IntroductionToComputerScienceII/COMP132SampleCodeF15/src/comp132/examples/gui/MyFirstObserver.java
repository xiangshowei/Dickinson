package comp132.examples.gui;

import java.util.*;

/**
 * Demonstration of the Observer/Observable relationship. The MyFirstObserver
 * class plays the role of the GUI (View/Controller) in the MVC pattern and the
 * MyFirstObservable plays the role of the Model. The Observer is registered
 * with the Observable. Then any time the Observable notifies its Observers the
 * update method in the Observers is invoked.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Sep 24, 2009
 */
public class MyFirstObserver implements Observer {

	/**
	 * Creates a MyFirstObserver and a MyFirstObservable. The MyFirstObserver is
	 * registered as an observer on the MyFirstObservable. A method that changes
	 * the MyFirstObservable and notifies its observers is invoked.
	 * 
	 * @param args none
	 */
	public static void main(String[] args) {
		MyFirstObserver mfoer = new MyFirstObserver();
		MyFirstObservable mfoable = new MyFirstObservable();

		/*
		 * Establish the mfoer object as an observer of the mfoable object.
		 * Thus, the update method in mfoer will be invoked any time the
		 * observers of mfoable are notified.
		 */
		mfoable.addObserver(mfoer);

		mfoable.changeMe();
	}

	/*
	 * This method is invoked any time the object that we are observing notifies
	 * its observers. The Observable object is a reference to the object that
	 * notified us. Arg is a parameter that can be passed from the observable to
	 * give information about what has changed. It is very often unused and has
	 * the value null.
	 */
	public void update(Observable o, Object arg) {
		System.out.println("MyObserver was notified!");
	}
}

/*
 * The Model will be Observable so that it can notify the GUI when a change
 * occurs.
 */
class MyFirstObservable extends Observable {
	public void changeMe() {
		System.out.println("MyFirstObservable is being changed.");

		/*
		 * Mark the object as having been changed and notify all of the
		 * registered observers. Pass an argument to notifyObservers and the
		 * Observer will receive it as arg.
		 */
		setChanged();
		notifyObservers();
	}
}
