package comp132.examples.inheritance.cards;

/**
 * A deck of Pinochle cards. A Pinochle deck has 48 cards. 2 each of
 * 9,10,J,Q,K,A in 4 suits.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Sep 14, 2009
 */
public class PinochleDeck extends DeckOfCards {

    public PinochleDeck() {
        /*
         * ... Create the cards and add them to the deck.
         */
        Card c = new Card(9, "Clubs"); // 9 of clubs
        deck.add(c);
        
        // and so on...
    }
}
