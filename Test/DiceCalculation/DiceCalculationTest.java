package DiceCalculation;
import Tools.InputValidation;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiceCalculationTest {
    private DiceCalculationOtherCards diceTool = new DiceCalculationOtherCards(mock(InputValidation.class));

    @Test
    public void generateDiceTest(){
        Optional<int[]> dice = diceTool.generateDice(6);
        assertEquals(false,dice.isEmpty());
    }
}
