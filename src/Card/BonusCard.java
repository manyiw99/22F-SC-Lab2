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
        int[] dice = diceTool.generateDice(6);

        //If contains at least one valid dice--------------------------------------------------------
        if(diceTool.isValidate(dice)){
            boolean isTOTTO=false;
            while(dice!=null){
                System.out.println("Your dice are valid. Choose Continue or Stop(enter C or S):");
                String chooseInput = inputValidation_tool.readUser();

                if(chooseInput.equals("S")){ // stop ----------------------------
                    playPoints = playPoints+diceTool.calculatePoints(dice);
                    dice=null;
                }else if(chooseInput.equals("C")){ // continue---------------------
                    List<int[]> allValidDice = diceTool.allValidDice(dice);

                    // Get the dice to keep-----------------------------------------
                    int[] selectedDice = null;

                    boolean isSelected = false;
                    while(!isSelected){
                        System.out.println("Please choose the valid dice you want to keep(enter thr number): ");
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
                    if(dice.length - selectedDice.length==0){
                        isTOTTO=true;
                        dice = diceTool.generateDice(6);
                        System.out.println("TUTTO!");
                    }else{
                        dice = diceTool.generateDice(dice.length - selectedDice.length);
                    }
                    playPoints = diceTool.calculatePoints(selectedDice);

                }else{
                    System.out.println("Input wrong. Please enter again.");
                }
            }

            if(isTOTTO){
                playPoints=playPoints+bonus;
            }
        // No valid dice ------------------------------------------------------------------------------------------
        }else{
            playPoints=0;
            System.out.println("You have rolled a null. Next turn.");
        }

        return playPoints;
    }
}
