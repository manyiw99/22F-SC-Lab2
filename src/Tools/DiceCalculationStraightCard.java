package Tools;

import java.util.List;
import java.util.Optional;

public interface DiceCalculationStraightCard extends DiceCalculationAllCards{
    List<Integer> allValidDice(List<Integer> diceList, List<Integer> expectedList);
    int[] selectDice(List<Integer> allValidDice, List<Integer> expectedDice);
    List<Integer> diceToList(Optional<int[]> dice);
}
