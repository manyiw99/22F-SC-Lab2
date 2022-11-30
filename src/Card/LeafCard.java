package Card;

import Tools.DiceCalculationAllCards;
import Tools.DiceCalculationOtherCards;

import java.util.List;
import java.util.Optional;

// Prototype design pattern
public class LeafCard extends Card {

    public LeafCard(Optional<Suit> leaf) {
        super(leaf);
    }

    @Override
    public Optional<Integer> playGame() {
        int TuttoNum = 0; //number of times that Tutto, it has to be 2 to finish
        Optional<int[]> dice = DiceCalculationAllCards.generateDice(6);
        if (DiceCalculationOtherCards.isValidate(dice)) {
            // Cannot stop until NULL
            System.out.println("You cannot stop before TUTTO twice or NULL.");
            while (dice.isPresent()) {
                List<Integer> allValidValue = DiceCalculationOtherCards.allValidValue(dice.get());
                // Roll the remaining dice and keep all valid dice
                dice = super.remainingDice(dice, allValidValue.size());
                if (dice.isEmpty()) { //Tutto and continue throwing dice
                    TuttoNum++;
                    if (TuttoNum == 2) {
                        System.out.println("You WIN! You finished two TUTTO.");
                        return Optional.ofNullable(99999);// return 99999 score and end game
                    }
                    System.out.println("TUTTO! You cannot stop before another TUTTO.");
                    dice = DiceCalculationAllCards.generateDice(6);
                    if (!DiceCalculationOtherCards.isValidate(dice)) { //If no valid dice
                        break;
                    }
                }
            }
            // No valid dice ------------------------------------------------------------------------------------------
        } else {
            super.continuousAfterTutto = false;
        }
        System.out.println("You have rolled a null. Next turn.");
        return Optional.empty();
    }

}
