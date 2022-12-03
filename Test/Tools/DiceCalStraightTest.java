package Tools;

import DiceCalculation.DiceCalculationStraight;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
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

public class DiceCalStraightTest {

    private DiceCalculationStraight diceTool = new DiceCalculationStraight();

    @Test //Test allValidDice
    public void allValidDiceTest(){
        List<Integer> expectedDiceCopy = new ArrayList<>();
        expectedDiceCopy.add(3);
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(2);
        expectedList.add(3);
        List<Integer> diceList = new ArrayList<>();
        diceList.add(1);
        diceList.add(3);
        diceList.add(5);
        assertEquals(expectedDiceCopy,diceTool.allValidDice(diceList,expectedList));
    }
    @Test //Test diceToList method
    public void diceToListTest(){
        int[] values = new int[]{1,2,3};
        List<Integer> diceList = new ArrayList<>();
        diceList.add(1);diceList.add(2);diceList.add(3);
        assertEquals(diceList,diceTool.diceToList(Optional.of(values)));
    }
}
