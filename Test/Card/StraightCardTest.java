package Card;

import DiceCalculation.DiceCalculationStraight;
import Tools.InputValidation;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StraightCardTest {

    DiceCalculationStraight diceTool = mock(DiceCalculationStraight.class);
    InputValidation inputValidation = mock(InputValidation.class);
    StraightCard straightCard = new StraightCard(Optional.of(Suit.STRAIGHT), inputValidation, diceTool);

    public List<Integer> diceToList(int[] dice) {
        List<Integer> diceList = new ArrayList<>();
        for (int i : dice) {
            diceList.add(i);
        }
        return diceList;
    }

//    private DiceCalculationStraight diceTool = mock(DiceCalculationStraight.class);

    @Test
    public void tuttoThenStopTest() {

        int[] dice = {1, 2, 3, 4, 5, 6};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice);
        List<Integer> diceList = diceToList(dice);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.diceToList(Mockito.any())).thenReturn(diceList);
        when(diceTool.allValidDice(Mockito.any(), Mockito.anyList())).thenReturn(diceList);
        when(diceTool.selectDice(Mockito.any(), Mockito.any())).thenReturn(dice);
        when(diceTool.diceToList(Mockito.any())).thenReturn(diceList);
//        when(inputValidation.readUser()).thenReturn("1,2,3,4,5,6");
        when(diceTool.generateDice(0)).thenReturn(Optional.empty());
        when(inputValidation.readUser()).thenReturn("S");

        Optional<Integer> gamePoints = straightCard.playGame();
        assertEquals(Optional.of(2000), gamePoints);
    }

    @Test
    public void tuttoThenContinueTest() {

        int[] dice = {1, 2, 3, 4, 5, 6};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice);
        List<Integer> diceList = diceToList(dice);

        int[] dice1 = {7, 2, 3, 4, 5, 6};
        Optional<int[]> tuttoDice1 = Optional.ofNullable(dice1);
        List<Integer> diceList1 = diceToList(dice1);

        when(diceTool.generateDice(Mockito.anyInt())).thenReturn(tuttoDice).thenReturn(Optional.empty()).thenReturn(Optional.empty());
        when(diceTool.diceToList(Mockito.any())).thenReturn(diceList);
        when(diceTool.allValidDice(Mockito.any(), Mockito.anyList())).thenReturn(diceList);
        when(diceTool.selectDice(Mockito.any(), Mockito.any())).thenReturn(dice);
        when(inputValidation.readUser()).thenReturn("H").thenReturn("C");

        Optional<Integer> gamePoints = straightCard.playGame();
        assertEquals(Optional.ofNullable(2000), gamePoints);
    }

    @Test
    public void noValidDiceTest() {

        int[] dice = {7};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice);
        List<Integer> diceList = diceToList(dice);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.diceToList(Mockito.any())).thenReturn(diceList);

        assertEquals(Optional.empty(), straightCard.playGame());
    }

    @Test
    public void selectTwiceThenTuttoTest() {
        int[] dice = {1, 2, 3, 4, 5, 6};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice);
        List<Integer> diceList = diceToList(dice);

        int[] dice1 = {4, 5, 6};
        int[] dice2 = {1, 2, 3};
        Optional<int[]> tuttoDice1 = Optional.ofNullable(dice2);
        List<Integer> diceList1 = diceToList(dice1);
        List<Integer> diceList2 = diceToList(dice2);

        when(diceTool.generateDice(Mockito.anyInt())).thenReturn(tuttoDice).thenReturn(tuttoDice1).thenReturn(Optional.empty());
        when(diceTool.diceToList(Mockito.any())).thenReturn(diceList).thenReturn(diceList1).thenReturn(diceList2);
        when(diceTool.allValidDice(Mockito.any(),Mockito.anyList())).thenReturn(diceList).thenReturn(diceList2);
        //select 4,5,6 first, then 1,2,3
        when(diceTool.selectDice(Mockito.any(),Mockito.any())).thenReturn(dice1).thenReturn(dice2);
        when(inputValidation.readUser()).thenReturn("S");

        assertEquals(Optional.ofNullable(2000), straightCard.playGame());
    }

}
