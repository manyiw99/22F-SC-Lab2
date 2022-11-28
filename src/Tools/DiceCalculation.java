package Tools;

import java.util.*;

import static Tools.InputValidation.*;

public class DiceCalculation implements DiceCalculationOtherCards {

    /**
     * Check if the dice valid
     *
     * @param dice
     * @return
     */
    @Override
    public boolean isValidate(Optional<int[]> dice) {
        //return true as long as the dice is not null
        if (!dice.isPresent()) {
            return false;
        }
        return true;
    }

    /**
     * Return all possible combinations of valid dice
     *
     * @param dice
     * @return
     */

    public static List<Integer> allValidValue(int[] dice) {
        List<Integer> pos = new ArrayList<Integer>();
        int[] counter = count(dice);
        for (int i = 1; i < 7; i++) {
            if (((i != 1) && counter[i] < 3) || ((i != 5) && counter[i] < 3) && counter[i] == 0) {
                continue;
            } else {
                int size = counter[i];
                for (int j = 0; j < size; j++) {
                    pos.add(i);
                }
            }
        }
        return pos;
    }

    public static List<int[]> allValidDice(int[] dice, int k){
        // todo: remove the ones which are not valid
        List<Integer> pos = allValidValue(dice);
        int[] choice = pos.stream().mapToInt(i -> i).toArray();
        List<int[]> result = combination(choice, k);
        return result;

    }

    @Override
    public int[] selectDice(List<int[]> allValidDice) {
        int[] selectedDice = null;

        boolean isSelected = false;
        while (!isSelected) {
            System.out.println("Please choose the valid dice you want to keep(enter the number): ");
            for (int a = 0; a < allValidDice.size(); a++) {
                System.out.println("(" + (a + 1) + ")  " + Arrays.toString(allValidDice.get(a)));
            }
            String selectedInput = readUser();
            if (validateSelectNum(selectedInput, allValidDice.size())) {
                selectedDice = allValidDice.get(Integer.parseInt(selectedInput) - 1);
                isSelected = true;
            } else {
                System.out.println("Your selection is wrong, please enter again.");
            }
        }

        return selectedDice;
    }

    public static boolean selectedSizeValidation(int[] dice, int k){
        List<Integer> pos = allValidValue(dice);
        if (k > pos.size()) {
            return false;
        }
        return true;
    }

    // Add all above methods for straight card
    public static int calculatePointsStraightCard(int[] dice, int[] counter) {
        for (int i = 0; i < dice.length; i++) {
            counter[dice[i]] += 1;
        }
        for (int i = 1; i < counter.length; i++) {
            if (counter[i] != 1) {
                return 0;
            }
        }
        return 2000;
    }

    public static List<int[]> allValidDiceStraightCard(int[] dice, int k){
        // todo: find out all the combination of valid dices

        List<Integer> unique = new ArrayList<Integer>();
        int[] counter = count(dice);
        for (int i = 1; i < 7; i++) {
            if (counter[i] > 0) {
                unique.add(i);
            }
        }
        int[] choice = unique.stream().mapToInt(i -> i).toArray();
        List<int[]> result = combination(choice, k);
        return result;
    }
    public static List<int[]> combination(int[] e, int k){
        List<int[]> result = new ArrayList<>();
        int[] ignore = new int[e.length-k]; // --> [0][0]
        int[] combination = new int[k]; // --> [][][]

        // set initial ignored elements
        //(last k elements will be ignored)
        for(int w = 0; w < ignore.length; w++)
            ignore[w] = e.length - k +(w+1);

        int i = 0, r = 0, g = 0;

        boolean terminate = false;
        while(!terminate){

            // selecting N-k non-ignored elements
            while(i < e.length && r < k){

                if(i != ignore[g]){
                    combination[r] = i;
                    r++;
                }
                else{
                    if(g != ignore.length-1)
                        g++;
                }
                i++;
            }
            result.add(combination);
            i = 0; r = 0; g = 0;

            terminate = true;

            // shifting ignored indices
            for(int w = 0 ; w < ignore.length; w++){
                if(ignore[w] > w){
                    ignore[w]--;

                    if(w > 0)
                        ignore[w-1] = ignore[w]-1;
                    terminate = false;
                    break;
                }
            }
        }
        return result;
    }

}
