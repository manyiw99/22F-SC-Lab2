package Card;

import Tools.DiceCalculationOtherCards;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.util.Optional;

public class BonusCardTest {

    private DiceCalculationOtherCards diceTool = mock(DiceCalculationOtherCards.class);

    @Test
    void tuttoTest(){
        int[] dice1 = {1, 2, 3, 4, 5, 6};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice1);
        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
    }

}
