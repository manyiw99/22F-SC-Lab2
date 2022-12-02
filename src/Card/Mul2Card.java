package Card;

import Tools.DiceCalculationOtherCards;
import Tools.InputValidation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Mul2Card extends Card {
    public DiceCalculationOtherCards diceTool;
    public InputValidation inputValidation;
    public Mul2Card(Optional<Suit> suit, DiceCalculationOtherCards diceCalculationOtherCards, InputValidation inputValidation) {
        super(suit);
        this.diceTool = diceCalculationOtherCards;
        this.inputValidation = inputValidation;
    }

    @Override
    public int finalPoints(int playPoints) {
        return playPoints * 2;
    }
}
