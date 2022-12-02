package Card;
import DiceCalculation.DiceCalculationOtherCards;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

public class DiceCalOtherCardsTest {
    private DiceCalculationOtherCards diceTool = new DiceCalculationOtherCards();

    @Test //Test formatSelectedInput method
    public void formatSelectedInputTest(){
        List<String> formatInput = new ArrayList<>();
        formatInput.add("[1]");
        formatInput.add("[5]");
        String input = new String("[1],[5]");
        assertEquals(formatInput,diceTool.formatSelectedInput(input));
    }

}
