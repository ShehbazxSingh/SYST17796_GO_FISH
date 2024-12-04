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
     * 
     * @param rank
     * @param suit
     */
    public Deck() {
            this.generateNewDeck();
    }

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

    public Card getTopCard() {
            return cards.removeFirst();
    }

    public void updateDeck() {
            // TODO - implement Deck.updateDeck
            throw new UnsupportedOperationException();
    }
    
    public void generateNewDeck() {
        this.cards = new ArrayList<>();
        for(int i = 0; i < Suit.values().length; i++){
            for(int j = 0; j< Rank.values().length; j++ ){
                Card newCard = new Card(Rank.values()[j],Suit.values()[i]);
                this.cards.addLast(newCard);
            }
        }
    }
    
    public void dealCardToPlayer(Player player) {
        player.addCardtoDeck(this.getTopCard());
    }
    
}//end class
