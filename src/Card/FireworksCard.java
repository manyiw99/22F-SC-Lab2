package Card;

import Tools.DiceCalculationOtherCards;
import Tools.InputValidation;

import java.util.List;
import java.util.Optional;

//Composite design pattern - leaf
//Keep throwing dice until null
public class FireworksCard extends Card {

    public DiceCalculationOtherCards diceCalculationTool;

    public FireworksCard(Optional<Suit> suit, DiceCalculationOtherCards diceCalculationOtherCards, InputValidation inputValidation) {
        super(suit, inputValidation);
        this.diceCalculationTool = diceCalculationOtherCards;
    }

    @Override
    public Optional<Integer> playGame() {
        int playPoints = 0;
        super.continuousAfterTutto = false;
        Optional<int[]> dice = diceCalculationTool.generateDice(6);
        //for(int i =0; i<6;i++) diceCopy[i] = dice.get()[i];
        //If contains at least one valid dice--------------------------------------------------------
        if (diceCalculationTool.isValidate(dice)) {
            // Cannot stop until NULL
            System.out.println("You cannot stop before NULL.");
            while (dice.isPresent()) {
//                List<int[]> allValidDiceChoice = DiceCalculationOtherCards.selectDice(dice.get());
//                int[] allValidDice = allValidDiceChoice.get(allValidDiceChoice.size());
                List<Integer> allValidDice = diceCalculationTool.allValidValue(dice.get());
                if (allValidDice.size() == 0) {
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
                playPoints = playPoints + diceCalculationTool.calculatePoints(diceArray);
                if (dice.isEmpty()) { //Tutto and continue throwing dice
                    dice = diceCalculationTool.generateDice(6);
                    if (!diceCalculationTool.isValidate(dice)) { //If no valid dice
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
    public String ContinueOrStop() {
        System.out.println("TUTTO! You cannot stop before NULL.");
        return "C";
    }
}
