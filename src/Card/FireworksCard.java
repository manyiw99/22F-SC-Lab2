package Card;

import Tools.DiceCalculationAllCards;
import Tools.DiceCalculationOtherCards;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//Composite design pattern - leaf
//Keep throwing dice until null
public class FireworksCard extends Card {
    public FireworksCard(Optional<Suit> suit) {
        super(suit);
    }

    @Override
    public Optional<Integer> playGame() {
        int playPoints = 0;
        super.continuousAfterTutto = false;
        Optional<int[]> dice = DiceCalculationAllCards.generateDice(6);
        //for(int i =0; i<6;i++) diceCopy[i] = dice.get()[i];
        //If contains at least one valid dice--------------------------------------------------------
        if (DiceCalculationOtherCards.isValidate(dice)) {
            // Cannot stop until NULL
            System.out.println("You cannot stop before NULL.");
            while (dice.isPresent()) {
//                List<int[]> allValidDiceChoice = DiceCalculationOtherCards.selectDice(dice.get());
//                int[] allValidDice = allValidDiceChoice.get(allValidDiceChoice.size());
                List<Integer> allValidDice = DiceCalculationOtherCards.allValidValue(dice.get());
                if (allValidDice.size() == 0){
                    super.continuousAfterTutto = false;
                    System.out.println("You have rolled a null. Next turn.");
                    return Optional.of(playPoints);
                }
                // Roll the remaining dice and keep all valid dice
                dice = super.remainingDice(dice, allValidDice.size());
                int[] diceArray = new int[allValidDice.size()];
                for (int i = 0; i < allValidDice.size(); i++) {
                    diceArray[i] = allValidDice.get(i);
                }
                playPoints = playPoints + DiceCalculationOtherCards.calculatePoints(diceArray);
                if (dice.isEmpty()) { //Tutto and continue throwing dice
                    dice = DiceCalculationAllCards.generateDice(6);
                    if (!DiceCalculationOtherCards.isValidate(dice)) { //If no valid dice
                        break;
                    }
                }
            }
            System.out.println("You have rolled a null. Next turn.");
            return Optional.ofNullable(playPoints);
            // No valid dice ------------------------------------------------------------------------------------------
        } else {
            super.continuousAfterTutto = false;
            System.out.println("You have rolled a null. Next turn.");
            return Optional.empty();
        }
    }
    @Override
    public String ContinueOrStop(){
        System.out.println("TUTTO! You cannot stop before NULL.");
        return "C";
    }
}
