package Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static Tools.InputValidation.*;

public class DiceCalculationStraight extends DiceCalculationAll {

    public boolean isValidate(Optional<int[]> dice, int i) {
        return false;
    }

    public static List<Integer> allValidDice(List<Integer> diceList, List<Integer> expectedList) {
        List<Integer> expectedDiceCopy = new ArrayList<>(expectedList);
        expectedDiceCopy.retainAll(diceList);
        return expectedDiceCopy;
    }

    public static int[] selectDice(List<Integer> allValidDice, List<Integer> expectedDice) {
        while (true) {
            System.out.println("Please choose the valid dice you want to keep(eg.3,4,6): ");
            for (Integer integer : allValidDice) {
                System.out.print(integer + "  ");
            }
            System.out.println();
            String selectedInput = readUser();

            //convert input "String" to List<Integer>
            String[] strings = selectedInput.split(",");
            int[] values = new int[strings.length];
            for (int i = 0; i < strings.length; i++) {
                values[i] = Integer.parseInt(strings[i]);
            }
            List<Integer> inputList = Arrays.stream(values).boxed().collect(Collectors.toList());

            if (validateSelectStraight(inputList, allValidDice)) {
//                expectedDice.removeAll(inputList);
                return values;
            } else {
                System.out.println("Your selection is wrong, please enter again.");
            }
        }
    }

    public static List<Integer> diceToList(Optional<int[]> dice) {
        int[] diceNum = dice.get();
        List<Integer> diceList = new ArrayList<>();
        for (int i : diceNum) {
            diceList.add(i);
        }
        return diceList;
    }
}
