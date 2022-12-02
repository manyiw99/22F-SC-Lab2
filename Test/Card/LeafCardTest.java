package Card;

import DiceCalculation.DiceCalculationOtherCards;
import Tools.InputValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LeafCardTest {
    DiceCalculationOtherCards diceTool = mock(DiceCalculationOtherCards.class);
    InputValidation inputValidation = mock(InputValidation.class);
    LeafCard leafCard = new LeafCard(diceTool, inputValidation);

    @Test
    public void noValidDiceAtFirstTest(){
        when(diceTool.generateDice(Mockito.anyInt())).thenReturn(Optional.empty());
        when(diceTool.isValidate(Mockito.any())).thenReturn(false);

        Assertions.assertEquals(Optional.empty(), leafCard.playGame());
    }

    @Test
    public void noValidDiceTest(){
        when(diceTool.generateDice(Mockito.anyInt())).thenReturn(Optional.empty());
        when(diceTool.isValidate(Mockito.any())).thenReturn(true);

        Assertions.assertEquals(Optional.empty(), leafCard.playGame());
    }

    @Test
    public void tuttoTwiceTest(){
        int[] dice = {1, 1, 1, 5, 5, 5};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice);
        List<int[]> diceInt = List.of(dice);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.isValidate(Mockito.any())).thenReturn(true);
        when(diceTool.selectDice(Mockito.any())).thenReturn(diceInt);

        Optional<Integer> gamePoints = leafCard.playGame();
        Assertions.assertEquals(Optional.ofNullable(99999), gamePoints);
    }

    @Test
    public void selectTwiceThenTuttoTest(){
        int[] dice = {1, 1, 1, 5, 5, 5};
        int[] dice1 = {1, 1, 1, 5, 5};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice);
        List<int[]> diceInt = List.of(dice1);
        int[] dice2 = {1};
        Optional<int[]> dice3 = Optional.ofNullable(dice2);

        when(diceTool.generateDice(Mockito.anyInt())).thenReturn(tuttoDice).thenReturn(dice3);
        when(diceTool.isValidate(Mockito.any())).thenReturn(true).thenReturn(false);
        when(diceTool.selectDice(Mockito.any())).thenReturn(diceInt);

        Optional<Integer> gamePoints = leafCard.playGame();
        Assertions.assertEquals(Optional.empty(), gamePoints);
    }

    @Test
    public void tuttoThenNoValideTest(){
        int[] dice = {1, 1, 1, 5, 5, 5};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice);
        List<int[]> diceInt = List.of(dice);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.isValidate(Mockito.any())).thenReturn(true).thenReturn(false);
        when(diceTool.selectDice(Mockito.any())).thenReturn(diceInt);

        Optional<Integer> gamePoints = leafCard.playGame();
        Assertions.assertEquals(Optional.empty(), gamePoints);
    }
}
