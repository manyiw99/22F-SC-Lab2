
package Tools;

import DiceCalculation.DiceCalculationOtherCards;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiceCalOtherCardsTest {
    private DiceCalculationOtherCards diceTool = new DiceCalculationOtherCards();

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

    @Test //Test selectDice method
    public void selectDiceTest(){
        final InputStream oldIn = System.in;
        List<int[]> selectedDice = new ArrayList<>();
        int[] i=new int[]{1};
        selectedDice.add(i);
        System.out.println(selectedDice);
        String s = "[1]";
        ByteArrayInputStream in=new ByteArrayInputStream(s.getBytes());
        System.setIn(in);
        System.out.println(diceTool.selectDice(new int[]{1,2,3,4,5,6}));
        //assertEquals(selectedDice,diceTool.selectDice(new int[]{1,2,3,4,5,6}));
        System.setIn(oldIn);
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


}
