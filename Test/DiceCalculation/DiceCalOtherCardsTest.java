
package DiceCalculation;

import DiceCalculation.DiceCalculationOtherCards;

import Tools.InputValidation;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiceCalOtherCardsTest {
    InputValidation inputValidation =  mock(InputValidation.class);
    private DiceCalculationOtherCards DiceCalO = mock(DiceCalculationOtherCards.class);
    private DiceCalculationOtherCards diceTool = new DiceCalculationOtherCards(mock(InputValidation.class));

    @Test //Test formatSelectedInput method
    public void formatSelectedInputTest(){
        List<String> formatInput = new ArrayList<>();
        formatInput.add("[1]");
        assertEquals(formatInput,diceTool.formatSelectedInput("[1]"));
        formatInput.add("[5]");
        assertEquals(formatInput,diceTool.formatSelectedInput("[1],[5]"));
        formatInput.add("[2,2,2]");
        assertEquals(formatInput,diceTool.formatSelectedInput("[1],[5],[2,2,2]"));
    }


    @Test
    public void validValidateSelectedDiceTest() {
        String input = "[1]";
        int[] dice = new int[]{1,2,2,2,5,6};

        assertTrue(diceTool.validateSelectedDice(input, dice));
        input = "[2,2,2]";
        assertTrue(diceTool.validateSelectedDice(input, dice));
        input = "";
        assertTrue(!diceTool.validateSelectedDice(input, dice));
        input = "[3,2,1]";
        assertTrue(!diceTool.validateSelectedDice(input, dice));
        input = "[6,5,1]";
        assertTrue(!diceTool.validateSelectedDice(input, dice));
        input = "[2,2]";
        assertTrue(!diceTool.validateSelectedDice(input, dice));
    }

    @Test
    public void invalidValidateSelectedDiceTest() {
        String input = "[1]";
        int[] dice = new int[]{2,2,3,4,5,6};

        assertFalse(diceTool.validateSelectedDice(input, dice));
    }

    @Test
    public void countTest(){
        int[] dice = new int[]{1,1,1,3,3,3};
        int[] counter = new int[]{0,3,0,3,0,0,0};
        assertArrayEquals(counter,diceTool.count(dice));
    }

    @Test
    public void calculateSingleDices() {
        int[] dice = new int[]{1};
        int point = 100;
        assertEquals(200, diceTool.calculateSingleDices(dice, point));
    }

    @Test
    public void calculateThreeDices() {
        int[] dice = new int[]{3,3,3};
        int point = 100;
        assertEquals(400, diceTool.calculateThreeDices(dice, point));
    }

    @Test
    public void validExistThreeDices() {
        int[] dice = new int[]{3,3,3};
        assertTrue(diceTool.exitThreeDices(dice));
    }

    @Test
    public void invalidExistThreeDices() {
        int[] dice = new int[]{2,3,3};
        assertFalse(diceTool.exitThreeDices(dice));
    }

    @Test //Test selectDice method
    public void selectDiceTest(){
        List<int[]> selectedDice = new ArrayList<>();
        int[] i=new int[]{1};
        selectedDice.add(i);
        int[] dice = new int[]{1,2,3,4};
        when(inputValidation.readUser()).thenReturn("[1]");
        when(DiceCalO.validateSelectedDice("[1]",dice)).thenReturn(true);
        List<int[]> answer;
        answer = DiceCalO.selectDice(dice);
        System.out.println(answer);


//
//        when(inputValidation.readUser()).thenReturn("[1]");
//        List<int[]> result = diceTool.selectDice(new int[]{1,2,3,4,5,6});
//        assertEquals(selectedDice, result);
    }

    @Test //Test allValidValue method
    public void allValidValueTest(){
        List<Integer> pos = new ArrayList<Integer>();
        pos.add(1);
        pos.add(5);
        assertEquals(pos,diceTool.allValidValue(new int[]{1,2,3,4,5,6}));
        pos.add(2);pos.add(2);pos.add(2);pos.remove(1);pos.add(5);
        assertEquals(pos,diceTool.allValidValue(new int[]{1,2,2,2,5,6}));
    }

    @Test //Test calculatePoints method
    public void calculatePointsTest(){
        int point;
        point = 150;
        assertEquals(point,diceTool.calculatePoints(new int[]{1,2,3,4,5,6}));
        point = 350;
        assertEquals(point,diceTool.calculatePoints(new int[]{1,2,2,2,5,6}));
        point = 1000;
        assertEquals(point,diceTool.calculatePoints(new int[]{1,1,1,2,3,6}));
    }

    @Test //Test isValidate method
    public void isValidateTest(){
        boolean valid = true;
        assertEquals(valid, diceTool.isValidate(Optional.of(new int[]{1, 2, 2, 2, 5, 6})));
        valid = false;
        assertEquals(valid, diceTool.isValidate(Optional.of(new int[]{2, 2, 3, 3, 4, 6})));
    }

    @Test
    public void validCalculatePoints() {
        List<int[]> input = new ArrayList<int[]>();
        input.add(new int[]{3,3,3});
        input.add(new int[]{1});

        assertEquals(400, diceTool.calculatePoints(input));
    }


}
