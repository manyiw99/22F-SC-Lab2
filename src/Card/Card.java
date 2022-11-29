package Card;

import Tools.*;

import java.util.List;
import java.util.Optional;

// Strategy design pattern
// Composite design pattern - composite
// prototype design pattern
public abstract class Card implements Cloneable {
    public Optional<Suit> suit; //type of card
    public boolean continuousAfterTutto;
    public InputValidation inputValidation_tool;
    public DiceCalculation diceTool;

    public Card(Optional<Suit> suit) {
        this.suit = suit;
        this.continuousAfterTutto = false;
    }

    // Strategy design pattern

    /**
     * If return is empty(), means nullability result
     *
     * @return
     */
    public Optional<Integer> playGame() {
        int playPoints = 0;
        // Generate dice randomly
        Optional<int[]> dice = DiceCalculationAllCards.generateDice(6);
        //If contains at least one valid dice--------------------------------------------------------
        if (DiceCalculationOtherCards.isValidate(dice)) {
            while (dice.isPresent()) {

                System.out.println("You have valid dice! Choose Continue or Stop(enter C or S):");
                String chooseInput = inputValidation_tool.readUser();

                if (chooseInput.equals("S")) { // stop ----------------------------
                    playPoints = playPoints + DiceCalculationOtherCards.calculatePoints(dice.get());
                    continuousAfterTutto = false;
                    dice = Optional.empty();
                } else if (chooseInput.equals("C")) { // continue---------------------
                    //List<int[]> allValidDice = DiceCalculationOtherCards.allValidDice(dice.get());

                    // Get the dice to keep-----------------------------------------
                    List<int[]> selectedDice = DiceCalculationOtherCards.selectDice(dice.get());
                    int selectedDiceLength = 0;
                    for (int l = 0; l < selectedDice.size(); l++) {
                        selectedDiceLength = selectedDice.get(l).length + selectedDiceLength;
                    }

                    // Roll the remaining dice
                    dice = remainingDice(dice, selectedDiceLength);
                    playPoints += DiceCalculationOtherCards.calculatePoints(selectedDice);
                } else {
                    continuousAfterTutto = false;
                    System.out.println("Input wrong. Please enter again.");
                }
            }

            return Optional.of(finalPoints(playPoints));
            // No valid dice ------------------------------------------------------------------------------------------
        } else {
            continuousAfterTutto = false;
            System.out.println("You have rolled a null. Next turn.");
            return Optional.empty();
        }
    }

    public int finalPoints(int playPoints) {
        return playPoints;
    }

    public Suit getSuit() {
        return suit.get();
    }

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
            dice = DiceCalculationAllCards.generateDice(dice.get().length - selectedDiceLength);
        }

        return dice;
    }

    public String ContinueOrStop() {
        System.out.println("Tutto! Choose Continue or Stop(enter C or S):");
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

    public boolean isWin() {
        System.out.println("Error - isWin method in Card Class");
        return false;
    }

    ;
}
