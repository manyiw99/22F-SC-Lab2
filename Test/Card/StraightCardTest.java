package Card;

import Tools.DiceCalculationStraight;
import Tools.InputValidation;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StraightCardTest {
    public List<Integer> diceToList(int[] dice) {
        List<Integer> diceList = new ArrayList<>();
        for (int i : dice) {
            diceList.add(i);
        }
        return diceList;
    }

//    private DiceCalculationStraight diceTool = mock(DiceCalculationStraight.class);

    @Test
    public void playGameTuttoTest() {
        DiceCalculationStraight diceTool = mock(DiceCalculationStraight.class);
        InputValidation inputValidation = mock(InputValidation.class);
        StraightCard straightCard = new StraightCard(Optional.of(Suit.STRAIGHT),inputValidation,diceTool);

        int[] dice1 = {1, 2, 3, 4, 5, 6};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice1);
        List<Integer> diceList = diceToList(dice1);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.diceToList(Mockito.any())).thenReturn(diceList);
        when(diceTool.allValidDice(Mockito.any(),Mockito.anyList())).thenReturn(diceList);
        when(diceTool.selectDice(Mockito.any(),Mockito.any())).thenReturn(dice1);
        when(diceTool.diceToList(Mockito.any())).thenReturn(diceList);
//        when(inputValidation.readUser()).thenReturn("1,2,3,4,5,6");
        when(diceTool.generateDice(0)).thenReturn(Optional.empty());
        when(inputValidation.readUser()).thenReturn("S");

        Optional<Integer> gamePoints = straightCard.playGame();
        assertEquals(Optional.of(2000), gamePoints);
    }
}
