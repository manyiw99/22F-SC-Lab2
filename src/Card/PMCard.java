package Card;

import DiceCalculation.*;
import Tools.InputValidation;

import java.util.List;
import java.util.Optional;

public class PMCard extends Card {

    public DiceCalculationOtherCards diceCalculationTool;
//    public InputValidation inputValidation;

//    public PMCard(Optional<Suit> suit, DiceCalculationOtherCards diceCalculationOtherCards, InputValidation inputValidation) {
//        super(suit);
//        this.diceCalculationTool = diceCalculationOtherCards;
//        this.inputValidation = inputValidation;
//    }

    public PMCard(DiceCalculationOtherCards diceCalculationOtherCards, InputValidation inputValidation) {
        super(diceCalculationOtherCards,inputValidation);

        this.diceCalculationTool=diceCalculationOtherCards;
    }

    /**
     * If TUTTO, return 1000
     *
     * @return
     */
    @Override
    public Optional<Integer> playGame() {
        Optional<int[]> dice = diceCalculationTool.generateDice(6);
        //If contains at least one valid dice--------------------------------------------------------

        // Cannot stop until TUTTO
        System.out.println("You cannot stop before TUTTO.");
        while (dice.isPresent()) {
            if (diceCalculationTool.isValidate(dice)) {
                // Get the dice to keep-----------------------------------------
                List<int[]> selectedDice = diceCalculationTool.selectDice(dice.get());

                int selectedDiceLength = 0;
                for (int l = 0; l < selectedDice.size(); l++) {
                    selectedDiceLength = selectedDice.get(l).length + selectedDiceLength;
                }
                // Roll the remaining dice
                dice = super.remainingDice(dice, selectedDiceLength);
                // No valid dice ------------------------------------------------------------------------------------------
            } else {
                super.continuousAfterTutto = false;
                System.out.println("You have rolled a null. Next turn.");
                return Optional.empty();
            }
        }

        return Optional.ofNullable(1000);

    }
}
