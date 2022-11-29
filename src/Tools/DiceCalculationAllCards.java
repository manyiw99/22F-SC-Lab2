package Tools;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public interface DiceCalculationAllCards {
    /**
     * Random generate dice
     *
     * @param num
     * @return
     */
    static Optional<int[]> generateDice(int num) {
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
