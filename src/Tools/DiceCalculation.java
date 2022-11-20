package Tools;

import java.util.*;

import static Tools.InputValidation.*;

public class DiceCalculation {
    /**
     * Random generate dice
     * @param num
     * @return
     */
    public static Optional<int[]> generateDice(int num){
        System.out.println("The dice are: ");
        Random random = new Random();
        int[] dice = new int[num];
        for(int d=0; d< dice.length; d++){
            dice[d] = random.nextInt(6)+1;
            System.out.print("("+(d+1)+") "+dice[d] +"    ");
        }

        System.out.println();
        return Optional.ofNullable(dice);
    }

    /**
     * Check if the dice valid
     * @param dice
     * @return
     */
    public static boolean isValidate(int[] dice){
        //只要不是Null就返回true
        return true;
    }

    /**
     * Return all possible combinations of valid dice
     * @param dice
     * @return
     */
    public static List<int[]> allValidDice(int[] dice){
        List<int[]> result = new ArrayList<>();
        result.add(new int[]{3});

        return result;
    }

    /**
     * Count all the valid dice from the input and calculate the final points
     * @param dice
     * @return
     */
    public static int calculatePoints(int[] dice){
        // 最后返回的是输入的骰子可以获得的最大points，dice有可能是六位也有可能是12345位
        return 100;
    }

    public static int[] selectDice(List<int[]> allValidDice){
        int[] selectedDice = null;

        boolean isSelected = false;
        while(!isSelected){
            System.out.println("Please choose the valid dice you want to keep(enter the number): ");
            for(int a=0;a<allValidDice.size();a++){
                System.out.println("("+(a+1)+")  "+ Arrays.toString(allValidDice.get(a)));
            }
            String selectedInput = readUser();
            if(validateSelectNum(selectedInput,allValidDice.size())){
                selectedDice = allValidDice.get(Integer.parseInt(selectedInput)-1);
                isSelected = true;
            }else{
                System.out.println("Your selection is wrong, please enter again.");
            }
        }

        return selectedDice;
    }

    // Add all above methods for straight card
}
