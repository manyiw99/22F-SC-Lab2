package Card;

import DiceCalculation.DiceCalculationOtherCards;
import Tools.InputValidation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

public class BonusCardTest {

    DiceCalculationOtherCards diceTool = mock(DiceCalculationOtherCards.class);
    InputValidation inputValidation = mock(InputValidation.class);

    @Test
    public void playGameTuttoTest() {
        BonusCard straightCard = new BonusCard(200, diceTool, inputValidation);

        int[] dice1 = {1, 2, 3, 4, 5, 6};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice1);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.isValidate(Mockito.any())).thenReturn(true);
        when(inputValidation.readUser()).thenReturn("S");

        Optional<Integer> gamePoints = straightCard.playGame();
        assertEquals(Optional.of(150), gamePoints);
    }

}
