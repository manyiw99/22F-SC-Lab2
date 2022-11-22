package Card;

import java.util.List;
import java.util.Optional;

// Prototype design pattern
public class LeafCard extends Card{

    public LeafCard(Optional<Suit> leaf) {
        super(leaf);
    }

    @Override
    public Optional<Integer> playGame() {
        int TuttoNum = 0; //number of times that Tutto, it has to be 2 to finish
        Optional<int[]> dice = diceTool.generateDice(6);
        if(diceTool.isValidate(dice.get())){
            // Cannot stop until NULL
            System.out.println("You cannot stop before TUTTO twice or NULL.");
            while(dice.isPresent()){
                List<int[]> allValidDiceChoice = diceTool.allValidDice(dice.get());
                int[] allValidDice = allValidDiceChoice.get(allValidDiceChoice.size());
                // Roll the remaining dice and keep all valid dice
                dice = super.remainingDice(dice,allValidDice);
                if(dice.isEmpty()){ //Tutto and continue throwing dice
                    TuttoNum++;
                    if(TuttoNum==2){
                        System.out.println("You WIN! You finished two TUTTO.");
                        return Optional.ofNullable(99999);// return 99999 score and end game
                    }
                    System.out.println("TUTTO! You cannot stop before another TUTTO.");
                    dice = diceTool.generateDice(6);
                    if(!diceTool.isValidate(dice.get())){ //If no valid dice
                        break;
                    }
                }
            }
            System.out.println("You have rolled a null. Next turn.");
            return Optional.empty();
            // No valid dice ------------------------------------------------------------------------------------------
        }else{
            super.continuousAfterTutto = false;
            System.out.println("You have rolled a null. Next turn.");
            return Optional.empty();
        }
    }
    
}
