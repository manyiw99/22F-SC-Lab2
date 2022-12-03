package Card;

import DiceCalculation.*;
import Tools.InputValidation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StraightCard extends Card {
    private DiceCalculationStraight diceTool;

//    public StraightCard(Optional<Suit> suit, InputValidation inputValidation, DiceCalculationStraight diceTool) {
//        super(suit);
//        this.diceTool = diceTool;
//        this.inputValidation = inputValidation;
//    }

    public StraightCard(DiceCalculationStraight diceCalculation, InputValidation inputValidation) {
        super(diceCalculation, inputValidation);

        if (diceCalculation instanceof DiceCalculationStraight) {
            super.diceCalculation = (DiceCalculationStraight) diceCalculation;
        }

        this.diceTool = diceCalculation;
    }

    @Override
    public Optional<Integer> playGame() {
        List<Integer> expectedDice = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> diceList = new ArrayList<>();

        Optional<int[]> dice = diceTool.generateDice(6);
//        int[] test = {1,2,3,4,5,6};
//        Optional<int[]> dice = Optional.of(test);
        int playPoints = 0;

        //If contains at least one valid dice--------------------------------------------------------
        while (dice.isPresent()) {
            diceList = diceTool.diceToList(dice);
            if (Collections.disjoint(expectedDice, diceList)) { //return false if they have same element
                super.continuousAfterTutto = false;
                System.out.println("You have rolled a null. Next turn.");
                return Optional.empty();
            } else {
                //find all valid dices that users can select
                List<Integer> allValidDice = diceTool.allValidDice(diceList, expectedDice);

                // Get the dice to keep----------------------------------------
                int[] selectedDice = diceTool.selectDice(allValidDice, expectedDice);

                expectedDice.removeAll(diceTool.diceToList(Optional.of(selectedDice)));

                // Roll the remaining dice
                dice = diceTool.generateDice(diceList.size() - selectedDice.length);

                if (diceList.size() - selectedDice.length == 0) { //Tutto and continue throwing dice
                    playPoints += 2000;
                    while (true) {
                        System.out.println("TUTTO! Choose Continue or Stop(enter C or S):");
                        String chooseInput = super.inputValidation_tool.readUser();

                        if (chooseInput.equals("S")) { // stop ----------------------------
                            super.continuousAfterTutto = false;
                            dice = Optional.empty();
                            return Optional.ofNullable(playPoints);
//                            validInput = true;
                        } else if (chooseInput.equals("C")) { // continue---------------------
                            expectedDice = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
                            dice = diceTool.generateDice(0);
//                            validInput = true;
                            super.continuousAfterTutto = true;
                            return Optional.ofNullable(playPoints);
                        } else {
                            continuousAfterTutto = false;
                            System.out.println("Input wrong. Please enter again.");
                        }
                    }
                }
            }
        }

        return Optional.ofNullable(playPoints);
    }
}
