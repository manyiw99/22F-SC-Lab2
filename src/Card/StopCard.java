package Card;

import Tools.DiceCalculationOtherCards;
import Tools.InputValidation;

import java.util.Optional;

public class StopCard extends Card {
    public DiceCalculationOtherCards diceTool;
    public InputValidation inputValidation;
    public StopCard(Optional<Suit> suit, DiceCalculationOtherCards diceCalculationOtherCards, InputValidation inputValidation) {
        super(suit);
        this.diceTool = diceCalculationOtherCards;
        this.inputValidation = inputValidation;
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
