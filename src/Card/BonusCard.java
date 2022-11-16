package Card;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//Composite design patter - composite
public class BonusCard extends Card{
    private Optional<Suit> suit;
    private int bonus;

    public BonusCard(Optional<Suit> suit, int bonus){
        super(suit);
        this.bonus=bonus;
    }

    public int getBonus(){
        return bonus;
    }

    @Override
    public int playGame() {
        int playPoints=0;
        // Generate dice randomly
        Optional<int[]> dice = diceTool.generateDice(6);
        boolean isTOTTO=false;
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
                    int[] selectedDice = null;

                    boolean isSelected = false;
                    while(!isSelected){
                        System.out.println("Please choose the valid dice you want to keep(enter the number): ");
                        for(int a=0;a<allValidDice.size();a++){
                            System.out.println("("+a+") "+ Arrays.toString(allValidDice.get(a)));
                        }
                        String selectedInput = inputValidation_tool.readUser();
                        if(inputValidation_tool.inputValidation(selectedInput,"SELECT")){
                            selectedDice = allValidDice.get(Integer.parseInt(selectedInput));
                            isSelected = true;
                        }else{
                            System.out.println("Your selection is wrong, please enter again.");
                        }
                    }

                    // Roll the remaining dice
                    if(dice.get().length - selectedDice.length==0){
                        dice=Optional.empty();
                        isTOTTO=true;
                        boolean isContinuous=false;
                        while(!isContinuous) {
                            System.out.println("Tutto! Choose Continue or Stop(enter C or S):");
                            String chooseInputAfterTutto = inputValidation_tool.readUser();
                            if (chooseInputAfterTutto.equals("S")) { // stop ----------------------------
                                isContinuous=true;
                                System.out.println("Next turn.");
                            } else if (chooseInputAfterTutto.equals("C")) {
                                isContinuous=true;
                                super.continuousAfterTutto = true;
                            } else {
                                super.continuousAfterTutto = false;
                                System.out.println("Input wrong. Please enter again.");
                            }
                        }

                    }else{
                        super.continuousAfterTutto = false;
                        dice = diceTool.generateDice(dice.get().length - selectedDice.length);
                    }

                    playPoints = diceTool.calculatePoints(selectedDice);

                }else{
                    super.continuousAfterTutto = false;
                    System.out.println("Input wrong. Please enter again.");
                }
            }

            if(isTOTTO){
                playPoints=playPoints+bonus;
            }
        // No valid dice ------------------------------------------------------------------------------------------
        }else{
            playPoints=0;
            super.continuousAfterTutto = false;
            System.out.println("You have rolled a null. Next turn.");
        }

        return playPoints;
    }
}
