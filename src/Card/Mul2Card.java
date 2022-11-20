package Card;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Mul2Card extends Card{
    public Mul2Card(Optional<Suit> suit) {
        super(suit);
    }

    @Override
    public Optional<Integer> playGame() {
        int playPoints=0;
        // Generate dice randomly
        Optional<int[]> dice = diceTool.generateDice(6);
        //If contains at least one valid dice--------------------------------------------------------
        if(diceTool.isValidate(dice.get())){
            while(dice.isPresent()){
                System.out.println("Your dice are valid. Choose Continue or Stop(enter C or S):");
                String chooseInput = inputValidation_tool.readUser();

                if(chooseInput.equals("S")){ // stop ----------------------------
                    playPoints = playPoints+diceTool.calculatePoints(dice.get());
                    super.continuousAfterTutto = false;
                    dice=Optional.empty();
                }else if(chooseInput.equals("C")){ // continue---------------------
                    List<int[]> allValidDice = diceTool.allValidDice(dice.get());

                    // Get the dice to keep-----------------------------------------
                    int[] selectedDice = diceTool.selectDice(allValidDice);

                    // Roll the remaining dice
                    dice=super.remainingDice(dice,selectedDice);
                    playPoints = diceTool.calculatePoints(selectedDice);

                }else{
                    super.continuousAfterTutto = false;
                    System.out.println("Input wrong. Please enter again.");
                }
            }

            playPoints=playPoints*2;
            return Optional.ofNullable(playPoints);
            // No valid dice ------------------------------------------------------------------------------------------
        }else{
            super.continuousAfterTutto = false;
            System.out.println("You have rolled a null. Next turn.");
            return Optional.empty();
        }
    }
}
