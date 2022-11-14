package Card;

import Tools.*;

import java.util.Optional;

// Strategy design pattern
// Composite design pattern - composite
// prototype design pattern
public abstract class Card implements Cloneable{
    public Optional<Suit> suit;
    public InputValidation inputValidation_tool;
    public DiceCalculation diceTool;

    public Card(Optional<Suit> suit){
        this.suit=suit;
    }

    // Strategy design pattern
    public abstract int playGame();

    public Suit getSuit(){
        return suit.get();
    }

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
