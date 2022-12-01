package CardTest;

import Card.StraightCard;
import Card.Suit;
import Tools.DiceCalculationStraight;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.Optional;

public class StraightCardTest {

    private DiceCalculationStraight diceTool = mock(DiceCalculationStraight.class);
    StraightCard straightCard = new StraightCard(Optional.of(Suit.STRAIGHT));

    @Test
    public void playGameTestTutto() {

        int[] dice1 = {1, 2, 3, 4, 5, 6};
        Optional<int[]> tuttoDice = Optional.ofNullable(dice1);
        when(diceTool.generateDice(6)).thenReturn(tuttoDice);
        when(diceTool.selectDice(Mockito.any(),Mockito.any())).thenReturn(dice1);


        assertEquals(Optional.of(2000), straightCard.playGame());
    }
}
