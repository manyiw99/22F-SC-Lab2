package DiceCalculation;

import DiceCalculation.DiceCalculationStraight;
import Tools.InputValidation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import DiceCalculation.DiceCalculationOtherCards;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiceCalStraightTest {

    InputValidation inputValidation = mock(InputValidation.class);
    private DiceCalculationStraight diceTool = new DiceCalculationStraight(inputValidation);

    @Test //Test allValidDice
    public void allValidDiceTest() {
        List<Integer> expectedDiceCopy = new ArrayList<>();
        expectedDiceCopy.add(3);
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(2);
        expectedList.add(3);
        List<Integer> diceList = new ArrayList<>();
        diceList.add(1);
        diceList.add(3);
        diceList.add(5);
        assertEquals(expectedDiceCopy, diceTool.allValidDice(diceList, expectedList));
    }

    @Test //Test diceToList method
    public void diceToListTest() {
        int[] values = new int[]{1, 2, 3};
        List<Integer> diceList = new ArrayList<>();
        diceList.add(1);
        diceList.add(2);
        diceList.add(3);
        assertEquals(diceList, diceTool.diceToList(Optional.of(values)));
    }

    @Test
    public void selectDiceNullTest() {
        int[] values = new int[]{1, 2, 3};
        List<Integer> diceList = new ArrayList<>();
        diceList.add(1);
        diceList.add(2);
        diceList.add(3);
        int[] expectedValues = new int[1];
        expectedValues[0] = 7;

        when(inputValidation.readUser()).thenReturn(null);
        when(inputValidation.validateSelectStraight(Mockito.anyList(), Mockito.any())).thenReturn(true);

        assertArrayEquals(diceTool.selectDice(diceList, diceList), expectedValues);
    }

    @Test
    public void validInputTest() {
        String input = "1,2,3,4,5,6,7";
        int[] values = new int[]{1, 2, 3, 4, 5, 6, 7};
        List<Integer> diceList = new ArrayList<>();
        diceList.add(1);
        diceList.add(2);
        diceList.add(3);

        when(inputValidation.readUser()).thenReturn(input);
        when(inputValidation.validateSelectStraight(Mockito.anyList(), Mockito.any())).thenReturn(false).thenReturn(true);

        assertArrayEquals(values, diceTool.selectDice(diceList, diceList));
    }
}
