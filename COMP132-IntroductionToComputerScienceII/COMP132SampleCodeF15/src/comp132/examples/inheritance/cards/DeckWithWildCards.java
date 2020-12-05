package comp132.examples.inheritance.cards;

/**
 * A standard deck of cards with two jokers tossed in as wild cards.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Sep 14, 2009
 */
public class DeckWithWildCards extends DeckOfCards {

    public DeckWithWildCards() {
        super();  // add all the normal cards.
        
        deck.add(new Card(0, "Joker"));
        deck.add(new Card(0, "Joker"));
    }
}
