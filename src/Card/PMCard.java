package Card;

import Tools.DiceCalculationAllCards;
import Tools.DiceCalculationOtherCards;

import java.util.List;
import java.util.Optional;

public class PMCard extends Card{
    public PMCard(Optional<Suit> suit) {
        super(suit);
    }

    /**
     *  If TUTTO, return 1000
     * @return
     */
    @Override
    public Optional<Integer> playGame() {
        Optional<int[]> dice = DiceCalculationAllCards.generateDice(6);
        //If contains at least one valid dice--------------------------------------------------------
        if(DiceCalculationOtherCards.isValidate(dice)){
            // Cannot stop until TUTTO
            System.out.println("You cannot stop before TUTTO.");
            while(dice.isPresent()){
                // Get the dice to keep-----------------------------------------
                List<int[]> selectedDice = DiceCalculationOtherCards.selectDice(dice.get());

                int selectedDiceLength=0;
                for (int l = 0; l < selectedDice.size(); l++) {
                    selectedDiceLength = selectedDice.get(l).length + selectedDiceLength;
                }
                // Roll the remaining dice
                dice = super.remainingDice(dice,selectedDiceLength);
            }

            return Optional.ofNullable(1000);
        // No valid dice ------------------------------------------------------------------------------------------
        }else{
            super.continuousAfterTutto = false;
            System.out.println("You have rolled a null. Next turn.");
            return Optional.empty();
        }
    }
}
