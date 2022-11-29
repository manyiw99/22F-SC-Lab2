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

    private GameModel(Optional<ArrayList<Player>> players, Optional<Integer> points) {
        this.players = players;
        this.points = points;
        deck = new Deck();
    }

    /**
     * Implementation of singleton design pattern
     *
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

    public ArrayList<Player> getPlayers() {
        return players.get();
    }

    public Card drawCard() {
        Card card = deck.draw();
        if (card == null) {
            deck = new Deck();
            drawCard();
        }
        return card;
    }

    public int getCurrentPlayerPoint(int i) {
        for (int j = 0; j < players.get().size(); j++) {
            if (players.get().get(j).getOrder().get() == i) {
                return players.get().get(i).getPoints().get();
            }
        }

        return -1;
    }

    public String getCurrentPlayerName(int i) {
        for (int j = 0; j < players.get().size(); j++) {
            if (players.get().get(j).getOrder().get() == i) {
                return players.get().get(i).getName().get();
            }
        }

        return null;
    }

    public void setCurrentPlayerPointByName(int point, String name) {
        for (int j = 0; j < players.get().size(); j++) {
            if (players.get().get(j).getName().get() == name) {
                players.get().get(j).setPoints(Optional.ofNullable(point));
            }
        }
    }

    public Optional<Integer> playGame(Card card) {
        return card.playGame();
    }

    public int getWinningPoints() {
        return points.get();
    }

    public boolean isContinuous(Card card) {
        return card.getContinuousAfterTutto();
    }

    /**
     * Get leading players names according to points, used for PMCard
     *
     * @return
     */
    public List<String> getLeadingPlayers() {
        List<String> res = new ArrayList<>();

        Integer[] points =new Integer[players.get().size()];
        for(int i=0; i<players.get().size();i++){
            points[i]=players.get().get(i).getPoints().get();
        }

        //After sort: int[] points
        Arrays.sort(points, Collections.reverseOrder());

        for (int i = 0; i < points.length; i++){
            for(int j=0; j< players.get().size();j++){
                if(players.get().get(j).getPoints().get()==points[i]){
                    res.add(players.get().get(j).getName().get());
                }
            }
        }

        return res;
    }

    /**
     * Sort thr order of players
     */
    public void sortOrder() {
        // Set order for each player

        // create string array called names
        String names[]=new String[players.get().size()];
        for(int i=0; i<players.get().size();i++){
            names[i]=players.get().get(i).getName().get();
        }

        String temp;
        for (int i = 0; i < names.length; i++) {
            for (int j = i + 1; j < names.length; j++) {
                // to compare one string with other strings
                if (names[i].compareTo(names[j]) > 0) {
                    // swapping
                    temp = names[i];
                    names[i] = names[j];
                    names[j] = temp;
                }
            }
        }

        // print output array
        // The names in alphabetical order are String[] names
        for (int i = 0; i < names.length; i++){
            for(int j=0; j< players.get().size();j++){
                if(players.get().get(j).getName().get()==names[i]){
                    players.get().get(j).setOrder(Optional.ofNullable(i));
                }
            }
        }

    }

}
