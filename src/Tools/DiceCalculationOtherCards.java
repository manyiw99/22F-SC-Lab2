package Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static Tools.InputValidation.readUser;

public class DiceCalculationOtherCards implements DiceCalculationAllCards {

//    static List<int[]> allValidDice(int[] dice, int k) {
//        List<Integer> pos = allValidValue(dice);
//        int[] choice = pos.stream().mapToInt(i -> i).toArray();
//        List<int[]> result = combination(choice, k);
//        return result;
//    }

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

    public static List<int[]> selectDice(int[] dice) {
        List<int[]> selectedDice = new ArrayList<>();

        boolean isSelected = false;
        while (!isSelected) {
            System.out.println("Please enter the valid dice you want to keep( eg. [1],[2,2,2],[6] ): ");
            String selectedInput = readUser();
            if (validateSelectedDice(selectedInput, dice)) {
                String[] dices = null;
                if(selectedInput.contains("],[")) {
                    List<String> formatInput = new ArrayList<>();
                    int length=0;
                    while(length<selectedInput.length()){
                        //System.out.println("length===="+length);
                        int index = selectedInput.indexOf("],[",length);

                        if(index!=-1){
                            String sub = selectedInput.substring(length,index+1);
                            formatInput.add(sub);
                            length=index+2;
                        }else{
                            String sub = selectedInput.substring(length);
                            formatInput.add(sub);
                            length=selectedInput.length();
                        }

                    }
                    dices=formatInput.toArray(new String[formatInput.size()]);

                }else{
                    dices = new String[]{selectedInput};
                }

                //System.out.println(Arrays.toString(dices));
                for (int i = 0; i < dices.length; i++) {
                    //System.out.println("输入逗号分割 - "+ dices[i].length()+ " "+ dice[i]);

                    if (dices[i].length() == 3) {
                        int[] formatDice = new int[1];
                        formatDice[0] = Integer.parseInt(dices[i].substring(1, 2));
                        selectedDice.add(formatDice);
                    } else if (dices[i].length() == 7) {
                        int[] formatDice = new int[3];
                        formatDice[0] = Integer.parseInt(dices[i].substring(1, 2));
                        formatDice[1] = Integer.parseInt(dices[i].substring(1, 2));
                        formatDice[2] = Integer.parseInt(dices[i].substring(1, 2));
                        selectedDice.add(formatDice);
                    } else {
                        System.out.println("Your input is invalid dice, please enter again.");
                        break;
                    }

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
     *
     * @param input
     * @param dice
     * @return
     */
    private static boolean validateSelectedDice(String input, int[] dice) {
        // System.out.println("test(manyi) - "+input);   // OK
        int[] counter = count(dice);
        List<String> separatedInput = List.of(input.split(","));
        for (String i : separatedInput) {
            String value = i.substring(1,i.length() - 1);
            if (value.length() == 1 && value.charAt(0) != '1' && value.charAt(0) == '5') {
                return false;
            } else if (value.length() == 5) {
                char target = value.charAt(0);
                for (int j = 2; j < value.length(); j = j + 2) {
                    if (value.charAt(j) != target) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
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

    public static List<Integer> allValidValue(int[] dice) {
        List<Integer> pos = new ArrayList<Integer>();
        int[] counter = DiceCalculationOtherCards.count(dice);
        for (int i = 1; i < 7; i++) {
            if (counter[i] >= 3 || ((i == 1 || i == 5) && counter[i] > 0)) {
                int size = counter[i];
                for (int j = 0; j < size; j++) {
                    pos.add(i);
                }
            }
        }
        return pos;
    }


//    static List<int[]> combination(int[] e, int k){
//        List<int[]> result = new ArrayList<>();
//        int[] ignore = new int[e.length-k]; // --> [0][0]
//        int[] combination = new int[k]; // --> [][][]
//
//        // set initial ignored elements
//        //(last k elements will be ignored)
//        for(int w = 0; w < ignore.length; w++)
//            ignore[w] = e.length - k +(w+1);
//
//        int i = 0, r = 0, g = 0;
//
//        boolean terminate = false;
//        while(!terminate){
//
//            // selecting N-k non-ignored elements
//            while(i < e.length && r < k){
//
//                if(i != ignore[g]){
//                    combination[r] = i;
//                    r++;
//                }
//                else{
//                    if(g != ignore.length-1)
//                        g++;
//                }
//                i++;
//            }
//            result.add(combination);
//            i = 0; r = 0; g = 0;
//
//            terminate = true;
//
//            // shifting ignored indices
//            for(int w = 0 ; w < ignore.length; w++){
//                if(ignore[w] > w){
//                    ignore[w]--;
//
//                    if(w > 0)
//                        ignore[w-1] = ignore[w]-1;
//                    terminate = false;
//                    break;
//                }
//            }
//        }
//        return result;
//    }

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
//            dice[i] = 0;
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
        for (int i = 1; i < 7; i++) {
            if (counter[i] == 3) {
                return true;
            }
        }
        return false;
    }

    // Use of overload
    public static int calculatePoints(List<int[]> dice){
        int point = 0;
        for (int[] i : dice) {
            if (i.length == 1) {
                point = calculateSingleDices(i, point);
            } else {
                point = calculateThreeDices(i, point);
            }
        }
        return point;
    }

    public static int calculatePoints(int[] dice) {
        int point = 0;
        boolean existThree = exitThreeDices(dice);
        while (existThree) {
            point += calculateThreeDices(dice, point);
            existThree = exitThreeDices(dice);
        }
        for (int i : dice) {
            if (i == 1 ) {
                point += 100;
            } else if (i == 5) {
                point += 50;
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
    public static boolean isValidate(Optional<int[]> dice) {
        //return true as long as the dice is not null
        int[] result = new int[6];
        if (dice.isPresent()) {
            result = dice.get();
        }
        int[] counter = count(result);
        for (int i = 1; i < 7; i++) {
            if (i == 1 || i == 5) {
                if (counter[i] > 0) {
                    return true;
                }
            } else {
                if (counter[i] >= 3) {
                    return true;
                }
            }
        }
        return false;
    }


}
