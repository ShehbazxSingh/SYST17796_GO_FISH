package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Optional;

public class Players {

	private ArrayList <Player> players;
        
        public Players() {
            this.players = new ArrayList<>();
        }
 
        public Player getPlayerByIndex(int index) {
            Player filtered = players.stream().filter(
                    player -> player.getPlayerNumber() == index
                    )
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No player found with this id: "));
            
            return filtered;
        }
	public void addPlayer(Player player) {
            this.players.addLast(player);
            System.out.println(player.getPlayerName()+ "  added");
	}

	public ArrayList <Player> getPlayers() {
            return this.players;
	}

	/**
	 * 
	 * @param players
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
        
        public ArrayList printOtherPlayersInfo(Player player){
            ArrayList otherPlayers = new ArrayList<>();
            System.out.println("=========================================");
            System.out.println("Other Players In Game");
            System.out.println("-----------------------------------------");

            for (Player x : this.getPlayers()) {
                if(player.getPlayerNumber() != x.getPlayerNumber()){
                    otherPlayers.addLast(x.getPlayerNumber());
                    System.out.println(x.getPlayerNumber() + ". " + x.getPlayerName());
                }
            }
            System.out.println("=========================================");
            return otherPlayers;
        }

}