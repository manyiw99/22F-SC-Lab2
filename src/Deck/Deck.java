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
        cards.add((Card)CardCache.getShape("LeafCard"));
        for(int i=0; i<STOP_NUM;i++){
            cards.add((Card)CardCache.getShape("StopCard"));
        }
        for(int i=0; i<OTHER_NUM;i++){
            cards.add((Card)CardCache.getShape("FireworksCard"));
            cards.add((Card)CardCache.getShape("StraightCard"));
            cards.add((Card)CardCache.getShape("PMCard"));
            cards.add((Card)CardCache.getShape("Mul2Card"));
            cards.add((Card)CardCache.getShape("Bonus200"));
            cards.add((Card)CardCache.getShape("Bonus300"));
            cards.add((Card)CardCache.getShape("Bonus400"));
            cards.add((Card)CardCache.getShape("Bonus500"));
            cards.add((Card)CardCache.getShape("Bonus600"));
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

    /**
     * Draw a card from the deck
     * @return null: need to create a new deck and shuffle again
     */
    public Card draw(){
        if(iterator().hasNext()){
            Card c =  iterator().next();
            iterator().remove();
            return c;
        }else{
            return null;
        }
    }

}
