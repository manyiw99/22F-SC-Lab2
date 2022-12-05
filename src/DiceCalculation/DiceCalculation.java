package DiceCalculation;

import Tools.InputValidation;

import java.util.Optional;
import java.util.Random;

public class DiceCalculation {
    public InputValidation inputValidation;
    public DiceCalculation(InputValidation inputValidation){
        this.inputValidation = inputValidation;
    }
    public Optional<int[]> generateDice(int num) {
        System.out.println("The dice are: ");
        Random random = new Random();
        int[] dice = new int[num];
        for (int d = 0; d < dice.length; d++) {
            dice[d] = random.nextInt(6) + 1;
            System.out.print(dice[d] + "    ");
        }

        System.out.println();
        return Optional.ofNullable(dice);
    }
}
