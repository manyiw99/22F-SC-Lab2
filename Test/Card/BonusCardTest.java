package Card;

import DiceCalculation.DiceCalculationOtherCards;
import Tools.InputValidation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

public class BonusCardTest {

    DiceCalculationOtherCards diceTool = mock(DiceCalculationOtherCards.class);
    InputValidation inputValidation = mock(InputValidation.class);
    BonusCard bonusCard = new BonusCard(200, diceTool, inputValidation);

    @Test
    public void stopTest() {
        int[] dice1 = {1, 1, 1, 5, 5, 5};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice1);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.isValidate(Mockito.any())).thenReturn(true);
        when(inputValidation.readUser()).thenReturn("S");
        when(diceTool.calculatePoints(dice1)).thenReturn(100);

        Optional<Integer> gamePoints = bonusCard.playGame();
        assertEquals(Optional.of(100), gamePoints);
    }

    @Test
    public void noValidDiceTest(){
        int[] dice1 = {1, 1, 1, 5, 5, 5};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice1);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.isValidate(tuttoDice)).thenReturn(false);

        assertEquals(Optional.empty(),bonusCard.playGame());
    }

    @Test
    public void tuttoTest(){
        int[] dice1 = {1, 1, 1, 5, 5, 5};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice1);
        List<int[]> diceInt = List.of(dice1);

        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.isValidate(Mockito.any())).thenReturn(true).thenReturn(false);
        when(inputValidation.readUser()).thenReturn("C").thenReturn("L").thenReturn("S");
        when(diceTool.selectDice(Mockito.any())).thenReturn(diceInt);
        when(diceTool.calculatePoints(diceInt)).thenReturn(100);

        Optional<Integer> gamePoints = bonusCard.playGame();
        assertEquals(Optional.of(100), gamePoints);//actual is 300
    }
}
