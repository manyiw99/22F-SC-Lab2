package Card;

import DiceCalculation.*;
import Tools.InputValidation;

import java.util.List;
import java.util.Optional;

// Strategy design pattern
// Composite design pattern - composite
// prototype design pattern
public abstract class Card implements Cloneable {
    // public Optional<Suit> suit; //type of card
    public boolean continuousAfterTutto;
    public InputValidation inputValidation_tool;
    public DiceCalculation diceCalculation;

//    public Card(Optional<Suit> suit) {
//        this.suit = suit;
//        this.continuousAfterTutto = false;
//    }
    public Card( DiceCalculation diceCalculation, InputValidation inputValidation) {
        this.continuousAfterTutto = false;
        this.inputValidation_tool=inputValidation;
        this.diceCalculation=diceCalculation;
    }

    // Strategy design pattern

    /**
     * If return is empty(), means nullability result
     *
     * @return
     */
    public Optional<Integer> playGame() {
        DiceCalculationOtherCards diceCalculationTool = (DiceCalculationOtherCards)diceCalculation;
        int playPoints = 0;
        // Generate dice randomly
        Optional<int[]> dice = diceCalculationTool.generateDice(6);
        //If contains at least one valid dice--------------------------------------------------------
        //if (DiceCalculationOtherCards.isValidate(dice)) {
        while (dice.isPresent()) {
            if (diceCalculationTool.isValidate(dice)) {

                System.out.println("Your dice are valid. Choose Continue or Stop(enter C or S):");
                String chooseInput = inputValidation_tool.readUser();

                if (chooseInput.equals("S")) { // stop ----------------------------
                    playPoints = playPoints + diceCalculationTool.calculatePoints(dice.get());
                    continuousAfterTutto = false;
                    dice = Optional.empty();
                } else if (chooseInput.equals("C")) { // continue---------------------

                    // Get the dice to keep-----------------------------------------
                    List<int[]> selectedDice = diceCalculationTool.selectDice(dice.get());
                    int selectedDiceLength = 0;
                    for (int l = 0; l < selectedDice.size(); l++) {
                        selectedDiceLength = selectedDice.get(l).length + selectedDiceLength;
                    }

                    // Roll the remaining dice
                    dice = remainingDice(dice, selectedDiceLength);
                    playPoints += diceCalculationTool.calculatePoints(selectedDice);
                } else {
                    continuousAfterTutto = false;
                    System.out.println("Input wrong. Please enter again.");
                }
            }else {
                continuousAfterTutto = false;
                System.out.println("You have rolled a null. Next turn.");
                return Optional.empty();
            }
        }

        if(continuousAfterTutto){
            return Optional.of(finalPoints(playPoints));
        }else {
            return Optional.of(playPoints);
        }
        // No valid dice ------------------------------------------------------------------------------------------

    }

    public int finalPoints(int playPoints) {
        return playPoints;
    }

//    public Suit getSuit() {
//        return suit.get();
//    }

    public boolean getContinuousAfterTutto() {
        return continuousAfterTutto;
    }

    /**
     * @param dice
     * @param selectedDiceLength
     * @return if tutto, return Optional.empty(); else, return diceTool.generateDice(dice.get().length - selectedDice.length);
     */
    public Optional<int[]> remainingDice(Optional<int[]> dice, int selectedDiceLength) {
        if (dice.get().length - selectedDiceLength == 0) {
            dice = Optional.empty();
            boolean isContinuous = false;
            while (!isContinuous) {
                String chooseInputAfterTutto = ContinueOrStop();
                if (chooseInputAfterTutto.equals("S")) { // stop ----------------------------
                    isContinuous = true;
                    continuousAfterTutto = false;
                    System.out.println("Next turn.");
                } else if (chooseInputAfterTutto.equals("C")) {
                    isContinuous = true;
                    continuousAfterTutto = true;
                } else {
                    continuousAfterTutto = false;
                    System.out.println("Input wrong. Please enter again.");
                }
            }
        } else {
            continuousAfterTutto = false;
            dice = diceCalculation.generateDice(dice.get().length - selectedDiceLength);
        }

        return dice;
    }

    public String ContinueOrStop() {
        System.out.println("TUTTO! Choose Continue or Stop(enter C or S):");
        return inputValidation_tool.readUser();
    }

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

}
