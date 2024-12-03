/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game.
 * HINT, you might want to subclass this more than once.
 * The group of cards has a maximum size attribute which is flexible for reuse.
 * @author dancye
 */
public class Deck 
{
   
    //The group of cards, stored in an ArrayList
    private ArrayList <Card> cards;
    
    /**
     * A method that will get the group of cards as an ArrayList
     * @return the group of cards.
     */
    public ArrayList<Card> showCards()
    {
        return cards;
    }
    
    public void shuffle()
    {
        Collections.shuffle(cards);
    }

	public void getTopCard() {
		// TODO - implement Deck.getTopCard
		throw new UnsupportedOperationException();
	}

	public void updateDeck() {
		// TODO - implement Deck.updateDeck
		throw new UnsupportedOperationException();
	}
    
}//end class
