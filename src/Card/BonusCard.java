package Card;

import Tools.DiceCalculation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//Composite design patter - composite
public class BonusCard extends Card {
    private Optional<Suit> suit;
    private int bonus;

    public BonusCard(Optional<Suit> suit, int bonus) {
        super(suit);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    @Override
    public int finalPoints(int playPoints){
        return playPoints + bonus;
    }
}
