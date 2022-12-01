package Card;

import Tools.DiceCalculationOtherCards;
import Tools.InputValidation;

import java.util.Optional;

//Composite design patter - composite
public class BonusCard extends Card {
    private Optional<Suit> suit;
    private int bonus;
//    public InputValidation inputValidation_tool;
    public DiceCalculationOtherCards diceCalculationTool;

    public BonusCard(Optional<Suit> suit, int bonus,  DiceCalculationOtherCards diceCalculationOtherCards, InputValidation inputValidation) {
        super(suit, inputValidation);
        this.bonus = bonus;
        this.diceCalculationTool = diceCalculationOtherCards;
    }

    public int getBonus() {
        return bonus;
    }

    @Override
    public int finalPoints(int playPoints){
        return playPoints + bonus;
    }
}
