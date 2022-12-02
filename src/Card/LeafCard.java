package Card;

import DiceCalculation.*;
import Tools.InputValidation;

import java.util.List;
import java.util.Optional;

// Prototype design pattern
public class LeafCard extends Card {
    public DiceCalculationOtherCards diceCalculationTool;
    //public InputValidation inputValidation;

//    public LeafCard(Optional<Suit> leaf, DiceCalculationOtherCards diceCalculationOtherCards, InputValidation inputValidation) {
//        super(leaf);
//        this.diceCalculationTool = diceCalculationOtherCards;
//        this.inputValidation = inputValidation;
//    }

    public LeafCard(DiceCalculationOtherCards diceCalculationOtherCards, InputValidation inputValidation) {
        super(diceCalculationOtherCards,inputValidation);

        if(diceCalculation instanceof DiceCalculationOtherCards){
            super.diceCalculation=(DiceCalculationOtherCards)diceCalculation;
        }

        this.diceCalculationTool=diceCalculationOtherCards;
    }

    @Override
    public Optional<Integer> playGame() {
        System.out.println("LeafCard! You cannot stop before TUTTO twice or NULL.");
        int TuttoNum = 0; //number of times that Tutto, it has to be 2 to finish
        Optional<int[]> dice = diceCalculationTool.generateDice(6);
        if (diceCalculationTool.isValidate(dice)) {
            // Cannot stop until NULL

            while (dice.isPresent()) {
                List<int[]> selectedDice = diceCalculationTool.selectDice(dice.get());

                int selectedDiceLength = 0;
                for (int l = 0; l < selectedDice.size(); l++) {
                    selectedDiceLength = selectedDice.get(l).length + selectedDiceLength;
                }
                // Roll the remaining dice
                dice = super.remainingDice(dice, selectedDiceLength);
                if (dice.isEmpty()) { //Tutto and continue throwing dice
                    TuttoNum++;
                    if (TuttoNum == 2) {
                        System.out.println("You WIN! You finished two TUTTO.");
                        super.continuousAfterTutto = false;
                        return Optional.ofNullable(99999);// return 99999 score and end game
                    }
                    System.out.println("You cannot stop before another TUTTO.");
                    System.out.println("Take another round.");
                    dice = diceCalculationTool.generateDice(6);
                    if (!diceCalculationTool.isValidate(dice)) { //If no valid dice
                        break;
                    }
                }
                if (!diceCalculationTool.isValidate(dice)) { //If no valid dice

                    break;
                }
            }
            // No valid dice ------------------------------------------------------------------------------------------
        } else {
            super.continuousAfterTutto = false;
        }
        super.continuousAfterTutto = false;
        System.out.println("You have rolled a null. Next turn.");
        return Optional.empty();
    }

}
