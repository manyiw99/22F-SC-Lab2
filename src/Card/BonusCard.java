package Card;

import DiceCalculation.DiceCalculationOtherCards;
import Tools.InputValidation;

import java.util.Optional;

//Composite design patter - composite
public class BonusCard extends Card {
    private Optional<Suit> suit;
    private int bonus;
//    public InputValidation inputValidation_tool;
    public DiceCalculationOtherCards diceCalculationTool;
    public InputValidation inputValidation;

    public BonusCard(Optional<Suit> suit, int bonus,  DiceCalculationOtherCards diceCalculationOtherCards, InputValidation inputValidation) {
        super(suit);
        this.bonus = bonus;
        this.diceCalculationTool = diceCalculationOtherCards;
        this.inputValidation = inputValidation;
    }

    public int getBonus() {
        return bonus;
    }

    @Override
    public int finalPoints(int playPoints){
        return playPoints + bonus;
    }
}
