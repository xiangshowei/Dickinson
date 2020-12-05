package comp132.examples.inheritance.cards;

import java.util.*;

/**
 * A standard deck of 52 cards A,2,3,...,K in four suits and methods for
 * manipulating the cards (e.g. shuffle / draw). Note: much of the
 * implementation details of the methods are not necessary for this example and
 * thus have been omitted.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Sep 14, 2009
 */
public class DeckOfCards {

    /*
     * Use a protected field here so that sub-classes of Card (e.g.
     * PinochleDeck, DoubleDeck, etc...) that need to directly manipulate the
     * ArrayList of Cards can do so.
     */
    protected ArrayList<Card> deck;

    public DeckOfCards() {
        // ... Create the cards and add them to the deck.
        Card c = new Card(2, "Clubs"); // 2 of clubs
        deck.add(c);

        // and so on...
    }

    public void Shuffle() {
        // ... Shuffle the cards ...
    }

    public void cutDeck(int cutPiont) {
        // ... cut the deck ...
    }
    
    public Card drawCard() {
        Card c = null;
        // ... draw a card ...
        return c;
    }
}
