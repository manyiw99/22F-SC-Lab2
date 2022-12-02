package Card;

import Tools.DiceCalculationOtherCards;
import Tools.InputValidation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PMCardTest {

    DiceCalculationOtherCards diceTool = mock(DiceCalculationOtherCards.class);
    InputValidation inputValidation = mock(InputValidation.class);

    @Test
    public void playGameTuttoTest() {
        PMCard pmCard = new PMCard(Optional.of(Suit.BONUS), diceTool, inputValidation);

        int[] dice1 = {1, 2, 3, 4, 5, 6};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice1);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.isValidate(Mockito.any())).thenReturn(false);

        Optional<Integer> gamePoints = pmCard.playGame();
        assertEquals(Optional.empty(), gamePoints);
    }
}
