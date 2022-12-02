package Deck;

import Card.*;

import java.util.*;

// Iterator design pattern
// composite design pattern - client
public class Deck implements Iterable<Card>{
    private List<Card> cards;
    private final int STOP_NUM=10, OTHER_NUM=5; // cannot change


    public Deck(){
        //Initialize card list
        cards=new ArrayList<Card>(56);

        CardCache.loadCache();
        cards.add((Card)CardCache.getCard("LeafCard"));
        for(int i=0; i<STOP_NUM;i++){
            cards.add((Card)CardCache.getCard("StopCard"));
        }
        for(int i=0; i<OTHER_NUM;i++){
            cards.add((Card)CardCache.getCard("FireworksCard"));
            cards.add((Card)CardCache.getCard("StraightCard"));
            cards.add((Card)CardCache.getCard("PMCard"));
            cards.add((Card)CardCache.getCard("Mul2Card"));
            cards.add((Card)CardCache.getCard("Bonus200"));
            cards.add((Card)CardCache.getCard("Bonus300"));
            cards.add((Card)CardCache.getCard("Bonus400"));
            cards.add((Card)CardCache.getCard("Bonus500"));
            cards.add((Card)CardCache.getCard("Bonus600"));
        }

        // Shuffle the cards
        Collections.shuffle(cards);
    }

    /**
     * Iterator design pattern
     * @return
     */
    public Iterator<Card> iterator(){
        return new Iterator<Card>() {
            private int i = 0;

            public boolean hasNext() {
                if (i < cards.size()) {
                    return true;
                } else {
                    return false;
                }
            }

            public Card next() {
                return cards.get(i++);
            }

            public void remove(){
                cards.remove(i++);
            }
        };
    }


}
