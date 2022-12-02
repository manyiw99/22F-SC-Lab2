package Card;

import Tools.DiceCalculationOtherCards;
import Tools.InputValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FireworkCardTest {
    DiceCalculationOtherCards diceTool = mock(DiceCalculationOtherCards.class);
    InputValidation inputValidation = mock(InputValidation.class);
    FireworksCard fireworksCard = new FireworksCard(Optional.of(Suit.FIREWORKS), diceTool, inputValidation);

    @Test
    public void noValidDiceAtFirstTest() {

        int[] dice = {1, 1, 1, 5, 5, 5};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.isValidate(Mockito.any())).thenReturn(false);

        Optional<Integer> gamePoints = fireworksCard.playGame();
        Assertions.assertEquals(Optional.empty(), gamePoints);
    }

    @Test
    public void noValidDice() {

        int[] dice = {1, 1, 1, 5, 5, 5};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice);
        List<Integer> diceList = new ArrayList<>();

        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.isValidate(Mockito.any())).thenReturn(true);
        when(diceTool.allValidValue(Mockito.any())).thenReturn(diceList);

        Optional<Integer> gamePoints = fireworksCard.playGame();
        Assertions.assertEquals(Optional.ofNullable(0), gamePoints);
    }
}
