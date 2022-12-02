package Card;

import DiceCalculation.DiceCalculationOtherCards;
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
    FireworksCard fireworksCard = new FireworksCard(diceTool, inputValidation);

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
    public void noValidDiceTest() {

        int[] dice = {1, 1, 1, 5, 5, 5};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice);
        List<Integer> diceList = new ArrayList<>();

        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.isValidate(Mockito.any())).thenReturn(true);
        when(diceTool.allValidValue(Mockito.any())).thenReturn(diceList);

        Optional<Integer> gamePoints = fireworksCard.playGame();
        Assertions.assertEquals(Optional.ofNullable(0), gamePoints);
    }

    @Test
    public void noDiceTest() {
        when(diceTool.generateDice(6)).thenReturn(Optional.empty());
        when(diceTool.isValidate(Mockito.any())).thenReturn(true);

        Assertions.assertEquals(Optional.ofNullable(0), fireworksCard.playGame());
    }

    @Test
    public void tuttoThenNoValidDiceTest() {

        int[] dice = {1, 1, 1, 5, 5, 5};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice);
        List<Integer> diceList = List.of(1, 1, 1, 5, 5, 5);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice).thenReturn(Optional.empty());
        when(diceTool.isValidate(Mockito.any())).thenReturn(true).thenReturn(false);
        when(diceTool.allValidValue(Mockito.any())).thenReturn(diceList);
        when(diceTool.calculatePoints(dice)).thenReturn(1000);

        Optional<Integer> gamePoints = fireworksCard.playGame();
        Assertions.assertEquals(Optional.ofNullable(1000), gamePoints);
    }

    @Test
    public void tuttoThenValidDiceTest() {

        int[] dice = {1, 1, 1, 5, 5, 5};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice);
        List<Integer> diceList = List.of(1, 1, 1, 5, 5, 5);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice).thenReturn(Optional.empty());
        when(diceTool.isValidate(Mockito.any())).thenReturn(true).thenReturn(true);
        when(diceTool.allValidValue(Mockito.any())).thenReturn(diceList);
        when(diceTool.calculatePoints(dice)).thenReturn(1000);

        Optional<Integer> gamePoints = fireworksCard.playGame();
        Assertions.assertEquals(Optional.ofNullable(1000), gamePoints);
    }

    @Test
    public void noTuttoAtFirstTest() {

        int[] dice = {1, 1, 1, 5, 5, 5};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice);
        List<Integer> diceList = List.of(1, 1, 1, 5, 5);
        List<Integer> diceList2 = new ArrayList<>();
        int[] dice1 = {1};
        Optional<int[]> dice2 = Optional.ofNullable(dice1);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice).thenReturn(dice2);
        when(diceTool.isValidate(Mockito.any())).thenReturn(true);
        when(diceTool.allValidValue(Mockito.any())).thenReturn(diceList).thenReturn(diceList2);
        when(diceTool.calculatePoints(dice)).thenReturn(1000);

        Optional<Integer> gamePoints = fireworksCard.playGame();
        Assertions.assertEquals(Optional.ofNullable(0), gamePoints);
    }
}
