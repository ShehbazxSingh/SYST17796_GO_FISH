/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 * @author dancye, 2018
 */
public abstract class Player 
{
    private String playerName;
    private int playerNumber;
    private ArrayList <Card> deckInHand; //the unique ID for this player
    
    /**
     * A constructor that allows you to set the player's unique ID
     * @param name the unique ID to assign to this player. 
     */
    public Player(String name, int playerNumber)
    {
        this.playerNumber = playerNumber;
        playerName= name;
        this.deckInHand = new ArrayList();
        
    }
    public List<Card> getRankAsked(int rank){
//        Convert int to rank
        Rank askedRank = Rank.values()[rank-1];
        List<Card> matchedCards = this.deckInHand.stream().filter(card -> card.getRank() == askedRank).collect(Collectors.toList());
        this.deckInHand.removeAll(matchedCards);
        
        return matchedCards;
    }
    
    public int getPlayerNumber (){
        return this.playerNumber;
    }
     
    public String getPlayerName (){
        return this.playerName;
    }
    
    public void addCardtoDeck(Card card) {
        this.deckInHand.addLast(card);
        this.sortCardsByRank();
    }
    
    public void setDeckInHand(ArrayList <Card> deck) {
        this.deckInHand = deck;
    }
    
    public ArrayList <Card> getDeckInHand() {
        return deckInHand;
    }
    
    public void sortCardsByRank() {
        // Sort the cards in hand by their rank
        Collections.sort(this.deckInHand, (card1, card2) -> card1.getRank().compareTo(card2.getRank()));
    }
    /**
     * The method to be instantiated when you subclass the Player class
     * with your specific type of Player and filled in with logic to play your game.
     */
    public abstract void play();
    
    public void printCardInHand() {
        System.out.println("=========================================");
        System.out.println("Yours Cards in Hand");
        System.out.println("-----------------------------------------");
        for (Card card : this.getDeckInHand()) {
            System.out.println(card.toString());
        }
        System.out.println("=========================================");
        System.out.println("");
    }
    
    public boolean hasRank(int rank) {
        Rank askedRank = Rank.values()[rank - 1];
        return this.deckInHand.stream().anyMatch(card -> card.getRank() == askedRank);
    }
     
    public int countFullSets() {
        Map<Rank, List<Card>> rankGroups = this.deckInHand.stream()
                .collect(Collectors.groupingBy(Card::getRank));

        int completeSetCount = 0;
        for (Map.Entry<Rank, List<Card>> entry : rankGroups.entrySet()) {
            if (entry.getValue().size() == 4) {
                completeSetCount++;
            }
        }
        return completeSetCount;
    }
}