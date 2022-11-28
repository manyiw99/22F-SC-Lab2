package Card;

import Tools.DiceCalculationAllCards;

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
        if(diceTool.isValidate(dice)){
            // Cannot stop until TUTTO
            System.out.println("You cannot stop before TUTTO.");
            while(dice.isPresent()){
                List<int[]> allValidDice = diceTool.allValidDice(dice.get());

                // Get the dice to keep-----------------------------------------
                int[] selectedDice = diceTool.selectDice(allValidDice);

                // Roll the remaining dice
                dice = super.remainingDice(dice,selectedDice);
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
