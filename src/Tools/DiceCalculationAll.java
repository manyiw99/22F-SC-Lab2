package Tools;

import java.util.*;

import static Tools.InputValidation.readUser;
import static Tools.InputValidation.validateSelectNum;

public abstract class DiceCalculationAll {
    /**
     * Random generate dice
     *
     * @param num
     * @return
     */
    public static Optional<int[]> generateDice(int num) {
        System.out.println("The dice are: ");
        Random random = new Random();
        int[] dice = new int[num];
        for (int d = 0; d < dice.length; d++) {
            dice[d] = random.nextInt(6) + 1;
            System.out.print("(" + (d + 1) + ") " + dice[d] + "    ");
        }

        System.out.println();
        return Optional.ofNullable(dice);
    }

    /**
     * Check if the dice valid
     *
     * @param dice
     * @return
     */
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
    public List<int[]> allValidDice(int[] dice) {
        // todo: find out all the combination of valid dices
        // problem: five and one should be less than 3
        List<int[]> result = new ArrayList<>();
        int[] counter = count(dice);


        return result;
    }

    public static int[] count(int[] dice) {
        var counter = new int[7];
        for (int i = 0; i < dice.length; i++) {
            counter[dice[i]] += 1;
        }
        return counter;
    }

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
}
