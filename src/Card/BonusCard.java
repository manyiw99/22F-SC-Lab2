package Card;

import DiceCalculation.*;
import Tools.InputValidation;

import java.util.Optional;

//Composite design patter - composite
public class BonusCard extends Card {
    //private Optional<Suit> suit;
    private int bonus;
//    public DiceCalculationOtherCards diceCalculationTool;
//    public InputValidation inputValidation;

//    public BonusCard(Optional<Suit> suit, int bonus,  DiceCalculationOtherCards diceCalculationOtherCards, InputValidation inputValidation) {
//        super(suit);
//        this.bonus = bonus;
//        this.diceCalculationTool = diceCalculationOtherCards;
//        this.inputValidation = inputValidation;
//    }

    public BonusCard(int bonus, DiceCalculation diceCalculation, InputValidation inputValidation) {
        super(diceCalculation, inputValidation);
        this.bonus = bonus;

        if (diceCalculation instanceof DiceCalculationOtherCards) {
            super.diceCalculation = (DiceCalculationOtherCards) diceCalculation;
        }
    }

    public int getBonus() {
        return bonus;
    }

    @Override
    public int finalPoints(int playPoints) {
        return playPoints + bonus;
    }
}
