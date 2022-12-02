
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

    @Test //Test formatSelectedInput method
    public void countTest(){
        List<String> formatInput = new ArrayList<>();
        formatInput.add("[1]");
        assertEquals(formatInput,diceTool.formatSelectedInput("[1]"));
        formatInput.add("[5]");
        assertEquals(formatInput,diceTool.formatSelectedInput("[1],[5]"));
        formatInput.add("[2,2,2]");
        assertEquals(formatInput,diceTool.formatSelectedInput("[1],[5],[2,2,2]"));
    }


}
