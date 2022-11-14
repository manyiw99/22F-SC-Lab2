package Game;

import Card.Card;
import Deck.Deck;
import Player.Player;

import java.util.*;

public class GameModel {
    private Optional<ArrayList<Player>> players;
    private Optional<Integer> points; //winning points
    private Deck deck;
    private static GameModel INSTANCE;  //Singleton design pattern

    private GameModel(Optional<ArrayList<Player>> players, Optional<Integer> points){
        this.players=players;
        this.points=points;
        deck=new Deck();
    }

    /**
     * Implementation of singleton design pattern
     * @param players
     * @param points
     * @return
     */
    public static synchronized GameModel getInstance(Optional<ArrayList<Player>> players, Optional<Integer> points) {
        if (INSTANCE == null) {
            INSTANCE = new GameModel(players, points);
        }
        return INSTANCE;
    }

    public ArrayList<Player> getPlayers(){
        return players.get();
    }

    /**
     * Sort thr order of players
     */
    public void sortOrder(){
        // Set order for each player

        // 从0开始设置order
        players.get().get(0).setOrder(Optional.ofNullable(1));
        players.get().get(1).setOrder(Optional.ofNullable(0));
    }

    public Card drawCard(){
        return deck.draw();
    }

    public int getCurrentPlayerPoint(int i){
        for(int j=0; j<players.get().size(); j++) {
            if (players.get().get(j).getOrder().get() == i) {
                return players.get().get(i).getPoints().get();
            }
        }

        return -1;
    }

    public String getCurrentPlayerName(int i){
        for(int j=0; j<players.get().size(); j++) {
            if (players.get().get(j).getOrder().get() == i) {
                return players.get().get(i).getName().get();
            }
        }

        return null;
    }

    public void setCurrentPlayerPoint(int point, int order){
        for(int j=0; j<players.get().size(); j++) {
            if (players.get().get(j).getOrder().get() == order) {
                players.get().get(j).setPoints(Optional.ofNullable(point));
            }
        }
    }

    public int playGame(Card card){
        return card.playGame();
    }

    public int getWinningPoints(){
        return points.get();
    }
}