package Card;

import Tools.DiceCalculationAllCards;
import Tools.DiceCalculationStraight;
import Tools.InputValidation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StraightCard extends Card {
    public StraightCard(Optional<Suit> suit) {
        super(suit);
    }

    public DiceCalculationStraight diceTool = new DiceCalculationStraight();
    private List<Integer> expectedDice = new ArrayList<>(List.of(1,2,3,4,5,6));
    private List<Integer> diceList = new ArrayList<>();

    @Override
    public Optional<Integer> playGame() {
        Optional<int[]> dice = DiceCalculationAllCards.generateDice(6);
//        int[] test = {1,2,3,4,5,6};
//        Optional<int[]> dice = Optional.of(test);
        int playPoints = 0;

        //If contains at least one valid dice--------------------------------------------------------
        while (dice.isPresent()) {
            diceList = diceTool.diceToList(dice);
            if (Collections.disjoint(expectedDice, diceList)){ //return false if they have same element
                super.continuousAfterTutto = false;
                System.out.println("You have rolled a null. Next turn.");
                return Optional.empty();
            } else {
                //find all valid dices that users can select
                List<Integer> allValidDice = diceTool.allValidDice(diceList,expectedDice);

                // Get the dice to keep----------------------------------------
                int[] selectedDice = diceTool.selectDice(allValidDice, expectedDice);

                expectedDice.removeAll(diceTool.diceToList(Optional.of(selectedDice)));

                // Roll the remaining dice
                dice = DiceCalculationAllCards.generateDice(diceList.size() - selectedDice.length);

                if (diceList.size() - selectedDice.length == 0) { //Tutto and continue throwing dice
                    playPoints += 2000;
                    System.out.println("TUTTO! Choose Continue or Stop(enter C or S):");
                    String chooseInput = InputValidation.readUser();

                    if (chooseInput.equals("S")) { // stop ----------------------------
                        continuousAfterTutto = false;
                        dice = Optional.empty();
                    } else if (chooseInput.equals("C")) { // continue---------------------
                        expectedDice = new ArrayList<>(List.of(1,2,3,4,5,6));
                        dice = DiceCalculationAllCards.generateDice(6);
                    } else {
                        continuousAfterTutto = false;
                        System.out.println("Input wrong. Please enter again.");
                    }
                }
            }
        }

        return Optional.ofNullable(playPoints);
    }
}
