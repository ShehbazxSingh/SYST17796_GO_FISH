/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * The class that models your game. You should create a more specific
 * child of this class and instantiate the methods given.
 * @author dancye, 2018
 */
public abstract class Game 
{

    private Players players;// the players of the game
    protected int playerCount;
    
    public Game(Players players, int playerCount)
    {
        this.players = players;
        this.playerCount = playerCount;
    }

    
     /**
     * @return the playerCount
     */
    public int getPlayerCount() 
    {
        return playerCount;
    }
    
     /**
     * @return the players of this game
     */
    public Players getPlayers() 
    {
        return this.players;
    }

    /**
     * @param players the players of this game
     */
    public void setPlayers(Players players) 
    {
        this.players = players;
    }
    
    /**
     * Play the game. This might be one method or many method calls depending
     * on your game.
     */
    public abstract void play();
    
    /**
     * When the game is over, use this method to declare and display a winning
     * player.
     */
    public abstract void declareWinner();

   
    
}//end class
