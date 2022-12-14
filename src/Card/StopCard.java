package Card;

import DiceCalculation.DiceCalculationOtherCards;
import Tools.InputValidation;

import java.util.Optional;

public class StopCard extends Card {
//    public DiceCalculationOtherCards diceTool;
//    public InputValidation inputValidation;
//    public StopCard(Optional<Suit> suit, DiceCalculationOtherCards diceCalculationOtherCards, InputValidation inputValidation) {
//        super(suit);
//        this.diceTool = diceCalculationOtherCards;
//        this.inputValidation = inputValidation;
//    }

    public StopCard(DiceCalculationOtherCards diceCalculationOtherCards, InputValidation inputValidation) {
        super(diceCalculationOtherCards,inputValidation);

        if(diceCalculation instanceof DiceCalculationOtherCards){
            super.diceCalculation=(DiceCalculationOtherCards)diceCalculation;
        }
    }

    /**
     * Next turn
     *
     * @return
     */
    @Override
    public Optional<Integer> playGame() {
        super.continuousAfterTutto = false;
        return Optional.ofNullable(0);
    }
}
