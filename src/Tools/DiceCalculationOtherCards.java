package Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static Tools.InputValidation.readUser;
import static Tools.InputValidation.validateSelectNum;

public interface DiceCalculationOtherCards extends DiceCalculationAllCards{

    static List<int[]> allValidDice(int[] dice, int k) {
        List<Integer> pos = allValidValue(dice);
        int[] choice = pos.stream().mapToInt(i -> i).toArray();
        List<int[]> result = combination(choice, k);
        return result;
    }

//    static int[] selectDice(List<int[]> allValidDice) {
//        int[] selectedDice = null;
//
//        boolean isSelected = false;
//        while (!isSelected) {
//            System.out.println("Please choose the valid dice you want to keep( eg. [1],[2,2,2],[6] ): ");
//            for (int a = 0; a < allValidDice.size(); a++) {
//                System.out.println("(" + (a + 1) + ")  " + Arrays.toString(allValidDice.get(a)));
//            }
//            String selectedInput = readUser();
//            if (validateSelectNum(selectedInput, allValidDice.size())) {
//                selectedDice = allValidDice.get(Integer.parseInt(selectedInput) - 1);
//                isSelected = true;
//            } else {
//                System.out.println("Your selection is wrong, please enter again.");
//            }
//        }
//
//        return selectedDice;
//    }

    static List<int[]> selectDice(int[] dice) {
        List<int[]> selectedDice = new ArrayList<>();

        boolean isSelected = false;
        while (!isSelected) {
            System.out.println("Please enter the valid dice you want to keep( eg. [1],[2,2,2],[6] ): ");
            String selectedInput = readUser();
            if (validateSelectedDice(selectedInput, dice)) {
                String[] dices = selectedInput.split(",");
                for(int i=0; i<dices.length;i++){
                    int[] formatDice = new int[]{};
                    if(dices[i].length()==3){
                        formatDice[0]=Integer.parseInt(dices[i].substring(1,2));
                    }else if(dices[i].length()==7){
                        formatDice[0]=Integer.parseInt(dices[i].substring(1,2));
                        formatDice[1]=Integer.parseInt(dices[i].substring(1,2));
                        formatDice[2]=Integer.parseInt(dices[i].substring(1,2));
                    }else{
                        System.out.println("Your input is invalid dice, please enter again.");
                        break;
                    }
                    selectedDice.add(formatDice);
                    isSelected = true;
                }
                //selectedDice = allValidDice.get(Integer.parseInt(selectedInput) - 1);

            } else {
                System.out.println("Your input is invalid dice, please enter again.");
            }
        }

        return selectedDice;
    }

    /**
     * 1. not null, not empty
     * 2. isdigit()
     * 3. int[] format, split by comma
     * 4. overall length < dice.length()
     * 5. valid
     * @param input
     * @param dice
     * @return
     */
    private static boolean validateSelectedDice(String input, int[] dice){
        return true;
    }

    static int[] count(int[] dice) {
        var counter = new int[7];
        for (int i = 0; i < dice.length; i++) {
            counter[dice[i]] += 1;
        }
        return counter;
    }

    /**
     * Return all possible combinations of valid dice
     *
     * @param dice
     * @return
     */

    static List<Integer> allValidValue(int[] dice) {
        List<Integer> pos = new ArrayList<Integer>();
        int[] counter = DiceCalculationOtherCards.count(dice);
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

    static boolean selectedSizeValidation(int[] dice, int k){
        List<Integer> pos = allValidValue(dice);
        if (k > pos.size()) {
            return false;
        }
        return true;
    }

    static List<int[]> combination(int[] e, int k){
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

    /**
     * Count all the valid dice from the input and calculate the final points
     *
     * @param dice
     * @return
     */
    static int calculateSingleDices(int[] dice, int result) {
        for (int i = 0; i < dice.length; i++) {
            if (dice[i] == 1) {
                result += 100;
            } else {
                result += 50;
            }
            dice[i] = 0;
        }
        return result;
    }

    static int calculateThreeDices(int[] dice, int result) {
        var counter = count(dice);

        // find some value that occurs three times and replace it by 0
        int j = 0, found = 0, value = -1;
        for (; j < dice.length; j++) {
            if (counter[dice[j]] >= 3) {
                value = dice[j];
                dice[j] = 0;
                found = 1;
                break;
            }
        }

        if (value == -1) {
            System.out.println("no value occurs three times");
            return -1;
        }
        ;

        // replace two other occurrences of value in dice with 0
        for (; j < dice.length && found < 3; j++) {
            if (dice[j] == value) {
                dice[j] = 0;
                found++;
            }
        }
        return result + ((value == 1) ? 1000 : value * 100);
    }

    static boolean exitThreeDices(int[] dice) {
        int[] counter = count(dice);
        for (int i = 1; i < 8; i++) {
            if (counter[i] == 3) {
                return true;
            }
        }
        return false;
    }

    // Use of overload
    static int calculatePoints(List<int[]> dice){
        return 10;
    }

    static int calculatePoints(int[] dice) {
        int len = dice.length, point = 0;
        if (len == 1 || len == 2) {
            point = calculateSingleDices(dice, point);
        } else {
            int sum = Arrays.stream(dice).sum();
            while (sum != 0) {
                boolean existThree = exitThreeDices(dice);
                if (existThree) {
                    calculateThreeDices(dice, point);
                } else {
                    calculateSingleDices(dice, point);
                }
                sum = Arrays.stream(dice).sum();
            }
        }
        return point;


    }

    /**
     * Check if the dice valid
     *
     * @param dice
     * @return
     */
    static boolean isValidate(Optional<int[]> dice) {
        //return true as long as the dice is not null
        if (!dice.isPresent()) {
            return false;
        }
        return true;
    }


}
