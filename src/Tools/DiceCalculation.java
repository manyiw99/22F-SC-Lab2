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
    @Override
    public List<int[]> allValidDice(int[] dice) {
        // todo: find out all the combination of valid dices
        // problem: five and one should be less than 3
        List<int[]> result = new ArrayList<>();
        int[] counter = DiceCalculationOtherCards.count(dice);


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

}
