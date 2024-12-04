
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author sishehba
 */
public class PlayGame extends Game {

    private static Scanner input= new Scanner(System.in);
    public static Deck freshDeck;

    public PlayGame(Players players, int playerCount) {
        super(players, playerCount);
    }

    public void dealCardstoPlayer() {
        //  Deal cards to players
            int cardsToDealt = (this.getPlayers().getPlayers().size() >= 4) ? 5 : 7;
            for (int i = 0; i < cardsToDealt; i++) {
                for (Player player : this.getPlayers().getPlayers()) {
                    PlayGame.freshDeck.dealCardToPlayer(player);
                }
            }
    }
    
    /**
     * Play the game. This might be one method or many method calls depending on
     * your game.
     */
    public void play() {
            this.dealCardstoPlayer();

            while(!PlayGame.freshDeck.showCards().isEmpty()){
                for (Player player : this.getPlayers().getPlayers()) {
                    ArrayList<Integer> otherPlayers;
                    System.out.println("================================");
                    System.out.println("Player " + player.getPlayerNumber() + " turn : Name - " + player.getPlayerName());
                    System.out.println("================================");     
                    player.printCardInHand();


    //              Select Player to ask from
                    while (!PlayGame.freshDeck.showCards().isEmpty()) {
                        otherPlayers = this.getPlayers().printOtherPlayersInfo(player);
                        System.out.println("Whom You want to ask? (Enter the Number only)");
                        try {
                            int playerIndex = input.nextInt();
                            input.nextLine(); // Consume the newline character


                            if (otherPlayers.contains(playerIndex)) {
                                Player playerToAsk = this.getPlayers().getPlayerByIndex(playerIndex);

                                System.out.println("You have selected Player " + playerIndex + "");
    //                          Ask for Card

                                System.out.println("");
                                System.out.println("Which Card You want to ask? Select a Rank");
                                System.out.println("=========================================");
                                System.out.println("Card Rank");
                                System.out.println("-----------------------------------------");
                                for(Rank rank : Rank.values()){
                                    System.out.println((rank.ordinal()+1) + ". " +rank);
                                }
                                System.out.println("=========================================");

                                try {
                                    int rank = input.nextInt();
                                    input.nextLine(); // Consume the newline character

    //                              Check if asked rank is present in own deck
                                    if(player.hasRank(rank)){
                                        List<Card> askedCards = playerToAsk.getRankAsked(rank);

                                        if (!askedCards.isEmpty()) {
                                            for(Card card : askedCards){
                                                player.addCardtoDeck(card);
                                            }

                                            System.out.println("You Got the below mentioned Cards. ");
                                            System.out.println(askedCards.toString());

                                            player.printCardInHand();

                                        } else {
                                            System.out.println("Player does not have the asked card!!");
                                            System.out.println("GO FISH!!");
                                            Card pickedCard = PlayGame.freshDeck.getTopCard();
                                            player.addCardtoDeck(pickedCard);
                                            System.out.println("One Card Picked :"+pickedCard.toString());
                                            System.out.println( PlayGame.freshDeck.showCards().size()+ " Cards left");
                                            if(PlayGame.freshDeck.showCards().isEmpty()){
                                                this.declareWinner();
                                                return;
                                            }
                                            
                                            break;
                                        }
                                    } else {
                                        System.out.println("Make sure you have the rank you are asking.");
                                    }

                                } catch (Exception e) {
                                    System.out.println("Invalid input. Please enter a number.");
                                    input.nextLine(); // Clear the invalid input
                                }

                            } else {
                                System.out.println("Invalid number of player. Please enter a number from the list.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a number.");
                            input.nextLine(); // Clear the invalid input
                        }
                    }
                    System.out.println("Sets completed: " + player.countFullSets());
                }
            }
            this.declareWinner();
        };

        /**
         * When the game is over, use this method to declare and display a winning
         * player.
         */
        public void declareWinner() {
            Player winner = this.getPlayers().getPlayers().get(0);
            for (Player player : this.getPlayers().getPlayers()) {
                if (player.countFullSets() > winner.countFullSets()) {
                    winner = player;
                }
            }
            System.out.println("The winner is " + winner.getPlayerName()+ " with " + winner.countFullSets() + " sets!");
   
        };
        
        
        public static void main(String[] args) {
//              System.out.println(PlayGame.freshDeck.showCards());
                Players players = new Players();
                int playerCount;
                String name;

//              Initialize the Deck of Cards 
                PlayGame.freshDeck = new Deck();
                PlayGame.freshDeck.generateNewDeck();

//              Shuffle the fresh Deck of Cards
                freshDeck.shuffle();

//              Start The game
                System.out.println("==============================");
                System.out.println("           GO FISH");
                System.out.println("==============================");

                while (true) {
                    System.out.println("");
                    System.out.println("Enter the numbers of players you want to play with. (2-6 players only)");

                    try {
                        playerCount = input.nextInt();
                        input.nextLine(); // Consume the newline character
                        if (playerCount >= 2 && playerCount <= 6) {
                            System.out.println("You have selected " + playerCount + " players.");
                            break; // Exit the loop if the input is valid
                        } else {
                            System.out.println("Invalid number of players. Please enter a number between 2 and 6.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number.");
                        input.nextLine(); // Clear the invalid input
                    }
                }

                // Ask for player names
                for (int i = 0; i < playerCount; i++) {
                    int playerNumber = i + 1;
                    while (true) {
                        System.out.println("");
                        System.out.println("Enter name for player " + playerNumber);
                        name = input.nextLine();
                        if (name.isEmpty()) {
                            System.out.println("Please enter your name");
                        } else {
                            MainPlayer player = new MainPlayer(name, playerNumber);
                            players.addPlayer(player);
                            break;
                        }
                    }
                }

                PlayGame game = new PlayGame(players, playerCount);
                game.play();
        }
}
