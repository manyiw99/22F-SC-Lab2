package Card;

import Tools.*;

import java.util.Optional;

// Strategy design pattern
// Composite design pattern - composite
// prototype design pattern
public abstract class Card implements Cloneable{
    public Optional<Suit> suit; //type of card
    public boolean continuousAfterTutto;
    public InputValidation inputValidation_tool;
    public DiceCalculation diceTool;

    public Card(Optional<Suit> suit){
        this.suit=suit;
        this.continuousAfterTutto=false;
    }

    // Strategy design pattern

    /**
     * If return is empty(), means nullability result
     * @return
     */
    public abstract Optional<Integer> playGame();

    public Suit getSuit(){
        return suit.get();
    }

    public boolean getContinuousAfterTutto(){
        return continuousAfterTutto;
    }

    /**
     *
     * @param dice
     * @param selectedDice
     * @return if tutto, return Optional.empty(); else, return diceTool.generateDice(dice.get().length - selectedDice.length);
     */
    public Optional<int[]> remainingDice(Optional<int[]> dice, int[] selectedDice){
        if(dice.get().length - selectedDice.length==0){
            dice=Optional.empty();
            boolean isContinuous=false;
            while(!isContinuous) {
                System.out.println("Tutto! Choose Continue or Stop(enter C or S):");
                String chooseInputAfterTutto = inputValidation_tool.readUser();
                if (chooseInputAfterTutto.equals("S")) { // stop ----------------------------
                    isContinuous=true;
                    continuousAfterTutto=false;
                    System.out.println("Next turn.");
                } else if (chooseInputAfterTutto.equals("C")) {
                    isContinuous=true;
                    continuousAfterTutto = true;
                } else {
                    continuousAfterTutto = false;
                    System.out.println("Input wrong. Please enter again.");
                }
            }
        }else{
            continuousAfterTutto = false;
            dice = diceTool.generateDice(dice.get().length - selectedDice.length);
        }

        return dice;
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

    public boolean isWin(){
        System.out.println("Error - isWin method in Card Class");
        return false;
    };
}
