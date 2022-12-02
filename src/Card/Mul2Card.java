package Card;

import DiceCalculation.*;
import Tools.InputValidation;

import java.util.Optional;

public class Mul2Card extends Card {
    public DiceCalculationOtherCards diceTool;
    public InputValidation inputValidation;
//    public Mul2Card(Optional<Suit> suit, DiceCalculationOtherCards diceCalculationOtherCards, InputValidation inputValidation) {
//        super(suit);
//        this.diceTool = diceCalculationOtherCards;
//        this.inputValidation = inputValidation;
//    }

    public Mul2Card(DiceCalculation diceCalculationOtherCards, InputValidation inputValidation) {
        super(diceCalculationOtherCards,inputValidation);

        if(diceCalculation instanceof DiceCalculationOtherCards){
            super.diceCalculation=(DiceCalculationOtherCards)diceCalculation;
        }
    }

    @Override
    public int finalPoints(int playPoints) {
        return playPoints * 2;
    }
}
