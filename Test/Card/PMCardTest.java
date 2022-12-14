package Card;

import DiceCalculation.DiceCalculationOtherCards;
import Tools.InputValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PMCardTest {

    DiceCalculationOtherCards diceTool = mock(DiceCalculationOtherCards.class);
    InputValidation inputValidation = mock(InputValidation.class);
    //PMCard pmCard = new PMCard(Optional.of(Suit.PM), diceTool, inputValidation);
    PMCard pmCard = new PMCard(diceTool, inputValidation);

    @Test
    public void noValidDiceTest() {

        int[] dice = {1, 1, 1, 5, 5, 5};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.isValidate(Mockito.any())).thenReturn(false);

        Optional<Integer> gamePoints = pmCard.playGame();
        Assertions.assertEquals(Optional.empty(), gamePoints);
    }

//    @Test
//    public void noDiceTest(){
//        Optional<int[]> dice = Optional.empty();
//        when(diceTool.generateDice(6)).thenReturn(dice);
//        assertEquals(pmCard.playGame(), Optional.of(1000));
//
//    }

    @Test
    public void tuttoTest() {

        int[] dice = {1, 1, 1, 5, 5, 5};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice);
        List<int[]> diceList = List.of(dice);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.isValidate(Mockito.any())).thenReturn(true);
        when(diceTool.selectDice(Mockito.any())).thenReturn(diceList);
        when(inputValidation.readUser()).thenReturn("S");

        Optional<Integer> gamePoints = pmCard.playGame();
        Assertions.assertEquals(Optional.ofNullable(1000), gamePoints);
    }
}
