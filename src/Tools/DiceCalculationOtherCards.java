package Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static Tools.InputValidation.readUser;
import static Tools.InputValidation.validateSelectNum;

public interface DiceCalculationOtherCards extends DiceCalculationAllCards{
    static int[] count(int[] dice) {
        var counter = new int[7];
        for (int i = 0; i < dice.length; i++) {
            counter[dice[i]] += 1;
        }
        return counter;
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

    /**
     * Return all possible combinations of valid dice
     *
     * @param dice
     * @return
     */
    static List<int[]> allValidDice(int[] dice) {
        // todo: find out all the combination of valid dices
        // problem: five and one should be less than 3
        List<int[]> result = new ArrayList<>();
        int[] counter = DiceCalculationOtherCards.count(dice);


        return result;
    }


    static int[] selectDice(List<int[]> allValidDice) {
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
}
