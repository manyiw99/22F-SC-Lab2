package DiceCalculation;

import Tools.InputValidation;

import java.util.*;
import java.util.stream.Collectors;

public class DiceCalculationStraight extends DiceCalculation {
    public DiceCalculationStraight(InputValidation inputValidation) {
        super(inputValidation);
    }

//    public InputValidation inputValidation = new InputValidation();

    public List<Integer> allValidDice(List<Integer> diceList, List<Integer> expectedList) {
        List<Integer> expectedDiceCopy = new ArrayList<>(expectedList);
        expectedDiceCopy.retainAll(diceList);
        return expectedDiceCopy;
    }

    public int[] selectDice(List<Integer> allValidDice, List<Integer> expectedDice) {

        while (true) {
            System.out.println("Please choose the valid dice you want to keep(eg.3,4,6): ");
            for (Integer integer : allValidDice) {
                System.out.print(integer + "  ");
            }
            System.out.println();
            String selectedInput = inputValidation.readUser();

            int[] values;

            if (selectedInput != null) {
                //convert input "String" to List<Integer>
                String[] strings = selectedInput.split(",");
                values = new int[strings.length];
                for (int i = 0; i < strings.length; i++) {
                    if (strings[i].equals("1") || strings[i].equals("2") || strings[i].equals("3") || strings[i].equals("4") || strings[i].equals("5") || strings[i].equals("6")) {
                        values[i] = Integer.parseInt(strings[i]);
                    } else {
                        values[i] = 7;
                    }

                }
            } else {
                values = new int[1];
                values[0] = 7;
            }
            List<Integer> inputList = Arrays.stream(values).boxed().collect(Collectors.toList());

            if (inputValidation.validateSelectStraight(inputList, allValidDice)) {
                return values;
            } else {
                System.out.println("Your selection is wrong, please enter again.");
            }
        }
    }

    public List<Integer> diceToList(Optional<int[]> dice) {
        int[] diceNum = dice.get();
        List<Integer> diceList = new ArrayList<>();
        for (int i : diceNum) {
            diceList.add(i);
        }
        return diceList;
    }

}
